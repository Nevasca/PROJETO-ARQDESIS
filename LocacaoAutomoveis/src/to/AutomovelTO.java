package to;

import java.io.Serializable;
import java.util.ArrayList;

import model.Automovel;

public class AutomovelTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Automovel> lista;
	
	public AutomovelTO(){
		lista = new ArrayList<Automovel>();
	}
	
	public void add(Automovel automovel){
		lista.add(automovel);
	}
	
	public boolean remove(Automovel automovel){
		return(lista.remove(automovel));
	}
	
	public ArrayList<Automovel> getLista(){
		return lista;
	}
}
