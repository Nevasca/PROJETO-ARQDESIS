package dao.factory;

import dao.interfaces.*;

public abstract class DAOFactory {

	public static final int MYSQL = 0;
	public static final int POSTGREE = 1;

	public abstract AutomovelDAO getAutomovelDAO();

	public abstract ClienteDAO getClienteDAO();		

	public abstract DevolucaoDAO getDevolucaoDAO();
	
	public abstract LocacaoDAO getLocacaoDAO();
	
	public abstract PagamentoDAO getPagamentoDAO();
	
	public abstract PagamentoDebitoDAO getPagamentoDebitoDAO();
	
	public abstract PagamentoCreditoDAO getPagamentoCreditoDAO();
	
	public abstract RelatorioDAO getRelatorioDAO();
	
	public abstract LoginDAO getLoginDAO();

	
	public static DAOFactory getDAOFactory(int factory) {

		switch (factory) {
		case MYSQL:
			return new MySQLDAOFactory();
		case POSTGREE:
			return null;
		default:
			return null;
		}
	}

}