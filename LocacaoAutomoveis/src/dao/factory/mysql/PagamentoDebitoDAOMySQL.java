package dao.factory.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import to.PagamentoDebitoTO;
import model.GenericException;
import model.Pagamento;
import model.PagamentoDebito;
import dao.factory.MySQLDAOFactory;
import dao.interfaces.PagamentoDebitoDAO;

public class PagamentoDebitoDAOMySQL implements PagamentoDebitoDAO {

	@Override
	public PagamentoDebito consultar(PagamentoDebito pagamento)
			throws GenericException {
		
		String sql = "SELECT P.BANCO,"
				+ "          P.NM_CLIENTE,"
				+ "          P.CPF_CLIENTE,"
				+ "          P.NUM_AGENCIA,"
				+ "	         P.NUM_CONTA_CORRENTE,"
				+ "		     P.TEL_CLIENTE, " 
				+ "		     P.SALDO"
				+ "	    FROM TB_PAGAMENTO_DEBITO P"
				+ "    WHERE P.NUM_CONTA_CORRENTE = ?";
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			conn = MySQLDAOFactory.conectar();

			pst = conn.prepareStatement(sql);
			pst.setInt(1, pagamento.getNumeroContaDebito());
			rs = pst.executeQuery();

			while (rs.next()) {

				pagamento = new PagamentoDebito();				
				pagamento.setBancoDebito(rs.getString(1));
				pagamento.setNomeTitular(rs.getString(2));
				pagamento.setCPFCliente(rs.getString(3));
				pagamento.setNumeroAgenciaDebito(rs.getInt(4));
				pagamento.setNumeroContaDebito(rs.getInt(5));				
				pagamento.setTelefone(rs.getString(6));
				pagamento.setValorPagamento(rs.getDouble(7));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {

			MySQLDAOFactory.desconectar(rs,pst,conn);

		}

		return pagamento;
	}

	@Override
	public PagamentoDebitoTO consultar() throws GenericException {
		
		String sql = "SELECT P.BANCO,"
				+ "          P.NM_CLIENTE,"
				+ "          P.CPF_CLIENTE,"
				+ "          P.NUM_AGENCIA,"
				+ "	         P.NUM_CONTA_CORRENTE,"
				+ "		     P.TEL_CLIENTE, " 
				+ "		     P.SALDO"
				+ "	    FROM TB_PAGAMENTO_DEBITO P";
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		PagamentoDebitoTO pagTO = new PagamentoDebitoTO();

		try {

			conn = MySQLDAOFactory.conectar();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {

				PagamentoDebito pagDebito = new PagamentoDebito();				
				pagDebito.setBancoDebito(rs.getString(1));
				pagDebito.setNomeTitular(rs.getString(2));
				pagDebito.setCPFCliente(rs.getString(3));
				pagDebito.setNumeroAgenciaDebito(rs.getInt(4));
				pagDebito.setNumeroContaDebito(rs.getInt(5));				
				pagDebito.setTelefone(rs.getString(6));
				pagDebito.setValorPagamento(rs.getDouble(7));
				pagTO.add(pagDebito);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {

			MySQLDAOFactory.desconectar(rs,pst,conn);

		}

		return pagTO;
	}


	@Override
	public boolean inserir(Pagamento pagamento, int cliente, int locacao)
			throws GenericException {
		return false;
	}

}
