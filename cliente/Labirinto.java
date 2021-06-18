package cliente;

/**
 * A classe labirinto manipula a matriz do labirinto atraves de entradas, sa�das e valida��es.
 * @author Thiago, Ruan, Lucas
 */
public class Labirinto implements Cloneable {

    private int numLinhas, numColunas;
    private Pilha<Coordenada> pilhaAdj, inverso, caminho;
    private char labirinto[][];
    private Pilha<Pilha<Coordenada>> possibilidades;
    private Coordenada ondeEsta = null;
    private char up, down, right, left;

    public Labirinto() { }

    /**
     * M�todo construtor da classe labirinto que recebe um n�mero de linhas e colunas do arquivo para criar a matriz.
     * @param arq arquivo que cont�m o labirinto.
     * @throws Exception quando o labirinto estiver incorreto.
     */
    public Labirinto(String arquivo) throws Exception {

        Arquivo aLab = new Arquivo(arquivo);

        int numLinha = aLab.getUmInt();//5
        String str = aLab.getUmString();
        int numColuna = str.length();

        String x;
        int colunaAtual;
        for (int l = 2; l <= numLinha; l++) {
            x = aLab.getUmString();
            colunaAtual = x.length();
            if (numColuna != colunaAtual) {
                throw new Exception("Colunas invalidas");
            }
        }
        x = aLab.getUmString();
        if(x != null){
            throw new Exception("Linhas invalidas");
        }else{
            Arquivo igual = new Arquivo(arquivo);

            this.numLinhas = numLinha;
            this.numColunas = numColuna;

            igual.getUmInt();
            this.labirinto = new char[this.numLinhas][this.numColunas];
            for (int l = 0; l < this.numLinhas; l++) {
                String string = igual.getUmString();
                for (int k = 0; k < this.numColunas; k++) {
                    this.labirinto[l][k] = string.charAt(k);
                }
            }
        }
    }

    /**
     * Pega a primeira linha do labirinto.
     * @return Primeira linha do labirinto.
     */
    private char getUp() {
        return this.labirinto[this.ondeEsta.getLinha() - 1][this.ondeEsta.getColuna()];
    }
    /**
     * Pega a ultima linha do labirinto.
     * @return Ulitma linha do labirinto.
     */
    private char getDown() {
        return this.labirinto[this.ondeEsta.getLinha() + 1][this.ondeEsta.getColuna()];
    }
    /**
     * Pega a coluna da ponta direita do labirinto.
     * @return Ponta da coluna da direita do labirinto.
     */
    private char getRight() {
        return this.labirinto[this.ondeEsta.getLinha()][this.ondeEsta.getColuna() + 1];
    }
    /**
     * Pega a coluna da ponta esquerda do labirinto.
     * @return Ponta da coluna da esquerda do labirinto.
     */
    private char getLeft() {
        return this.labirinto[this.ondeEsta.getLinha()][this.ondeEsta.getColuna() - 1];
    }
    /**
     * Pega a localiza��o atual no labirinto.
     * @return localiza��o atual no labirinto.
     */
    private char getAqui() {
        return this.labirinto[this.ondeEsta.getLinha()][this.ondeEsta.getColuna()];
    }
    /**
     * Pega a quantidade de colunas.
     * @return quantidade de colunas.
     */
    public int getColunas() {
        return this.numColunas;
    }
    /**
     * Pega a quantidade de linhas.
     * @return quantidade de linhas.
     */
    public int getLinhas() {
        return this.numLinhas;
    }

    /**
     * Gera um modelo de uma copia do atributo da classe labirinto.
     * @param modelo atributo da classe labirinto.
     */
    public Labirinto(Labirinto modelo) throws Exception {
        try {
            if (modelo == null) {
                throw new Exception("Modelo inexistente");
            }
            modelo.numColunas = this.numColunas;
            modelo.numLinhas = this.numLinhas;
            modelo.labirinto = this.labirinto;
        } catch (Exception erro) {
            System.out.println("Algo deu errado sua anta");
        }
    }

