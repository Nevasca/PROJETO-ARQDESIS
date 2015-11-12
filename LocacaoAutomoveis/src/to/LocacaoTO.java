package to;

import java.io.Serializable;
import java.util.ArrayList;

import model.Locacao;

public class LocacaoTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Locacao> lista;
	
	public LocacaoTO(){
		lista = new ArrayList<Locacao>();
	}
	
	public void add(Locacao locacao){
		lista.add(locacao);
	}
	
	public boolean remove(Locacao locacao){
		return(lista.remove(locacao));
	}
	
	public ArrayList<Locacao> getLista(){
		return lista;
	}
}
