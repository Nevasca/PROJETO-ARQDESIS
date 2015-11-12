package control.automovel;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Automovel;
import model.GenericException;

import java.util.ArrayList;
import util.JsonFacade;

/**
 * Servlet implementation class ConsultaAutomovelJSON
 */
@WebServlet("/automovel/consulta.json")
public class ConsultaAutomovelJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultaAutomovelJSON() {
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
		response.setContentType("application/json");   
		response.setCharacterEncoding("UTF-8");
		
		Automovel auto = new Automovel();
		auto.setCidadeAutomovel(request.getParameter("cidade"));
		
		ArrayList<Automovel> lista = null;
		try {
			lista = auto.consultarCidade().getLista();
		} catch (GenericException e) {
			e.printStackTrace();
		}
		response.getWriter().println(JsonFacade.listToJson(lista));
	}

}
