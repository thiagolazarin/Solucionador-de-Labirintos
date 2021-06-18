package cliente;

	/**
	a classe coordernada basicamente guarda a posi��o da matriz em coordenadas.
	@author Thiago Lazarin, Ruan Sotovia, Lucas Lacerda
	@since 2021.
	*/

public class Coordenada implements Cloneable {

    private int linha;
    private int coluna;
    
    /**
	gera dois parametros para coordenada linha e coluna 
	@param quantidade de linha e coluna da matriz
	@throws Exception se a quantidade for menor que 0
   */
    public Coordenada(int linha, int coluna) throws Exception {

        if (linha < 0 || coluna < 0) {
            throw new Exception("Coordenada inexistente");
        } else {
            this.linha = linha;
            this.coluna = coluna;
        }
    }
    
    /**
    Cria um c�digo, para identifica��o do objeto.
    � criado um c�digo que servir� para identifica��o do objeto, que ser� usado para
    armazenar objetos da classe em estrutura de dados usando a t�cnica de hash
    (espalhamento), e para isso � feito um c�lculo utilizando o �ltimo elemento preenchido.
    Dessa forma, todo elemento ter� um n�mero diferente.
    @return o codigo de espalhamento da coordenada do m�todo.
    */

    public int hashCode() {

        int retorna = 13;

        retorna = retorna * 13 + new Integer(this.linha).hashCode();
        retorna = retorna * 13 + new Integer(this.coluna).hashCode();

        return retorna;
    }
    
    /**
    Verifica se o Object fornecido como par�metro representa uma
    coordenada igual �quela representada pela inst�ncia
    @param  objeto a ser comparado com a inst�ncia 
    @return falso caso o objeto chamente nao represente coordenadas iguais, true, caso represente
    */

    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Coordenada)) {
            return false;
        }
        Coordenada cord = (Coordenada) obj;
        if (this.linha != cord.linha) {
            return false;
        }
        if (this.coluna != cord.coluna) {
            return false;
        } else {
            return true;
        }
    }
    /**
	mostra todo conteudo visual dos dados das coordenadas
   @return String que possui as coordernadas
   */
    public String toString() {

        String retorna;

        retorna = "{";

        retorna += this.linha + "," + this.linha;

        retorna += "}";

        return retorna;
    }
    /**
	metodo para alterar a linha do usuario 
    @throws Exeption se a anta tentar fazer uma altera��o para uma linha menor que 0
    */
    public void setLinha(int linha) {
        this.linha = linha;
    }
    /**
	metodo para alterar a coluna do usuario 
    @throws Exeption se a anta tentar fazer uma altera��o para uma coluna menor que 0
    */
    public void setColuna(int coluna) {
        this.coluna = coluna;
    }
    /**
	m�todo responsavel por retornar a linha
    @return retornamos a linha do atributo this.linha
    */
    public int getLinha() {
        return this.linha;
    }
    /**
	m�todo responsavel por retornar a coluna
    @return retornamos a coluna do atributo this.coluna
    */
    public int getColuna() {
        return this.coluna;
    }
    /**
     * Gera um modelo de uma copia do construtor de coordenada.
     * @param modelo atributo da classe cordenada.
     */
    public Coordenada(Coordenada modelo) throws Exception {

        if (modelo == null) {
            throw new Exception("Modelo inexistente");
        }

        this.linha = modelo.linha;
        this.coluna = modelo.coluna;
    }
    /**
   m�todo clone que se  refere a coordenada
   @return retorna uma c�pia do m�todo coordernada.
  */
    public Object clone() {

        Coordenada ret = null;

        try {
            ret = new Coordenada(this);
        } catch (Exception erro) {
        }
        return ret;
    }
}