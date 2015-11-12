package dao.interfaces;

import to.PagamentoDebitoTO;
import model.GenericException;
import model.PagamentoDebito;

public interface PagamentoDebitoDAO extends PagamentoDAO{
		
	public PagamentoDebito consultar(PagamentoDebito pagamento) throws GenericException;

	public PagamentoDebitoTO consultar() throws GenericException;
}
