package dao.factory.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import to.RelatorioTO;
import model.GenericException;
import model.Relatorio;
import dao.factory.MySQLDAOFactory;
import dao.interfaces.RelatorioDAO;

public class RelatorioMySQL implements RelatorioDAO {

	public RelatorioTO consultar() throws GenericException {
				
		String sql = "SELECT A.MODELO_AUTOMOVEL MODELO,"
				+ "          C.NM_CLIENTE CLIENTE,       "
				+ "          C.CPF_CLIENTE CPF,  "
				+ "		     DATE_FORMAT(L.DATA_LOCACAO,'%d/%m/%Y') LOCACAO,"
				+ "		     DATE_FORMAT(D.DATA_DEVOLUCAO_LOCACAO,'%d/%m/%Y') DEVOLUCAO, "
				+ "          D.LOCAL_DEVOLUCAO_LOCACAO,"
				+ "          DATE_FORMAT(P.DATA_PAGAMENTO,'%d/%m/%Y') PAGAMENTO,"
				+ "          P.VLR_PAGAMENTO VALOR_PAGO"
				+ "    FROM TB_LOCACAO L "
				+ "   INNER JOIN TB_CLIENTE C       "
				+ "      ON C.ID_CLIENTE = L.ID_CLIENTE    "
				+ "   INNER JOIN TB_AUTOMOVEL A"
				+ "      ON L.ID_AUTOMOVEL = A.ID_AUTOMOVEL"
				+ "    LEFT OUTER JOIN TB_PAGAMENTO P"
				+ "      ON P.ID_LOCACAO = L.ID_LOCACAO"
				+ "   LEFT OUTER JOIN TB_DEVOLUCAO D"
				+ "      ON D.ID_LOCACAO = L.ID_LOCACAO"
				+ "     AND D.ID_CLIENTE = C.ID_CLIENTE";

	
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		RelatorioTO relTO = new RelatorioTO();

		try {

			conn = MySQLDAOFactory.conectar();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {

				Relatorio relatorio = new Relatorio();
				relatorio.setModeloAutomovel(rs.getString(1));
				relatorio.setNomeCliente(rs.getString(2));
				relatorio.setCpfCliente(rs.getString(3));
				relatorio.setLocalDevolucao(rs.getString(4));
				
				String strData = relatorio.getDataFormatadaJAVA(rs.getDate(5));  

				relatorio.setDataPagamento(strData);
				relatorio.setValorPago(rs.getDouble(6));
				relTO.add(relatorio);

			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {

			MySQLDAOFactory.desconectar(rs,pst,conn);
			
		}

		return relTO;
	}

	public RelatorioTO consultar(String dataReferencia)
			throws GenericException {
		
		String sql = "SELECT A.MODELO_AUTOMOVEL MODELO,"
				+ "          C.NM_CLIENTE CLIENTE,       "
				+ "          C.CPF_CLIENTE CPF,  "
				+ "		     DATE_FORMAT(L.DATA_LOCACAO,'%d/%m/%Y') LOCACAO,"
				+ "		     DATE_FORMAT(D.DATA_DEVOLUCAO_LOCACAO,'%d/%m/%Y') DEVOLUCAO, "
				+ "          D.LOCAL_DEVOLUCAO_LOCACAO,"
				+ "          DATE_FORMAT(P.DATA_PAGAMENTO,'%d/%m/%Y') PAGAMENTO,"
				+ "          P.VLR_PAGAMENTO VALOR_PAGO"
				+ "    FROM TB_LOCACAO L "
				+ "   INNER JOIN TB_CLIENTE C       "
				+ "      ON C.ID_CLIENTE = L.ID_CLIENTE    "
				+ "   INNER JOIN TB_AUTOMOVEL A"
				+ "      ON L.ID_AUTOMOVEL = A.ID_AUTOMOVEL"
				+ "    LEFT OUTER JOIN TB_PAGAMENTO P"
				+ "      ON P.ID_LOCACAO = L.ID_LOCACAO"
				+ "   LEFT OUTER JOIN TB_DEVOLUCAO D"
				+ "      ON D.ID_LOCACAO = L.ID_LOCACAO"
				+ "     AND D.ID_CLIENTE = C.ID_CLIENTE";

		if (dataReferencia != null && dataReferencia.toString() != "") {
			sql += "  WHERE DATE_FORMAT(L.DATA_LOCACAO,'%d/%m/%Y') = ?";
		}
	
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		RelatorioTO relTO = new RelatorioTO();

		try {

			conn = MySQLDAOFactory.conectar();
			pst = conn.prepareStatement(sql);
			pst.setString(1, dataReferencia);
			rs = pst.executeQuery();

			while (rs.next()) {

				Relatorio relatorio = new Relatorio();
				relatorio.setModeloAutomovel(rs.getString(1));
				relatorio.setNomeCliente(rs.getString(2));
				relatorio.setCpfCliente(rs.getString(3));
				relatorio.setLocalDevolucao(rs.getString(4));

				String strData = relatorio.getDataFormatadaJAVA(rs.getDate(5));  
							
				relatorio.setDataPagamento(strData);
				relatorio.setValorPago(rs.getDouble(6));
				relTO.add(relatorio);

			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {

			MySQLDAOFactory.desconectar(rs,pst,conn);

		}

		return relTO;
	}

}
