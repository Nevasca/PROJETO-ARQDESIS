package dao.factory.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import model.GenericException;
import model.Pagamento;
import dao.factory.MySQLDAOFactory;
import dao.interfaces.PagamentoDAO;

public class PagamentoDAOMySQL implements PagamentoDAO {

	public boolean inserir(Pagamento pagamento,int idCliente,int idLocacao) throws GenericException {
		
		String sql = "INSERT INTO TB_PAGAMENTO" 
				+"	 ("
		        + "	   DATA_PAGAMENTO,"
				+ "    VLR_PAGAMENTO,"
				+ "    ID_CLIENTE,"
				+ "    ID_LOCACAO"
				+"    )"
				+ "	VALUES"
				+"	 ("
				+ "	    ?," 
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
			
   			pst.setString(1, data.format(pagamento.getDataPagamento()));
			pst.setDouble(2, pagamento.getValorPagamento());
			pst.setInt(3, idCliente);
			pst.setInt(4, idLocacao);
			pst.execute();

			return true;
			
		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {

			MySQLDAOFactory.desconectar(pst,conn);

		}
		
		return false;

	}


}
