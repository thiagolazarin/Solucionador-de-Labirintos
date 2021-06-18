package cliente;

import java.io.*;

   /**
   A classe Teclado serve basicamente para ler o que � escrito pelo teclado.
   @author Lucas, Thiago e Ruan.
   @since 2021.
   */

public class Teclado {
    private static BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

     /**
    Retorna uma String digitada pelo teclado.
    Definimos uma vari�vel do tipo string, que vai receber o que est� sendo digitado pelo
    teclado, e como sabemos que n�o h� possibilidade erros, n�o h� nada escrito nas 
    chaves. Por fim � retornado a vari�vel ret, que � um elemento string.
    @return uma string, que foi digitada pelo teclado.
    */

    public static String getUmString() {
        String ret = null;

        try {
            ret = teclado.readLine();
        } catch (IOException erro) {
        } // sei que não vai dar erro

        return ret;
    }

     /**
    Retorna um Char digitado pelo teclado.
    � definida uma vari�vel do tipo char, que recebe um valor null. Logo ap�s, vamos  
    tentar armazenar o que foi escrito pelo teclado em uma vari�vel do tipo string, e ser� 
    realizado um teste para verificar se essa vari�vel � null, se sim, dever� lan�ar uma 
    exce��o. Um �ltimo teste � feito para verificar se o tamanho da string � 1, pois um 
    char s� armazena um valor, e se caso for maior que 1, dever� ser lan�ada uma 
    exce��o. Por fim, vamos capturar o primeiro elemento da string, armazenar na 
    vari�vel char e retornar o elemento.
    @return um elemento do tipo char.
    @throws Exception lan�a a exce��o se a string for null.
     @throws Exception lan�a a exce��o se a string tiver mais de 1 caracter.
     */

    public static char getUmChar() throws Exception {
        char ret = ' ';
        try {
            String str = teclado.readLine();

            if (str.length() != 1)
                throw new Exception("Char invalido!");

            ret = str.charAt(0);
        } catch (IOException erro) {
        }
        return ret;
    }
}