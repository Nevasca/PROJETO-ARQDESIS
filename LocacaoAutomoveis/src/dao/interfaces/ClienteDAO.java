package dao.interfaces;

import to.ClienteTO;
import model.Cliente;
import model.GenericException;

public interface ClienteDAO {
	
	public boolean inserir(Cliente cliente) throws GenericException;
	
	public boolean alterar(Cliente cliente) throws GenericException;
	
	public boolean excluir(Cliente cliente) throws GenericException;
	
	public ClienteTO consultar(Cliente cliente) throws GenericException;
	
	public ClienteTO consultar() throws GenericException;
	
	public Cliente consultarCliente(Cliente cliente) throws GenericException;
	
	public Cliente consultarPorID(Cliente cliente);

}
