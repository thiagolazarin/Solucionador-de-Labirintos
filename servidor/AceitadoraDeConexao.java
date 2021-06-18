package servidor;

import java.net.*;
import java.util.*;

import comunicado.Parceiro;

/**
 * essa classe verifica se tudo que esta entre o cliente e o servidor esta funcionando
 * @author Thiago Lazarin, Ruan Sotovia, Lucas Lacerda.
 * @since 2021.
 * */
public class AceitadoraDeConexao extends Thread
{
    private ServerSocket        pedido;
    private ArrayList<Parceiro> usuarios;
    private int numerClientes = 0;

	/**
	 * Esse metodo construtor faz referencia a instancia da classe supervisora de conexao 
	 * @param porta o servidor recebe a porta de conexao
	 * @param usuarios e uma estrutura de dados do tipo arraylist que armazenam conexoes de usuarios de parceiros
	 * @throws Exception se a porta for nula ou invalida e se usuarios forem nulos.
	 */
    public AceitadoraDeConexao (String porta, ArrayList<Parceiro> usuarios) throws Exception
    {
        if (porta==null)
            throw new Exception ("Porta ausente");

        try
        {
            this.pedido =
            new ServerSocket (Integer.parseInt(porta));
        }
        catch (Exception  erro)
        {
            throw new Exception ("Porta invalida");
        }

        if (usuarios==null)
            throw new Exception ("Usuarios ausentes");

        this.usuarios = usuarios;
    }
    
	/**
	 * metodo que verifica as conexoes feitas pelo usuario dentro do servidor
	 */
    public void run ()
    {
        for(;;)
        {
            Socket conexao=null;
            try
            {
                conexao = this.pedido.accept();
                   
                this.numerClientes++;
                System.out.println(this.numerClientes);
                System.out.println("Cliente conectado " + conexao.getInetAddress().getHostAddress());
                
            }
            catch (Exception erro)
            {
                continue;
            }

            SupervisoraDeConexao supervisoraDeConexao=null;
            try
            {
                supervisoraDeConexao =
                new SupervisoraDeConexao (conexao, usuarios);
            }
            catch (Exception erro)
            {} 
            supervisoraDeConexao.start();
        }
    }
}