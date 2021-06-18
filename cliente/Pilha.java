package cliente;

   /**
   A classe Pilha � uma estrutura de dado LIFO. O �ltimo elemento armazenado 
   ser� o primeiro a ser retirado, por exemplo: uma pilha de pratos, onde podemos apenas 
   retirar ou inserir no seu topo. Na classe haver� m�todos para incluir, excluir, redimensionar e listar.
   @author Lucas, Thiago e Ruan.
   @since 2021.
   */

public class Pilha<X> implements Cloneable {
    private Object[] elemento;
    private int tamanhoInicial;
    private int ultimo = -1;

    /**
    Valida o tamanho fornecido para Pilha. 
    Verifica se o valor recebido pelo par�metro � um valor positivo, e se n�o for, � lan�ada 
    uma exce��o. Se caso passe pelo teste anterior, o tamanho da pilha ser� o valor recebido  
    pelo par�metro, e este valor tamb�m ser� considerado o tamanho inicial da Pilha.
    @param tamanho � o valor a ser validado.
    @throws Exception lan�a a exce��o se o valor recebido n�o for um valor positivo.
    */

    public Pilha(int tamanho) throws Exception {
        if (tamanho <= 0)
            throw new Exception("Tamanho invalido");

        this.elemento = new Object[tamanho];
        this.tamanhoInicial = tamanho;
    }

    /**
    Retorna o tamanho da Pilha.
    � um m�todo que podemos retornar o tamanho da Pilha atualmente, ent�o, se na Pilha 
    h� duas informa��es armazenadas, ser� retornado o valor 2.
    @return um inteiro que � o tamanho da Pilha.
    */

    public int getQuantidade() {
        return this.ultimo + 1;
    }

    private void redimensioneSe(float fator) {
        Object[] novo = new Object[Math.round(this.elemento.length * fator)];

        for (int i = 0; i <= this.ultimo; i++)
            novo[i] = this.elemento[i];

        this.elemento = novo;
    }
   
    /**
    Armazena elementos na Pilha.
    Recebe um par�metro do tipo definido na constru��o da pilha, basicamente o x
    � o valor que ser� armazenado, enquanto X � o tipo da classe da Pilha. � feito
    um teste para saber se o valor recebido � nulo, se a Pilha est� cheia, e por �ltimo, 
    se o x � clon�vel.
    Na �ltima etapa o valor � atribuido a �ltima posi��o dispon�vel.
    @param x � o valor que ser� armazenado na Pilha.
    @throws Exception lan�a a exce��o se o valor for nulo.
    */

   public void guardeUmItem(X x) throws Exception {
        if (x == null)
        throw new Exception("Valor ausente");

        if (this.ultimo + 1 == this.elemento.length) // cheia
        this.redimensioneSe(2.0F);

       /* if (x instanceof Cloneable) {
        Clonador<X> clonador = new Clonador<X>();
        this.elemento[this.ultimo+1] = clonador.clone(x);
    }*/

        this.ultimo++;
        this.elemento[this.ultimo] = x;
    }

    /**
    Recupera um elemento da Pilha.
    � feito inicialmente um teste para checar se a Pilha se encontra vazia, e se sim, dever� ser
    lan�ada uma exce��o, e logo em sequ�ncia � testado se o �ltimo elemento da Pilha � clon�vel.
    Ent�o � retornado o �ltimo elemento da Pilha clonado, e caso ele n�o entre no if anterior,
    apenas dever� retornar o �ltimo elemento da Pilha.  
    @throws Exception lan�a a exce��o se a Pilha estiver vazia.
    @return o valor do �ltimo elemento clonado preenchido na Pilha.	
    @return o valor do �ltimo elemento preenchido na Pilha.
    */

    public X recupereUmItem() throws Exception {
        if (this.ultimo == -1)
        	throw new Exception("Pilha vazia");
        if(this.elemento[this.ultimo] instanceof Cloneable) {
	Clonador<X> clonador = new Clonador<X> ();
	return clonador.clone((X) this.elemento[this.ultimo]);
       }
	return (X) this.elemento[this.ultimo]; 
    }

