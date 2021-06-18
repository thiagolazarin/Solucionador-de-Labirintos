package cliente;

import java.io.*;

/**
 * Classe para leitura, manipula��o e uso das informa��es do arquivo em outras classes.
 * @author Thiago Lazarin, Ruan Sotovia, Lucas Lacerda
 * @since 2021.
*/

public class Arquivo {

    private BufferedReader arquivo;

    /**
    * Construtor que realiza a leitura do arquivo passado como par�metro.
    * @param nomeArq par�metro do endere�o do arquivo passado.
    * @throws Exception se n�o for poss�vel ler o arquivo.
    */    
    
    public Arquivo(String nomeArq) {
        try {
            arquivo = new BufferedReader(new FileReader(nomeArq));

        } catch (IOException erro) {
            System.err.println("Arquivo Invalido");
        }
    }
    
    /**
    * Obt�m uma linha selecionada do arquivo.
    * @return ret String da linha do arquivo
    */

    public String getUmString() {
        String ret = null;
        try {
            ret = arquivo.readLine();
        } catch (IOException erro) {
        }
        return ret;
    }
    
    /**
    * Obt�m um n�mero inteiro do arquivo.
    * @return ret Int obtido do arquivo.
    * @throws Exception se n�o for obtido um Int.
    */

    public int getUmInt() throws Exception {
        int ret = 0;
        try {
            ret = Integer.parseInt(arquivo.readLine());
        } catch (IOException erro) {
        } catch (NumberFormatException erro) {
            throw new Exception("Int invalido!");
        }
        return ret;
    }

}