package cliente;

import java.io.*;
import java.net.*;

import comunicado.Comunicado;
import comunicado.Parceiro;
import comunicado.TratadoraDeComunicadoDeDesligamento;

/**
 * a classe cliente tenta uma conexao com o servidor, se o servidor estiver ativo com hosts e portas funcionando, 
 * o cliente ira conseguir se conectar com o servidor.
 * @author Thiago Lazarin, Ruan Sotovia, Lucas Lacerda
 * @since 2021.
 * */

public class Cliente extends Comunicado implements Serializable, Cloneable{

	public static final String HOST_PADRAO = "127.0.0.1";
	public static final int PORTA_PADRAO = 3000;

	public static void main(String[] args) throws Exception{

		if (args.length > 2) {
			System.err.println("Uso esperado: Java client [HOST][PORTA]");
			return;
		}

		 Socket conexao = null;

		try {
			String host = Cliente.HOST_PADRAO;
			int porta = Cliente.PORTA_PADRAO;

			if (args.length > 0)
				host = args[0];

			if (args.length == 2)
				porta = Integer.parseInt(args[1]);

			  conexao = new Socket (host,porta);


		} catch (Exception erro) {
			System.err.println("Indique o servidor e a porta");
			return;
		}

		ObjectOutputStream transmissor = null;

		try {
			 transmissor = 
			 new ObjectOutputStream(conexao.getOutputStream());
			 

		} catch (Exception erro) {
			System.err.println("Indique o servidor e a porta");
			return;
		}

		ObjectInputStream receptor = null;

		try {
			 receptor = new ObjectInputStream(conexao.getInputStream());

		} catch (Exception erro) {
			System.err.println("Indique o servidor e a porta");
			return;
		}

		 Parceiro servidor = null;

		try {
			 servidor = new Parceiro (conexao, receptor , transmissor);

			
		} catch (Exception erro) {
			System.err.println("Indique o servidor e a porta");
			return;
		}

		 TratadoraDeComunicadoDeDesligamento tratadoraDeComunicadoDeDesligamento =  null;
		 
		try {
			tratadoraDeComunicadoDeDesligamento = new
			 TratadoraDeComunicadoDeDesligamento(servidor);

		} catch (Exception erro) {
		} 
		
		try {
			new Janela(servidor);
		} catch (Exception erro) {
			System.err.println(erro);
		}
		
	}
	
}