    /**
     * M�todo que inst�ncia outros m�todos para a valida��o e solu��o do labirinto atrav�s de suas coordenadas.
     * @throws Exception se labirinto diferente de valido.
     */

    public void Solucionar() throws Exception {

        try{
            if (!validacoes()) {
                throw new Exception("ERRO");
            }

            this.ondeEsta = new Coordenada(entrada());
            this.caminho = new Pilha<Coordenada>(getColunas() * getLinhas());
            this.possibilidades = new Pilha<Pilha<Coordenada>>(getColunas() * getLinhas());

            while (getAqui() != 'S') {
                carregarAdj();
                while (this.pilhaAdj.isVazia()) {
                    this.ondeEsta = caminho.recupereUmItem();
                    this.caminho.removaUmItem();
                    this.labirinto[this.ondeEsta.getLinha()][this.ondeEsta.getColuna()] = ' ';
                    this.pilhaAdj = this.possibilidades.recupereUmItem();
                    this.possibilidades.removaUmItem();
                }
                this.ondeEsta = this.pilhaAdj.recupereUmItem();
                if (getAqui() != 'S') {
                    this.pilhaAdj.removaUmItem();
                    this.labirinto[this.ondeEsta.getLinha()][this.ondeEsta.getColuna()] = '*';
                    this.caminho.guardeUmItem(this.ondeEsta);
                    this.possibilidades.guardeUmItem(this.pilhaAdj);
                }
            }
        }catch (Exception erro){
            throw new Exception("Labirinto errado anta");
        }

        System.out.print("\n");
        lerInverso();
    }

    /**
     * M�todo que realiza a leitura da matriz do labirinto ao inverso, armazenando o caminho percorrido (coordenadas) em uma pilha.
     * @throws Exception se o m�todo n�o realizar a leitura do caminho.
     */

    private void lerInverso() throws Exception {

        try {

            inverso = new Pilha<Coordenada>(caminho.getQuantidade());

            while (!caminho.isVazia()) {
                inverso.guardeUmItem(caminho.recupereUmItem());
                caminho.removaUmItem();
            }

        } catch (Exception erro) {
            System.out.println("ALGO DEU ERRADO SUA ANTA");
        }
    }

    /**
     * Exibe as coordenadas armazenadas para demonstrar o caminho percorrido para solu��o do labirinto.
     * @return ret coordenadas do caminho.
     * @throws Exception se n�o houver caminho para printar.
     */
    public String printarSolucao() throws Exception {

        String ret = " ";

        int i = 0;

        while (!inverso.isVazia()) {
            i++;
            if(i<=31){
                ret += inverso.recupereUmItem() + " ";
                System.out.print(" " + inverso.recupereUmItem());
                inverso.removaUmItem();
            }else{
                ret += inverso.recupereUmItem() + "    \n";
                System.out.print(" " + inverso.recupereUmItem());
                inverso.removaUmItem();
                i=0;
            }

        }
        return ret;
    }

    /**
     *
     * @throws Exception
     */

    private void carregarAdj() throws Exception {

        try {
            this.pilhaAdj = new Pilha<Coordenada>(3);
            Coordenada coorAdj;

            coorAdj = pegarUp();
            if (coorAdj != null) {
                if (getUp() == ' ' || getUp() == 'S'){
                    this.pilhaAdj.guardeUmItem(coorAdj);
                }
            }

            coorAdj = pegarLeft();
            if (coorAdj != null) {
                if (getLeft() == ' ' || getLeft() == 'S'){
                    this.pilhaAdj.guardeUmItem(coorAdj);
                }
            }

            coorAdj = pegarDown();
            if (coorAdj != null && coorAdj.getLinha() < getLinhas()) {
                if (getDown() == ' ' || getDown() == 'S'){
                    this.pilhaAdj.guardeUmItem(coorAdj);
                }
            }

            coorAdj = pegarRight();
            if (coorAdj != null && coorAdj.getColuna() < getColunas()) {
                if (getRight() == ' ' || getRight() == 'S'){
                    this.pilhaAdj.guardeUmItem(coorAdj);
                }
            }

        } catch (Exception erro) {
            System.out.println("ALGO DEU ERRADO SUA ANTA");
        }
    }

