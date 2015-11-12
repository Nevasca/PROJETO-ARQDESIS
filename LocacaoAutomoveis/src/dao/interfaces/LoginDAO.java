package dao.interfaces;

import to.LoginTO;
import model.GenericException;
import model.Login;

public interface LoginDAO{
	
	public LoginTO consultar() throws GenericException;

	public Login consultar(Login login) throws GenericException;

	public Login consultarNome(Login login) throws GenericException;
	
}
