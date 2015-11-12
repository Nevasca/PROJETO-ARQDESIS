package dao.interfaces;

import to.LocacaoTO;
import model.Locacao;
import model.GenericException;

public interface LocacaoDAO {
	
	public int inserir(Locacao locacao) throws GenericException;
	
	public Locacao consultarPorID(Locacao locacao) throws GenericException;
	
	public LocacaoTO consultar() throws GenericException;
	
	public LocacaoTO consultar(Locacao locacao) throws GenericException;

	public LocacaoTO consultarPorAutomovel(Locacao locacao) throws GenericException;

	public LocacaoTO consultarPorDataLocacao(Locacao locacao) throws GenericException;

}
