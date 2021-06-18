package comunicado;
/**
 * 
 * @author Thiago Lazarin, Lucas Lacerda e Ruan Sotovia
 *
 */

import comunicado.Comunicado;

public class PedidoSalvamento extends Comunicado {
    private int id;
    private TesteLab lab;

    public PedidoSalvamento(int id, TesteLab lab) throws Exception {
    	if(lab == null) throw new Exception("Objeto vazio");
    	
    	if(id <=0 )throw new Exception("Id invalido");
    	
    	this.lab = lab;
    	this.id = id;
        
    }

    /**
     * pega o id do usuario
     * @return id
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * pega o labirinto do usuario
     * @return labirinto
     */
    public TesteLab getLab() {
        return this.lab;
    }
    /**
     * o equals teste se os objetos sao iguais ou diferentes, se for igual retorna true, se nao, retorna false
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (!(obj instanceof PedidoSalvamento)) {
            return false;
        } else {
            PedidoSalvamento save = (PedidoSalvamento)obj;
            if (this.id != save.id) 
                return false;
            if (this.id != save.id) 
                return false;
             else {
                return this.lab.equals(obj);
            }
        }
    }

    /**
    Cria um codigo, para identificacao do objeto.
    E criado um codigo que servira para identificacao do objeto, que sera usado para
    armazenar objetos da classe em estrutura de dados usando a t�cnica de hash
    (espalhamento), e para isso e feito um calculo utilizando o ultimo elemento preenchido.
    Dessa forma, todo elemento tera um numero diferente.
    @return retorna um valor inteiro.
    */

    public int hashCode() {
        int retorno = 2000;
       
        if (this.lab != null) {
        	retorno = retorno * 7 + this.lab.hashCode();
        }

        if (retorno < 0) {
        	retorno = - retorno;
        }

        return retorno;
    }
 
    /**
     * ele retorna para o usuario o numero do seu id e o labirinto.
     */
    public String toString() {
        return "ID" + this.id + "Labirinto" + this.lab;
    }
}
