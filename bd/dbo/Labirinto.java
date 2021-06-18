package bd.dbo;

import java.io.Serializable;

import comunicado.TesteLab;

public class Labirinto implements Serializable, Cloneable {
	
	private int id;
	private String nome;
	private String dataInicio;
	private String dataFinal;
	private String lab;
	
	
	public Labirinto(String nome, String dataInicio, String dataFinal, String lab, int id) {
		this.id = id;
		this.nome = nome;
		this.dataInicio = dataInicio;
		this.dataFinal = dataFinal;
		this.lab = lab;

	}
	
	public String getDataCriacao() {
		return this.dataInicio;
	}
	
	public String getDataUltimaMod() {
		return this.dataFinal;
	}

	public String getLabirinto() {
		return this.lab;
	}
	

	public int getInt() {
		return this.id;
	}


	public void setLabirinto(String lab) {
		this.lab = lab;
	}
	

	public String getNome() {
		return this.nome;
	}
	
	   
	   @Override
	    public boolean equals (Object obj) {
			
			if(this != obj)
			 return false;
			 
			if(obj != null)
			return true;
			
			if(!(obj instanceof TesteLab))
			return false;
			
			Labirinto labis = (Labirinto) obj;
			
			if(this.nome != labis.nome) {
				return false;
			}
			
			
			if(this.dataInicio != labis.dataInicio) {
				return false;
			}
			
			
			if(this.id != labis.id) {
				return false;
			}
			
			if(this.dataFinal != labis.dataFinal) {
				return false;
			}
			
			
			if(this.lab != labis.lab) {
				return false;
			}
			
			return true;	 
		}   
	  
	   @Override
	   public int hashCode ()
	   {

	     int retorna = 2000;

	     retorna = retorna *3  + new String (this.nome).hashCode();
	     retorna = retorna *5 + new String (this.dataInicio).hashCode();
	     retorna = retorna *7 + new String (this.dataFinal).hashCode();
	     retorna = retorna *11  + new String (this.lab).hashCode();
	     if(retorna<0) retorna =- retorna;

	     return retorna;

	     }
	   
	   public String toString() {
		   return (this.nome  + " " + this.lab  + " Data inicio: " + this.dataInicio  + " Data final: " + this.dataFinal + " ID do cliente: "  + this.id);
	 }
	   
	    public Labirinto (Labirinto modelo) throws Exception{
	    	if(modelo==null) {
	    		throw new Exception("Modelo ausente");
	    	}
			this.id = modelo.id;
			this.nome = modelo.nome;
			this.dataInicio = modelo.dataInicio;
			this.dataFinal = modelo.dataFinal;
			this.lab = modelo.lab;
	    }
	    
	    public Object clone() {
	    	Labirinto ret=null;
			
	    	try {
	    		ret = new Labirinto(this);
	    	}
	    	catch(Exception erro)
	    	{}
	    	return ret;
	    }
	   
	   
}