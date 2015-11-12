package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/* DATA */
import org.joda.time.DateTime;
import org.joda.time.Days;

import to.DevolucaoTO;

import dao.factory.DAOFactory;
import dao.interfaces.DevolucaoDAO;

public class Devolucao {

	private String localDevolucao, agenciaDevolucao;
	private Date dataDevolucaoCliente;
	private int idDevolucao, numeroLocacaoAutomovel;
	public double KMRodadaAtual;

	public int getIDDevolucao() {
		return idDevolucao;
	}

	public void setIDDevolucao(int idDevolucao) {
		this.idDevolucao = idDevolucao;
	}

	public Date getDataDevolucaoCliente() {
		return dataDevolucaoCliente;
	}

	public void setDataDevolucaoCliente(Date dataDevolucao) {
		this.dataDevolucaoCliente = dataDevolucao;
	}

	public int getNumeroLocacaoAutomovel() {
		return numeroLocacaoAutomovel;
	}

	public void setNumeroLocacaoAutomovel(int numeroLocacaoAutomovel) {
		this.numeroLocacaoAutomovel = numeroLocacaoAutomovel;
	}

	public String getLocalDevolucao() {
		return localDevolucao;
	}

	public void setLocalDevolucao(String localDevolucao) {
		this.localDevolucao = localDevolucao;
	}

	public String getAgenciaDevolucao() {
		return this.agenciaDevolucao;
	}

	public void setAgenciaDevolucao(String agenciaDevolucao) {
		this.agenciaDevolucao = agenciaDevolucao;
	}

	public double getKmRodadaAtual() {
		return this.KMRodadaAtual;
	}

	public void setKmRodadaAtual(double KMRodadaAtual) {
		this.KMRodadaAtual = KMRodadaAtual;
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

	public double getValorMulta(Locacao loc, Devolucao dev) {

		double multaData = 0.01;
		double multaLocal = 4.00;
		double multaAgencia = 30.00;

		double valorTotalDevolucao = 0;
		int dias = 0;
				
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dataPrevistaDevolucao = "";	
		String dataDevolucao = "";	

		try{
			 dataPrevistaDevolucao = sdf.format(loc.getDataPrevistaDevolucaoLocacao());
			 dataDevolucao = sdf.format(dev.getDataDevolucaoCliente());	

		}catch(Exception ex){
			ex.printStackTrace();
		}

		if (dataDevolucao != dataPrevistaDevolucao) {

		    Date data1 = null;
			Date data2 = null;

			try {
				data1 = sdf.parse(dataDevolucao);
				data2 = sdf.parse(dataPrevistaDevolucao);

			} catch (ParseException e) {
				e.printStackTrace();
			}

			DateTime dt1 = new DateTime(data1);
			DateTime dt2 = new DateTime(data2);

			dias = Days.daysBetween(dt2, dt1).getDays();

			if (dias > 1 && dias <= 20) {
				valorTotalDevolucao += (multaData * dias);
			}

		}

		if (loc.getLocalPrevistaDevolucaoLocacao() != dev.getLocalDevolucao()) {

			valorTotalDevolucao += multaLocal * 15; /*
													 * DISTANCIA DA CIDADE A
													 * OUTRA É SEMPRE 15 - MEU
													 * CRITERIO
													 */
		}

		if (loc.getAgenciaPrevistaDevolucaoLocacao() != dev
				.getAgenciaDevolucao()) {

			valorTotalDevolucao += multaAgencia;

		}

		/* VERIFICA O TIPO DE TARIFA DO AUTOMOVEL */
      	int idAutomovel = loc.getAutomovel().getIDAutomovel();

		Automovel aut = new Automovel();
		aut.setIDAutomovel(idAutomovel);


			try {
				aut = aut.consultarPorID();
			} catch (GenericException e) {
				e.printStackTrace();
			}
		

		/* CONTROLADA */
		if (loc.getTipoTaxaEmprestarLocacao() == 1) {

			valorTotalDevolucao += (dev.getKmRodadaAtual() - aut
					.getKmRodadoAutomovel())
					* aut.getTarifaControladaAutomovel();
		}

		return valorTotalDevolucao;

	}

	public int hashCode() {

		int hash = 7;

		hash = 89 * hash + Objects.hashCode(this.getIDDevolucao());
		hash = 89 * hash + Objects.hashCode(this.getDataDevolucaoCliente());
		hash = 89 * hash + Objects.hashCode(this.getNumeroLocacaoAutomovel());
		hash = 89 * hash + Objects.hashCode(this.getLocalDevolucao());
		hash = 89 * hash + Objects.hashCode(this.getAgenciaDevolucao());
		hash = 89 * hash + Objects.hashCode(this.getKmRodadaAtual());

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

		final Devolucao other = (Devolucao) obj;

		if (!Objects.equals(this.getIDDevolucao(), other.getIDDevolucao())) {
			return false;
		}
		if (!Objects.equals(this.getDataDevolucaoCliente(),
				other.getDataDevolucaoCliente())) {
			return false;
		}
		if (!Objects.equals(this.getNumeroLocacaoAutomovel(),
				other.getNumeroLocacaoAutomovel())) {
			return false;
		}
		if (!Objects
				.equals(this.getLocalDevolucao(), other.getLocalDevolucao())) {
			return false;
		}
		if (!Objects.equals(this.getAgenciaDevolucao(),
				other.getAgenciaDevolucao())) {
			return false;
		}
		if (!Objects.equals(this.getKmRodadaAtual(), other.getKmRodadaAtual())) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return "Devolucao [idDevolucao=" + idDevolucao
				+ ",dataDevolucaoCliente=" + dataDevolucaoCliente
				+ ", localDevolucao=" + localDevolucao + ", agenciaDevolucao="
				+ agenciaDevolucao + ", numeroLocacaoAutomovel="
				+ numeroLocacaoAutomovel + ",KMRodadaAtual=" + KMRodadaAtual
				+ "]";
	}

	/*------------------------- MÉTODOS----------------------------*/

	public boolean inserir(Devolucao dev2) throws GenericException {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		DevolucaoDAO dev = factory.getDevolucaoDAO();
		return dev.inserir(dev2);
	}

	public DevolucaoTO consultarGeral() throws GenericException {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		DevolucaoDAO dev = factory.getDevolucaoDAO();
		return dev.consultar();
	}

	public Devolucao consultar() throws GenericException {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		DevolucaoDAO loc = factory.getDevolucaoDAO();
		return loc.consultar(this);
	}
}
