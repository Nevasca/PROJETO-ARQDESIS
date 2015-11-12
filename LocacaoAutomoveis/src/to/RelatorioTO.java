package to;

import java.io.Serializable;
import java.util.ArrayList;

import model.Relatorio;

public class RelatorioTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Relatorio> lista;
	
	public RelatorioTO(){
		lista = new ArrayList<Relatorio>();
	}
	
	public void add(Relatorio relatorio){
		lista.add(relatorio);
	}
	
	public boolean remove(Relatorio relatorio){
		return(lista.remove(relatorio));
	}
	
	public ArrayList<Relatorio> getLista(){
		return lista;
	}
}
