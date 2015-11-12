package control.cliente;

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


/**
 * Servlet implementation class AlteracaoAutomovelCTRL
 */
@WebServlet("/cliente/alteracao.do")
public class AlteracaoClienteControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlteracaoClienteControl() {
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
		Cliente cliente = new Cliente();
		cliente.setIDCliente(Integer.parseInt(request.getParameter("idCliente")));
		cliente.setNomeCliente(request.getParameter("nome")); 
		cliente.setCpfCliente(request.getParameter("cpf"));
		cliente.setRgCliente(request.getParameter("rg"));
		cliente.setEmailCliente(request.getParameter("email"));
		cliente.setTelCliente(request.getParameter("telefone"));
		cliente.setUfCliente(request.getParameter("uf"));
		cliente.setRegistroCliente(request.getParameter("numeroRegistro"));
		
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date dataNasc = null;
        Date dataCNH = null;
        
        try
        {
        	//Formata a data do date input para data do java
        	dataNasc = f.parse(request.getParameter("nascimento").replace("T", ""));
        	dataCNH = f.parse(request.getParameter("validadeCnh").replace("T", ""));

        	
        }
        catch(Exception e)
        {
        	e.printStackTrace();        
        }
        		
		cliente.setDataNascCliente(dataNasc);
		cliente.setGeneroCliente(request.getParameter("sexo"));
		cliente.setDataValidadeCNH(dataCNH);
		cliente.setCNHCliente(request.getParameter("cnh"));
				
		//Boolean para informar usuario se alterou ou nao
		try {
			if(cliente.alterar())
			{			
				request.setAttribute("alterado", true);
				request.setAttribute("clienteConsultado", cliente);
			}
			else
			{
				
				request.setAttribute("alterado", false);
			}
		} catch (GenericException e) {
			e.printStackTrace();
		}				
		
		//Volta para a mesma tela de detalhes para tratar se alterou ou nao
		RequestDispatcher view = request.getRequestDispatcher("detalhes.do?id=" + cliente.getIDCliente());
		view.forward(request, response);
				
	}

}
