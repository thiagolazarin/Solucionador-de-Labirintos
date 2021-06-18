package bd.daos;

import java.sql.*;
import bd.*;
import bd.core.*;
import bd.dbo.*;
import comunicado.TesteLab;

public class Labirintos
{
	

    public static boolean cadastrado (int id, String nome) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM labirintos " +
                  "WHERE id = ? and nome = ?";

            BDOracle.COMANDO.prepareStatement (sql);

            BDOracle.COMANDO.setInt (1, id);
            BDOracle.COMANDO.setString (2, nome);
            
            MeuResultSet resultado = (MeuResultSet)BDOracle.COMANDO.executeQuery ();

            retorno = resultado.first(); 

        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar labirinto");
        }

        return retorno;
    }

    
    public static void incluir (TesteLab labirinto) throws Exception
    {
        if (labirinto==null)
            throw new Exception ("labirinto nao fornecido");

        try
        {
            String sql;

            sql = "INSERT INTO labirintos " +
                  "(ID, nome, data_criacao, data_ultima_modificacao, labirinto) " +
                  "VALUES " +
                  "(?,?,?,?,?)";

            BDOracle.COMANDO.prepareStatement (sql);
            
            System.out.println("labirinto salvo!");
            BDOracle.COMANDO.setInt (1, labirinto.getId());
            BDOracle.COMANDO.setString (2, labirinto.getNome());
            BDOracle.COMANDO.setString (3, labirinto.getDataCriacao());
            BDOracle.COMANDO.setString (4, labirinto.getDataUltimaCriacao());
            BDOracle.COMANDO.setString (5, labirinto.getConteudo());
            
            BDOracle.COMANDO.executeUpdate ();
            BDOracle.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
        	erro.printStackTrace();
        	BDOracle.COMANDO.rollback();
            throw new Exception ("nao foi possivel salvar labirinto");
        }
    }
    

    public static void excluir (int id, String nome) throws Exception
    {
        if (!cadastrado (id, nome))
            throw new Exception ("Labirinto nao existe");

        try
        {
            String sql;
            
            sql = "DELETE FROM labirintos " +
                  "WHERE ID=? and nome=?";

            BDOracle.COMANDO.prepareStatement (sql);

            BDOracle.COMANDO.setInt (1, id);
            BDOracle.COMANDO.setString (2, nome);

            BDOracle.COMANDO.executeUpdate ();
            BDOracle.COMANDO.commit        ();        
        }
        catch (SQLException erro)
        {
        	BDOracle.COMANDO.rollback();
            throw new Exception ("Erro ao excluir labirinto");
        }
    }
    

    public static void alterar (TesteLab labirinto) throws Exception
    {
        if (labirinto==null)
            throw new Exception ("Labirinto nao foi encontrado ");

        if (!cadastrado (labirinto.getId(), labirinto.getNome()))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "UPDATE Labirintos " +
                  "SET data_ultima_modificacao=?, labirinto=?" +
                  "WHERE =? and nome=?";

            BDOracle.COMANDO.prepareStatement (sql);
           
            BDOracle.COMANDO.setString (1, labirinto.getDataUltimaCriacao());
            BDOracle.COMANDO.setString (2, labirinto.getConteudo());
            BDOracle.COMANDO.setInt (3, labirinto.getId());
            BDOracle.COMANDO.setString (4, labirinto.getNome());

            BDOracle.COMANDO.executeUpdate ();
            BDOracle.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
        	BDOracle.COMANDO.rollback();
            throw new Exception ("Nao foi possivel atualizar os labirintos");
        }
    }
    

    public static TesteLab getLabirinto (int id, String nome) throws Exception
    {
        TesteLab labirinto = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM Labirintos " +
                  "WHERE id=? and nome=? ";

            BDOracle.COMANDO.prepareStatement (sql);

            BDOracle.COMANDO.setInt (1, id);
            BDOracle.COMANDO.setString (2, nome);

            MeuResultSet resultado = (MeuResultSet)BDOracle.COMANDO.executeQuery ();

            if (!resultado.first())
                throw new Exception ("Sem cadastro");

            
           
            labirinto = new TesteLab(resultado.getString("ID"),resultado.getString("NOME"),
            						  resultado.getString("DATA_CRIACAO"),
            						  resultado.getString("DATA_ULTIMA_MODIFICACAO"),
            						  resultado.getInt("LABIRINTO"));
        }
        catch (SQLException erro)
        {
        	erro.printStackTrace();
            throw new Exception ("Nao foi possivel encontrar o labirinto");
        }

        return labirinto;
    }

    public static MeuResultSet getLabirintos () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM Labirintos";

            BDOracle.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BDOracle.COMANDO.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Nao foi possivel pegar os labirintos");
        }

        return resultado;
    }
}