package to;

import java.io.Serializable;
import java.util.ArrayList;

import model.Cliente;

public class ClienteTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Cliente> lista;
	
	public ClienteTO(){
		lista = new ArrayList<Cliente>();
	}
	
	public void add(Cliente cliente){
		lista.add(cliente);
	}
	
	public boolean remove(Cliente cliente){
		return(lista.remove(cliente));
	}
	
	public ArrayList<Cliente> getLista(){
		return lista;
	}
	
}
