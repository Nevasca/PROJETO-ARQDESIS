package control.automovel;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Automovel;
import model.GenericException;

/**
 * Servlet implementation class ExclusaoAutomovelCTRL
 */
@WebServlet("/automovel/exclusao.do")
public class ExclusaoAutomovelCTRL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExclusaoAutomovelCTRL() {
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
request.setCharacterEncoding("UTF-8");
		
		//Fazer verificações de preenchimento?
		Automovel auto = new Automovel();
		
		auto.setIDAutomovel(Integer.parseInt(request.getParameter("idAutomovel")));		
		
		//Se conseguiu excluir, ir para tela de sucesso
		try {
			if(auto.excluir())
			{
				RequestDispatcher view = request.getRequestDispatcher("/automovel/exclusao-sucesso.jsp");
				view.forward(request, response);
			}
			else
			{
				request.setAttribute("erroExclusao", "1");
				RequestDispatcher view = request.getRequestDispatcher("/automovel/detalhes.do?id=" + auto.getIDAutomovel());
				view.forward(request, response);
			}
		} catch (GenericException e) {
			e.printStackTrace();
		}				
		
	}

}
