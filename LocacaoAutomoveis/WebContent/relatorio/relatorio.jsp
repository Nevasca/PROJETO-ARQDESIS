<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="to.*" %>
<%@ page import="model.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
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
	
	//Somente supervisor pode consultar o relatorio
	if(acesso == 1)
	{
		RequestDispatcher view = request.getRequestDispatcher("/acesso-negado.jsp");
		view.forward(request, response);
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.min.css">
<script src="../scripts/jquery-1.11.3.min.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
<script src="../scripts/navegacao.js"></script>
<script src="../scripts/relatorio.js"></script>

<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/sidebar.css">
<title>Relatório Locações</title>

 
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
		<h1>Relatório de Locações Diárias</h1>
		<p>Informe a data de locação para buscar os registro do dia</p>
	</div>
	<form role="form" name="formRelatorio" method="post" action="consultaLocacoesDiarias.do" onsubmit="return validarRelatorio();">
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label>Data Locação: </label>
					<input type="date" class="form-control" name="dataLocacao">
					<span class="erro-form" id="erroData"></span>
				</div>
				<div class="row">
				<button type="submit" class="btn btn-success">
					<span class="glyphicon glyphicon-search"></span> Consultar
				</button>
				</div>			
		</div>
		
		
	</form>
	
	<%
		//Verifica se ja foi realizado uma consulta
		if(request.getAttribute("listaLocacoes") != null)
		{
			LocacaoTO locTO = (LocacaoTO)request.getAttribute("listaLocacoes");
			ArrayList<Locacao> lista = locTO.getLista();
			int tam = lista.size();
			
			if(tam == 0)
			{
				out.println("<div class='row'><div class='col-md-12'><h3>A busca não retornou resultados :/</h3></div></div>");
			}
			else
			{
				DateFormat data = new SimpleDateFormat("dd/MM/yyyy");
				DateFormat dataHora = new SimpleDateFormat("dd/MM/yyyy hh:mm");
				out.println("<div class='row'><div class='col-md-12'>");
				out.println("<h4 style='margin-top: 50px;'>Locações no dia " + 
						data.format(lista.get(0).getDataEmprestimoLocacao()) + 
						": <b>" + tam + "</b></h4><br><br>");
				
				out.println("<table class='table table-hover'>");
				out.println("<thead>");
				out.println("<tr>");
				out.println("<th>Data Devolução</th>");
				out.println("<th>Agência Locação</th>");
				out.println("<th>Agência Devolução</th>");
				out.println("<th>Cidade Locação</th>");
				out.println("<th>Cidade Devolução</th>");
				out.println("<th>Tipo Tarifa</th>");
				
				//out.println("<th>Ações</th>");
				out.println("</tr>");
				out.println("</thead>");
				out.println("<tbody>");
				
				for(int i = 0; i < tam; i++)
				{
					out.println("<tr>");
					out.println("<td>" + dataHora.format(lista.get(i).getDataPrevistaDevolucaoLocacao()) + "</td>");
					out.println("<td>" + lista.get(i).getAgenciaEmprestimoLocacao() + "</td>");
					out.println("<td>" + lista.get(i).getAgenciaPrevistaDevolucaoLocacao() + "</td>");
					out.println("<td>" + lista.get(i).getLocalEmprestimoLocacao() + "</td>");
					out.println("<td>" + lista.get(i).getLocalPrevistaDevolucaoLocacao() + "</td>");
					out.println("<td>" + lista.get(i).getNomeTipoTarifa() + "</td>");
					
					/*
					out.println("<td><button type=\"button\" onClick=\"detalhar(" + 
						lista.get(i).getId() + 
						")\" class=\"btn btn-primary btn-sm\"><span class=\"glyphicon glyphicon-edit\"></span> Detalhes</button></td>");
					*/
					out.println("</tr>");
					
				}
				
				out.println("</tbody>");
				out.println("</table>");
				out.println("</div></div>");
			}
			
		}
	%>
	</div>
	<form id="formDetalhes" method="get" action="detalhes.do">
		<input id="idDetalhes" type="hidden" name="id">
	</form>
</body>
</html>