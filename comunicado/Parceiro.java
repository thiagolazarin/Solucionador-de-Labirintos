package comunicado;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.concurrent.Semaphore;

/**
 * Essa classe � uma conex�o de socket
 * entre o cliente e o servidor recebendo 
 * e enviando informa��e dos dois lados
 * @author Thiago Lazarin, Ruan Sotovia, Lucas Lacerda.
 * @since 2021.
 * */

public class Parceiro extends Comunicado implements Serializable, Cloneable {
    private Socket conexao;
    private ObjectInputStream receptor;
    private ObjectOutputStream transmissor;
    private Comunicado proximoComunicado = null;
    private Semaphore mutEx = new Semaphore(1, true);

    
	/**
	 * Esse m�todo faz a conexao direta entre cliente e servidor
	 * @param os parametros desse metodo servem para receber informa��es do servidor.
	 * @throws se tiver algum paramentro nulo
	 */
    public Parceiro(Socket conexao, ObjectInputStream receptor, ObjectOutputStream transmissor) throws Exception {
        if (conexao == null) {
            throw new Exception("Conexao ausente");
        } else if (receptor == null) {
            throw new Exception("Receptor ausente");
        } else if (transmissor == null) {
            throw new Exception("Transmissor ausente");
        } else {
            this.conexao = conexao;
            this.receptor = receptor;
            this.transmissor = transmissor;
        }
    }

	/**
	 * Esse metodo � usado quando desejamos enviar alguma informa��o do tipo comunicado para o servidor
	 * @param objeto que estamos enviando para o servidor
	 * @throws se acontecer algum erro durante o processo de transmissao.
	 */
    
    public void receba(Comunicado x) throws Exception {
        try {
            this.transmissor.writeObject(x);
            this.transmissor.flush();
        } catch (IOException var3) {
        	var3.printStackTrace();
            throw new Exception("Erro de transmissao");
        }
    }

    
	/**
	 * esse metodo � uma forma de espiar a informa��o de forma leve sem ocupar espa�o 
	 * 
	 * @throws se algum erro aparecer na recep��o.
	 */
    public Comunicado espie() throws Exception {
        try {
            this.mutEx.acquireUninterruptibly();
            if (this.proximoComunicado == null) {
                this.proximoComunicado = (Comunicado)this.receptor.readObject();
            }

            this.mutEx.release();
            return this.proximoComunicado;
        } catch (Exception var2) {
            throw new Exception("Erro de recepcao");
        }
    }

    
	/**
	 * esse metodo � usado quando o cliente precisa que o servidor envie uma informa�ao.
	 * 
	 * @throws se acontecer algum erro de recep��o da informa��o enviada.
	 */
    public Comunicado envie() throws Exception {
        try {
            if (this.proximoComunicado == null) {
                this.proximoComunicado = (Comunicado)this.receptor.readObject();
            }
            Comunicado ret = this.proximoComunicado;
            this.proximoComunicado = null;
            return ret;
        } catch (Exception var2) {
        	throw new Exception("Erro de recepcao");
        }
    }
    
	/**
	 * Metodo sera util quando o usuario querer se desconectar do servidor, todas as conex�es vao ser finalizadas
	 * @throws se acontecer algum erro na hora que o usuario for se desconectar.
	 */

    public void adeus() throws Exception {
        try {
            this.transmissor.close();
            this.receptor.close();
            this.conexao.close();
        } catch (Exception var2) {
            throw new Exception("Erro de desconexao");
        }
    }
}
