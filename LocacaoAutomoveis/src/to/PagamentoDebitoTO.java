package to;

import java.io.Serializable;
import java.util.ArrayList;

import model.PagamentoDebito;

public class PagamentoDebitoTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<PagamentoDebito> lista;
	
	public PagamentoDebitoTO(){
		lista = new ArrayList<PagamentoDebito>();
	}
	
	public void add(PagamentoDebito pagamentoD){
		lista.add(pagamentoD);
	}
	
	public boolean remove(PagamentoDebito pagamentoD){
		return(lista.remove(pagamentoD));
	}
	
	public ArrayList<PagamentoDebito> getLista(){
		return lista;
	}
}
