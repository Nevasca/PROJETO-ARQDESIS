package dao.factory;

import java.sql.*;
import dao.factory.mysql.*;
import dao.interfaces.*;

public class MySQLDAOFactory extends DAOFactory {

	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/locautomoveisbeg";
	public static final String USER = "root";
	public static final String PASSWORD = "1234";

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public static Connection conectar() {

		Connection conn = null;

		try {

			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			System.out.println("Conexão realizada com sucesso");

		} catch (SQLException ex) {
			System.out.println("Problemas na conexão com o banco de dados.");

		}
		
		return conn;
	}

	public static void desconectar(ResultSet rs, Statement st, Connection conn) {

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
				System.out.println("Conexão finalizada com sucesso");

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Problemas no encerramento da conexão com o banco de dados. ");
			}
		}
	}

	public static void desconectar(Statement st, Connection conn) {
		desconectar(null, st, conn);
	}

	public static void desconectar(Connection conn) {
		desconectar(null, null, conn);
	}

	//----------------------------- METODOS DO MYSQL
	public AutomovelDAO getAutomovelDAO() {
		return new AutomovelDAOMySQL();
	}

	public ClienteDAO getClienteDAO() {
		return new ClienteDAOMySQL();
	}

	public DevolucaoDAO getDevolucaoDAO() {
		return new DevolucaoDAOMySQL();
	}

	public LocacaoDAO getLocacaoDAO() {
		return new LocacaoDAOMySQL();
	}

	public PagamentoDAO getPagamentoDAO() {
		return new PagamentoDAOMySQL();
	}

	public PagamentoDebitoDAO getPagamentoDebitoDAO() {
		return new PagamentoDebitoDAOMySQL();
	}

	public PagamentoCreditoDAO getPagamentoCreditoDAO() {
		return new PagamentoCreditoDAOMySQL();
	}

	public RelatorioDAO getRelatorioDAO() {
		return new RelatorioMySQL();
	}

	public LoginDAO getLoginDAO() {
		return new LoginDAOMySQL();
	}

}