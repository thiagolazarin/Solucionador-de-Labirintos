package servidor;

import java.io.*;

   /**
   A classe Teclado serve basicamente para ler o que é escrito pelo teclado.
   @author Lucas, Thiago e Ruan.
   @since 2021.
   */

public class Teclado {
    private static BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

     /**
    Retorna uma String digitada pelo teclado.
    Definimos uma variável do tipo string, que vai receber o que está sendo digitado pelo
    teclado, e como sabemos que não há possibilidade erros, não há nada escrito nas 
    chaves. Por fim é retornado a variável ret, que é um elemento string.
    @return uma string, que foi digitada pelo teclado.
    */

    public static String getUmString() {
        String ret = null;

        try {
            ret = teclado.readLine();
        } catch (IOException erro) {
        } // sei que nÃ£o vai dar erro

        return ret;
    }

     /**
    Retorna um Char digitado pelo teclado.
    É definida uma variável do tipo char, que recebe um valor null. Logo após, vamos  
    tentar armazenar o que foi escrito pelo teclado em uma variável do tipo string, e será 
    realizado um teste para verificar se essa variável é null, se sim, deverá lançar uma 
    exceção. Um último teste é feito para verificar se o tamanho da string é 1, pois um 
    char só armazena um valor, e se caso for maior que 1, deverá ser lançada uma 
    exceção. Por fim, vamos capturar o primeiro elemento da string, armazenar na 
    variável char e retornar o elemento.
    @return um elemento do tipo char.
    @throws Exception lança a exceção se a string for null.
     @throws Exception lança a exceção se a string tiver mais de 1 caracter.
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