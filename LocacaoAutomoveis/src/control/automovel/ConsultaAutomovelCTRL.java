package control.automovel;

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
@WebServlet("/automovel/consultaAutomovel.do")
public class ConsultaAutomovelCTRL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultaAutomovelCTRL() {
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
		
		Automovel auto = new Automovel();
		
		if(!request.getParameter("grupo").equals("Não"))
		{
			auto.setGruposAutomovel(request.getParameter("grupo"));
		}
		
		auto.setAcessoriosAutomovel(Integer.parseInt(request.getParameter("acessorio")));
		auto.setChassiAutomovel(request.getParameter("chassi"));
		auto.setPlacaAutomovel(request.getParameter("placa").replace("-",""));
		auto.setCidadeAutomovel(request.getParameter("cidade"));
		
		if(!request.getParameter("uf").equals("Não"))
		{
			auto.setUFAutomovel(request.getParameter("uf"));
		}
		
		auto.setModeloAutomovel(request.getParameter("modelo"));
		auto.setFabricanteAutomovel(request.getParameter("fabricante"));
		
		try {
			request.setAttribute("listaAutomoveis", auto.consultar());
		} catch (GenericException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher view = request.getRequestDispatcher("/automovel/consulta.jsp");
		view.forward(request, response);
	}

}
