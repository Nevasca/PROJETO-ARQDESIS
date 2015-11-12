<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
        
<link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.min.css">

<script src="../scripts/jquery-1.11.3.min.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>

<script src="../scripts/navegacao.js"></script>

<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/sidebar.css">
<title>BEG</title>
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
    
    	<div class="col-md-12">
    		<div class="jumbotron jumb-sucesso">
    			<h3><span class="glyphicon glyphicon-ok"></span> Automóvel cadastrado com sucesso</h3>
    			<p>Ele está na garagem esperando para ser alugado!</p>
    		</div>    		
    	</div>
    </div>
</body>
</html>