    /**
     * M�todo que passa o valor da coordenada acima referente � atual.
     * @return retorno valor da coordenada acima da atual.
     * @throws Exception se n�o houver coordenada acima.
     */
    private Coordenada pegarUp() throws Exception {
        Coordenada retorno = null;
        try {
            retorno = new Coordenada(this.ondeEsta.getLinha() - 1, this.ondeEsta.getColuna());
        } catch (Exception erro) {
            //System.out.println("Algo deu errado sua ANTA");
            return null;
        }
        return retorno;
    }

    /**
     * M�todo que passa o valor da coordenada da direita referente � atual.
     * @return retorno valor da coordenada a direita da atual.
     * @throws Exception se n�o houver coordenada � direita.
     */

    private Coordenada pegarRight() throws Exception {
        Coordenada retorno = null;
        try {
            retorno = new Coordenada(this.ondeEsta.getLinha(), this.ondeEsta.getColuna() + 1);
        } catch (Exception erro) {
            System.out.println("Algo deu errado sua ANTA");
            return retorno;
        }
        return retorno;
    }

    /**
     * M�todo que passa o valor da coordenada abaixo referente � atual.
     * @return retorno valor da coordenada abaixo da atual.
     * @throws Exception se n�o houver coordenada abaixo.
     */

    private Coordenada pegarDown() throws Exception {
        Coordenada retorno = null;
        try {
            retorno = new Coordenada(this.ondeEsta.getLinha() + 1, this.ondeEsta.getColuna());
        } catch (Exception erro) {
            System.out.println("Algo deu errado sua ANTA");
            return retorno;
        }
        return retorno;
    }

    /**
     * M�todo que passa o valor da coordenada a esquerda referente a atual.
     * @return retorno valor da coordenada a direita da atual.
     * @throws Exception se n�o houver coordenada a direita.
     */

    private Coordenada pegarLeft() throws Exception {
        Coordenada retorno = null;
        try {
            retorno = new Coordenada(this.ondeEsta.getLinha(), this.ondeEsta.getColuna() - 1);
        } catch (Exception erro) {
            return retorno;
        }

        return retorno;
    }

    /**
     * M�todo que verifica se h� entrada no labirinto atrav�s da letra E, caso exista ser� passado o valor da coordenada.
     * para a classe Coordenada e em seguida verificar se h� paredes completas na matriz do labirinto.
     * @return retorno valor da coordenada de entrada.
     * @throws Exception se n�o houver paredes completas.
     */
    private Coordenada entrada() throws Exception {

        Coordenada retorno = null;
        for (int l = 0; l < this.numLinhas; l++) {
            for (int k = 0; k < this.numColunas; k++) {
                if ((l == 0 || l == this.numLinhas - 1)) {
                    if (this.labirinto[l][k] == 'E') {
                        retorno = new Coordenada(l, k);
                    }
                    if (this.labirinto[l][k] == ' ')
                        throw new Exception("N�o existe parede");
                }
                if ((k == 0 || k == this.numColunas - 1)) {
                    if (this.labirinto[l][k] == 'E') {
                        retorno = new Coordenada(l, k);
                    }
                    if (this.labirinto[l][k] == ' ')
                        throw new Exception("N�o existe parede");
                }
            }
        }
        return retorno;
    }

    /**
     * M�todo clone da classe labirinto.
     * @return retorno m�todo c�pia da classe labirinto.
     */

    public Object Clone() {
        Labirinto retorno = null;
        try {
            retorno = new Labirinto(this);
        } catch (Exception erro) {
        }
        return retorno;
    }

