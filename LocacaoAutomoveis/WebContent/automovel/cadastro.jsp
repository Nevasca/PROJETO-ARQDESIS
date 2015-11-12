<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Login"%>

<%
	String nomeUsuario = "";
	int acesso = -1;

	Login login = (Login) session.getAttribute("dadosAcesso");

	if (login == null) {
		RequestDispatcher view = request
				.getRequestDispatcher("/login.jsp");
		view.forward(request, response);
	} else {
		nomeUsuario = login.getNomeUsuario();
		acesso = login.getTipoUsuario();
	}

	//Somente supervisor pode cadastrar automovel
	if (acesso == 1) {
		RequestDispatcher view = request
				.getRequestDispatcher("/acesso-negado.jsp");
		view.forward(request, response);
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" type="text/css"
	href="../bootstrap/css/bootstrap.min.css">

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

<title>BEG</title>

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
		<div class="col-md-8">
			<div class="jumbotron">
				<h1>Novo Automóvel</h1>
				<p>Informe os dados do veículo</p>
			</div>
			<span class="campo-obrigatorio">* Campo obrigatório</span><br> <br>
			<form name="cadastroAuto" method="post" action="cadastroAutomovel.do"
				onsubmit="return validarCadastroAutomovel();">

				<div class="form-group">
					<label>Grupo: <span class="campo-obrigatorio">*</span></label> <select
						name="grupo" class="form-control">
						<option value="NaoSelecionado">Selecione...</option>
						<option value="A">A - Econômico</option>
						<option value="C">C - Econômico com Ar</option>
						<option value="F">F - Intermediário</option>
						<option value="G">G - Intermediário Wagon Especial</option>
						<option value="H">H - Executivo</option>
						<option value="I">I - Utilitário</option>
						<option value="K">K - Executivo Luxo</option>
						<option value="M">M - Intermediário Wagon</option>
						<option value="N">N - Pick-up</option>
						<option value="P">P - 4 x 4 Especial</option>
						<option value="R">R - Minivan</option>
						<option value="U">U - Furgão</option>
						<option value="Y">Y - Blindado</option>
					</select> <span class="erro-form" id="erroGrupo"></span>
				</div>
				<div class="form-group">
					<label>Acessório: </label> <select name="acessorio"
						class="form-control">
						<option value="0">Sem acessório</option>
						<option value="1">Navegador GPS</option>
						<option value="2">Cadeira de Bebê</option>
						<option value="3">Motorista</option>
					</select>
				</div>
				<div class="form-group">
					<label>Número do chassi: <span class="campo-obrigatorio">*</span></label>
					<input type="text" id="chassi" name="chassi" class="form-control"
						maxlength="20">
					<!-- required oninvalid="this.setCustomValidity('Campo ObrigatÃ³rio')" -->
					<span class="erro-form" id="erroChassi"></span>
				</div>
				<div class="form-group">
					<label>Número da placa: <span class="campo-obrigatorio">*</span></label>
					<input type="text" id="placa" name="placa" class="form-control">
					<span class="erro-form" id="erroPlaca"></span>
				</div>
				<div class="form-group">
					<label>Cidade: <span class="campo-obrigatorio">*</span></label> <input
						type="text" name="cidade" class="form-control" maxlength="25">
					<span class="erro-form" id="erroCidade"></span>
				</div>
				<div class="form-group">
					<label>Estado: <span class="campo-obrigatorio">*</span></label> <select
						name="uf" class="form-control">
						<option value="NaoSelecionado">Selecione...</option>
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
					</select> <span class="erro-form" id="erroEstado"></span>
				</div>
				<div class="form-group">
					<label>Quilometragem: <span class="campo-obrigatorio">*</span></label>
					<input type="text" id="quilometragem" name="quilometragem"
						class="form-control"> <span class="erro-form"
						id="erroQuilometragem"></span>
				</div>
				<div class="form-group">
					<label>Modelo: <span class="campo-obrigatorio">*</span></label> <input
						type="text" name="modelo" class="form-control" maxlength="25">
					<span class="erro-form" id="erroModelo"></span>
				</div>
				<div class="form-group">
					<label>Fabricante: <span class="campo-obrigatorio">*</span></label>
					<input type="text" name="fabricante" class="form-control"
						maxlength="30"> <span class="erro-form"
						id="erroFabricante"></span>
				</div>
				<div class="form-group">
					<label>Tarifa: <span class="campo-obrigatorio">*</span></label> <input
						type="text" id="tarifa" name="tarifa" class="form-control">
					<span class="erro-form" id="erroTarifa"></span>
				</div>
				<div class="form-group">
					<label>Tarifa controlada: <span class="campo-obrigatorio">*</span></label>
					<input type="text" id="tarifaControlada" name="tarifaControlada"
						class="form-control"> <span class="erro-form"
						id="erroTarifaControlada"></span>
				</div>
				<button class="btn btn-success" name="btnCadastrar" type="submit"
					value="Cadastrar">
					<span class="glyphicon glyphicon-plus"></span>Cadastrar
				</button>
				<br /> <br /> <br /> <br /> <br />
			</form>

		</div>
	</div>
</body>
</html>