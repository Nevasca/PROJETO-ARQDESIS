package control.relatorio;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;


/**
 * Servlet implementation class ConsultaLocacaoDiariaCTRL
 */
@WebServlet("/relatorio/consultaLocacoesDiarias.do")
public class ConsultaLocacaoDiariaCTRL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultaLocacaoDiariaCTRL() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		Locacao loc = new Locacao();
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		
		try
		{
			loc.setDataEmprestimoLocacao(f.parse(request.getParameter("dataLocacao")));
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		try {
			request.setAttribute("listaLocacoes", loc.consultarPorDataLocacao());
		} catch (GenericException e) {
			e.printStackTrace();
		}
		RequestDispatcher view = request.getRequestDispatcher("/relatorio/relatorio.jsp");
		view.forward(request, response);
		
		
	}

}
