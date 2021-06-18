package cliente;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.sql.DataSource;
import javax.swing.*;

import comunicado.ComunicadoDeDesligamento;
import comunicado.Parceiro;
import comunicado.PedidoParaSair;
import comunicado.PedidoSalvamento;
import comunicado.TesteLab;

/**
 * a classe janela � uma interface grafica que possui metodos para o usuario 
 * consiga colocar seu labirinto, salvar seu labirinto, executar, etc.
 * @author Thiago Lazarin, Ruan Sotovia, Lucas Lacerda
 * @since 2021.
 */

public class Janela extends Labirinto implements Serializable, Cloneable {
    private JButton botao[] = new JButton[6];
    private JButton b1[] = new JButton[2];	
    Parceiro servidor;
    JFrame janela = new JFrame("Editor Labirinto");
    JFrame j2 = new JFrame("Salvar online");
    JTextArea editor = new JTextArea();
    JTextArea log = new JTextArea(12, 12);
    JTextField idLab = new JTextField("Id do lab", 25);
    JTextField nomeLab = new JTextField("Labirintos do banco",25);
    JTextField dataCriacaoLab = new JTextField("Data criacao",25);
    JTextField dataUltimaCriacaoLab = new JTextField("Data ultima criacao lab", 25);
    JTextField conteudoDoLab = new JTextField("Conteudo do labirinto",25);
    
    
	/**
	 * esse metodo represente o que cada botao vai fazer quando o usuario clicar nele
	 * @throws Exception se o botao estiver com problemas no metodo solucionar vai dar catch
	 */
    
    private class TratadoraDeMouse implements ActionListener {
        String nomeTexto;
        String nTexto;
        String guardar;

        @Override
        public void actionPerformed(ActionEvent event) {
            String executar = event.getActionCommand();
            if (executar == "Novo") {
                editor.setFont(new Font("Monospaced", 4, 20));
                editor.setText("");
                log.setText("");
            } else if (executar == "Abrir") {
                JFileChooser file = new JFileChooser();
                file.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int i = file.showSaveDialog(null);
                if (i == 1) {
                    editor.setText("");
                } else {
                    File arq = file.getSelectedFile();
                    try {
                        Labirinto lab1 = new Labirinto(arq.getPath());
                        this.nomeTexto = arq.getPath();
                        editor.setFont(new Font("Monospaced", 4, 20));
                        editor.setText(lab1.toString());

                    } catch (Exception e1) {
                        log.setFont(new Font("Monospaced", 2, 25));
                    }

                }

            } else if (executar == "Salvar") {

                if (editor.getText().equals("")) {
                    log.setFont(new Font("Monospaced", 2, 25));
                    log.setText("Tem que colocar alguma coisa");
                } else {
                    String pegar;
                    pegar = editor.getText();
                    log.setText("Labirinto salvo");

                    try {
                        FileWriter fileWriter = new FileWriter("E:\\teste3.txt");
                        PrintWriter pw = new PrintWriter(fileWriter);
                        this.nTexto = "E:\\teste3.txt";

                        pw.print(pegar);
                        pw.flush();
                        pw.close();
                        fileWriter.close();

                        Labirinto lab3 = new Labirinto(this.nTexto);
                        lab3.Solucionar();

                        FileWriter fileWriter1 = new FileWriter("E:\\teste2.txt");
                        PrintWriter pw1 = new PrintWriter(fileWriter1);
                        this.nTexto = "E:\\teste2.txt";

                        pw1.print(pegar);
                        pw1.flush();
                        pw1.close();
                        fileWriter1.close();

                    } catch (Exception ioException) {
                        log.setFont(new Font("Monospaced", 2, 25));
                        log.setText("N�o � possivel salvar o labirinto");
                    }
                }
            }

            else if (executar == "Executar") {

                try {
                    if (editor.getText().equals("")) {
                        log.setFont(new Font("Monospaced", 2, 20));
                        log.setText("Editor esta vazio sua anta");
                    }
                    Labirinto lab2 = new Labirinto(this.nomeTexto);
                    lab2.Solucionar();
                    editor.setFont(new Font("Monospaced", 4, 20));
                    this.guardar = lab2.printarSolucao();
                    log.setText(this.guardar);
                    editor.setText(lab2.toString());
                    log.setFont(new Font("Arial", 2, 15));

                } catch (Exception e1) {
                    System.out.println("");
                }
            }
            
            else if (executar == "Salvar no banco") {
            	j2.setVisible(true);     

            }
            else if(executar == "Salvar Labirinto") {
				try {
	            	String nome = nomeLab.getText();
	            	String dataCriacao = dataCriacaoLab.getText();
	            	String dataUltima = dataUltimaCriacaoLab.getText();
	            	String conteudo = conteudoDoLab.getText();
	            	String id = idLab.getText();
	            	int idConvertido = Integer.parseInt(id);
	            	System.out.println(nome);
	            	System.out.println(dataCriacao);
	            	System.out.println(dataUltima);
	            	System.out.println(conteudo);
	            	System.out.println(idConvertido);
	            	
	            	TesteLab maze = new TesteLab(nome, dataCriacao, dataUltima, conteudo, idConvertido);
					servidor.receba(new PedidoSalvamento(idConvertido, maze));
				} catch (Exception e) {
					e.getStackTrace();
					
				}
            }
            else if (executar == "Pegar do banco") {
            	j2.setVisible(true);
            }
            
            else if(executar == "fechar programa") {
				try {
					servidor.receba(new PedidoParaSair());
				} catch (Exception erro) {
				}
				try {
					janela.setVisible(false);
					j2.setVisible(false);
				} catch (Exception erro) {
				}
			}
            
        }
    }

