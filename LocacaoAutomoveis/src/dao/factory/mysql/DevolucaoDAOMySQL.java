package dao.factory.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import to.DevolucaoTO;

import model.Devolucao;
import model.GenericException;
import dao.factory.MySQLDAOFactory;
import dao.interfaces.DevolucaoDAO;

public class DevolucaoDAOMySQL implements DevolucaoDAO {

	public boolean inserir(Devolucao devolucao) throws GenericException {
	
		String sql = "INSERT INTO TB_DEVOLUCAO" 
				+"	 ("
		        + "	   DATA_DEVOLUCAO_LOCACAO,"
				+ "    LOCAL_DEVOLUCAO_LOCACAO,"
		        + "    AGENCIA_DEVOLUCAO,"
		        + "    KM_RODADA_ATUAL,"
				+ "    ID_LOCACAO"
				+"    )"
				+ "	VALUES"
				+"	 ("
				+ "	    ?," 
				+ "		?,"
				+ "		?,"
				+ "		?,"
				+ "		?" 
				+ "	  )";

		Connection conn = null;
		PreparedStatement pst = null;
   		DateFormat data = new SimpleDateFormat("yyyy-MM-dd hh:mm");

		try {
			
			conn = MySQLDAOFactory.conectar();
			pst = conn.prepareStatement(sql);
			
   			pst.setString(1, data.format(devolucao.getDataDevolucaoCliente()));
			pst.setString(2, devolucao.getLocalDevolucao());
			pst.setString(3, devolucao.getAgenciaDevolucao());
			pst.setDouble(4, devolucao.getKmRodadaAtual());
			pst.setInt(5, devolucao.getNumeroLocacaoAutomovel());
			pst.execute();

			return true;
			
		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {

			MySQLDAOFactory.desconectar(pst,conn);

		}
		
		return false;

	}

	public DevolucaoTO consultar() throws GenericException {
		
		String sql = "SELECT D.ID_DEVOLUCAO," 
				+ "          D.DATA_DEVOLUCAO_LOCACAO,"
				+ "          D.LOCAL_DEVOLUCAO_LOCACAO,"
		        + "          D.AGENCIA_DEVOLUCAO,"
		        + "     	 D.KM_RODADA_ATUAL,"
				+ "    		 D.ID_LOCACAO"
				+ "     FROM TB_DEVOLUCAO D";

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		DevolucaoTO devTO = new DevolucaoTO();
   		DateFormat data = new SimpleDateFormat("yyyy-MM-dd hh:mm");

		try {

			conn = MySQLDAOFactory.conectar();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while (rs.next()) {
										
				Devolucao devolucao = new Devolucao();
				devolucao.setIDDevolucao(rs.getInt(1));

				try
				{
					devolucao.setDataDevolucaoCliente(data.parse(rs.getString(2)));
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}								 
				devolucao.setLocalDevolucao(rs.getString(3));
				devolucao.setAgenciaDevolucao(rs.getString(4));
				devolucao.setNumeroLocacaoAutomovel(rs.getInt(5));
				devolucao.setKmRodadaAtual(rs.getDouble(6));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {

			MySQLDAOFactory.desconectar(rs,pst,conn);

		}

		return devTO;
	}


	public 	Devolucao consultar(Devolucao devolucao)
			throws GenericException {
		
		String sql = "SELECT D.ID_DEVOLUCAO," 
				+ "          D.DATA_DEVOLUCAO_LOCACAO,"
				+ "          DLOCAL_DEVOLUCAO_LOCACAO,"
		        + "          D.AGENCIA_DEVOLUCAO,"
		        + "     	 D.KM_RODADA_ATUAL,"
				+ "    		 D.ID_LOCACAO"
				+ "     FROM TB_DEVOLUCAO D"
				+ "    WHERE D.ID_LOCACAO = ?";

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
   		DateFormat data = new SimpleDateFormat("yyyy-MM-dd hh:mm");

		try {

			conn = MySQLDAOFactory.conectar();
			pst = conn.prepareStatement(sql);
			pst.setInt(1,devolucao.getNumeroLocacaoAutomovel());
			rs = pst.executeQuery();
			
			while (rs.next()) {
				
				devolucao = new Devolucao();
				devolucao.setIDDevolucao(rs.getInt(1));
							      
				try
				{
					devolucao.setDataDevolucaoCliente(data.parse(rs.getString(2)));
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				devolucao.setLocalDevolucao(rs.getString(3));
				devolucao.setAgenciaDevolucao(rs.getString(4));
				devolucao.setNumeroLocacaoAutomovel(rs.getInt(5));
				devolucao.setKmRodadaAtual(rs.getDouble(6));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {

			MySQLDAOFactory.desconectar(rs,pst,conn);

		}

		return devolucao;
	}

}
