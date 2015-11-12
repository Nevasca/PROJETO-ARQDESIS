package dao.factory.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import to.ClienteTO;
import model.Cliente;
import model.GenericException;
import dao.factory.MySQLDAOFactory;
import dao.interfaces.ClienteDAO;

public class ClienteDAOMySQL implements ClienteDAO {

	@Override
	public boolean inserir(Cliente cliente) throws GenericException {
	
		String sql = "INSERT INTO tb_cliente (NM_CLIENTE, CPF_CLIENTE, RG_CLIENTE, "
				+ "EST_EMISSOR, EMAIL_CLIENTE, TELEFONE_CLIENTE, DT_NASC,SEXO_CLIENTE, REG_CLIENTE, "
				+ "CNH_CLIENTE,DT_VALIDADE_CNH) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection conn = null;
		PreparedStatement ps = null;
   		DateFormat data = new SimpleDateFormat("yyyy-MM-dd");

		try
		{
										
			conn = MySQLDAOFactory.conectar();
			ps = conn.prepareStatement(sql);
			ps.setString(1,cliente.getNomeCliente());
			ps.setString(2,cliente.getCpfCliente());
			ps.setString(3,cliente.getRgCliente());
			ps.setString(4,cliente.getUfCliente());
			ps.setString(5,cliente.getEmailCliente());
			ps.setString(6,cliente.getTelCliente());			
			ps.setString(7,data.format(cliente.getDataNascCliente()));
			ps.setString(8,cliente.getGeneroCliente());
			ps.setString(9,cliente.getRegistroCliente());
			ps.setString(10,cliente.getCNHCliente());
			ps.setString(11,data.format(cliente.getDataValidadeCNH()));

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

	@Override
	public boolean alterar(Cliente cliente) throws GenericException {
		String sql = "UPDATE tb_cliente SET NM_CLIENTE = ?, CPF_CLIENTE = ?, RG_CLIENTE = ?, "
				+ "EST_EMISSOR = ?, EMAIL_CLIENTE = ?, TELEFONE_CLIENTE = ?, DT_NASC = ?, SEXO_CLIENTE = ?, "
				+ "REG_CLIENTE = ?, CNH_CLIENTE = ? ,DT_VALIDADE_CNH = ?"
				+ "WHERE ID_CLIENTE = ?";
		
		
		Connection conn = null;
		PreparedStatement ps = null;
   		DateFormat data = new SimpleDateFormat("yyyy-MM-dd");

		try
		{
			conn = MySQLDAOFactory.conectar();
			ps = conn.prepareStatement(sql);
			ps.setString(1,cliente.getNomeCliente());
			ps.setString(2, cliente.getCpfCliente());
			ps.setString(3, cliente.getRgCliente());
			ps.setString(4, cliente.getUfCliente());
			ps.setString(5, cliente.getEmailCliente());
			ps.setString(6, cliente.getTelCliente());			
			ps.setString(7, data.format(cliente.getDataNascCliente()));
			ps.setString(8, cliente.getGeneroCliente());
			ps.setString(9, cliente.getRegistroCliente());
			ps.setString(10, cliente.getCNHCliente());
			ps.setString(11, data.format(cliente.getDataValidadeCNH()));
			ps.setInt(12, cliente.getIDCliente());

			
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

	@Override
	public boolean excluir(Cliente cliente) throws GenericException {
		
		String sql = "DELETE FROM tb_cliente WHERE ID_CLIENTE = ?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try
		{
			conn = MySQLDAOFactory.conectar();
			ps = conn.prepareStatement(sql);
			ps.setInt(1,cliente.getIDCliente());			
			
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


	@Override
	public ClienteTO consultar() throws GenericException {
		
		String sql = "SELECT ID_CLIENTE, NM_CLIENTE, CPF_CLIENTE, RG_CLIENTE, "
				+ "EST_EMISSOR, EMAIL_CLIENTE, TELEFONE_CLIENTE, DT_NASC, SEXO_CLIENTE, REG_CLIENTE, "
				+ "CNH_CLIENTE,DT_VALIDADE_CNH "
				+ "FROM TB_CLIENTE WHERE ID_CLIENTE = ?";
						
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ClienteTO cliTO = new ClienteTO();
   		DateFormat f = new SimpleDateFormat("yyyy-MM-dd");

		try
		{
			conn = MySQLDAOFactory.conectar();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Cliente cliente = new Cliente();
				cliente.setIDCliente(rs.getInt(1));
				cliente.setNomeCliente(rs.getString(2));
				cliente.setCpfCliente(rs.getString(3));
				cliente.setRgCliente(rs.getString(4));
				cliente.setUfCliente(rs.getString(5));
				cliente.setEmailCliente(rs.getString(6));
				cliente.setTelCliente(rs.getString(7));		

				try
				{
					cliente.setDataNascCliente(f.parse(rs.getString(8)));
					cliente.setDataValidadeCNH(f.parse(rs.getString(12)));

				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				
				cliente.setGeneroCliente(rs.getString(9));
				cliente.setRegistroCliente(rs.getString(10));
				cliente.setCNHCliente(rs.getString(11));
				cliTO.add(cliente);
			}
			
		}
		catch(SQLException sqe)
		{
			sqe.printStackTrace();
		}
		finally 
		{	
			if(rs != null)
			{
				try{
					rs.close();
				} catch(SQLException sqe){
					sqe.printStackTrace();
				}
			}
			if(ps != null)
			{
				try
				{
					ps.close();
				} catch(SQLException sqe)
				{
					sqe.printStackTrace();
				}
			}
			
			if(conn != null)
			{
				MySQLDAOFactory.desconectar(conn);
			}
		}
		
		return cliTO;
	}
	
	public ClienteTO consultar(Cliente cliente) throws GenericException {
		//Strings auxiliares para formar a query de consulta e atribui null para as comparações
				String sqlWhere = "";
				String sqlNome = "";
				String sqlCpf = "", sqlAndCpf = "";
				String whereCompleto = "";
			
				if(cliente.getNomeCliente().length() != 0)
				{
					sqlNome = "NM_CLIENTE LIKE '%" + cliente.getNomeCliente() + "%'";
					sqlWhere = " WHERE ";
				}
				if(cliente.getCpfCliente().length() != 0)
				{
					if(cliente.getNomeCliente() == ""){
						
					sqlCpf = "CPF_CLIENTE LIKE '%" + cliente.getCpfCliente() + "%'";
					sqlAndCpf = " WHERE ";	
						
						
					}
					else{
						
						sqlCpf = "CPF_CLIENTE LIKE '%" + cliente.getCpfCliente() + "%'";
						sqlAndCpf = " AND ";	
					}
								
				}
				
				if(cliente.getNomeCliente() == "" && cliente.getCpfCliente() == ""){
					
					whereCompleto = "";
				}
			
				
				whereCompleto = sqlWhere + sqlNome + sqlAndCpf + sqlCpf;
			
				
				String sql = "SELECT ID_CLIENTE, NM_CLIENTE, CPF_CLIENTE, RG_CLIENTE, "
						+ "EST_EMISSOR, EMAIL_CLIENTE, TELEFONE_CLIENTE, DT_NASC, SEXO_CLIENTE, REG_CLIENTE, "
						+ "CNH_CLIENTE,DT_VALIDADE_CNH "
						+ "FROM TB_CLIENTE " + whereCompleto;
								
				Connection conn = null;
				PreparedStatement ps = null;
				ResultSet rs = null;			
				ClienteTO clienteTO = new ClienteTO();
		   		DateFormat f = new SimpleDateFormat("yyyy-MM-dd");

				try
				{
					conn = MySQLDAOFactory.conectar();
					ps = conn.prepareStatement(sql);
					rs = ps.executeQuery();
					
					while(rs.next())
					{
						cliente = new Cliente();
						cliente.setIDCliente(rs.getInt(1));
						cliente.setNomeCliente(rs.getString(2));
						cliente.setCpfCliente(rs.getString(3));
						cliente.setRgCliente(rs.getString(4));
						cliente.setUfCliente(rs.getString(5));
						cliente.setEmailCliente(rs.getString(6));
						cliente.setTelCliente(rs.getString(7));
					
						try
						{
							cliente.setDataNascCliente(f.parse(rs.getString(8)));
							cliente.setDataValidadeCNH(f.parse(rs.getString(12)));

						}
						catch(Exception e)
						{
							e.printStackTrace();
						} 
						
						cliente.setGeneroCliente(rs.getString(9));
						cliente.setRegistroCliente(rs.getString(10));
						cliente.setCNHCliente(rs.getString(11));
						clienteTO.add(cliente);
						
						
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
				
				return clienteTO;
	}

	
	@Override
	public Cliente consultarCliente(Cliente cliente) throws GenericException {
		//Strings auxiliares para formar a query de consulta e atribui null para as comparações
		
				String sqlWhere = "";
				String sqlNome = "";
				String sqlCpf = "", sqlAndCpf = "";
				String whereCompleto = "";
							
				if(cliente.getNomeCliente() != null)
				{
					sqlNome = "NM_CLIENTE LIKE '%" + cliente.getNomeCliente() + "%'";
					sqlWhere = " WHERE ";
				}
				
				if(cliente.getCpfCliente() != null)
				{
					
					if(cliente.getNomeCliente() == null){
						
					sqlCpf = "CPF_CLIENTE LIKE '%" + cliente.getCpfCliente() + "%'";
					sqlAndCpf = " WHERE ";	
						
						
					}
					else{
						
						sqlCpf = "CPF_CLIENTE LIKE '%" + cliente.getCpfCliente() + "%'";
						sqlAndCpf = " AND ";	
					}
								
				}
				
				if(cliente.getNomeCliente() == "" && cliente.getCpfCliente() == ""){
					
					whereCompleto = "";
				}
			
				
				whereCompleto = sqlWhere + sqlNome + sqlAndCpf + sqlCpf;
			
				String sql = "SELECT ID_CLIENTE, NM_CLIENTE, CPF_CLIENTE, RG_CLIENTE, "
						+ "EST_EMISSOR, EMAIL_CLIENTE, TELEFONE_CLIENTE, DT_NASC, SEXO_CLIENTE, REG_CLIENTE, "
						+ "CNH_CLIENTE,DT_VALIDADE_CNH "
						+ "FROM TB_CLIENTE " + whereCompleto;

				System.out.print(sql);
				
				Connection conn = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
		   		DateFormat f = new SimpleDateFormat("yyyy-MM-dd");

				try
				{
					conn = MySQLDAOFactory.conectar();
					ps = conn.prepareStatement(sql);
					rs = ps.executeQuery();
					
					while(rs.next())
					{
						cliente = new Cliente();
						cliente.setIDCliente(rs.getInt(1));
						cliente.setNomeCliente(rs.getString(2));
						cliente.setCpfCliente(rs.getString(3));
						cliente.setRgCliente(rs.getString(4));
						cliente.setUfCliente(rs.getString(5));
						cliente.setEmailCliente(rs.getString(6));
						cliente.setTelCliente(rs.getString(7));
					
						try
						{
							cliente.setDataNascCliente(f.parse(rs.getString(8)));
							cliente.setDataValidadeCNH(f.parse(rs.getString(12)));

						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
						
						cliente.setGeneroCliente(rs.getString(9));
						cliente.setRegistroCliente(rs.getString(10));
						cliente.setCNHCliente(rs.getString(11));
					
						
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
				
				return cliente;
	}

	@Override
	public Cliente consultarPorID(Cliente cliente) {

		String sql = "SELECT ID_CLIENTE, NM_CLIENTE, CPF_CLIENTE, RG_CLIENTE, "
				+ "EST_EMISSOR, EMAIL_CLIENTE, TELEFONE_CLIENTE, DT_NASC, SEXO_CLIENTE, REG_CLIENTE, "
				+ "CNH_CLIENTE,DT_VALIDADE_CNH "
				+ "FROM TB_CLIENTE WHERE ID_CLIENTE = ?";
				
		//System.out.println(sql);
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ClienteTO cliTO = new ClienteTO();
   		DateFormat f = new SimpleDateFormat("yyyy-MM-dd");

		try
		{
			conn = MySQLDAOFactory.conectar();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cliente.getIDCliente());
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				
				cliente.setIDCliente(rs.getInt(1));
				cliente.setNomeCliente(rs.getString(2));
				cliente.setCpfCliente(rs.getString(3));
				cliente.setRgCliente(rs.getString(4));
				cliente.setUfCliente(rs.getString(5));
				cliente.setEmailCliente(rs.getString(6));
				cliente.setTelCliente(rs.getString(7));
			
				try
				{
					cliente.setDataNascCliente(f.parse(rs.getString(8)));
					cliente.setDataValidadeCNH(f.parse(rs.getString(12)));

				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				cliente.setGeneroCliente(rs.getString(9));
				cliente.setRegistroCliente(rs.getString(10));
				cliente.setCNHCliente(rs.getString(11));
				cliTO.add(cliente);
			}
			else
			{
				cliente = null;
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
		
		return cliente;
	}

}
