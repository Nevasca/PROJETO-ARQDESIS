package to;

import java.io.Serializable;
import java.util.ArrayList;

public class PagamentoTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<PagamentoTO> lista;
	
	public PagamentoTO(){
		lista = new ArrayList<PagamentoTO>();
	}
	
	public void add(PagamentoTO pagamentoC){
		lista.add(pagamentoC);
	}
	
	public boolean remove(PagamentoTO pagamentoC){
		return(lista.remove(pagamentoC));
	}
	
	public ArrayList<PagamentoTO> getLista(){
		return lista;
	}
}
