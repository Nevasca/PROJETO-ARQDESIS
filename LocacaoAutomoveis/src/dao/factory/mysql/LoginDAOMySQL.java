package dao.factory.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import to.LoginTO;

import model.GenericException;
import model.Login;
import dao.factory.MySQLDAOFactory;
import dao.interfaces.LoginDAO;

public class LoginDAOMySQL implements LoginDAO {

	public LoginTO consultar() throws GenericException {

		String sql = "SELECT L.ID_LOGIN,"
				+ "          L.NM_LOGIN, "
				+ "          L.NM_SENHA," 
				+ "          L.NM_USUARIO,"
				+ "		     L.TP_USUARIO"
				+ "  FROM TB_LOGIN L";

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		LoginTO loginTO = new LoginTO();
		
		try {

			conn = MySQLDAOFactory.conectar();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {

				Login login = new Login();
				login.setIDLogin(rs.getInt(1));
				login.setLoginUsuario(rs.getString(2));
				login.setSenhaUsuario(rs.getString(3));
				login.setNomeUsuario(rs.getString(4));
				login.setTipoUsuario(rs.getInt(5));
				loginTO.add(login);
			}
			
			
		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {

			MySQLDAOFactory.desconectar(rs,pst,conn);

		}

		return loginTO;
	}

	public Login consultar(Login login) throws GenericException {

		String sql = "SELECT L.ID_LOGIN," 
				+ "          L.NM_LOGIN, "
				+ "          L.NM_SENHA,"
				+ "          L.NM_USUARIO,"
				+ "		     L.TP_USUARIO"
				+ "  FROM TB_LOGIN L"
				+ " WHERE L.NM_LOGIN = ?"
				+ "   AND L.NM_SENHA = ?";

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			conn = MySQLDAOFactory.conectar();
			pst = conn.prepareStatement(sql);
			pst.setString(1, login.getLoginUsuario());
			pst.setString(2, login.getSenhaUsuario());
			rs = pst.executeQuery();

			while (rs.next()) {

				login = new Login();
				login.setIDLogin(rs.getInt(1));
				login.setLoginUsuario(rs.getString(2));
				login.setSenhaUsuario(rs.getString(3));
				login.setNomeUsuario(rs.getString(4));
				login.setTipoUsuario(rs.getInt(5));

			}
		
		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {

			MySQLDAOFactory.desconectar(rs,pst,conn);

		}

		return login;
	}

	public Login consultarNome(Login login) throws GenericException {

		String sql = "SELECT L.ID_LOGIN,"
				+ "          L.NM_LOGIN, "
				+ "          L.NM_SENHA," 
				+ "          L.NM_USUARIO,"
				+ "		     L.TP_USUARIO"
				+ "  FROM TB_LOGIN L"
				+ " WHERE L.NM_USUARIO = ?";
				
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {

			conn = MySQLDAOFactory.conectar();
			pst = conn.prepareStatement(sql);
			pst.setString(1, login.getNomeUsuario());
			rs = pst.executeQuery();

			if(rs.first()){
							
				login.setIDLogin(rs.getInt(1));
				login.setLoginUsuario(rs.getString(2));
				login.setSenhaUsuario(rs.getString(3));
				login.setNomeUsuario(rs.getString(4));
				login.setTipoUsuario(rs.getInt(5));							
			}						
			
		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {

			MySQLDAOFactory.desconectar(rs,pst,conn);

		}

		return login;
	}

}
