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
 * Servlet implementation class AlteracaoAutomovelCTRL
 */
@WebServlet("/automovel/alteracao.do")
public class AlteracaoAutomovelCTRL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlteracaoAutomovelCTRL() {
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
		auto.setGruposAutomovel(request.getParameter("grupo")); 
		auto.setAcessoriosAutomovel(Integer.parseInt(request.getParameter("acessorio")));
		auto.setChassiAutomovel(request.getParameter("chassi"));
		auto.setPlacaAutomovel(request.getParameter("placa").replace("-",""));
		auto.setCidadeAutomovel(request.getParameter("cidade"));
		auto.setUFAutomovel(request.getParameter("uf"));
		auto.setKmRodadoAutomovel(Double.parseDouble(request.getParameter("quilometragem").replace(" ","").replace("KM","")));
		auto.setModeloAutomovel(request.getParameter("modelo"));
		auto.setFabricanteAutomovel(request.getParameter("fabricante"));
		auto.setTarifaAutomovel(Double.parseDouble(request.getParameter("tarifa")));
		auto.setTarifaControladaAutomovel(Double.parseDouble(request.getParameter("tarifaControlada")));
		auto.setTPStatus(0);

		//Boolean para informar usuario se alterou ou nao
		try {
			if(auto.alterar())
			{			
				request.setAttribute("alterado", true);
				request.setAttribute("automovelConsultado", auto);
			}
			else
			{
				
				request.setAttribute("alterado", false);
			}
		} catch (GenericException e) {
			e.printStackTrace();
		}				
		
		//Volta para a mesma tela de detalhes para tratar se alterou ou nao
		RequestDispatcher view = request.getRequestDispatcher("detalhes.do?id=" + auto.getIDAutomovel());
		view.forward(request, response);
	}

}
