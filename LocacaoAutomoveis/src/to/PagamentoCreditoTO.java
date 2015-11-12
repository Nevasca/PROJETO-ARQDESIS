package to;

import java.io.Serializable;
import java.util.ArrayList;

import model.PagamentoCredito;

public class PagamentoCreditoTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<PagamentoCredito> lista;
	
	public PagamentoCreditoTO(){
		lista = new ArrayList<PagamentoCredito>();
	}
	
	public void add(PagamentoCredito pagamentoC){
		lista.add(pagamentoC);
	}
	
	public boolean remove(PagamentoCredito pagamentoC){
		return(lista.remove(pagamentoC));
	}
	
	public ArrayList<PagamentoCredito> getLista(){
		return lista;
	}
}
