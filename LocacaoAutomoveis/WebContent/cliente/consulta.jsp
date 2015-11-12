<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="to.*" %>
<%@ page import="model.*" %>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Login" %>
<%
	String nomeUsuario = "";
	int acesso = -1;
	
	Login login = (Login)session.getAttribute("dadosAcesso");
	
	if(login == null)
	{
		RequestDispatcher view = request.getRequestDispatcher("/login.jsp");
		view.forward(request, response);
	}
	else
	{
		nomeUsuario = login.getNomeUsuario();
		acesso = login.getTipoUsuario();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pt">
<head>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.min.css">
<script src="../scripts/jquery-1.11.3.min.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
<script src="../scripts/navegacao.js"></script>
<script src="../scripts/validacaoCliente.js"></script>
<script src="../scripts/validacaoAutomovel.js"></script>

<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/sidebar.css">
<title>Consulta Automovel</title>

</head>
<body>

<script>
	
		function detalhar(id)
		{
			document.getElementById("idDetalhes").value = id;
			document.getElementById("formDetalhes").submit();
		}
	
	</script>
	
</head>
<body>

	<!-- Carrega os itens de navegacao e menu em comum -->
	<div id="barraTopo"></div>
	<div id="menu"></div>
	<span id="top-link-block" class="volta-topo">
    	<a href="#topo" class="well well-sm" onclick="$('html,body').animate({scrollTop:0},'fast');return false;">
        	<span class="glyphicon glyphicon-chevron-up"></span>
    	</a>
	</span>
	<div class='cobre-pagina'></div>
	
	<script>
		setBarraTopo();
		setMenu('<%out.print(nomeUsuario);%>');
		
		$(document).ready(function(){
	    	$("#menu-toggle").click(function() {
	        	$("#wrapper").toggleClass("toggled");
	        	$("body").toggleClass("pagina-coberta");
	        	$(".cobre-pagina").css("opacity", 0.6).fadeToggle("slow");
	        	
	    	});
		});
	</script>
    <!-- Fim da navegacao em comum -->
    
	<div class="container">
	<div class="jumbotron">
		<h1>Consulta de Clientes</h1>
		<p>Informe os dados para consulta ou deixe em branco para buscar todos os clientes</p>
	</div>
	<form method="post" action="consultaCliente.do">
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label>Nome: </label>
					<input type="text" class="form-control" name="nome" onkeyup="numeros( this );" maxlength="50">
				</div>
				<div class="form-group">
					<label>CPF:</label>
					<input type="text" class="form-control" name="cpf" onkeypress="mascara(this, cpf_mask);" maxlength="14">
				</div>							
			</div>
		</div>
		<button type="submit" class="btn btn-success">Consultar</button>
		
	</form>
	<br><br><br>
	<%
		//Verifica se ja foi realizado uma consulta
		if(request.getAttribute("listaClientes") != null)
		{
			ClienteTO clienteTO = (ClienteTO)request.getAttribute("listaClientes");
			ArrayList<Cliente> lista = clienteTO.getLista();
			int tam = lista.size();
			
			if(tam == 0)
			{
				out.println("<h3>A busca não retornou resultados :/</h3>");
				
			}
			else
			{
				String dataNasc = "";
				String dataValidadeCNH = "";
				
				DateFormat data = new SimpleDateFormat("dd/MM/yyyy");
		       
				out.println("<table class='table table-hover'>");
				out.println("<thead>");
				out.println("<tr>");
				out.println("<th>Nome</th>");
				out.println("<th>CPF</th>");
				out.println("<th>RG</th>");
				out.println("<th>Email</th>");
				out.println("<th>Telefone</th>");
				out.println("<th>UF</th>");
				out.println("<th>Numero Registro</th>");
				out.println("<th>Nascimento</th>");
				out.println("<th>Sexo</th>");
				out.println("<th>Validade CNH</th>");
				out.println("</tr>");
				out.println("</thead>");
				out.println("<tbody>");
				
				for(int i = 0; i < tam; i++)
				{
					out.println("<tr>");
					out.println("<td>" + lista.get(i).getNomeCliente() + "</td>");
					out.println("<td>" + lista.get(i).getCpfCliente() + "</td>");
					out.println("<td>" + lista.get(i).getRgCliente() + "</td>");
					out.println("<td>" + lista.get(i).getEmailCliente() + "</td>");
					out.println("<td>" + lista.get(i).getTelCliente() + "</td>");
					out.println("<td>" + lista.get(i).getUfCliente() + "</td>");
					out.println("<td>" + lista.get(i).getRegistroCliente() + "</td>");
					
					 dataNasc = data.format(lista.get(i).getDataNascCliente());
				     dataValidadeCNH = data.format(lista.get(i).getDataValidadeCNH());	        	        
						
					out.println("<td>" + dataNasc + "</td>");
					out.println("<td>" + lista.get(i).getGeneroCliente() + "</td>");
					out.println("<td>" + dataValidadeCNH + "</td>");
					//out.println("<td><span class='glyphicon glyphicon-share'></span> ");
					out.println("<td><button type=\"button\" onClick=\"detalhar(" + 
						lista.get(i).getIDCliente() + 
						")\" class=\"btn btn-primary btn-sm\"><span class=\"glyphicon glyphicon-edit\"></span> Detalhes</button></td>");
					//out.println("<td>" + lista.get(i).getId() + "</td>");
					out.println("</tr>");
					
				}
				
				out.println("</tbody>");
				out.println("</table>");
				
				
				
			}
			
			
		}
	%>
	</div>
	<form id="formDetalhes" method="get" action="detalhes.do">
		<input id="idDetalhes" type="hidden" name="id">
	</form>
</body>
</html>