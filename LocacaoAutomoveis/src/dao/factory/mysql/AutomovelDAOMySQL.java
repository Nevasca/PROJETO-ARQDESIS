package dao.factory.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import to.AutomovelTO;
import model.Automovel;
import model.GenericException;
import dao.factory.MySQLDAOFactory;
import dao.interfaces.AutomovelDAO;

public class AutomovelDAOMySQL implements AutomovelDAO {

	@Override
	public boolean inserir(Automovel automovel) throws GenericException {
		String sql = "INSERT INTO tb_automovel (GRUPO_AUTOMOVEL, ACESSORIOS_AUTOMOVEL, NUM_CHASSI_AUTOMOVEL, "
				+ "NUM_PLACA_AUTOMOVEL, CIDADE_AUTOMOVEL, UF_AUTOMOVEL, KM_AUTOMOVEL, MODELO_AUTOMOVEL, "
				+ "FABRICANTE_AUTOMOVEL, TARIFA_AUTOMOVEL, TARIFA_KM_CONTROLADO,TP_STATUS) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = MySQLDAOFactory.conectar();
			ps = conn.prepareStatement(sql);
			ps.setString(1, automovel.getGruposAutomovel());
			ps.setInt(2, automovel.getAcessoriosAutomovel());
			ps.setString(3, automovel.getChassiAutomovel());
			ps.setString(4, automovel.getPlacaAutomovel());
			ps.setString(5, automovel.getCidadeAutomovel());
			ps.setString(6, automovel.getUFAutomovel());
			ps.setDouble(7, automovel.getKmRodadoAutomovel());
			ps.setString(8, automovel.getModeloAutomovel());
			ps.setString(9, automovel.getFabricanteAutomovel());
			ps.setDouble(10, automovel.getTarifaAutomovel());
			ps.setDouble(11, automovel.getTarifaControladaAutomovel());
			ps.setInt(12, automovel.getTPStatus());

			ps.execute();

			return true;

		} catch (SQLException sqe) {
			sqe.printStackTrace();
		} finally {
			
			MySQLDAOFactory.desconectar(ps,conn);

		}

		return false;
	}

	@Override
	public boolean alterar(Automovel auto) throws GenericException {
		String sql = "UPDATE tb_automovel SET GRUPO_AUTOMOVEL = ?, ACESSORIOS_AUTOMOVEL = ?, NUM_CHASSI_AUTOMOVEL = ?, "
				+ "NUM_PLACA_AUTOMOVEL = ?, CIDADE_AUTOMOVEL = ?, UF_AUTOMOVEL = ?, KM_AUTOMOVEL = ?, MODELO_AUTOMOVEL = ?, "
				+ "FABRICANTE_AUTOMOVEL = ?, TARIFA_AUTOMOVEL = ?, TARIFA_KM_CONTROLADO = ?, TP_STATUS = ? "
				+ "WHERE ID_AUTOMOVEL = ?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		boolean alterou = false;
		try
		{
			conn = MySQLDAOFactory.conectar();
			ps = conn.prepareStatement(sql);
			ps.setString(1,auto.getGruposAutomovel());
			ps.setInt(2, auto.getAcessoriosAutomovel());
			ps.setString(3, auto.getChassiAutomovel());
			ps.setString(4, auto.getPlacaAutomovel());
			ps.setString(5, auto.getCidadeAutomovel());
			ps.setString(6, auto.getUFAutomovel());
			ps.setDouble(7, auto.getKmRodadoAutomovel());
			ps.setString(8, auto.getModeloAutomovel());
			ps.setString(9, auto.getFabricanteAutomovel());
			ps.setDouble(10, auto.getTarifaAutomovel());
			ps.setDouble(11, auto.getTarifaControladaAutomovel());
			ps.setInt(12, auto.getTPStatus());
			ps.setInt(13, auto.getIDAutomovel());
			
			ps.execute();
			alterou = true;
			
		}
		catch(SQLException sqe)
		{
			sqe.printStackTrace();
		}
		finally 
		{			
			
		   MySQLDAOFactory.desconectar(ps,conn);

		}		
		return alterou;
	}

	@Override
	public boolean excluir(Automovel auto) throws GenericException {
		//String sql = "DELETE FROM tb_automovel WHERE ID_AUTOMOVEL = ? AND NOT EXISTS(SELECT L.ID_AUTOMOVEL FROM TB_LOCACAO L WHERE L.ID_AUTOMOVEL = ID_AUTOMOVEL)";
		String sql = "DELETE FROM tb_automovel WHERE ID_AUTOMOVEL = ?";

		Connection conn = null;
		PreparedStatement ps = null;
		
		try
		{
			conn = MySQLDAOFactory.conectar();
			ps = conn.prepareStatement(sql);
			ps.setInt(1,auto.getIDAutomovel());			
			
			ps.execute();
			return true;
			
		}
		catch(SQLException sqe)
		{
			sqe.printStackTrace();
		}
		finally 
		{			
			MySQLDAOFactory.desconectar(ps,conn);

		}
			
		return false;
	}
	
