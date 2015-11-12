package control.pagamento;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class PagamentoDebitoController
 */
@WebServlet("/pagamento/formaDePagamento.do")
public class EscolherFormaPagamentoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EscolherFormaPagamentoController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String url = "";
		
		String valorAPagar = request.getParameter("valorAPagar");
		String codigo = request.getParameter("codigo");
		String urlAnterior = request.getParameter("urlAnterior");
		
		if (request.getParameter("btnCredito") != null) {
			url = "/pagamento/PagamentoCredito.jsp";

		}else if(request.getParameter("btnDebito") != null){
			url = "/pagamento/PagamentoDebito.jsp";

		}else{
			url = "/pagamento/EscolhaFormaDePagamento.jsp";
		}
		
		request.setAttribute("valorAPagar", valorAPagar);
		request.setAttribute("codigo", codigo);	
		request.setAttribute("urlAnterior", urlAnterior);	
		
		RequestDispatcher view = request.getRequestDispatcher(url);
		view.forward(request, response);

	}

}
