package control.locacao;

import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

/**
 * Servlet implementation class NovaLocacaoCTRL
 */
@WebServlet("/locacao/locacao.do")
public class NovaLocacaoCTRL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NovaLocacaoCTRL() {
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
		
		//Escolhe o que fazer de acordo com o input acao
		
		//Veio da tela de detalhes do automovel
		if(request.getParameter("acao") == null) 
		{
			//Pega os dados do automovel, seta como atributo na request e manda para a tela de locacao
			Automovel auto = null;
			try {
				auto = getAutomovel(request);
			} catch (GenericException e) {
				e.printStackTrace();
			}
			request.setAttribute("automovel", auto);
			RequestDispatcher view = request.getRequestDispatcher("/locacao/nova-locacao.jsp");
			view.forward(request, response);
		}
		else if(request.getParameter("acao").equals("validarLocacao"))
		{
			Locacao loc = getLocacao(request);
			Automovel auto = null;
			try {
				auto = getAutomovel(request);
			} catch (GenericException e) {
				e.printStackTrace();
			}
			loc.setAutomovel(auto);
			
			//Verifica se o periodo informado está disponivel
			//Se não tiver, colocar id de erro
			/*if(!loc.estaDisponivel())
			{
				request.setAttribute("erro", "2");
				
			}*/
			
			request.setAttribute("automovel", auto);
			request.setAttribute("locacao", loc);
			
			
			RequestDispatcher view = request.getRequestDispatcher("/locacao/nova-locacao.jsp");
			view.forward(request, response);			
			
			
		}
		else if(request.getParameter("acao").equals("consultarCliente"))
		{
			Locacao loc = getLocacao(request);
			Automovel auto = null;
			try {
				auto = getAutomovel(request);
			} catch (GenericException e) {
				e.printStackTrace();
			}
			Cliente cli = getCliente(request);
			
			loc.setAutomovel(auto);
			request.setAttribute("locacao", loc);
			request.setAttribute("automovel", auto);
			request.setAttribute("cliente", cli);
			
			
			//Não encontrou um cliente com esse cpf
			if(cli == null)
			{
				request.setAttribute("erro", "3");	
			}
			
			RequestDispatcher view = request.getRequestDispatcher("/locacao/nova-locacao.jsp");
			view.forward(request, response);
		}
		else if(request.getParameter("acao").equals("pagamento"))
		{
			Locacao loc = getLocacao(request);
			Automovel auto = null;
			try {
				auto = getAutomovel(request);
			} catch (GenericException e) {
				e.printStackTrace();
			}
			Cliente cli = getCliente(request);
			loc.setAutomovel(auto);
			loc.setCliente(cli);

			int idLocacao = 0;
			
			try {
				idLocacao = loc.inserir();
				
			} catch (GenericException e) {
				e.printStackTrace();
			}
			
			//Se for diferente de 0, quer dizer que a locação foi inserida com sucesso
			if(idLocacao != 0)
			{				
				loc.setIDLocacao(idLocacao);
			
				//Se o tipo de tarifa escolhida for simples, ir para a tela de pagamento
				if(loc.getTipoTaxaEmprestarLocacao() == 0)
				{								
					//Ir para tela de pagamento (ver qual é o nome para acertar)
					request.setAttribute("valorAPagar", loc.getValorLocacao() + "");
					request.setAttribute("codigo", idLocacao + "");
					request.setAttribute("urlAnterior", "/locacao/nova-locacao.jsp");
					

					HttpSession sessao = request.getSession();
					sessao.setAttribute("dadosLoc", loc);
					
					
					RequestDispatcher view = request.getRequestDispatcher("/pagamento/EscolhaFormaDePagamento.jsp");
					view.forward(request, response);
					

				}
				else //Na tarifa controlada, o pagamento é feito na devolução, ir para tela de locação efetuada
				{
					request.setAttribute("locacao", loc);

					RequestDispatcher view = request.getRequestDispatcher("/locacao/locacao-sucesso.jsp");
					view.forward(request, response);
				}
								
			}
			else //Não conseguiu cadastrar a locacao
			{
				request.setAttribute("locacao", loc);
				request.setAttribute("automovel", auto);
				request.setAttribute("cliente", cli);
				request.setAttribute("erro", "1");
				RequestDispatcher view = request.getRequestDispatcher("/locacao/nova-locacao.jsp");
				view.forward(request, response);
			}
		}
		
	}
	
	//Pega os dados do automovel e retorna um objeto da classe Automovel
	protected Automovel getAutomovel(HttpServletRequest request) throws GenericException
	{
		Automovel auto = new Automovel();
		auto.setIDAutomovel(Integer.parseInt(request.getParameter("idAutomovel")));
				
		return auto.consultarPorID();
	}
	
	protected Locacao getLocacao(HttpServletRequest request)
	{
		Locacao locacao = new Locacao();

		locacao.setAgenciaEmprestimoLocacao(request.getParameter("agenciaLocacao"));
		locacao.setAgenciaPrevistaDevolucaoLocacao(request.getParameter("agenciaDevolucao"));
		locacao.setLocalEmprestimoLocacao(request.getParameter("cidadeLocacao"));
		locacao.setLocalPrevistaDevolucaoLocacao(request.getParameter("cidadeDevolucao"));
		String modoTarifa = request.getParameter("modoTarifa");
		if(modoTarifa.equals("simples"))
		{
			locacao.setTipoTaxaEmprestarLocacao(0);
		}
		else
		{
			locacao.setTipoTaxaEmprestarLocacao(1);
		}
		
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date dataLocacao = null;
        Date dataDevolucao = null;
        
        try
        {
        	//Formata a data do datetime-local input para data do java
        	dataLocacao = f.parse(request.getParameter("dataLocacao").replace("T", " "));
        	dataDevolucao = f.parse(request.getParameter("dataDevolucao").replace("T", " "));
        	
        }
        catch(Exception e)
        {
        	e.printStackTrace();        
        }
        
        locacao.setDataEmprestimoLocacao(dataLocacao);
        locacao.setDataPrevistaDevolucaoLocacao(dataDevolucao);
		return locacao;
	}
	
	protected Cliente getCliente(HttpServletRequest request)
	{
		Cliente cli = new Cliente();
		
		int idCliente = Integer.parseInt(request.getParameter("idCliente"));

		
		//Cliente já foi consultado, então só precisa do id
		if(idCliente != 0)
		{
			cli.setIDCliente(idCliente);
		}
		//Cliente ainda não consultado, realizar a consulta
		else
		{
		
			cli.setCpfCliente(request.getParameter("cpfCliente").replace(".", "").replace("-",""));			
			try {
				cli = cli.consultarCliente();
			} catch (GenericException e) {
				e.printStackTrace();
			}
		}
		
		return cli;
	}

}
