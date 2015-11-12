package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import dao.factory.DAOFactory;
import dao.interfaces.LocacaoDAO;

import to.LocacaoTO;

public class Locacao {
	
	private String localEmprestimoLocacao,localPrevistaDevolucaoLocacao,agenciaEmprestimoLocacao,agenciaPrevistaDevolucaoLocacao;	
	private Date dataEmprestimoLocacao,dataPrevistaDevolucaoLocacao;
	private Cliente cliente;
	private Automovel automovel;
	private int idLocacao,tipoTaxaEmprestarLocacao;
	
	public int getIDLocacao() 
	{
		return idLocacao;
	}
	
	public void setIDLocacao(int idLocacao) 
	{
		this.idLocacao = idLocacao;
	}	
	
	public Date getDataEmprestimoLocacao() 
	{
		return dataEmprestimoLocacao;
	}
	
	public void setDataEmprestimoLocacao(Date dataEmprestimoLocacao) 
	{
		this.dataEmprestimoLocacao = dataEmprestimoLocacao;
	}
	
	public Date getDataPrevistaDevolucaoLocacao() 
	{
		return dataPrevistaDevolucaoLocacao;
	}
	
	public void setDataPrevistaDevolucaoLocacao(Date dataPrevistaDevolucaoLocacao) 
	{
		this.dataPrevistaDevolucaoLocacao = dataPrevistaDevolucaoLocacao;
	}
	
	public int getTipoTaxaEmprestarLocacao() 
	{
		return tipoTaxaEmprestarLocacao;
	}
	
	public void setTipoTaxaEmprestarLocacao(int tipoTaxaEmprestarLocacao) 
	{
		this.tipoTaxaEmprestarLocacao = tipoTaxaEmprestarLocacao;
	}	
	
	public String getAgenciaEmprestimoLocacao() {
		return agenciaEmprestimoLocacao;
	}

	public void setAgenciaEmprestimoLocacao(String agenciaEmprestimoLocacao) {
		this.agenciaEmprestimoLocacao = agenciaEmprestimoLocacao;
	}

	public String getAgenciaPrevistaDevolucaoLocacao() {
		return agenciaPrevistaDevolucaoLocacao;
	}

	public void setAgenciaPrevistaDevolucaoLocacao(
			String agenciaPrevistaDevolucaoLocacao) {
		this.agenciaPrevistaDevolucaoLocacao = agenciaPrevistaDevolucaoLocacao;
	}
		
	public String getLocalEmprestimoLocacao() {
		return localEmprestimoLocacao;
	}

	public void setLocalEmprestimoLocacao(String localEmprestimoLocacao) {
		this.localEmprestimoLocacao = localEmprestimoLocacao;
	}

	public String getLocalPrevistaDevolucaoLocacao() {
		return localPrevistaDevolucaoLocacao;
	}

	public void setLocalPrevistaDevolucaoLocacao(
			String localPrevistaDevolucaoLocacao) {
		this.localPrevistaDevolucaoLocacao = localPrevistaDevolucaoLocacao;
	}
	
	public Cliente getCliente() 
	{
		return cliente;
	}
	
	public void setCliente(Cliente cliente) 
	{
		this.cliente = cliente;
	}
	
	public Automovel getAutomovel() 
	{
		return automovel;
	}
	
	public void setAutomovel(Automovel automovel) 
	{
		this.automovel = automovel;
	}
	
	public long getDiasLocado()
	{
		long dias = 0;
		
		long diferenca = dataPrevistaDevolucaoLocacao.getTime() - dataEmprestimoLocacao.getTime();
		dias = TimeUnit.DAYS.convert(diferenca, TimeUnit.MILLISECONDS);
		
		return dias;
	}
	
	public double getValorLocacao()
	{
		if(tipoTaxaEmprestarLocacao == 0)
		{
			return automovel.getTarifaAutomovel() * getDiasLocado();
		}
		
		return 0;
	}
		
