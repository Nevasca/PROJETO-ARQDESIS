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
import model.Locacao;

/**
 * Servlet implementation class DetalhesAutomovelCTRL
 */
@WebServlet("/automovel/detalhes.do")
public class DetalhesAutomovelCTRL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetalhesAutomovelCTRL() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.setCharacterEncoding("UTF-8");
		int id;
		
		if(request.getParameter("id") != null || request.getParameter("id").length() != 0)
		{
			id = Integer.parseInt(request.getParameter("id"));
		}
		else
		{
			//Mandar pra tela de erro depois
			id = 0;	
		}
		
		Automovel auto = new Automovel();
		auto.setIDAutomovel(id);
		try {
			auto = auto.consultarPorID();
		} catch (GenericException e1) {
			e1.printStackTrace();
		}
		
		//Se não existir automovel não fazer nada
		if(auto == null)
		{
			//Apenas para teste
			RequestDispatcher view = request.getRequestDispatcher("consulta.jsp");
			view.forward(request, response);
		}
		else //Se existir automovel, setar o atributo e ir para a tela de detalhes
		{
			request.setAttribute("automovelConsultado", auto);
			
			//Seta a lista de locacoes realizadas do automovel
			Locacao loc = new Locacao();
			loc.setAutomovel(auto);
			try {
				request.setAttribute("listaLocacoes", loc.consultarPorAutomovel());
			} catch (GenericException e) {
				e.printStackTrace();
			}
			RequestDispatcher view = request.getRequestDispatcher("detalhes.jsp");
			view.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
