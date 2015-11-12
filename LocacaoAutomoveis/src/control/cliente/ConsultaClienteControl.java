package control.cliente;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.*;


/**
 * Servlet implementation class ConsultaAutomovelCTRL
 */
@WebServlet("/cliente/consultaCliente.do")
public class ConsultaClienteControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultaClienteControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Cliente cliente = new Cliente();
		
		
		cliente.setNomeCliente(request.getParameter("nome"));
		cliente.setCpfCliente(request.getParameter("cpf").replace(".", "").replace("-", ""));
		
		
		//Apenas teste!!! Chamar outra classe ao inves da DAO direto

		try {
			request.setAttribute("listaClientes", cliente.consultar());
		} catch (GenericException e) {
			e.printStackTrace();
		}
		RequestDispatcher view = request.getRequestDispatcher("/cliente/consulta.jsp");
		view.forward(request, response);
	}

}
