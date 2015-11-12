package model;

import java.util.Objects;
import to.AutomovelTO;

import dao.factory.DAOFactory;
import dao.interfaces.AutomovelDAO;

public class Automovel {

	private String gruposAutomovel, cidadeAutomovel, modeloAutomovel,
			placaAutomovel, ufAutomovel, fabricanteAutomovel, chassiAutomovel;
	private double kmRodadoAutomovel, tarifaControladaAutomovel,
			tarifaAutomovel;
	private int idAutomovel, acessoriosAutomovel, tpStatus;

	public int getIDAutomovel() {
		return idAutomovel;
	}

	public void setIDAutomovel(int idAutomovel) {
		this.idAutomovel = idAutomovel;
	}

	public String getGruposAutomovel() {
		return gruposAutomovel;
	}

	public void setGruposAutomovel(String gruposAutomovel) {
		this.gruposAutomovel = gruposAutomovel;
	}

	public String getChassiAutomovel() {
		return chassiAutomovel;
	}

	public void setChassiAutomovel(String chassiAutomovel) {
		this.chassiAutomovel = chassiAutomovel;
	}

	public String getPlacaAutomovel() {
		return placaAutomovel;
	}

	public void setPlacaAutomovel(String placaAutomovel) {
		this.placaAutomovel = placaAutomovel;
	}

	public String getModeloAutomovel() {
		return modeloAutomovel;
	}

	public void setModeloAutomovel(String modeloAutomovel) {
		this.modeloAutomovel = modeloAutomovel;
	}

	public String getCidadeAutomovel() {
		return cidadeAutomovel;
	}

	public void setCidadeAutomovel(String cidadeAutomovel) {
		this.cidadeAutomovel = cidadeAutomovel;
	}

	public String getUFAutomovel() {
		return ufAutomovel;
	}

	public void setUFAutomovel(String ufAutomovel) {
		this.ufAutomovel = ufAutomovel;
	}

	public double getKmRodadoAutomovel() {
		return kmRodadoAutomovel;
	}

	public void setKmRodadoAutomovel(double kmRodadoAutomovel) {
		this.kmRodadoAutomovel = kmRodadoAutomovel;
	}

	public String getFabricanteAutomovel() {
		return fabricanteAutomovel;
	}

	public void setFabricanteAutomovel(String fabricanteAutomovel) {
		this.fabricanteAutomovel = fabricanteAutomovel;
	}

	public double getTarifaAutomovel() {
		return tarifaAutomovel;
	}

	public void setTarifaAutomovel(double tarifaAutomovel) {
		this.tarifaAutomovel = tarifaAutomovel;
	}

	public double getTarifaControladaAutomovel() {
		return tarifaControladaAutomovel;
	}

	public void setTarifaControladaAutomovel(double tarifaControladaAutomovel) {
		this.tarifaControladaAutomovel = tarifaControladaAutomovel;
	}

	public int getAcessoriosAutomovel() {
		return acessoriosAutomovel;
	}

	public void setAcessoriosAutomovel(int acessoriosAutomovel) {
		this.acessoriosAutomovel = acessoriosAutomovel;
	}

	public int getTPStatus() {
		return tpStatus;
	}

	public void setTPStatus(int tpStatus) {
		this.tpStatus = tpStatus;
	}

	public String getNomegruposAutomovel() {
		String nome = null;

		switch (gruposAutomovel) {
		case "A":
			nome = gruposAutomovel + " - Econômico";
			break;
		case "C":
			nome = gruposAutomovel + " - Econômico com Ar";
			break;
		case "F":
			nome = gruposAutomovel + " - Intermediário";
			break;
		case "G":
			nome = gruposAutomovel + " - Intermediário Wagon Especial";
			break;
		case "H":
			nome = gruposAutomovel + " - Executivo";
			break;
		case "I":
			nome = gruposAutomovel + " - Utilitário";
			break;
		case "K":
			nome = gruposAutomovel + " - Executivo Luxo";
			break;
		case "M":
			nome = gruposAutomovel + " - Intermediário Wagon";
			break;
		case "N":
			nome = gruposAutomovel + " - Pick-up";
			break;
		case "P":
			nome = gruposAutomovel + " - 4 x 4 Especial";
			break;
		case "R":
			nome = gruposAutomovel + " - Minivan";
			break;
		case "U":
			nome = gruposAutomovel + " - Furgão";
			break;
		case "Y":
			nome = gruposAutomovel + " - Blindado";
			break;
		default:
			nome = "gruposAutomovel desconhecido";
			break;
		}

		return nome;
	}

	public String getNomeAcessorio() {
		String nome = null;

		switch (acessoriosAutomovel) {
		case 0:
			nome = "Não selecionado";
			break;
		case 1:
			nome = "Navegador GPS";
			break;
		case 2:
			nome = "Cadeira de Bebê";
			break;
		case 3:
			nome = "Motorista";
			break;
		default:
			nome = "Acessório desconhecido";
			break;
		}

		return nome;
	}

