package dao.factory.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import to.LocacaoTO;
import model.Automovel;
import model.Cliente;
import model.GenericException;
import model.Locacao;
import dao.factory.MySQLDAOFactory;
import dao.interfaces.LocacaoDAO;

public class LocacaoDAOMySQL implements LocacaoDAO {

	@Override
	public int inserir(Locacao loc) throws GenericException {
	 	 String sql = "INSERT INTO TB_LOCACAO " +
				   "			(   "+
				   "			    DATA_LOCACAO, " +
				   "				DATA_DEVOLUCAO_LOCACAO,"+
				   "				AGENCIA_LOCACAO,"+
				   "				AGENCIA_DEVOLUCAO_LOCACAO,"+			
				   " 				LOCAL_EMPRESTIMO_LOCACAO,"+
				   "				LOCAL_DEVOLUCAO_LOCACAO, "+
				   "				TIPO_TARIFA_LOCACAO, " +
				   "				ID_CLIENTE , "+
				   "				ID_AUTOMOVEL"+
				   "			) "+
				   "		VALUES" +
				   "		    (" +
				   "			   ?,"+
				   "			   ?,"+
				   "			   ?,"+
				   "			   ?,"+
				   "			   ?,"+
				   "			   ?,"+
				   "			   ?,"+
				   "			   ?,"+
				   "			   ?"+
				   "			)";
	   		
	   		Connection conn = null;
	   		PreparedStatement ps = null;
	   		ResultSet rs = null;
	   		DateFormat data = new SimpleDateFormat("yyyy-MM-dd hh:mm");
	   		int idLocacao = 0;

	   		try
	   		{
	   			conn = MySQLDAOFactory.conectar();
	   			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	   			ps.setString(1, data.format(loc.getDataEmprestimoLocacao()));
	   			ps.setString(2, data.format(loc.getDataPrevistaDevolucaoLocacao()));
	   			ps.setString(3, loc.getAgenciaEmprestimoLocacao());
	   			ps.setString(4, loc.getAgenciaPrevistaDevolucaoLocacao());
	   			ps.setString(5, loc.getLocalEmprestimoLocacao());
	   			ps.setString(6, loc.getLocalPrevistaDevolucaoLocacao());
	   			ps.setInt(7, loc.getTipoTaxaEmprestarLocacao());
	   			ps.setInt(8, loc.getCliente().getIDCliente());
	   			ps.setInt(9, loc.getAutomovel().getIDAutomovel());
	   			
	   			ps.execute();
	   			
	   			rs = ps.getGeneratedKeys();
	   			if(rs != null && rs.next())
	   			{
	   				idLocacao = rs.getInt(1);
	   			}
	   			
	   		}
	   		catch(SQLException sqe)
	   		{
	   			sqe.printStackTrace();
	   		}
	   		finally 
	   		{			
	   			MySQLDAOFactory.desconectar(rs,ps,conn);
	   		
	   		}
	   			
	   		return idLocacao;
	}

	@Override
	public Locacao consultarPorID(Locacao locacao) throws GenericException {
		String sql = "SELECT ID_LOCACAO, DATA_LOCACAO, DATA_DEVOLUCAO_LOCACAO, AGENCIA_LOCACAO, "
				+ "AGENCIA_DEVOLUCAO_LOCACAO, LOCAL_EMPRESTIMO_LOCACAO, LOCAL_DEVOLUCAO_LOCACAO, "
				+ "TIPO_TARIFA_LOCACAO, ID_CLIENTE, ID_AUTOMOVEL "
				+ "FROM TB_LOCACAO WHERE ID_LOCACAO = ?";		
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Locacao loc = null;

		try
		{
			conn = MySQLDAOFactory.conectar();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, locacao.getIDLocacao());
			rs = ps.executeQuery();
			
			//Se existir um automovel com esse id, completar com os outros dados
			if(rs.next())
			{
				
				loc = new Locacao();
				loc.setIDLocacao(rs.getInt(1));
				
				try
				{
					loc.setDataEmprestimoLocacao(f.parse(rs.getString(2)));
					loc.setDataPrevistaDevolucaoLocacao(f.parse(rs.getString(3)));
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				loc.setAgenciaEmprestimoLocacao(rs.getString(4));
				loc.setAgenciaPrevistaDevolucaoLocacao(rs.getString(5));
				loc.setLocalEmprestimoLocacao(rs.getString(6));
				loc.setLocalPrevistaDevolucaoLocacao(rs.getString(7));
				loc.setTipoTaxaEmprestarLocacao(rs.getInt(8));
				
				Cliente cli = new Cliente();
				cli.setIDCliente(rs.getInt(9));				
				loc.setCliente(cli);
				
				Automovel auto = new Automovel();
				auto.setIDAutomovel(rs.getInt(10));
				loc.setAutomovel(auto);
			}
			//Se não existir, retornar null
			else
			{
				loc = null;
			}
			
		}
		catch(SQLException sqe)
		{
			sqe.printStackTrace();
		}
		finally 
		{	
			MySQLDAOFactory.desconectar(rs,ps,conn);
		}

		
		return loc;
	}

