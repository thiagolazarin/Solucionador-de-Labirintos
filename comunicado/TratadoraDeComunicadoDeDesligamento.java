package comunicado;

import java.net.*;

	/**
	 * a classe tratatoraDeComunicaDeDesligamento é um 
	 * comunicado de desligamento do cliente e ficara ativa 
	 * enquanto nao foi desligada
	 * @author Thiago Lazarin, Ruan Sotovia, Lucas Lacerda
	 * @since 2021.
	 * */


public class TratadoraDeComunicadoDeDesligamento extends Thread {
	private Parceiro servidor;
	
	  /**
     * esse metodo recebe a instancia de desligamento do server
     * @param o objeto do parametro vem o parceiro servidor ao qual fez a conexao na main
     * para fazer o desligamento do cliente no servidor
     * @throws se o servidor for nulo o servidor do parametro tambem é nulo
     * */
	
	public TratadoraDeComunicadoDeDesligamento(Parceiro servidor) throws Exception {
		if (servidor == null)
			throw new Exception("Porta invalida");

		this.servidor = servidor;
	}
	
	/**
	 * esse metodo "caminhara" junto com o cliente verificando 
	 * se ele desejar sair do servidor, o metodo ficara ativo 
	 * enquanto o cliente nao se desligar 
     * */
	public void run() {
		for (;;) {
			try {
				if (this.servidor.espie() instanceof ComunicadoDeDesligamento) {
					System.out.println("\nO servidor vai ser desligado agora;");
					System.err.println("volte mais tarde!\n");
					System.exit(0);
				}

			} catch (Exception erro) {
				System.err.println("a");
			}
		}
	}
}