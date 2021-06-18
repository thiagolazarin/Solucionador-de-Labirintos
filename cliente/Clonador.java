package cliente;

import java.lang.reflect.*;

   /**
   A classe Clonador faz a c�pia de um objeto, n�o fazendo a mesma refer�ncia de 
   mem�ria, e serve para qualquer objeto.
   @author Lucas, Thiago e Ruan.
   @since 2021.
   */

public class Clonador<X>
{
     /**
    Clone de um objeto.
    Inicialmente, armazenamos o par�metro em um objeto chamado classe, logo ap�s
    atribu�mos null a tpsParmsForms, pois � um m�todo sem par�metros, e tentamos
    atribuir a vari�vel ret ao m�todo invoke. Como sabemos que n�o h� possibilidade de     erros, n�o h� nada escrito dentro das chaves. Por �ltimo � retornado o objeto clonado.
    @param x � o elemento a ser clonado.
    @return o elemento clonado.
    */
    public X clone (X x)
    {        
        Class<?> classe = x.getClass();
        
        Class<?>[] tpsParmsForms = null;
        
        Method metodo=null;
        try
        {
            metodo = classe.getMethod ("clone",tpsParmsForms);
        }
        catch (NoSuchMethodException erro)
        {}
        
        Object[] parmsReais = null;
                
        X ret=null;
        try
        {
            ret = (X)metodo.invoke(x,parmsReais);
        }
        catch (InvocationTargetException erro)
        {}
        catch (IllegalAccessException erro)
        {}

        return ret;
    }
}