package dao.interfaces;

import model.GenericException;
import model.Pagamento;

public interface PagamentoDAO {
	
	public boolean inserir(Pagamento pagamento, int cliente, int locacao) throws GenericException;
		
}
