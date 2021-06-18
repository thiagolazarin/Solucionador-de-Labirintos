
package comunicado;

import java.io.Serializable;

/**
 * Essa classe salva as informa��es inseridas pelo usu�rio para armazenar no banco de dados
 * @author Thiago Lazarin, Ruan Sotovia, Lucas Lacerda
 */
public class TesteLab extends Comunicado implements Serializable, Cloneable {
    private String nome;
    private String dataCriacao;
    private String dataUltimaCriacao;
    private String conteudo;
    private int id;

	/**
	 * Construtor das informa��es recebidas pelo cliente para inser��o no banco de dados
	 * @param name nome de identifica��o do labirinto
	 * @param dataInicio data de cria��o do labirinto
	 * @param dataFinal data da ultima cria��o do labirinto
	 * @param lab conteudo do labirinto
	 * @param id id do cliente
	 */
    
    public TesteLab(String nome, String dataCriacao, String dataUltima, String conteudo, int idConvertido) {
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.dataUltimaCriacao = dataUltima;
        this.conteudo = conteudo;
        this.id = idConvertido;

    }

    /**
     * M�todo chamado para armazenar conte�do do labirinto
     * @param lab recebe a informa��o
     */
    public void setConteudo(String lab) {
        this.conteudo = lab;
    }

    /**
     * Pega o id do cliente
     * @return id
     */
    public int getId() {
        return this.id;
    }

    /**
     * pega o nome do labirinto
     * @return nome
     */
    public String getNome() {
        return this.nome;
    }
    /**
     * pega a data de inicio do labirinto
     * @return data
     */
    public String getDataCriacao() {
        return this.dataCriacao;
    }
	/**
	 * pega a ultima data do labirinto
	 * @return data
	 */
    public String getDataUltimaCriacao() {
        return this.dataUltimaCriacao;
    }

    /**
     * pega o conteudo do labirinto
     * @return conteudo
     */
    public String getConteudo() {
        return this.conteudo;
    }

    /**
     * m�todo para mostrar as informa��es do cliente salvas pelo servidor
     * @return retorna o nome, conteudo, data da cria��o , data da ultima cria��o e o id do cliente.
     */
    public String toString() {
        return this.nome + " \n\n " + this.conteudo + "\n\n Data de criacao: " + this.dataCriacao + "\n Data ultima criacao: " + this.dataUltimaCriacao + "\n ID do cliente: " + this.id;
    }

    /**
     * M�todo que calcula e resulta o c�digo de hash ao qual o m�todo for aplicado.
     * @return retorno c�digo de hash do m�todo chamante.
     */
    public int hashCode() {
        int ret = 2000;
        ret = ret * 13 + (new String(this.nome)).hashCode();
        ret = ret * 13 + (new String(this.dataCriacao)).hashCode();
        ret = ret * 13 + (new String(this.dataUltimaCriacao)).hashCode();
        ret = ret * 13 + (new String(this.conteudo)).hashCode();
        if (ret < 0) {
            ret = -ret;
        }

        return ret;
    }

    /**
     * Realiza compara��o entre os objetos ao qual o m�todo equals for aplicado, mensurando se o Object do par�metro
     * passado � igual ao obj.
     * @param obj objeto que ser� comparado ao m�todo que o equals for aplicado.
     * @return true se os obj forem iguais, false se forem diferentes.
     */
    
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (!(obj instanceof TesteLab)) {
            return false;
        } else {
        	TesteLab labis = (TesteLab)obj;
            if (this.nome != labis.nome) {
                return false;
            } else if (this.dataCriacao != labis.dataCriacao) {
                return false;
            } else if (this.id != labis.id) {
                return false;
            } else if (this.dataUltimaCriacao != labis.dataUltimaCriacao) {
                return false;
            } else {
                return this.conteudo == labis.conteudo;
            }
        }
    }
    
    public TesteLab (TesteLab modelo) throws Exception{
    	if(modelo==null) {
    		throw new Exception("Modelo ausente");
    	}
        this.nome = modelo.nome;
        this.dataCriacao = modelo.dataCriacao;
        this.dataUltimaCriacao = modelo.dataUltimaCriacao;
        this.conteudo = modelo.conteudo;
        this.id = modelo.id;
    }
    
    public Object clone() {
    	TesteLab ret=null;
		
    	try {
    		ret = new TesteLab(this);
    	}
    	catch(Exception erro)
    	{}
    	return ret;
    }
}