    /**
     * Realiza todas as valida��es relacionadas � estrutura da matriz do labirinto (linhas, colunas, entradas e sa�das)
     * @return true se possui linhas e colunas, uma sa�da e uma entrada, false se as linhas e colunas n�o forem v�lidas
     * ou/e se houver mais de uma sa�da/entrada.
     * @throws Exception erro na execu��o.
     */

    private boolean validacoes() throws Exception {
        try {

            int valida = 0;
            for (int l = 0; l < this.numLinhas; l++) {
                for (int k = 0; k < this.numColunas; k++) {
                    if (this.labirinto[l][k] == 'S') {
                        valida++;
                    }
                }
            }

            for (int l = 0; l < this.numLinhas; l++) {
                for (int k = 0; k < this.numColunas; k++) {
                    if (this.labirinto[l][k] == 'E') {
                        valida++;
                    }
                }
            }

            int contS = 0;
            for (int l = 0; l < this.numLinhas; l++) {
                for (int k = 0; k < this.numColunas; k++) {
                    if (this.labirinto[l][k] == 'S') {
                        contS++;
                    }
                }
            }

            int contE = 0;
            for (int l = 0; l < this.numLinhas; l++) {
                for (int k = 0; k < this.numColunas; k++) {
                    if (this.labirinto[l][k] == 'E') {
                        contE++;
                    }
                }
            }

            if (valida == 2 && contS == 1 && contE == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception erro) {
            System.out.println("Algo deu errado sua ANTA");
        }
        return false;
    }

    @Override
    /**
     * M�todo que exibe o labirinto o labirinto em formato String.
     * @return retorno labirinto String com caminho solucionado
     */
    public String toString() {
        if (this.labirinto == null) {
            return ("Labirinto invalido");
        }

        String retorno = "Labirinto\n";

        for (int l = 0; l < this.numLinhas; l++) {
            for (int k = 0; k < this.numColunas; k++) {
                retorno += Character.toString(this.labirinto[l][k]);
            }
            retorno += "\n";
        }
        return retorno;
    }

    @Override

    /**
     * M�todo que calcula e resulta o c�digo de hash ao qual o m�todo for aplicado.
     * @return retorno c�digo de hash do m�todo chamante.
     */
    public int hashCode() {

        int retorno = 29;

        retorno = retorno * 2 + new Integer(this.numLinhas).hashCode();
        retorno = retorno * 3 + new Integer(this.numColunas).hashCode();
        retorno = retorno * 7 + new Character(this.up).hashCode();
        retorno = retorno * 19 + new Character(this.right).hashCode();
        retorno = retorno * 11 + new Character(this.down).hashCode();
        retorno = retorno * 23 + new Character(this.left).hashCode();

        for (int l = 0; l < this.numLinhas; l++) {
            for (int k = 0; k < this.numColunas; k++)
                retorno = retorno * 11 + new Character(this.labirinto[l][k]).hashCode();
        }

        if (retorno < 0){
            retorno = retorno - retorno;
        }

        return retorno;
    }

    @Override

    /**
     * Realiza compara��o entre os labirintos ao qual o m�todo equals for aplicado, mensurando se o Object do par�metro
     * passado � igual ao labirinto.
     * @param obj objeto que ser� comparado ao m�todo que o equals for aplicado.
     * @return true se os labirintos forem iguais, false se forem diferentes.
     */
    public boolean equals(Object obj) {

        try {

            if (!(obj instanceof Labirinto)) {
                return false;
            }

            Labirinto outro = (Labirinto) obj;
            if (this.numLinhas != outro.numLinhas) {
                return false;
            }

            if (this.numColunas != outro.numColunas) {
                return false;
            }

            for (int l = 0; l < this.numLinhas; l++) {
                for (int k = 0; k < this.numColunas; k++) {
                    if (this.labirinto[l][k] != outro.labirinto[l][k]) {
                        return false;
                    }
                }
            }
        } catch (Exception erro) {
            System.out.println("ERRO SUA ANTA");
        }
        return true;
    }
}