	public String getNomeTipoTarifa()
	{
		if(tipoTaxaEmprestarLocacao == 0)
		{
			return "TARIFA SIMPLES";
		}
		
		return "TARIFA CONTROLADA";
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
	
	
	public int hashCode() {

		int hash = 7;

		hash = 89 * hash + Objects.hashCode(this.getIDLocacao());
		hash = 89 * hash + Objects.hashCode(this.getDataEmprestimoLocacao());
		hash = 89 * hash + Objects.hashCode(this.getDataPrevistaDevolucaoLocacao());
		hash = 89 * hash + Objects.hashCode(this.getTipoTaxaEmprestarLocacao());
		hash = 89 * hash + Objects.hashCode(this.getAgenciaEmprestimoLocacao());
		hash = 89 * hash + Objects.hashCode(this.getAgenciaPrevistaDevolucaoLocacao());
		hash = 89 * hash + Objects.hashCode(this.getLocalEmprestimoLocacao());
		hash = 89 * hash + Objects.hashCode(this.getLocalPrevistaDevolucaoLocacao());
		hash = 89 * hash + Objects.hashCode(this.getCliente());
		hash = 89 * hash + Objects.hashCode(this.getAutomovel());

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

		final Locacao other = (Locacao) obj;

		if (!Objects.equals(this.getIDLocacao(), other.getIDLocacao())) {
			return false;
		}
		if (!Objects.equals(this.getDataEmprestimoLocacao(),
				other.getDataEmprestimoLocacao())) {
			return false;
		}
		if (!Objects.equals(this.getDataPrevistaDevolucaoLocacao(),
				other.getDataPrevistaDevolucaoLocacao())) {
			return false;
		}
		if (!Objects
				.equals(this.getTipoTaxaEmprestarLocacao(), other.getTipoTaxaEmprestarLocacao())) {
			return false;
		}
		if (!Objects.equals(this.getAgenciaEmprestimoLocacao(),
				other.getAgenciaEmprestimoLocacao())) {
			return false;
		}
		if (!Objects.equals(this.getAgenciaPrevistaDevolucaoLocacao(),
				other.getAgenciaPrevistaDevolucaoLocacao())) {
			return false;
		}
		if (!Objects.equals(this.getLocalEmprestimoLocacao(),
				other.getAgenciaEmprestimoLocacao())) {
			return false;
		}
		if (!Objects.equals(this.getLocalPrevistaDevolucaoLocacao(),
				other.getAgenciaPrevistaDevolucaoLocacao())) {
			return false;
		}
		if (!Objects.equals(this.getCliente(),
				other.getCliente())) {
			return false;
		}
		if (!Objects.equals(this.getAutomovel(),
				other.getAutomovel())) {
			return false;
		}

		return true;
	}
	
	@Override
	public String toString() {
		return "Locacao [idLocacao="+ idLocacao +",localEmprestimoLocacao=" + localEmprestimoLocacao + ", localPrevistaDevolucaoLocacao=" + localPrevistaDevolucaoLocacao
				+ ", dataEmprestimoLocacao=" + dataEmprestimoLocacao + ", dataPrevistaDevolucaoLocacao=" + dataPrevistaDevolucaoLocacao + ",tipoTaxaEmprestarLocacao=" + tipoTaxaEmprestarLocacao 
				+ ", cliente=" + cliente + ",automovel=" + automovel +  ",agenciaEmprestimoLocacao=" + agenciaEmprestimoLocacao + ",agenciaPrevistaDevolucaoLocacao=" + agenciaPrevistaDevolucaoLocacao + "]";
		
	}

	/*------------------------- MÉTODOS----------------------------*/

	public int inserir() throws GenericException{
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		LocacaoDAO loc = factory.getLocacaoDAO();
		return loc.inserir(this);
	}
	  	
	public LocacaoTO consultar() throws GenericException{
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		LocacaoDAO loc = factory.getLocacaoDAO();
		return loc.consultar();
	}
	
	public LocacaoTO consultar(Locacao Locacao) throws GenericException{
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		LocacaoDAO loc = factory.getLocacaoDAO();
		return loc.consultar(Locacao);
	}
	
	public LocacaoTO consultarPorAutomovel() throws GenericException{
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		LocacaoDAO loc = factory.getLocacaoDAO();
		return loc.consultarPorAutomovel(this);
	}
	
	
	public LocacaoTO consultarPorDataLocacao() throws GenericException{
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		LocacaoDAO loc = factory.getLocacaoDAO();
		return loc.consultarPorDataLocacao(this);
	}
	
	
	public Locacao consultarPorID() throws GenericException{
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		LocacaoDAO loc = factory.getLocacaoDAO();
		return loc.consultarPorID(this);
	}
	

}
