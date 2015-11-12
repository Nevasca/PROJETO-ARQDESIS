package dao.interfaces;

import to.AutomovelTO;
import model.Automovel;
import model.GenericException;

public interface AutomovelDAO {
	
	public boolean inserir(Automovel automovel) throws GenericException;
	
	public boolean alterar(Automovel automovel) throws GenericException;
	
	public boolean excluir(Automovel automovel) throws GenericException;
		
	public AutomovelTO consultar(Automovel automovel) throws GenericException;
	
	public Automovel consultarPorID(Automovel automovel) throws GenericException;
	
	public AutomovelTO consultarModelo(Automovel automovel) throws GenericException;
	
	public AutomovelTO consultarCidade(Automovel auto) throws GenericException;
	
	public boolean alterarSTATUS(Automovel automovel) throws GenericException;

}
