package control.devolver;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Devolucao;
import model.Automovel;
import model.GenericException;
import model.Locacao;

/**
 * Servlet implementation class SelecionarCervejasController
 */
@WebServlet("/devolucao/devolver.do")
public class DevolverAutomovelController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processoDeDevolucao(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processoDeDevolucao(request, response);

	}

	public void processoDeDevolucao(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String url = "";
		String error = "";
								
		if (request.getParameter("btnConfirmar") != null) {

			String numeroLocacao = request.getParameter("txtNumeroLocacao");

			if (numeroLocacao != null) {

				Locacao loc = new Locacao();
				loc.setIDLocacao(Integer.parseInt(numeroLocacao));

				try {
					loc = loc.consultarPorID();
				} catch (GenericException e) {
					e.printStackTrace();
				}

				// -----------------------VERIFICA SE EXISTE A LOCACAO
				if (loc != null) {

					double valorTotal = 0.00;
					
					Devolucao dev = new Devolucao();
					
					DateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			        Date dataDevolucao = null;
			        
			        try
			        {
			        	dataDevolucao = f.parse( request.getParameter(
								"txtDataDevolucao").replace("T", " "));
			        	
			        }
			        catch(Exception e)
			        {
			        	e.printStackTrace();        
			        }
			        
					dev.setDataDevolucaoCliente(dataDevolucao);
					dev.setNumeroLocacaoAutomovel(Integer
							.parseInt(numeroLocacao));
					dev.setLocalDevolucao(request.getParameter("cbLocalD")
							.toString());
					dev.setAgenciaDevolucao(request.getParameter("cbAgencia")
							.toString());
					dev.setKmRodadaAtual(Double.parseDouble(request.getParameter("txtKMRodado").replace(" ","").replace("KM","")));

			
					HttpSession sessao = request.getSession();
					sessao.setAttribute("dadosDev", dev);
					sessao.setAttribute("dadosLoc", loc);

					// -----------------------VERIFICA SE EXISTE UMA MULTA
					valorTotal = dev.getValorMulta(loc, dev);

					if (valorTotal > 0) {

						url = "/pagamento/EscolhaFormaDePagamento.jsp";
						request.setAttribute("valorAPagar", valorTotal);
						request.setAttribute("codigo", numeroLocacao);
						request.setAttribute("urlAnterior", "/devolucao/devolucao.jsp");

					} else {

						valorTotal = 0;

						try {
							if (dev.inserir(dev)) {

								Automovel aut = new Automovel();
								aut.setTPStatus(0);
								aut.setIDAutomovel(loc.getAutomovel()
										.getIDAutomovel());

								aut.alterarSTATUS();

								url = "/devolucao/devolucao-sucesso.jsp";


							} else {

								error = "Devolução de automovel não realizada.";
								url = "/devolucao/devolucao.jsp";

							}
						} catch (GenericException e) {
							e.printStackTrace();
						}

						request.setAttribute("valorAPagar", valorTotal);
						request.setAttribute("codigo", numeroLocacao);
						request.setAttribute("urlAnterior", "/devolucao/devolucao.jsp");
						request.setAttribute("error", error);

					}

				} else {

					error = "Nº de locacao é invalido ou não encontrado.";
					request.setAttribute("error", error);
					url = "/devolucao/devolucao.jsp";

				}

			}

		} else {

			error = "Nº de locacao é invalido ou não encontrado.";
			request.setAttribute("error", error);
			url = "/devolucao/devolucao.jsp";
		}

		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);

	}
}
