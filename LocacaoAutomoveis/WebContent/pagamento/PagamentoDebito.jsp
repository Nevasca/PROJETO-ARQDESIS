<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Login"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pagamento</title>


<link rel="stylesheet" type="text/css"
	href="../bootstrap/css/bootstrap.min.css">
<script src="../scripts/jquery-1.11.3.min.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
<script src="../scripts/navegacao.js"></script>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/sidebar.css">

<script src="../scripts/pagamento.js" type="text/javascript"></script>

<script type="text/javascript" src="../scripts/jquery.js"></script>
<script type="text/javascript" src="../scripts/jquery.maskedinput.js"></script>


</head>
<script type="text/javascript">
	$(document).ready(function() {
		$("#txtCpf").mask("999.999.999-99");
		//$('#txtDataPagamento').mask('99/99/9999');
		$("#txtTelefone").mask("(99) 9999-9999");
		$('#txtNumAgenciaDebito').mask('9999');
		$('#txtNumContaDebito').mask('99.999-9');
	});
</script>
<body>
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

	<%
		double valorAPagar = Double.parseDouble(request.getAttribute(
				"valorAPagar").toString());
		valorAPagar = (valorAPagar != 0.00 ? valorAPagar : 0.00);

		String codigo = (String) request.getAttribute("codigo");
		codigo = (codigo != null ? codigo : "");

		String urlAnterior = (String) request.getAttribute("urlAnterior");
		urlAnterior = (urlAnterior != null ? urlAnterior : "");
	%>

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
		

		function fecharDiv(){
		 
			var divErro = document.getElementById('erro');	 
			divErro.style.display = 'none';
		}

		setTimeout('fecharDiv()', 10000);	
	</script>
	<!-- Fim da navegacao em comum -->


	<div class="container">
		<div class="col-md-8">

			<%
				if (request.getAttribute("error") != null) {
					out.print("<div id='erro' class='jumbotron jumb-alerta'>"
							+ request.getAttribute("error").toString() + "</div>");

				}
			%>

			<div class="jumbotron">
				<h1>Pagamento Débito</h1>
			</div>
			<span class="campo-obrigatorio">* Campo obrigatório</span><br> <br>

			<form name="pagamentoDebito" class="form-horizontal" method="post"
				onsubmit="return validarPagamento('D');" action="pagamentoDebito.do">

				<div class="form-group">
					<label for="nomeTitular">Nome Titular:<span
						class="campo-obrigatorio">*</span>
					</label> <input name="txtNomeTitular" type="text" value=""
						class="form-control" /> <span class="erro-form" id="erroTitular"></span>

				</div>

				<div class="form-group">
					<label for="cpfCliente">CPF: <span
						class="campo-obrigatorio">*</span>
					</label> <input id="txtCpf" name="txtCpf" type="text" value=""
						class="form-control" /> <span class="erro-form" id="erroCpf"></span>
				</div>
				<div class="form-group">
					<label for="valorPagamento">Valor Pagamento:<span
						class="campo-obrigatorio">*</span>
					</label> <input id="txtValorPagamento" name="txtValorPagamento" type="text"
						value="<%out.print(valorAPagar);%>"
						class="form-control" disabled /> <span class="erro-form"
						id="erroValorPagamento"></span>


				</div>
				<div class="form-group">
					<label for="dataPagamento">Data pagamento:<span
						class="campo-obrigatorio">*</span>
					</label> <input id="txtDataPagamento" name="txtDataPagamento" type="datetime-local"
						value="" class="form-control" /> <span class="erro-form"
						id="erroDataPagamento"></span>


				</div>
				<div class="form-group">
					<label for="dataPagamento">Número Agência:<span
						class="campo-obrigatorio">*</span>
					</label> <input id="txtNumAgenciaDebito" name="txtNumAgenciaDebito"
						type="text" value="" class="form-control" /> <span
						class="erro-form" id="erroNumAgenciaDebito"></span>
				</div>

				<div class="form-group">
					<label for="lblNumContaDebito">Número Conta:<span
						class="campo-obrigatorio">*</span>
					</label> <input id="txtNumContaDebito" name="txtNumContaDebito" type="text"
						value="" class="form-control" /> <span class="erro-form"
						id="erroNumContaDebito"></span>
				</div>
				<div class="form-group">
					<label for="lblBancoDebito">Banco Débito:<span
						class="campo-obrigatorio">*</span>
					</label> <select name="cbBancoDebito" size="1" class="form-control">
						<option value="SELECIONE:">SELECIONE:</option>
						<option value="BANCO DO BRASIL">BANCO DO BRASIL</option>
						<option value="BANCO ITAU">BANCO ITAU</option>
						<option value="BANCO BRADESCO">BANCO BRADESCO</option>
						<option value="BANCO DAYCOVAL">BANCO DAYCOVAL</option>
					</select> <span class="erro-form" id="erroBancoDebito"></span>
				</div>
				<div class="form-group">
					<label for="telefone">Telefone Cliente:<span
						class="campo-obrigatorio">*</span>
					</label> <input id="txtTelefone" name="txtTelefone" type="text" value=""
						class="form-control" /> <span class="erro-form" id="erroTelefone"></span>
				</div>
				<div class="form-group">
					<button class="btn btn-success" name="btnConfirmar" type="submit"
						value="Confirmar">
						<span class="glyphicon glyphicon-floppy-disk"></span> Confirmar
					</button>
					<button class="btn btn-danger" name="btnCancelar" type="button"
						value="Cancelar" onClick="javascript:history.back(1);">
						<span class="glyphicon glyphicon-chevron-left"></span>Cancelar
					</button>
				</div>
				<input id="valorAPagar" name="valorAPagar" type="hidden"
					value="<%out.print(valorAPagar);%>" /> <input id="codigo"
					name="codigo" type="hidden"
					value="<%if (codigo != null) {
				out.print(codigo);
			}%>" />
				<input id="urlAnterior" name="urlAnterior" type="hidden"
					value="<%if (urlAnterior != null) {
				out.print(urlAnterior);
			}%>" />
			</form>
		</div>
	</div>

</body>
</html>