    /**
    Remove um elemento da Pilha.
    � feito inicialmente um teste para checar se a Pilha se encontra vazia, e se sim, dever� ser lan�ada uma     
    exce��o. Na sequ�ncia, o �ltimo elemento do vetor recebe null, e o �ltimo elemento preenchido � decrementado. 
    Por �ltimo, � feito um teste para verificar se os valores preenchidos no vetor correspondem a pelo menos 25%, 
    se n�o, entrar� no if e o vetor ser� redimensionado para metade do seu tamanho.
    @throws Exception lan�a a exce��o se a Pilha estiver vazia.     
    */

    public void removaUmItem() throws Exception {
        if (this.ultimo == -1) // vazia
            throw new Exception("Nada a remover");

        this.elemento[this.ultimo] = null;
        this.ultimo--;

        if (this.elemento.length > this.tamanhoInicial && this.ultimo + 1 <= Math.round(this.elemento.length * 0.25F))
            this.redimensioneSe(0.5F);
    }
   
   /**
    Verifica se a Pilha est� cheia.
    � um m�todo que retorna verdadeiro se a Pilha estiver complemente preenchida,
    � feita uma compara��o do tamanho da Pilha com a quantidade de valores preenchidos        
    nela.	
    @return verdadeiro se a Pilha estiver cheia.
    */

    public boolean isCheia() {
        return this.ultimo + 1 == this.elemento.length;
    }

   /**
   Verifica se a Pilha est� vazia.
    � um m�todo que retorna verdadeiro, se o �ltimo elemento for igual a -1, dessa forma
    confirmando que a Pilha est� vazia.
    @return verdadeiro se a Pilha estiver vazia.
    */

    public boolean isVazia() {
        return this.ultimo == -1;
    }

    /**
    Retorna o �ltimo elemento, com um texto concatenado.
    � um m�todo que retorna o �ltimo elemento do vetor, de uma forma mais amig�vel,
    contendo um texto que facilita o entendimento do usu�rio, inclusive diferenciando
    o plural do singular, por exemplo: 2 elementos sendo o �ltimo 30.
    @return retorna o �ltimo elemento, junto com um texto.
    */

    @Override
    public String toString() {
        String ret;

        if (this.ultimo == 0)
            ret = "1 elemento";
        else
            ret = (this.ultimo + 1) + " elementos";

        if (this.ultimo != -1)
            ret += ", sendo o ultimo " + this.elemento[this.ultimo];

        return ret;
    }

    /**
    Retorna verdadeiro se os objetos forem iguais, e falso se forem diferentes.
    Para saber se dois objetos s�o iguais, precisamos de algumas etapas. Come�amos
    testando se o par�metro � igual ao objeto que est� chamando, verificamos se o
    par�metro � null, se o objeto que est� chamando � da mesma classe do par�metro,
    se o �ltimo elemento � diferente e se o tamanho inicial � diferente. Se caso passe por
    todos os ifs, dever� retornar verdadeiro, pois os objetos s�o iguais.
    @param obj � o objeto que est� sendo comparado.
    @return retorna true se o par�metro for igual ao objeto que est� chamando.
    @return retorna false se o par�metro for null.
    @return retorna false se as classes dos objetos comparados forem diferentes.
    @return retorna false se o �ltimo elemento dos dois objetos for diferente.
    @return retorna false se o tamanho inicial for diferente.
    @return retorna false se algum elemento for diferente.
    @return retorna true se passa por todos os ifs anteriores.
    */
	
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (this.getClass() != obj.getClass())
            return false;

        Pilha<X> pil = (Pilha<X>) obj;

        if (this.ultimo != pil.ultimo)
            return false;

        if (this.tamanhoInicial != pil.tamanhoInicial)
            return false;

        for (int i = 0; i <= this.ultimo; i++)
            if (!this.elemento[i].equals(pil.elemento[i]))
                return false;

        return true;
    }

    /**
    Cria um codigo, para identificacao do objeto.
    E criado um codigo que servira para identificacao do objeto, que sera usado para
    armazenar objetos da classe em estrutura de dados usando a t�cnica de hash
    (espalhamento), e para isso e feito um calculo utilizando o ultimo elemento preenchido.
    Dessa forma, todo elemento tera um numero diferente.
    @return retorna um valor inteiro.
    */

    @Override
    public int hashCode() {
        int ret = 666;

        ret = ret * 7 + new Integer(this.ultimo).hashCode();
        ret = ret * 7 + new Integer(this.tamanhoInicial).hashCode();

        for (int i = 0; i <= this.ultimo; i++)
            ret = ret * 7 + this.elemento[i].hashCode();

        if (ret < 0)
            ret = -ret;

        return ret;
    }
}