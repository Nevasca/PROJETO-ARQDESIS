package dao.interfaces;

import to.RelatorioTO;
import model.GenericException;

public interface RelatorioDAO {

	public RelatorioTO consultar() throws GenericException;
	
	public RelatorioTO consultar(String dataReferencia) throws GenericException;
}
