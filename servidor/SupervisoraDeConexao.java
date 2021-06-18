package servidor;

import java.io.*;
import java.net.*;
import java.util.*;

import comunicado.Comunicado;
import comunicado.Desenho;
import comunicado.TesteLab;
import comunicado.Parceiro;
//import comunicado.PedidoDeAbertura;
import comunicado.PedidoParaSair;
import comunicado.PedidoSalvamento;
import bd.daos.Labirintos;
import bd.dbo.Labirinto;

/**
A classse SupervisoraDeConexao foi implementada, pois há necessidade de acompanhar todo ato feito pelo usuário
@author Lucas, Thiago e Ruan.
@since 2021.
*/

public class SupervisoraDeConexao extends Thread {
	private Parceiro usuario;
	private Socket conexao;
	private ArrayList<Parceiro> usuarios;

	
	/**
	 * Esse metodo construtor faz referencia a instancia da classe supervisora de conexao 
	 * @param conexao o servidor manda para o cliente o socket de conexao 
	 * @param usuarios e uma estrutura de dados do tipo arraylist que armazenam conexoes de usuarios de parceiros
	 * @throws Exception se a conexao e usuarios forem nulos.
	 */
	public SupervisoraDeConexao(Socket conexao, ArrayList<Parceiro> usuarios) throws Exception {
		if (conexao == null)
			throw new Exception("Conexao ausente");

		if (usuarios == null)
			throw new Exception("Usuarios ausentes");

		this.conexao = conexao;
		this.usuarios = usuarios;
	}
	
	
	/**
	 * metodo que supervisiona a acao de cada usuario dentro do servidor
	 */
	public void run() {

		ObjectOutputStream transmissor;
		try {
			transmissor = new ObjectOutputStream(this.conexao.getOutputStream());
		} catch (Exception erro) {
			return;
		}

		ObjectInputStream receptor = null;
		try {
			receptor = new ObjectInputStream(this.conexao.getInputStream());
		} catch (Exception err0) {
			try {
				transmissor.close();
			} catch (Exception falha) {
			} 

			return;
		}

		try {
			this.usuario = new Parceiro(this.conexao, receptor, transmissor);
		} catch (Exception erro) {
		} 

		try {
			synchronized (this.usuarios) {
				this.usuarios.add(this.usuario);
			}

			for (;;) {
				Comunicado comunicado = this.usuario.envie();
				if (comunicado == null)
					return;

				if (comunicado instanceof PedidoParaSair) {

					synchronized (this.usuarios) {
						this.usuarios.remove(this.usuario);
					}
					this.usuario.adeus();
					System.out.println("usuario saiu");
				}
				
				if(comunicado instanceof PedidoSalvamento) {
					Labirintos.incluir(((PedidoSalvamento)comunicado).getLab());
				}
			}
		} catch (Exception erro) {
			try {
				transmissor.close();
				receptor.close();
			} catch (Exception falha) {
				
			}

			return;
		}
	}
}