/*
	@Override
	public AutomovelTO consultar() throws GenericException {

		String sql = "SELECT ID_AUTOMOVEL, GRUPO_AUTOMOVEL, ACESSORIOS_AUTOMOVEL, NUM_CHASSI_AUTOMOVEL, "
				+ "NUM_PLACA_AUTOMOVEL, CIDADE_AUTOMOVEL, UF_AUTOMOVEL, KM_AUTOMOVEL, MODELO_AUTOMOVEL, "
				+ "FABRICANTE_AUTOMOVEL, TARIFA_AUTOMOVEL, TARIFA_KM_CONTROLADO, TP_STATUS "
				+ "FROM TB_AUTOMOVEL";

		// System.out.println(sql);

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		AutomovelTO autoTO = new AutomovelTO();

		try {
			conn = MySQLDAOFactory.conectar();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Automovel autoCon = new Automovel();
				autoCon.setIDAutomovel(rs.getInt(1));
				autoCon.setGruposAutomovel(rs.getString(2));
				autoCon.setAcessoriosAutomovel(rs.getInt(3));
				autoCon.setChassiAutomovel(rs.getString(4));
				autoCon.setPlacaAutomovel(rs.getString(5));
				autoCon.setCidadeAutomovel(rs.getString(6));
				autoCon.setUFAutomovel(rs.getString(7));
				autoCon.setKmRodadoAutomovel(rs.getDouble(8));
				autoCon.setModeloAutomovel(rs.getString(9));
				autoCon.setFabricanteAutomovel(rs.getString(10));
				autoCon.setTarifaAutomovel(rs.getDouble(11));
				autoCon.setTarifaControladaAutomovel(rs.getDouble(12));
				autoCon.setTPStatus(rs.getInt(13));
				autoTO.add(autoCon);
			}

		} catch (SQLException sqe) {
			sqe.printStackTrace();
		} finally {
			MySQLDAOFactory.desconectar(rs,ps,conn);

		}

		return autoTO;
	}*/

	@Override
	public AutomovelTO consultar(Automovel automovel) throws GenericException {
		// Strings auxiliares para formar a query de consulta e atribui null
		// para as comparações
		String sqlWhere = "";
		String sqlModelo = "";
		String sqlPlaca = "", sqlAndPlaca = "";
		String sqlChassi = "", sqlAndChassi = "";
		String sqlFabricante = "", sqlAndFabricante = "";
		String sqlCidade = "", sqlAndCidade = "";
		String sqlUf = "", sqlAndUf = "";
		String sqlGrupo = "", sqlAndGrupo = "";
		String sqlAcessorio = "", sqlAndAcessorio = "";

		// Vê quais campos foram preenchidos e vai criando a query com os
		// filtros
		if (automovel.getModeloAutomovel().length() != 0) {
			sqlModelo = "MODELO_AUTOMOVEL LIKE '%"
					+ automovel.getModeloAutomovel() + "%'";
			sqlWhere = " WHERE ";
		}

		if (automovel.getPlacaAutomovel().length() != 0) {
			sqlPlaca = "NUM_PLACA_AUTOMOVEL = '"
					+ automovel.getPlacaAutomovel() + "'";
			sqlWhere = " WHERE ";

			// Verifica se é preciso colocar um AND antes desse filtro
			if (sqlModelo.length() != 0) {
				sqlAndPlaca = " AND ";
			}
		}

		if (automovel.getChassiAutomovel().length() != 0) {
			sqlChassi = "NUM_CHASSI_AUTOMOVEL = '"
					+ automovel.getChassiAutomovel() + "'";
			sqlWhere = " WHERE ";

			if (sqlModelo.length() != 0 || sqlPlaca.length() != 0) {
				sqlAndChassi = " AND ";
			}
		}

		if (automovel.getFabricanteAutomovel().length() != 0) {
			sqlFabricante = "FABRICANTE_AUTOMOVEL LIKE '%"
					+ automovel.getFabricanteAutomovel() + "%'";
			sqlWhere = " WHERE ";

			if (sqlModelo.length() != 0 || sqlPlaca.length() != 0
					|| sqlChassi.length() != 0) {
				sqlAndFabricante = " AND ";
			}
		}

		if (automovel.getCidadeAutomovel().length() != 0) {
			sqlCidade = "CIDADE_AUTOMOVEL LIKE '%"
					+ automovel.getCidadeAutomovel() + "%'";
			sqlWhere = " WHERE ";

			if (sqlModelo.length() != 0 || sqlPlaca.length() != 0
					|| sqlChassi.length() != 0 || sqlFabricante.length() != 0) {
				sqlAndCidade = " AND ";
			}
		}

		if (automovel.getUFAutomovel() != null) {
			sqlUf = "UF_AUTOMOVEL = '" + automovel.getUFAutomovel() + "'";
			sqlWhere = " WHERE ";

			if (sqlModelo.length() != 0 || sqlPlaca.length() != 0
					|| sqlChassi.length() != 0 || sqlFabricante.length() != 0
					|| sqlCidade.length() != 0) {
				sqlAndUf = " AND ";
			}
		}

		if (automovel.getGruposAutomovel() != null) {
			sqlGrupo = "GRUPO_AUTOMOVEL = '" + automovel.getGruposAutomovel()
					+ "'";
			sqlWhere = " WHERE ";

			if (sqlModelo.length() != 0 || sqlPlaca.length() != 0
					|| sqlChassi.length() != 0 || sqlFabricante.length() != 0
					|| sqlCidade.length() != 0 || sqlUf.length() != 0) {
				sqlAndGrupo = " AND ";
			}
		}

		if (automovel.getAcessoriosAutomovel() != 0) {
			sqlAcessorio = "ACESSORIOS_AUTOMOVEL = "
					+ automovel.getAcessoriosAutomovel();
			sqlWhere = " WHERE ";

			if (sqlModelo.length() != 0 || sqlPlaca.length() != 0
					|| sqlChassi.length() != 0 || sqlFabricante.length() != 0
					|| sqlCidade.length() != 0 || sqlUf.length() != 0
					|| sqlGrupo.length() != 0) {
				sqlAndAcessorio = " AND ";
			}
		}

		// Where concatenado
		String whereCompleto = "" + sqlWhere + sqlModelo + sqlAndPlaca
				+ sqlPlaca + sqlAndChassi + sqlChassi + sqlAndFabricante;
		whereCompleto += sqlFabricante + sqlAndCidade + sqlCidade + sqlAndUf
				+ sqlUf + sqlAndGrupo + sqlGrupo;
		whereCompleto += sqlAndAcessorio + sqlAcessorio;

		String sql = "SELECT ID_AUTOMOVEL, GRUPO_AUTOMOVEL, ACESSORIOS_AUTOMOVEL, NUM_CHASSI_AUTOMOVEL, "
				+ "NUM_PLACA_AUTOMOVEL, CIDADE_AUTOMOVEL, UF_AUTOMOVEL, KM_AUTOMOVEL, MODELO_AUTOMOVEL, "
				+ "FABRICANTE_AUTOMOVEL, TARIFA_AUTOMOVEL, TARIFA_KM_CONTROLADO,TP_STATUS "
				+ "FROM TB_AUTOMOVEL" + whereCompleto;

		// System.out.println(sql);

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
        AutomovelTO autTO = new AutomovelTO();
        
		try {
			conn = MySQLDAOFactory.conectar();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				automovel = new Automovel();
				automovel.setIDAutomovel(rs.getInt(1));
				automovel.setGruposAutomovel(rs.getString(2));
				automovel.setAcessoriosAutomovel(rs.getInt(3));
				automovel.setChassiAutomovel(rs.getString(4));
				automovel.setPlacaAutomovel(rs.getString(5));
				automovel.setCidadeAutomovel(rs.getString(6));
				automovel.setUFAutomovel(rs.getString(7));
				automovel.setKmRodadoAutomovel(rs.getDouble(8));
				automovel.setModeloAutomovel(rs.getString(9));
				automovel.setFabricanteAutomovel(rs.getString(10));
				automovel.setTarifaAutomovel(rs.getDouble(11));
				automovel.setTarifaControladaAutomovel(rs.getDouble(12));
				automovel.setTPStatus(rs.getInt(13));
				autTO.add(automovel);
			}

		} catch (SQLException sqe) {
			sqe.printStackTrace();
		} finally {
			
			MySQLDAOFactory.desconectar(rs,ps,conn);
		}

		return autTO;
	}

	@Override
	public Automovel consultarPorID(Automovel automovel)
			throws GenericException {
		String sql = "SELECT ID_AUTOMOVEL, GRUPO_AUTOMOVEL, ACESSORIOS_AUTOMOVEL, NUM_CHASSI_AUTOMOVEL, "
				+ "NUM_PLACA_AUTOMOVEL, CIDADE_AUTOMOVEL, UF_AUTOMOVEL, KM_AUTOMOVEL, MODELO_AUTOMOVEL, "
				+ "FABRICANTE_AUTOMOVEL, TARIFA_AUTOMOVEL, TARIFA_KM_CONTROLADO,TP_STATUS "
				+ "FROM TB_AUTOMOVEL WHERE ID_AUTOMOVEL = ?";

		// System.out.println(sql);

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = MySQLDAOFactory.conectar();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, automovel.getIDAutomovel());
			rs = ps.executeQuery();

			// Se existir um automovel com esse id, completar com os outros
			// dados
			if (rs.next()) {
				automovel.setGruposAutomovel(rs.getString(2));
				automovel.setAcessoriosAutomovel(rs.getInt(3));
				automovel.setChassiAutomovel(rs.getString(4));
				automovel.setPlacaAutomovel(rs.getString(5));
				automovel.setCidadeAutomovel(rs.getString(6));
				automovel.setUFAutomovel(rs.getString(7));
				automovel.setKmRodadoAutomovel(rs.getDouble(8));
				automovel.setModeloAutomovel(rs.getString(9));
				automovel.setFabricanteAutomovel(rs.getString(10));
				automovel.setTarifaAutomovel(rs.getDouble(11));
				automovel.setTarifaControladaAutomovel(rs.getDouble(12));
				automovel.setTPStatus(rs.getInt(13));

			}
			// Se não existir, retornar null
			else {
				automovel = null;
			}

		} catch (SQLException sqe) {
			sqe.printStackTrace();
		} finally {
			
			MySQLDAOFactory.desconectar(rs,ps,conn);

		}

		return automovel;
	}

	@Override
	public AutomovelTO consultarModelo(Automovel automovel)
			throws GenericException {
		// O que fazer se o usuario nao digitou nada no campo?

		String sqlWhere = " WHERE MODELO_AUTOMOVEL LIKE '%" + automovel.getModeloAutomovel()
				+ "%' OR " + " FABRICANTE_AUTOMOVEL LIKE '%"
				+ automovel.getFabricanteAutomovel() + "%'";

		String sql = "SELECT ID_AUTOMOVEL, GRUPO_AUTOMOVEL, ACESSORIOS_AUTOMOVEL, NUM_CHASSI_AUTOMOVEL, "
				+ "NUM_PLACA_AUTOMOVEL, CIDADE_AUTOMOVEL, UF_AUTOMOVEL, KM_AUTOMOVEL, MODELO_AUTOMOVEL, "
				+ "FABRICANTE_AUTOMOVEL, TARIFA_AUTOMOVEL, TARIFA_KM_CONTROLADO, TP_STATUS "
				+ "FROM TB_AUTOMOVEL" + sqlWhere;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		AutomovelTO autoTO = new AutomovelTO();

		try {
			conn = MySQLDAOFactory.conectar();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Automovel autoCon = new Automovel();
				autoCon.setIDAutomovel(rs.getInt(1));
				autoCon.setGruposAutomovel(rs.getString(2));
				autoCon.setAcessoriosAutomovel(rs.getInt(3));
				autoCon.setChassiAutomovel(rs.getString(4));
				autoCon.setPlacaAutomovel(rs.getString(5));
				autoCon.setCidadeAutomovel(rs.getString(6));
				autoCon.setUFAutomovel(rs.getString(7));
				autoCon.setKmRodadoAutomovel(rs.getDouble(8));
				autoCon.setModeloAutomovel(rs.getString(9));
				autoCon.setFabricanteAutomovel(rs.getString(10));
				autoCon.setTarifaAutomovel(rs.getDouble(11));
				autoCon.setTarifaControladaAutomovel(rs.getDouble(12));
				autoCon.setTPStatus(rs.getInt(13));

				autoTO.add(autoCon);
			}

		} catch (SQLException sqe) {
			sqe.printStackTrace();
		} finally {
			MySQLDAOFactory.desconectar(rs,ps,conn);
		}

		return autoTO;
	}

	@Override
	public AutomovelTO consultarCidade(Automovel auto) throws GenericException {

		String sqlWhere = " WHERE CIDADE_AUTOMOVEL LIKE '%"
				+ auto.getCidadeAutomovel() + "%'";

		String sql = "SELECT ID_AUTOMOVEL, GRUPO_AUTOMOVEL, ACESSORIOS_AUTOMOVEL, NUM_CHASSI_AUTOMOVEL, "
				+ "NUM_PLACA_AUTOMOVEL, CIDADE_AUTOMOVEL, UF_AUTOMOVEL, KM_AUTOMOVEL, MODELO_AUTOMOVEL, "
				+ "FABRICANTE_AUTOMOVEL, TARIFA_AUTOMOVEL, TARIFA_KM_CONTROLADO, TP_STATUS "
				+ "FROM TB_AUTOMOVEL" + sqlWhere;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		AutomovelTO autoTO = new AutomovelTO();

		try {
			conn = MySQLDAOFactory.conectar();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Automovel autoCon = new Automovel();
				autoCon.setIDAutomovel(rs.getInt(1));
				autoCon.setGruposAutomovel(rs.getString(2));
				autoCon.setAcessoriosAutomovel(rs.getInt(3));
				autoCon.setChassiAutomovel(rs.getString(4));
				autoCon.setPlacaAutomovel(rs.getString(5));
				autoCon.setCidadeAutomovel(rs.getString(6));
				autoCon.setUFAutomovel(rs.getString(7));
				autoCon.setKmRodadoAutomovel(rs.getDouble(8));
				autoCon.setModeloAutomovel(rs.getString(9));
				autoCon.setFabricanteAutomovel(rs.getString(10));
				autoCon.setTarifaAutomovel(rs.getDouble(11));
				autoCon.setTarifaControladaAutomovel(rs.getDouble(12));
				autoCon.setTPStatus(rs.getInt(13));

				autoTO.add(autoCon);
			}

		} catch (SQLException sqe) {
			sqe.printStackTrace();
		} finally {
			
			MySQLDAOFactory.desconectar(rs,ps,conn);

		}

		return autoTO;

	}

	@Override
	public boolean alterarSTATUS(Automovel auto) throws GenericException {
		String sql = "UPDATE TB_AUTOMOVEL SET TP_STATUS = ?  WHERE ID_AUTOMOVEL = ?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try
		{
			conn = MySQLDAOFactory.conectar();
			ps = conn.prepareStatement(sql);
			ps.setInt(1,auto.getTPStatus());
			ps.setInt(2, auto.getIDAutomovel());
			
			ps.execute();
			return true;
			
		}
		catch(SQLException sqe)
		{
			sqe.printStackTrace();
		}
		finally 
		{			
			MySQLDAOFactory.desconectar(ps,conn);

		}		
		return false;
	}

}
