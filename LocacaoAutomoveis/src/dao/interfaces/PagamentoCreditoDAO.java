package dao.interfaces;

import to.PagamentoCreditoTO;
import model.GenericException;
import model.PagamentoCredito;

public interface PagamentoCreditoDAO extends PagamentoDAO {

	public PagamentoCredito consultar(PagamentoCredito pagamento)	throws GenericException;

	public PagamentoCreditoTO consultar() throws GenericException;
}
