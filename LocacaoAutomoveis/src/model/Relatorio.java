package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import dao.factory.DAOFactory;
import dao.interfaces.RelatorioDAO;

import to.RelatorioTO;

public class Relatorio {

	private String modeloAutomovel, nomeCliente, cpfCliente, localDevolucao,
			dataPagamento;
	private double valorPago;

	public String getModeloAutomovel() {
		return modeloAutomovel;
	}

	public void setModeloAutomovel(String modeloAutomovel) {
		this.modeloAutomovel = modeloAutomovel;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getLocalDevolucao() {
		return localDevolucao;
	}

	public void setLocalDevolucao(String localDevolucao) {
		this.localDevolucao = localDevolucao;
	}

	public double getValorPago() {
		return valorPago;
	}

	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
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
		return "Relatorio [modeloAutomovel=" + modeloAutomovel
				+ ", nomeCliente=" + nomeCliente + ", cpfCliente=" + cpfCliente
				+ ", dataPagamento=" + dataPagamento + ",valorPago="
				+ valorPago + ", localDevolucao=" + localDevolucao + "]";
	}

	/*------------------------- MÉTODOS----------------------------*/
	
	public RelatorioTO consultar() throws GenericException {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		RelatorioDAO rel = factory.getRelatorioDAO();
		return rel.consultar();
	}

	public RelatorioTO consultar(String dataReferencia) throws GenericException {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		RelatorioDAO rel = factory.getRelatorioDAO();
		return rel.consultar(dataReferencia);
	}

}