	public LocacaoTO consultar() throws GenericException {
		
		String sql = "SELECT ID_LOCACAO, DATA_LOCACAO, DATA_DEVOLUCAO_LOCACAO, AGENCIA_LOCACAO, "
				+ "AGENCIA_DEVOLUCAO_LOCACAO, LOCAL_EMPRESTIMO_LOCACAO, LOCAL_DEVOLUCAO_LOCACAO, "
				+ "TIPO_TARIFA_LOCACAO, ID_CLIENTE, ID_AUTOMOVEL "
				+ "FROM TB_LOCACAO";			

	
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		LocacaoTO locTO = new LocacaoTO();
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		
		try {

			conn = MySQLDAOFactory.conectar();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while(rs.next()){
		
				Locacao loc = new Locacao();
				loc.setIDLocacao(rs.getInt(1));
				
				try
				{
					loc.setDataEmprestimoLocacao(f.parse(rs.getString(2)));
					loc.setDataEmprestimoLocacao(f.parse(rs.getString(3)));
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				loc.setAgenciaEmprestimoLocacao(rs.getString(4));
				loc.setAgenciaPrevistaDevolucaoLocacao(rs.getString(5));
				loc.setLocalEmprestimoLocacao(rs.getString(6));
				loc.setLocalPrevistaDevolucaoLocacao(rs.getString(7));
				loc.setTipoTaxaEmprestarLocacao(rs.getInt(8));
				
				Cliente cli = new Cliente();
				cli.setIDCliente(rs.getInt(9));				
				loc.setCliente(cli);
				
				Automovel auto = new Automovel();
				auto.setIDAutomovel(rs.getInt(10));
				
				loc.setAutomovel(auto);
				locTO.add(loc);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {

			MySQLDAOFactory.desconectar(rs,pst,conn);

		}

		return locTO;
	}

	@Override
	public LocacaoTO consultar(Locacao locacao) throws GenericException {
		
		String sql = "SELECT ID_LOCACAO, DATA_LOCACAO, DATA_DEVOLUCAO_LOCACAO, AGENCIA_LOCACAO, "
				+ "AGENCIA_DEVOLUCAO_LOCACAO, LOCAL_EMPRESTIMO_LOCACAO, LOCAL_DEVOLUCAO_LOCACAO, "
				+ "TIPO_TARIFA_LOCACAO, ID_CLIENTE, ID_AUTOMOVEL "
				+ "FROM TB_LOCACAO"			
		        + "WHERE ID_LOCACAO ='" + locacao.getIDLocacao()  +"'";			

	
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		LocacaoTO locTO = new LocacaoTO();
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		
		try {

			conn = MySQLDAOFactory.conectar();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while(rs.next()){
		
				Locacao loc = new Locacao();
				loc.setIDLocacao(rs.getInt(1));
				
				try
				{
					loc.setDataEmprestimoLocacao(f.parse(rs.getString(2)));
					loc.setDataEmprestimoLocacao(f.parse(rs.getString(3)));
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				loc.setAgenciaEmprestimoLocacao(rs.getString(4));
				loc.setAgenciaPrevistaDevolucaoLocacao(rs.getString(5));
				loc.setLocalEmprestimoLocacao(rs.getString(6));
				loc.setLocalPrevistaDevolucaoLocacao(rs.getString(7));
				loc.setTipoTaxaEmprestarLocacao(rs.getInt(8));
				
				Cliente cli = new Cliente();
				cli.setIDCliente(rs.getInt(9));				
				loc.setCliente(cli);
				
				Automovel auto = new Automovel();
				auto.setIDAutomovel(rs.getInt(10));
				
				loc.setAutomovel(auto);
				locTO.add(loc);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {

			MySQLDAOFactory.desconectar(rs,pst,conn);

		}

		return locTO;
	}

	@Override
	public LocacaoTO consultarPorAutomovel(Locacao locacao)
			throws GenericException {
		String sql = "SELECT ID_LOCACAO, DATA_LOCACAO, DATA_DEVOLUCAO_LOCACAO, AGENCIA_LOCACAO, "
				+ "AGENCIA_DEVOLUCAO_LOCACAO, LOCAL_EMPRESTIMO_LOCACAO, LOCAL_DEVOLUCAO_LOCACAO, "
				+ "TIPO_TARIFA_LOCACAO, ID_CLIENTE, ID_AUTOMOVEL "
				+ "FROM TB_LOCACAO WHERE ID_AUTOMOVEL = ?";		
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		LocacaoTO locTO = new LocacaoTO();
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		
		try
		{
			conn = MySQLDAOFactory.conectar();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, locacao.getAutomovel().getIDAutomovel());
			rs = ps.executeQuery();
			
			//Se existir um automovel com esse id, completar com os outros dados
			while(rs.next())
			{
				Locacao loc = new Locacao();
				loc.setIDLocacao(rs.getInt(1));
				
				try
				{
					loc.setDataEmprestimoLocacao(f.parse(rs.getString(2)));
					loc.setDataPrevistaDevolucaoLocacao(f.parse(rs.getString(3)));
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				loc.setAgenciaEmprestimoLocacao(rs.getString(4));
				loc.setAgenciaPrevistaDevolucaoLocacao(rs.getString(5));
				loc.setLocalEmprestimoLocacao(rs.getString(6));
				loc.setLocalPrevistaDevolucaoLocacao(rs.getString(7));
				loc.setTipoTaxaEmprestarLocacao(rs.getInt(8));
				
				Cliente cli = new Cliente();
				cli.setIDCliente(rs.getInt(9));				
				loc.setCliente(cli);
				
				Automovel auto = new Automovel();
				auto.setIDAutomovel(rs.getInt(10));
				
				locTO.add(loc);
			}
		}
		catch(SQLException sqe)
		{
			sqe.printStackTrace();
		}
		finally 
		{					
			MySQLDAOFactory.desconectar(rs,ps,conn);

		}
		return locTO;

	}

	public LocacaoTO consultarPorDataLocacao(Locacao locacao)
			throws GenericException {

   		String sql = "SELECT ID_LOCACAO, DATA_LOCACAO, DATA_DEVOLUCAO_LOCACAO, AGENCIA_LOCACAO, "
				+ "AGENCIA_DEVOLUCAO_LOCACAO, LOCAL_EMPRESTIMO_LOCACAO, LOCAL_DEVOLUCAO_LOCACAO, "
				+ "TIPO_TARIFA_LOCACAO, ID_CLIENTE, ID_AUTOMOVEL "
				+ "FROM TB_LOCACAO WHERE DATA_LOCACAO >= ?";		
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		LocacaoTO locTO = new LocacaoTO();
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		DateFormat data = new SimpleDateFormat("yyyy-MM-dd");
		
		try
		{
			conn = MySQLDAOFactory.conectar();
			ps = conn.prepareStatement(sql);
			ps.setString(1, data.format(locacao.getDataEmprestimoLocacao()));
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Locacao loc = new Locacao();
				loc.setIDLocacao(rs.getInt(1));
				
				try
				{
					loc.setDataEmprestimoLocacao(f.parse(rs.getString(2)));
					loc.setDataPrevistaDevolucaoLocacao(f.parse(rs.getString(3)));
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				loc.setAgenciaEmprestimoLocacao(rs.getString(4));
				loc.setAgenciaPrevistaDevolucaoLocacao(rs.getString(5));
				loc.setLocalEmprestimoLocacao(rs.getString(6));
				loc.setLocalPrevistaDevolucaoLocacao(rs.getString(7));
				loc.setTipoTaxaEmprestarLocacao(rs.getInt(8));
				
				Cliente cli = new Cliente();
				cli.setIDCliente(rs.getInt(9));				
				loc.setCliente(cli);
				
				Automovel auto = new Automovel();
				auto.setIDAutomovel(rs.getInt(10));
				
				loc.setAutomovel(auto);
				
				locTO.add(loc);
			}
		}
		catch(SQLException sqe)
		{
			sqe.printStackTrace();
		}
		finally 
		{	
			MySQLDAOFactory.desconectar(rs,ps,conn);

		}

		return locTO;
	}

		
}
