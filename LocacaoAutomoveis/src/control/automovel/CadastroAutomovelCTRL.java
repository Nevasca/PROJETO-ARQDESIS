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
 * Servlet implementation class CadastroAutomovelCTRL
 */
@WebServlet("/automovel/cadastroAutomovel.do")
public class CadastroAutomovelCTRL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroAutomovelCTRL() {
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
			
		
		//Fazer verificações de preenchimento?
		Automovel auto = new Automovel();
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
		
		//Validar posteriormente se cadastrou ou não
		try {
			auto.inserir();
		} catch (GenericException e) {
			e.printStackTrace();
		}
		
		//request.setAttribute("automovel", auto);
		
		RequestDispatcher view = request.getRequestDispatcher("/automovel/cadastro-sucesso.jsp");
		view.forward(request, response);
		
	}

}
