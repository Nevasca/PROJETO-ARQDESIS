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
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pt">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css"
	href="../bootstrap/css/bootstrap.min.css">
<script src="../scripts/jquery-1.11.3.min.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
<script src="../scripts/navegacao.js"></script>
<script src="../scripts/validacaoCliente.js"></script>

<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/sidebar.css">
<title>Cadastro</title>

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
				<h1>Novo Cliente</h1>
				<p>Informe os dados do Cliente</p>
			</div>
			<span class="campo-obrigatorio">* Campo obrigatório</span><br> <br>
			<form name="cadastroCli" method="post"
				action="cadastroCliente.do"
				onsubmit="return validarCadastroCliente();">

				<div class="form-group">
					<label>Nome: <span class="campo-obrigatorio">*</span></label> <input
						type="text" id="nome" name="nome" class="form-control"
						onkeyup="numeros( this );" maxlength="50"> <span
						class="erro-form" id="erroNome"></span>
				</div>
				<div class="form-group">
					<label>CPF:</label> <input type="text" id="cpf" name="cpf"
						class="form-control" onkeypress="mascara(this, cpf_mask);"
						maxlength="14"> <span class="erro-form" id="erroCpf"></span>
				</div>
				<div class="form-group">
					<label>RG:<span class="campo-obrigatorio">*</span></label> <input
						type="text" id="rg" name="rg" class="form-control"
						onkeypress="mascara(this, rg_mask);" maxlength="11">
					<!-- required oninvalid="this.setCustomValidity('Campo ObrigatÃ³rio')" -->
					<span class="erro-form" id="erroRg"></span>
				</div>
				<div class="form-group">
					<label>Email: <span class="campo-obrigatorio">*</span></label> <input
						type="text" id="email " name="email" class="form-control"
						maxlength="50"> <span class="erro-form" id="erroEmail"></span>
				</div>
				<div class="form-group">
					<label>Telefone: <span class="campo-obrigatorio">*</span></label> <input
						type="text" id="telefone" name="telefone" class="form-control"
						onkeypress="mascara(this, telefone_mask);" maxlength="13">
					<span class="erro-form" id="erroTelefone"></span>
				</div>
				<div class="form-group">
					<label>Estado: <span class="campo-obrigatorio">*</span></label> <select
						id="uf" name="uf" class="form-control">
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
					<label>Numero Registro: <span class="campo-obrigatorio">*</span></label>
					<input type="text" id="numeroRegistro" name="numeroRegistro"
						class="form-control" onkeyup="letras( this );" maxlength="20">
					<span class="erro-form" id="erroNumeroRegistro"></span>
				</div>
				<div class="form-group">
					<label>Nascimento: <span class="campo-obrigatorio">*</span></label>
					<input type="date" id="nascimento" name="nascimento"
						class="form-control"> <span class="erro-form"
						id="erroNascimento"></span>
				</div>
				<div class="form-group">
					<label>Sexo: <span class="campo-obrigatorio">*</span></label><br>
					<input type="radio" id="sexo" name="sexo" value="Masculino"
						value="sexo" checked>Masculino <input type="radio"
						id="sexo" name="sexo" value="Feminino">Feminino <span
						class="erro-form" id="erroSexo"></span>
				</div>
				<div class="form-group">
					<label>CNH: <span class="campo-obrigatorio">*</span></label> <input
						type="text" id="cnh" name="cnh" class="form-control" maxlength="9">
					<span class="erro-form" id="erroCnh"></span>
				</div>
				<div class="form-group">
					<label>Validade CNH: <span class="campo-obrigatorio">*</span></label>
					<input type="date" id="validadeCnh" name="validadeCnh"
						class="form-control"> <span class="erro-form"
						id="erroValidadeCnh"></span>
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