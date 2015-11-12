package model;

import dao.factory.DAOFactory;
import dao.interfaces.PagamentoDebitoDAO;
import to.PagamentoDebitoTO;

public class PagamentoDebito extends Pagamento {

	private String bancoDebito,telefone;
	private int numeroAgenciaDebito,numeroContaDebito;
    
	public int getNumeroAgenciaDebito() {
		return numeroAgenciaDebito;
	}

	public void setNumeroAgenciaDebito(int numeroAgenciaDebito) {
		this.numeroAgenciaDebito = numeroAgenciaDebito;
	}

	public int getNumeroContaDebito() {
		return numeroContaDebito;
	}

	public void setNumeroContaDebito(int numeroContaDebito) {
		this.numeroContaDebito = numeroContaDebito;
	}
	
	public String getBancoDebito() {
		return bancoDebito;
	}

	public void setBancoDebito(String bancoDebito) {
		this.bancoDebito = bancoDebito;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTelefone() {
		return telefone;
	}
	
	@Override
	public String toString() {
		return "PagamentoDebito [numeroAgenciaDebito=" + numeroAgenciaDebito 
				+ ", numeroContaDebito=" + numeroContaDebito + ", bancoDebito=" + bancoDebito 
				+ ", telefone=" + telefone + "]";
	}
	  
	/*------------------------- MÉTODOS----------------------------*/
	
	public PagamentoDebitoTO consultarGeral() throws GenericException{
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);	
		PagamentoDebitoDAO pag = factory.getPagamentoDebitoDAO();
		return pag.consultar();
	}
		
	public PagamentoDebito consultar() throws GenericException{
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);	
		PagamentoDebitoDAO pag = factory.getPagamentoDebitoDAO();
		return pag.consultar(this);
	}
			

}
