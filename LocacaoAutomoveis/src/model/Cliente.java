package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import to.ClienteTO;

import dao.factory.DAOFactory;
import dao.interfaces.ClienteDAO;

public class Cliente {

	private String nomeCliente, rgCliente, cpfCliente, generoCliente,
			ufCliente, emailCliente, CNHCliente, telCliente,
			registroCliente;
	private Date dataNascCliente,dataValidadeCNH;
	private int idCliente;

	public int getIDCliente() {
		return idCliente;
	}

	public void setIDCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getRgCliente() {
		return rgCliente;
	}

	public void setRgCliente(String rgCliente) {
		this.rgCliente = rgCliente;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getGeneroCliente() {
		return generoCliente;
	}

	public void setGeneroCliente(String generoCliente) {
		this.generoCliente = generoCliente;
	}

	public Date getDataNascCliente() {
		return dataNascCliente;
	}

	public void setDataNascCliente(Date dataNasc) {
		this.dataNascCliente = dataNasc;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public String getRegistroCliente() {
		return registroCliente;
	}

	public void setRegistroCliente(String registroCliente) {
		this.registroCliente = registroCliente;
	}

	public String getCNHCliente() {
		return CNHCliente;
	}

	public void setCNHCliente(String cnh) {
		CNHCliente = cnh;
	}

	public Date getDataValidadeCNH() {
		return dataValidadeCNH;
	}

	public void setDataValidadeCNH(Date dataCNH) {
		this.dataValidadeCNH = dataCNH;
	}

	public String getTelCliente() {
		return telCliente;
	}

	public void setTelCliente(String telCliente) {
		this.telCliente = telCliente;

	}

	public String getUfCliente() {
		return ufCliente;
	}

	public void setUfCliente(String ufCliente) {
		this.ufCliente = ufCliente;
	}
	
	public String getDataFormatadaJAVA(Date strData) {

		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		String data = null;

		try {
			data = formatador.format(strData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public int hashCode() {

		int hash = 7;

		hash = 89 * hash + Objects.hashCode(this.getIDCliente());
		hash = 89 * hash + Objects.hashCode(this.getNomeCliente());
		hash = 89 * hash + Objects.hashCode(this.getRgCliente());
		hash = 89 * hash + Objects.hashCode(this.getCpfCliente());
		hash = 89 * hash + Objects.hashCode(this.getGeneroCliente());
		hash = 89 * hash + Objects.hashCode(this.getDataNascCliente());
		hash = 89 * hash + Objects.hashCode(this.getEmailCliente());
		hash = 89 * hash + Objects.hashCode(this.getRegistroCliente());
		hash = 89 * hash + Objects.hashCode(this.getCNHCliente());

		hash = 89 * hash + Objects.hashCode(this.getDataValidadeCNH());
		hash = 89 * hash + Objects.hashCode(this.getTelCliente());
		hash = 89 * hash + Objects.hashCode(this.getUfCliente());

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

		final Cliente other = (Cliente) obj;

		if (!Objects.equals(this.getIDCliente(), other.getIDCliente())) {
			return false;
		}
		if (!Objects.equals(this.getNomeCliente(),
				other.getNomeCliente())) {
			return false;
		}
		if (!Objects.equals(this.getRgCliente(),
				other.getRgCliente())) {
			return false;
		}
		if (!Objects
				.equals(this.getCpfCliente(), other.getCpfCliente())) {
			return false;
		}
		if (!Objects.equals(this.getGeneroCliente(),
				other.getGeneroCliente())) {
			return false;
		}
		
		if (!Objects.equals(this.getDataNascCliente(),
				other.getDataNascCliente())) {
			return false;
		}
		if (!Objects.equals(this.getEmailCliente(),
				other.getEmailCliente())) {
			return false;
		}
		if (!Objects.equals(this.getRegistroCliente(),
				other.getRegistroCliente())) {
			return false;
		}
			
		
		if (!Objects.equals(this.getCNHCliente(),
				other.getCNHCliente())) {
			return false;
		}
		
		if (!Objects.equals(this.getDataValidadeCNH(),
				other.getDataValidadeCNH())) {
			return false;
		}
		
		if (!Objects.equals(this.getTelCliente(),
				other.getTelCliente())) {
			return false;
		}
		
		if (!Objects.equals(this.getUfCliente(),
				other.getUfCliente())) {
			return false;
		}
		
		
		return true;
	}
	
	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nomeCliente="
				+ nomeCliente + ", rgCliente=" + rgCliente + ", cpfCliente="
				+ cpfCliente + ", ufCliente=" + ufCliente + ", emailCliente="
				+ emailCliente + ",generoCliente=" + generoCliente
				+ ", CNHCliente=" + CNHCliente + ",dataNascCliente="
				+ dataNascCliente + ",telCliente=" + telCliente
				+ ",dataValidadeCNH=" + dataValidadeCNH + ",registroCliente="
				+ registroCliente + "]";

	}
		
	/*------------------------- MÉTODOS----------------------------*/

	public boolean inserir() throws GenericException {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ClienteDAO cli = factory.getClienteDAO();
		return cli.inserir(this);
	}

	public boolean alterar() throws GenericException {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ClienteDAO cli = factory.getClienteDAO();
		return cli.alterar(this);
	}

	public boolean excluir() throws GenericException {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ClienteDAO cli = factory.getClienteDAO();
		return cli.excluir(this);
	}

	public ClienteTO consultar() throws GenericException {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ClienteDAO cli = factory.getClienteDAO();
		return cli.consultar(this);
	}
	
	public ClienteTO consultarGeral() throws GenericException {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ClienteDAO cli = factory.getClienteDAO();
		return cli.consultar();
	}

	public Cliente consultarPorID() throws GenericException {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ClienteDAO cli = factory.getClienteDAO();
		return cli.consultarPorID(this);
	}
	
	public Cliente consultarCliente() throws GenericException {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ClienteDAO cli = factory.getClienteDAO();
		return cli.consultarCliente(this);
	}
	

	
}