	public int hashCode() {

		int hash = 7;

		hash = 89 * hash + Objects.hashCode(this.getIDAutomovel());
		hash = 89 * hash + Objects.hashCode(this.getGruposAutomovel());
		hash = 89 * hash + Objects.hashCode(this.getChassiAutomovel());
		hash = 89 * hash + Objects.hashCode(this.getPlacaAutomovel());
		hash = 89 * hash + Objects.hashCode(this.getModeloAutomovel());
		hash = 89 * hash + Objects.hashCode(this.getCidadeAutomovel());
		hash = 89 * hash + Objects.hashCode(this.getUFAutomovel());
		hash = 89 * hash + Objects.hashCode(this.getKmRodadoAutomovel());
		hash = 89 * hash + Objects.hashCode(this.getFabricanteAutomovel());

		hash = 89 * hash + Objects.hashCode(this.getTarifaAutomovel());
		hash = 89 * hash
				+ Objects.hashCode(this.getTarifaControladaAutomovel());
		hash = 89 * hash + Objects.hashCode(this.getAcessoriosAutomovel());
		hash = 89 * hash + Objects.hashCode(this.getTPStatus());

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

		final Automovel other = (Automovel) obj;

		if (!Objects.equals(this.getIDAutomovel(), other.getIDAutomovel())) {
			return false;
		}
		if (!Objects.equals(this.getGruposAutomovel(),
				other.getGruposAutomovel())) {
			return false;
		}
		if (!Objects.equals(this.getChassiAutomovel(),
				other.getChassiAutomovel())) {
			return false;
		}
		if (!Objects
				.equals(this.getPlacaAutomovel(), other.getPlacaAutomovel())) {
			return false;
		}
		if (!Objects.equals(this.getModeloAutomovel(),
				other.getModeloAutomovel())) {
			return false;
		}

		if (!Objects.equals(this.getCidadeAutomovel(),
				other.getCidadeAutomovel())) {
			return false;
		}
		if (!Objects.equals(this.getUFAutomovel(), other.getUFAutomovel())) {
			return false;
		}
		if (!Objects.equals(this.getKmRodadoAutomovel(),
				other.getKmRodadoAutomovel())) {
			return false;
		}

		if (!Objects.equals(this.getFabricanteAutomovel(),
				other.getFabricanteAutomovel())) {
			return false;
		}

		if (!Objects.equals(this.getTarifaAutomovel(),
				other.getTarifaAutomovel())) {
			return false;
		}

		if (!Objects.equals(this.getTarifaControladaAutomovel(),
				other.getTarifaControladaAutomovel())) {
			return false;
		}

		if (!Objects.equals(this.getAcessoriosAutomovel(),
				other.getAcessoriosAutomovel())) {
			return false;
		}

		if (!Objects.equals(this.getTPStatus(), other.getTPStatus())) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return "Automovel [idAutomovel=" + idAutomovel + ", gruposAutomovel="
				+ gruposAutomovel + ", cidadeAutomovel=" + cidadeAutomovel
				+ ", modeloAutomovel=" + modeloAutomovel + ", placaAutomovel="
				+ placaAutomovel + ", ufAutomovel=" + ufAutomovel
				+ ",fabricanteAutomovel=" + fabricanteAutomovel
				+ ", acessoriosAutomovel=" + acessoriosAutomovel + ",tpStatus="
				+ tpStatus + ",kmRodadoAutomovel=" + kmRodadoAutomovel
				+ ", tarifaControladaAutomovel=" + tarifaControladaAutomovel
				+ ",tarifaAutomovel=" + tarifaAutomovel + ",chassiAutomovel="
				+ chassiAutomovel + "]";
	}

	/*------------------------- MÉTODOS----------------------------*/

	public boolean inserir() throws GenericException {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		AutomovelDAO aut = factory.getAutomovelDAO();
		return aut.inserir(this);
	}

	public boolean alterar() throws GenericException {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		AutomovelDAO dao = factory.getAutomovelDAO();
		return dao.alterar(this);
	}

	public boolean excluir() throws GenericException {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		AutomovelDAO dao = factory.getAutomovelDAO();
		return dao.excluir(this);
	}

	public AutomovelTO consultar() throws GenericException {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		AutomovelDAO dao = factory.getAutomovelDAO();
		return dao.consultar(this);
	}

	public Automovel consultarPorID()
			throws GenericException {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		AutomovelDAO dao = factory.getAutomovelDAO();
		return dao.consultarPorID(this);
	}

	public AutomovelTO consultarModelo()
			throws GenericException {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		AutomovelDAO dao = factory.getAutomovelDAO();
		return dao.consultarModelo(this);
	}

	public AutomovelTO consultarCidade()
			throws GenericException {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		AutomovelDAO dao = factory.getAutomovelDAO();
		return dao.consultarCidade(this);
	}

	public boolean alterarSTATUS() throws GenericException {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		AutomovelDAO dao = factory.getAutomovelDAO();
		return dao.alterarSTATUS(this);
	}

}
