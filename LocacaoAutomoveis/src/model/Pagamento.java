package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import dao.factory.DAOFactory;
import dao.interfaces.PagamentoDAO;

public class Pagamento {
	
	private String nomeTitular, cpfTitular;
	private Date dataPagamento;
	private double valorPagamento;
	private int idPagamento;

	public int getIDPagamento() {
		return idPagamento;
	}

	public void setIDPagamento(int idPagamento) {
		this.idPagamento = idPagamento;
	}

	public void setNomeTitular(String nomeTitular) {
		this.nomeTitular = nomeTitular;
	}

	public String getNomeTitular() {
		return nomeTitular;
	}

	public void setCPFCliente(String cpfTitular) {
		this.cpfTitular = cpfTitular;
	}

	public String getCPFCliente() {
		return cpfTitular;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setValorPagamento(Double valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	public double getValorPagamento() {
		return valorPagamento;
	}
	
	public String getDataFormatadaJAVA(java.sql.Date strDataSQL) {

		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		String data = null;

		try {
			data = formatador.format(strDataSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public java.sql.Date getDataFormatadaSQL(String strData) {

		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date data = null;

		try {
			data = formatador.parse(strData);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		java.sql.Date strDataSQL = new java.sql.Date(data.getTime());

		return strDataSQL;
	}
	
	@Override
	public String toString() {
		return "Pagamento [idPagamento="+ idPagamento +",nomeTitular=" + nomeTitular + ", cpfTitular=" + cpfTitular
				+ ", dataPagamento=" + dataPagamento + ", valorPagamento="
				+ valorPagamento + "]";
	}
	
	@Override 
	public int hashCode() {
		
		int hash = 7; 
		
		hash = 89 * hash + Objects.hashCode(this.getIDPagamento()); 
		hash = 89 * hash + Objects.hashCode(this.getNomeTitular()); 
		hash = 89 * hash + Objects.hashCode(this.getCPFCliente()); 
		hash = 89 * hash + Objects.hashCode(this.getDataPagamento()); 
		hash = 89 * hash + Objects.hashCode(this.getValorPagamento()); 

		return hash;
	}
	
	@Override
	public boolean equals(Object obj) { 
		
		if (obj == null) { 
			
			return false;
		} 
		
		if (getClass() != obj.getClass()) {
			
			return false; 
		}
		
		final Pagamento other = (Pagamento) obj;
		
		if (!Objects.equals(this.getIDPagamento(), other.getIDPagamento())) {return false;}
		if (!Objects.equals(this.getNomeTitular(), other.getNomeTitular())) {return false;}
		if (!Objects.equals(this.getCPFCliente(), other.getCPFCliente())) {return false;}
		if (!Objects.equals(this.getDataPagamento(), other.getDataPagamento())) {return false;}
		if (!Objects.equals(this.getValorPagamento(), other.getValorPagamento())) {return false;}

		return true;
	}
	

	/*------------------------- MÉTODOS----------------------------*/

	public boolean inserir(int codigoCliente,int codigo) throws GenericException{
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);	
		PagamentoDAO pag = factory.getPagamentoDAO();
		return pag.inserir(this,codigoCliente,codigo);
	}
	  	

}
