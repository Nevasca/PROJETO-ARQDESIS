package control.pagamento;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Cliente;
import model.GenericException;
import model.Pagamento;
import model.PagamentoCredito;

/**
 * Servlet implementation class SelecionarCervejasController
 */
@WebServlet("/pagamento/pagamentoCredito.do")
public class PagamentoCreditoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String url = "";

		String valorAPagar = request.getParameter("valorAPagar");
		String codigo = request.getParameter("codigo");
		System.out.println("O c�digo � esse: " + codigo);
		String urlAnterior = request.getParameter("urlAnterior");

		String error = "";

		if (request.getParameter("btnConfirmar") != null) {

			PagamentoCredito pagamento = new PagamentoCredito();
			pagamento.setNumeroCartaoCredito(request.getParameter(
					"txtNumeroCartao").replace(" ", ""));

			try {

				pagamento = pagamento.consultar();

			} catch (GenericException e1) {
				e1.printStackTrace();
			}

			// -----------------------VERIFICA SE EXISTE OS DADOS DE PAGAMENTO
			// NO SISTEMA EXTERNO

			if (pagamento.getIDPagamento() > 0) {

				if (!pagamento.getOperadoraCredito()
						.equals(request.getParameter("cbOperadoraCartao")
								.toUpperCase())) {
					error = "PAGAMENTO N�O AUTORIZADO - Operadora de cr�dito invalida.";

				}

				if (!pagamento.getNomeTitular().equals(
						request.getParameter("txtNomeTitular").toUpperCase())) {
					error = "PAGAMENTO N�O AUTORIZADO - O nome do titular � invalido.";

				}

				if (!pagamento.getCPFCliente().equals(
						request.getParameter("txtCpf").replace(".", "")
								.replace("-", ""))) {
					error = "PAGAMENTO N�O AUTORIZADO - O CPF n�o confere.";

				}

				if (!pagamento.getValidadeCredito().equals(
						request.getParameter("txtValidadeCartao"))) {
					error = "PAGAMENTO N�O AUTORIZADO - Validade do cart�o � invalida.";

				}

				if (!(pagamento.getNumeroCartaoCredito().equals(request
						.getParameter("txtNumeroCartao").replace(" ", "")))) {
					error = "PAGAMENTO N�O AUTORIZADO - Numero de cart�o � invalido.";

				}

				if (!(pagamento.getCodigoSegurancaCredito() == Integer
						.parseInt(request.getParameter("txtCodSeguranca")))) {
					error = "PAGAMENTO N�O AUTORIZADO - C�digo de seguran�a � invalido.";

				}

				if ((pagamento.getValorPagamento() < Double.parseDouble(request
						.getParameter("valorAPagar")))) {
					error = "PAGAMENTO N�O AUTORIZADO - Saldo Insuficiente.";

				}

				if (error.toString().trim() == "") {

					if (Integer.parseInt(codigo) > 0) {

						Cliente cli = new Cliente();
						cli.setCpfCliente(request.getParameter("txtCpf")
								.replace(".", "").replace("-", ""));

						try {
							cli = cli.consultarCliente();
						} catch (GenericException e) {
							e.printStackTrace();
						}

						int codigoCliente = cli.getIDCliente();
						
						if(codigoCliente > 0){

							try {
								Pagamento pag = new Pagamento();

								pag.setNomeTitular(request
										.getParameter("txtNomeTitular"));
								pag.setCPFCliente(request.getParameter("txtCpf")
										.replace(".", "").replace("-", ""));

								DateFormat f = new SimpleDateFormat(
										"yyyy-MM-dd hh:mm");
								Date dataPagamento = null;

								try {
									// Formata a data do date input para data do
									// java
									dataPagamento = f.parse(request.getParameter(
											"txtDataPagamento").replace("T", " "));
								} catch (Exception e) {
									e.printStackTrace();
								}

								pag.setDataPagamento(dataPagamento);
								pag.setValorPagamento(Double
										.parseDouble(valorAPagar));

								if (pag.inserir(codigoCliente,
										Integer.parseInt(codigo))) {
									url = "/pagamento/pagamento-sucesso.jsp";

								} else {

									error = "PAGAMENTO N�O AUTORIZADO - VERIFIQUE COM A CENTRAL BANC�RIA.";
									request.setAttribute("error", error);
									url = "/pagamento/PagamentoCredito.jsp";
								}

							} catch (GenericException e) {
								e.printStackTrace();
							}
						}else{
							error = "PAGAMENTO N�O AUTORIZADO - Cliente n�o encontrado.";
							request.setAttribute("error", error);
							url = "/pagamento/PagamentoCredito.jsp";
						}


					}

				} else {

					request.setAttribute("error", error);
					url = "/pagamento/PagamentoCredito.jsp";

				}

			} else {

				error = "PAGAMENTO N�O AUTORIZADO - VERIFIQUE COM A CENTRAL BANC�RIA.";
				request.setAttribute("error", error);
				url = "/pagamento/PagamentoCredito.jsp";
			}

		} else {

			error = "PAGAMENTO N�O AUTORIZADO - VERIFIQUE COM A CENTRAL BANC�RIA.";
			request.setAttribute("error", error);
			url = "/pagamento/PagamentoCredito.jsp";
		}

		request.setAttribute("valorAPagar", valorAPagar);
		request.setAttribute("codigo", codigo);
		request.setAttribute("urlAnterior", urlAnterior);

		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
}