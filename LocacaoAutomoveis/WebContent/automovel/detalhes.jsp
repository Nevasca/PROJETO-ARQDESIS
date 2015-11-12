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
	String disabled = "";
	
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
	
	//Somente supervisor pode alterar e excluir automovel. Coloca hidden nos botoes de alterar e excluir
	if(acesso == 1)
	{
		disabled = "disabled='disabled'"; 
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
	<script src="../scripts/validacao.js"></script>
	<script src="../scripts/automovel.js"></script>
	<link rel="stylesheet" type="text/css" href="../css/style.css">
	<link rel="stylesheet" type="text/css" href="../css/sidebar.css">
	
<script type="text/javascript" src="../scripts/jquery.js"></script>
<script type="text/javascript" src="../scripts/jquery.maskedinput.js"></script>

<script type="text/javascript" src="../scripts/jquery.maskMoney.js"></script>
	
<script type="text/javascript">
	$(document).ready(function() {
		$("#placa").mask("aaa-9999");
		$("#tarifa").maskMoney({thousands:'', decimal:'.'});
		$("#tarifaControlada").maskMoney({thousands:'', decimal:'.'});
		$("#quilometragem").maskMoney({thousands:'', decimal:'.', symbol:'KM ', showSymbol:true, symbolStay:true});
	});
</script>

	<title>Detalhes Automóvel</title>
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

	<%
		//Verifica se há um automóvel consultado anteriormente
		Automovel auto = null;
		String grupo = null;
		int acessorio = 0;
		String uf = null;
	
		if(request.getAttribute("automovelConsultado") != null)
		{
			auto = (Automovel) request.getAttribute("automovelConsultado");
			grupo = auto.getGruposAutomovel();
			acessorio = auto.getAcessoriosAutomovel();
			uf = auto.getUFAutomovel();
		}
		else
		{
			RequestDispatcher view = request.getRequestDispatcher("consulta.jsp");
			view.forward(request, response);
		}				
	%>
	<div class="container">
		<%
			//Verifica se é um retorno de alteração e dá a mensagem de acordo
			if(request.getAttribute("alterado") != null)
			{
				
				boolean alterado = (Boolean)request.getAttribute("alterado");
				if(alterado)
				{
					out.print("<div class='jumbotron jumb-sucesso-sm'>Automóvel alterado com sucesso!</div>");
				}
				else
				{
					out.print("<div class='jumbotron jumb-alerta'>Não foi possível alterar o automóvel</div>");
				}
			}
		
		//Verifica se está voltando de um erro na exclusao
		if(request.getAttribute("erroExclusao") != null)
		{
			String erro = (String)request.getAttribute("erroExclusao");
			if(erro.equals("1"))
			{
				out.print("<div class='jumbotron jumb-alerta'>Não foi possível excluir o automóvel</div>");
			}
		}
		%>
		
	<form name="cadastroAuto" method="post">
	<input type="hidden" name="idAutomovel" value="<%out.print(auto.getIDAutomovel());%>">
	<div class="row well">
		<h4><span class="glyphicon glyphicon-wrench"></span> <%out.print(auto.getModeloAutomovel());%> <i><%out.print(auto.getFabricanteAutomovel());%></i></h4>
		<div class="col-md-6">
		<div class="form-group">
  			<label>Grupo:</label>
            <select name="grupo" class="form-control">
            	<!-- Verifica qual o grupo do automovel e coloca como selecionado se for o mesmo do option -->
            	<option value="A" <% if(grupo.equals("A")){ out.print("selected");} %>>A – Econômico</option>
                <option value="C" <% if(grupo.equals("C")){ out.print("selected");} %>>C – Econômico com Ar</option>
                <option value="F" <% if(grupo.equals("F")){ out.print("selected");} %>>F – Intermediário</option>
                <option value="G" <% if(grupo.equals("G")){ out.print("selected");} %>>G – Intermediário Wagon Especial</option>
                <option value="H" <% if(grupo.equals("H")){ out.print("selected");} %>>H – Executivo</option>
                <option value="I" <% if(grupo.equals("I")){ out.print("selected");} %>>I – Utilitário</option>
                <option value="K" <% if(grupo.equals("K")){ out.print("selected");} %>>K – Executivo Luxo</option>
                <option value="M" <% if(grupo.equals("M")){ out.print("selected");} %>>M – Intermediário Wagon</option>
                <option value="N" <% if(grupo.equals("N")){ out.print("selected");} %>>N – Pick-up</option>
                <option value="P" <% if(grupo.equals("P")){ out.print("selected");} %>>P – 4 x 4 Especial</option>	
                <option value="R" <% if(grupo.equals("R")){ out.print("selected");} %>>R – Minivan</option>
                <option value="U" <% if(grupo.equals("U")){ out.print("selected");} %>>U – Furgão</option>
                <option value="Y" <% if(grupo.equals("Y")){ out.print("selected");} %>>Y – Blindado</option>
            </select>
            <span class="erro-form" id="erroGrupo"></span>
   		</div>
		<div class="form-group">
			<label>Modelo:</label>
			<input type="text" name="modelo" value="<%out.print(auto.getModeloAutomovel());%>" class="form-control" maxlength="25"/>
			<span class="erro-form" id="erroModelo"></span>
		</div>
		<div class="form-group">
			<label>Fabricante:</label>
			<input type="text" name="fabricante" value="<%out.print(auto.getFabricanteAutomovel());%>" class="form-control" maxlength="30"/>
			<span class="erro-form" id="erroFabricante"></span>
		</div>
		<div class="form-group">
  			<label>Acessório:</label>
            <select name="acessorio" class="form-control">
            	<!-- Verifica qual o acessorioo do automovel e coloca como selecionado se for o mesmo do option -->
            	<option value="0" <% if(acessorio == 0){ out.print("selected");} %>>Sem acessório</option>
                <option value="1" <% if(acessorio == 1){ out.print("selected");} %>>Navegador GPS</option>
                <option value="2" <% if(acessorio == 2){ out.print("selected");} %>>Cadeira de Bebê</option>
                <option value="3" <% if(acessorio == 3){ out.print("selected");} %>>Motorista</option>                
            </select>
   		</div>
   		<div class="form-group">
			<label>Cidade:</label>
			<input type="text" name="cidade" value="<%out.print(auto.getCidadeAutomovel());%>" class="form-control"/>
			<span class="erro-form" id="erroCidade"></span>
		</div>
		<div class="form-group">
   			<label>Estado:</label>
   			<!-- Verifica qual a UF do automovel e coloca como selecionado se for o mesmo do option -->
   			<select name="uf" class="form-control">
   			<option value="AC">AC</option>
                    <option value="AL" <% if(uf.equals("AL")){ out.print("selected");} %>>AL</option>
                    <option value="AP" <% if(uf.equals("AP")){ out.print("selected");} %>>AP</option>
                    <option value="AM" <% if(uf.equals("AM")){ out.print("selected");} %>>AM</option>
                    <option value="BA" <% if(uf.equals("BA")){ out.print("selected");} %>>BA</option>
                    <option value="CE" <% if(uf.equals("CE")){ out.print("selected");} %>>CE</option>
                    <option value="DF" <% if(uf.equals("DF")){ out.print("selected");} %>>DF</option>
                    <option value="ES" <% if(uf.equals("ES")){ out.print("selected");} %>>ES</option>
                    <option value="GO" <% if(uf.equals("GO")){ out.print("selected");} %>>GO</option>
                    <option value="MA" <% if(uf.equals("MA")){ out.print("selected");} %>>MA</option>
                    <option value="MT" <% if(uf.equals("MT")){ out.print("selected");} %>>MT</option>
                    <option value="MS" <% if(uf.equals("MS")){ out.print("selected");} %>>MS</option>
                    <option value="MG" <% if(uf.equals("MG")){ out.print("selected");} %>>MG</option>
                    <option value="PR" <% if(uf.equals("PR")){ out.print("selected");} %>>PR</option>
                    <option value="PB" <% if(uf.equals("PB")){ out.print("selected");} %>>PB</option>
                    <option value="PA" <% if(uf.equals("PA")){ out.print("selected");} %>>PA</option>
                    <option value="PE" <% if(uf.equals("PE")){ out.print("selected");} %>>PE</option>
                    <option value="PI" <% if(uf.equals("PI")){ out.print("selected");} %>>PI</option>
                    <option value="RJ" <% if(uf.equals("RJ")){ out.print("selected");} %>>RJ</option>
                    <option value="RN" <% if(uf.equals("RN")){ out.print("selected");} %>>RN</option>
                    <option value="RS" <% if(uf.equals("RS")){ out.print("selected");} %>>RS</option>
                    <option value="RO" <% if(uf.equals("RO")){ out.print("selected");} %>>RO</option>
                    <option value="RR" <% if(uf.equals("RR")){ out.print("selected");} %>>RR</option>
                    <option value="SC" <% if(uf.equals("SC")){ out.print("selected");} %>>SC</option>
                    <option value="SE" <% if(uf.equals("SE")){ out.print("selected");} %>>SE</option>
                    <option value="SP" <% if(uf.equals("SP")){ out.print("selected");} %>>SP</option>
                    <option value="TO" <% if(uf.equals("TO")){ out.print("selected");} %>>TO</option>
   			</select>
   			<span class="erro-form" id="erroEstado"></span>
   		</div>
   		</div> <!-- fim coluna -->   		
   		<div class="col-md-6">
		<div class="form-group">
			<label>Número do Chassi:</label>
			<input type="text" name="chassi" value="<%out.print(auto.getChassiAutomovel());%>" maxlength="20" class="form-control"/>
			<span class="erro-form" id="erroChassi"></span>
		</div>
		<div class="form-group">
			<label>Número da Placa:</label>
			<input type="text" id="placa" name="placa" value="<%out.print(auto.getPlacaAutomovel());%>" class="form-control"/>
			<span class="erro-form" id="erroPlaca"></span>
		</div>
		
		<div class="form-group">
			<label>Quilometragem:</label>
			<input type="text" id="quilometragem" name="quilometragem" value="<%out.print(auto.getKmRodadoAutomovel());%>" class="form-control"/>
			<span class="erro-form" id="erroQuilometragem"></span>
		</div>
		
		<div class="form-group">
			<label>Tarifa:</label>
			<input type="text" id="tarifa" name="tarifa" value="<%out.print(auto.getTarifaAutomovel());%>" class="form-control"/>
			<span class="erro-form" id="erroTarifa"></span>
		</div>
		<div class="form-group">
			<label>Tarifa Controlada:</label>
			<input type="text" id="tarifaControlada" name="tarifaControlada" value="<%out.print(auto.getTarifaControladaAutomovel());%>" class="form-control"/>
			<span class="erro-form" id="erroTarifaControlada"></span>
  		</div>
  		</div> <!-- fim coluna -->
  		<div class="row">
  		<div class="col-md-12 text-center">
	  		<button type="button" class="btn btn-warning" onclick="alterarAutomovel()" <%out.print(disabled);%>>
	  			<span class="glyphicon glyphicon-pencil"></span> Alterar
	  		</button>
	  		<button type="button" class="btn btn-danger" onclick="$('#caixaExclusao').fadeIn('fast')" <%out.print(disabled);%>>
	  			<span class="glyphicon glyphicon-trash"></span> Excluir
	  		</button>
	  		<div id="caixaExclusao" class="jumbotron jumb-exclusao">
	  			O automóvel será excluído do sistema. Se houver locações, será apenas desativado. Deseja prosseguir com a exclusão? 
	  			<button class="btn btn-default" type="button" onclick="excluirAutomovel()"><b>Excluir</b></button>
	  			<button class="btn btn-default" type="button" onclick="$('#caixaExclusao').fadeOut('fast')">Cancelar</button>  			
	  		</div>
  		</div>
		</div>
  </form>
  
  </div>
  <div class="row well">
		<h4><span class="glyphicon glyphicon-road"></span> Locações</h4>
		<button type="button" class="btn btn-primary" onclick="locarAutomovel()">
			<span class="glyphicon glyphicon-plus"></span> Nova Locação
		</button>
		<%
		//Verifica se ja foi realizado uma consulta
		if(request.getAttribute("listaLocacoes") != null)
		{
			System.out.println("passei aqui");
			LocacaoTO locTO = (LocacaoTO)request.getAttribute("listaLocacoes");
			ArrayList<Locacao> lista = locTO.getLista();
			int tam = lista.size();
			
			if(tam == 0)
			{
				out.println("<div class='row'><div class='col-md-12'><h5>Esse automóvel ainda não foi locado. <a href='#' onclick='locarAutomovel()'>Vamos estreá-lo!</a></h5></div></div>");
				
			}
			else
			{
				DateFormat dataHora = new SimpleDateFormat("dd/MM/yyyy hh:mm");
				out.println("<div class='row'><div class='col-md-12'>");
				out.println("<h3>Locações Efetuadas</h3>");
				out.println("<table class='table table-hover'>");
				out.println("<thead>");
				out.println("<tr>");
				out.println("<th>Data Locação</th>");
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
					out.println("<td>" + dataHora.format(lista.get(i).getDataEmprestimoLocacao()) + "</td>");
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
</body>
</html>