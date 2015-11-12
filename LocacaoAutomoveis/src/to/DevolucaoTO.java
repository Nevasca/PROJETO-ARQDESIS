package to;

import java.io.Serializable;
import java.util.ArrayList;

import model.Devolucao;

public class DevolucaoTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Devolucao> lista;
	
	public DevolucaoTO(){
		lista = new ArrayList<Devolucao>();
	}
	
	public void add(Devolucao devolucao){
		lista.add(devolucao);
	}
	
	public boolean remove(Devolucao devolucao){
		return(lista.remove(devolucao));
	}
	
	public ArrayList<Devolucao> getLista(){
		return lista;
	}
}
