package comunicado;

import java.io.Serializable;

import bd.dbo.Labirinto;

/**
 * esse classe serve para quando o cliente pedir o 
 * labirinto salvo do servidor
 *@author Thiago Lazarin, Ruan Sotovia, Lucas Lacerda.
 *@since 2021.
 */


public class Desenho extends Comunicado implements Serializable, Cloneable {
    private String desenho;

    public Desenho(String desenho) {
        this.desenho = desenho;
    }

    public String getDesenho() {
        return this.desenho;
    }

    public void setDesenho(String desenho) {
        this.desenho = desenho;
    }
    
    public Desenho (Desenho modelo) throws Exception{
    	if(modelo==null) {
    		throw new Exception("Modelo ausente");
    	}
    	this.desenho = modelo.desenho;
    }
    
    public Object clone() {
    	Desenho ret=null;
		
    	try {
    		ret = new Desenho(this);
    	}
    	catch(Exception erro)
    	{}
    	return ret;
    }
}