	/**
	 * no construtor da janela vamos construir a interface 
	 * que o usuario ira visualizar, com tamanho, cores etc.
	 * e passamos o servidor como parametro para instanciar as 
	 * inforam��es pegas na parte digitada pelo usuario
	 * @param servidor
	 */

    public Janela(Parceiro servidor) {

        super();
        this.servidor = servidor;
        JPanel botoes = new JPanel();
        JPanel b1 = new JPanel();
        JPanel b2 = new JPanel();
        botoes.setLayout(new GridLayout(1, 4));

        String texto[] = { "Novo", "Abrir", "Salvar", "Executar", "Salvar no banco", "Pegar do banco"};
        String texto2[] = {"Salvar Labirinto", "fechar programa"};

        TratadoraDeMouse tratadorDeMouse = new TratadoraDeMouse();

        for (int i = 0; i < this.botao.length; i++) {
            this.botao[i] = new JButton(texto[i]);
            this.botao[i].setActionCommand(texto[i]);
            this.botao[i].addActionListener(tratadorDeMouse);
            botoes.add(this.botao[i]);
        }


        for (int i = 0; i < this.b1.length; i++) {
            this.b1[i] = new JButton(texto2[i]);
            this.b1[i].setActionCommand(texto2[i]);
            this.b1[i].addActionListener(tratadorDeMouse);
            b1.add(this.b1[i]);
        }
        
        this.janela.setSize(1980, 920);
        this.janela.getContentPane().setLayout(new BorderLayout());

        this.janela.add(botoes, BorderLayout.NORTH);
        this.janela.add(this.editor, BorderLayout.CENTER);
        this.janela.add(this.log, BorderLayout.SOUTH);
        log.setBackground(Color.ORANGE);
        editor.setBackground(Color.gray);

        this.janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.janela.setVisible(true);
        
        this.j2.setSize(700, 700);
		this.j2.setLayout(new GridLayout(2, 5));
		this.j2.add(nomeLab, BorderLayout.SOUTH);
		this.j2.add(dataCriacaoLab, BorderLayout.SOUTH);
		this.j2.add(dataUltimaCriacaoLab, BorderLayout.SOUTH);
		this.j2.add(conteudoDoLab, BorderLayout.SOUTH);
		this.j2.add(idLab, BorderLayout.SOUTH);
		this.j2.add(b1, BorderLayout.NORTH);
		
    }

}