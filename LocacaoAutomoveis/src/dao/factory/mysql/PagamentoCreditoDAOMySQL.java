package dao.factory.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import to.PagamentoCreditoTO;

import model.GenericException;
import model.Pagamento;
import model.PagamentoCredito;
import dao.factory.MySQLDAOFactory;
import dao.interfaces.PagamentoCreditoDAO;

public class PagamentoCreditoDAOMySQL implements PagamentoCreditoDAO {

	public PagamentoCredito consultar(PagamentoCredito pagamento)
			throws GenericException {
		
		String sql = "SELECT P.ID_PAGAMENTO_C," 
				+ "          P.OPERADORA_CARTAO,"
				+ "          P.NM_CLIENTE,"
				+ "          P.CPF_CLIENTE,"
				+ "          P.NUM_CARTAO,"
				+ "	         P.DT_VALIDADE,"
				+ "		     P.COD_SEGURANCA, " 
				+ "		     P.CREDITO"
				+ "	    FROM TB_PAGAMENTO_CREDITO P"
				+ "    WHERE P.NUM_CARTAO = ?";

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			conn = MySQLDAOFactory.conectar();
			pst = conn.prepareStatement(sql);
			pst.setString(1, pagamento.getNumeroCartaoCredito());
			rs = pst.executeQuery();

			while (rs.next()) {

				pagamento = new PagamentoCredito();	
				pagamento.setIDPagamento(rs.getInt(1));
				pagamento.setOperadoraCredito(rs.getString(2));
				pagamento.setNomeTitular(rs.getString(3));
				pagamento.setCPFCliente(rs.getString(4));
				pagamento.setNumeroCartaoCredito(rs.getString(5));
				pagamento.setValidadeCredito(rs.getString(6));
				pagamento.setCodigoSegurancaCredito(rs.getInt(7));
				pagamento.setValorPagamento(rs.getDouble(8));						
			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {

			MySQLDAOFactory.desconectar(rs,pst,conn);

		}

		return pagamento;
	}

	public PagamentoCreditoTO consultar() throws GenericException {
	
		String sql = "SELECT P.ID_PAGAMENTO_C," 
				+ "          P.OPERADORA_CARTAO,"
				+ "          P.NM_CLIENTE,"
				+ "          P.CPF_CLIENTE,"
				+ "          P.NUM_CARTAO,"
				+ "	         P.DT_VALIDADE,"
				+ "		     P.COD_SEGURANCA, " 
				+ "		     P.CREDITO"
				+ "	    FROM TB_PAGAMENTO_CREDITO P";

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		PagamentoCreditoTO pagTO = new PagamentoCreditoTO();
		try {

			conn = MySQLDAOFactory.conectar();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {

				PagamentoCredito pagamento = new PagamentoCredito();							
				pagamento.setIDPagamento(rs.getInt(1));
				pagamento.setOperadoraCredito(rs.getString(2));
				pagamento.setNomeTitular(rs.getString(3));
				pagamento.setCPFCliente(rs.getString(4));
				pagamento.setNumeroCartaoCredito(rs.getString(5));
				pagamento.setValidadeCredito(rs.getString(6));
				pagamento.setCodigoSegurancaCredito(rs.getInt(7));
				pagamento.setValorPagamento(rs.getDouble(8));				
				pagTO.add(pagamento);
							
			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {

			MySQLDAOFactory.desconectar(rs,pst,conn);

		}

		return pagTO;
	}

	public boolean inserir(Pagamento pagamento, int cliente, int locacao)
			throws GenericException {
		return false;
	}


}
