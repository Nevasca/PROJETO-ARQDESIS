<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="to.*"%>
<%@ page import="model.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Login"%>
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
<link rel="stylesheet" type="text/css"
	href="../bootstrap/css/bootstrap.min.css">
<script src="../scripts/jquery-1.11.3.min.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
<script src="../scripts/navegacao.js"></script>

<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/sidebar.css">

<script type="text/javascript" src="../scripts/jquery.js"></script>
<script type="text/javascript" src="../scripts/jquery.maskedinput.js"></script>

<title>Consulta Automóvel</title>

<script type="text/javascript">

$(document).ready(function() {
	$("#placa").mask("aaa-9999");
});
	
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
	<span id="top-link-block" class="volta-topo"> <a href="#topo"
		class="well well-sm"
		onclick="$('html,body').animate({scrollTop:0},'fast');return false;">
			<span class="glyphicon glyphicon-chevron-up"></span>
	</a>
	</span>
	<div class='cobre-pagina'></div>

	<script>
		setBarraTopo();
		setMenu('<%out.print(nomeUsuario);%>');

		$(document).ready(function() {
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
			<h1>Consulta de Automóveis</h1>
			<p>Informe os dados para consulta ou deixe em branco para buscar
				todos os veículos</p>
		</div>
		<form method="post" action="consultaAutomovel.do">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label>Modelo: </label> <input type="text" class="form-control"
							name="modelo" maxlength="30">
					</div>
					<div class="form-group">
						<label>Fabricante:</label> <input type="text" class="form-control"
							name="fabricante" maxlength="30">
					</div>
					<div class="form-group">
						<label>Grupo:</label> <select name="grupo" class="form-control"
							name="grupo">
							<option value="Não">Selecione...</option>
							<option value="A">A – Econômico</option>
							<option value="C">C – Econômico com Ar</option>
							<option value="F">F – Intermediário</option>
							<option value="G">G – Intermediário Wagon Especial</option>
							<option value="H">H – Executivo</option>
							<option value="I">I – Utilitário</option>
							<option value="K">K – Executivo Luxo</option>
							<option value="M">M – Intermediário Wagon</option>
							<option value="N">N – Pick-up</option>
							<option value="P">P – 4 x 4 Especial</option>
							<option value="R">R – Minivan</option>
							<option value="U">U – Furgão</option>
							<option value="Y">Y – Blindado</option>
						</select>
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Placa:</label> <input type="text" class="form-control"
							name="placa" id="placa">
					</div>
					<div class="form-group">
						<label>Cidade:</label> <input type="text" class="form-control"
							name="cidade" maxlength="25">
					</div>
					<div class="form-group">
						<label>Acessório:</label> <select name="acessorio"
							class="form-control" name="acessorio">							
						    <option value="0">Selecione...</option>							
							<option value="1">Navegador GPS</option>
							<option value="2">Cadeira de Bebê</option>
							<option value="3">Motorista</option>
						</select>
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label>Chassi:</label> <input type="text" class="form-control"
							name="chassi" maxlength="20">
					</div>
					<div class="form-group">
						<label>Estado:</label> <select name="uf" class="form-control"
							name="uf">
							<option value="Não">Selecione...</option>
							<option value="AC">AC</option>
							<option value="AL">AL</option>
							<option value="AP">AP</option>
							<option value="AM">AM</option>
							<option value="BA">BA</option>
							<option value="CE">CE</option>
							<option value="DF">DF</option>
							<option value="ES">ES</option>
							<option value="GO">GO</option>
							<option value="MA">MA</option>
							<option value="MT">MT</option>
							<option value="MS">MS</option>
							<option value="MG">MG</option>
							<option value="PR">PR</option>
							<option value="PB">PB</option>
							<option value="PA">PA</option>
							<option value="PE">PE</option>
							<option value="PI">PI</option>
							<option value="RJ">RJ</option>
							<option value="RN">RN</option>
							<option value="RS">RS</option>
							<option value="RO">RO</option>
							<option value="RR">RR</option>
							<option value="SC">SC</option>
							<option value="SE">SE</option>
							<option value="SP">SP</option>
							<option value="TO">TO</option>
						</select>
					</div>
				</div>
			</div>
			<button type="submit" class="btn btn-success">
				<span class="glyphicon glyphicon-search"></span> Consultar
			</button>

		</form>
		<br> <br> <br>
		<%
			//Verifica se ja foi realizado uma consulta
			if (request.getAttribute("listaAutomoveis") != null) {
				AutomovelTO autoTO = (AutomovelTO) request
						.getAttribute("listaAutomoveis");
				ArrayList<Automovel> lista = autoTO.getLista();
				int tam = lista.size();

				if (tam == 0) {
					out.println("<h3>A busca não retornou resultados :/</h3>");
				} else {
					out.println("<table class='table table-hover'>");
					out.println("<thead>");
					out.println("<tr>");
					out.println("<th>Grupo</th>");
					out.println("<th>Modelo</th>");
					out.println("<th>Fabricante</th>");
					out.println("<th>Acessórios</th>");
					//out.println("<th>Chassi</th>");
					//out.println("<th>Placa</th>");
					out.println("<th>Cidade</th>");
					out.println("<th>UF</th>");
					//out.println("<th>KM</th>");

					out.println("<th>Tarifa</th>");
					out.println("<th>Tarifa Controlada</th>");
					out.println("<th>Ações</th>");
					out.println("</tr>");
					out.println("</thead>");
					out.println("<tbody>");

					for (int i = 0; i < tam; i++) {
						out.println("<tr>");
						out.println("<td>"
								+ lista.get(i).getNomegruposAutomovel()
								+ "</td>");
						out.println("<td>" + lista.get(i).getModeloAutomovel()
								+ "</td>");
						out.println("<td>"
								+ lista.get(i).getFabricanteAutomovel()
								+ "</td>");
						out.println("<td>" + lista.get(i).getNomeAcessorio()
								+ "</td>");
						//out.println("<td>" + lista.get(i).getChassi() + "</td>");
						//out.println("<td>" + lista.get(i).getPlaca() + "</td>");
						out.println("<td>" + lista.get(i).getCidadeAutomovel()
								+ "</td>");
						out.println("<td>" + lista.get(i).getUFAutomovel()
								+ "</td>");
						//out.println("<td>" + lista.get(i).getQuilometragem() + "</td>");

						out.println("<td>" + lista.get(i).getTarifaAutomovel()
								+ "</td>");
						out.println("<td>"
								+ lista.get(i).getTarifaControladaAutomovel()
								+ "</td>");
						//out.println("<td><span class='glyphicon glyphicon-share'></span> ");
						out.println("<td><button type=\"button\" onClick=\"detalhar("
								+ lista.get(i).getIDAutomovel()
								+ ")\" class=\"btn btn-primary btn-sm\"><span class=\"glyphicon glyphicon-edit\"></span> Detalhes</button></td>");
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