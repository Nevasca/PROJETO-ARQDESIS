package control.pagamento;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cliente;
import model.GenericException;
import model.Pagamento;
import model.PagamentoDebito;

/**
 * Servlet implementation class PagamentoDebitoController
 */
@WebServlet("/pagamento/pagamentoDebito.do")
public class PagamentoDebitoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PagamentoDebitoController() {
		super();
	}

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
		String urlAnterior = request.getParameter("urlAnterior");

		String error = "";

		if (request.getParameter("btnConfirmar") != null) {

			PagamentoDebito pagamento = new PagamentoDebito();
			pagamento.setNumeroContaDebito(Integer.parseInt(request
					.getParameter("txtNumContaDebito").replace(" ", "").replace("-","").replace(".","")));

			try {

				pagamento = pagamento.consultar();

			} catch (GenericException e1) {
				e1.printStackTrace();
			}

			// -----------------------VERIFICA SE EXISTE OS DADOS DE PAGAMENTO
			// NO SISTEMA EXTERNO

			if (pagamento != null) {

				if (!pagamento.getBancoDebito().equals(
						request.getParameter("cbBancoDebito"))) {
					error = "PAGAMENTO NÃO AUTORIZADO - Código de segurança é invalido.";

				}
				if (!pagamento.getNomeTitular().equals(
						request.getParameter("txtNomeTitular"))) {
					error = "PAGAMENTO NÃO AUTORIZADO - O nome do titular é invalido.";

				}
				if (!pagamento.getCPFCliente().equals(
						request.getParameter("txtCpf").replace(".", "")
								.replace("-", ""))) {
					error = "PAGAMENTO NÃO AUTORIZADO - Os CPFs não conferem.";

				}
				if (!(pagamento.getNumeroAgenciaDebito() == Integer
						.parseInt(request.getParameter("txtNumAgenciaDebito")
								.replace(".", "").replace("-", "")))) {
					error = "PAGAMENTO NÃO AUTORIZADO - Número da agência esta incorreta.";

				}

				if (!(pagamento.getNumeroContaDebito() == Integer
						.parseInt(request.getParameter("txtNumContaDebito")
								.replace(".", "").replace("-", "")))) {
					error = "PAGAMENTO NÃO AUTORIZADO - Número da conta é invalida.";
				}

				if (!pagamento.getTelefone().equals(
						request.getParameter("txtTelefone").replace("(", "")
								.replace(")", "").replace("-", "").replace(" ",""))) {
					error = "PAGAMENTO NÃO AUTORIZADO - Telefone referencial do titular não confere.";

				}
				if ((pagamento.getValorPagamento() <  Double
						.parseDouble(request.getParameter("valorAPagar")))) {
					error = "PAGAMENTO NÃO AUTORIZADO - Saldo Insuficiente.";

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

									error = "PAGAMENTO NÃO AUTORIZADO - VERIFIQUE COM A CENTRAL BANCÁRIA.";
									request.setAttribute("error", error);
									url = "/pagamento/PagamentoDebito.jsp";
								}

							} catch (GenericException e) {
								e.printStackTrace();
							}
						}else{
							error = "PAGAMENTO NÃO AUTORIZADO - Cliente não encontrado.";
							request.setAttribute("error", error);
							url = "/pagamento/PagamentoDebito.jsp";
						}


					}

				} else {

					request.setAttribute("error", error);
					url = "/pagamento/PagamentoDebito.jsp";

				}

			} else {

				error = "PAGAMENTO NÃO AUTORIZADO - VERIFIQUE COM A CENTRAL BANCÁRIA.";
				request.setAttribute("error", error);
				url = "/pagamento/PagamentoDebito.jsp";
			}

		} else {

			error = "PAGAMENTO NÃO AUTORIZADO - VERIFIQUE COM A CENTRAL BANCÁRIA.";
			request.setAttribute("error", error);
			url = "/pagamento/PagamentoDebito.jsp";
		}

		request.setAttribute("valorAPagar", valorAPagar);
		request.setAttribute("codigo", codigo);
		request.setAttribute("urlAnterior", urlAnterior);

		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
}