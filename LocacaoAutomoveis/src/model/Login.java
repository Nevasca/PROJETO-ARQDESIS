package model;

import java.util.Objects;

import to.LoginTO;
import dao.factory.DAOFactory;
import dao.interfaces.LoginDAO;

public class Login {
	
	private String loginUsuario;
	private String senhaUsuario;
	private String nomeUsuario;
	private int idLogin,tipoUsuario;

	public int getIDLogin() {
		return idLogin;
	}

	public void setIDLogin(int idLogin) {
		this.idLogin = idLogin;
	}	
	
	public String getLoginUsuario() {
		return loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public int getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public int hashCode() {

		int hash = 7;

		hash = 89 * hash + Objects.hashCode(this.getIDLogin());
		hash = 89 * hash + Objects.hashCode(this.getLoginUsuario());
		hash = 89 * hash + Objects.hashCode(this.getSenhaUsuario());
		hash = 89 * hash + Objects.hashCode(this.getNomeUsuario());
		hash = 89 * hash + Objects.hashCode(this.getTipoUsuario());

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

		final Login other = (Login) obj;

		if (!Objects.equals(this.getIDLogin(), other.getIDLogin())) {
			return false;
		}
		if (!Objects.equals(this.getLoginUsuario(),
				other.getLoginUsuario())) {
			return false;
		}
		if (!Objects.equals(this.getSenhaUsuario(),
				other.getSenhaUsuario())) {
			return false;
		}
		if (!Objects
				.equals(this.getNomeUsuario(), other.getNomeUsuario())) {
			return false;
		}
		if (!Objects.equals(this.getTipoUsuario(),
				other.getTipoUsuario())) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		return "Login [idLogin="+ idLogin +",loginUsuario=" + loginUsuario + ", senhaUsuario=" + senhaUsuario
				+ ", nomeUsuario=" + nomeUsuario + ", tipoUsuario=" + tipoUsuario + "]";
		
	}

	/*------------------------- MÉTODOS----------------------------*/

	public Login consultarNome(Login login) throws GenericException{
 		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);		
 		LoginDAO log = factory.getLoginDAO();
 		return log.consultarNome(this);
 	}
 	
 	public Login consulta() throws GenericException{
 		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);		
 		LoginDAO log = factory.getLoginDAO();
 		return log.consultar(this);
 	}
 	
	public LoginTO consultar() throws GenericException{
 		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);		
 		LoginDAO log = factory.getLoginDAO();
 		return log.consultar();
 	}
	
}
