package dao.interfaces;

import to.DevolucaoTO;
import model.Devolucao;
import model.GenericException;

public interface DevolucaoDAO {
	
    public boolean inserir(Devolucao devolucao) throws GenericException;
			
	public DevolucaoTO consultar() throws GenericException;
	
	public Devolucao consultar(Devolucao devolucao) throws GenericException;

}
