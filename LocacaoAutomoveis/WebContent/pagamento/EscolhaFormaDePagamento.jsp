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

	<div class="container">

		<div class="jumbotron">
			<h1>Forma De Pagamento</h1>
		</div>
		<!--  method="post" action="formaPagamento.do" -->
		<form name="formaPagamento" class="form-horizontal"
			action="/LocacaoAutomoveis/pagamento/formaDePagamento.do"
			method="post">
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button class="btn btn-info" name="btnCredito" type="submit"
						value="Crédito" style="width: 200px; height: 80px;">
						<span class="glyphicon glyphicon-credit-card"></span> Crédito
					</button>
					<button class="btn btn-info" name="btnDebito" type="submit"
						value="Débito" style="width: 200px; height: 80px;">
						<span class="glyphicon glyphicon-credit-card"></span> Débito
					</button>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button class="btn btn-danger" name="btnCancelar" type="button"
						value="Cancelar" onClick="javascript:history.back(1);">
						<span class="glyphicon glyphicon-chevron-left"></span>Cancelar
					</button>
				</div>
			</div>

			<input id="valorAPagar" name="valorAPagar" type="hidden"
				value="<%out.print(valorAPagar);%>" />
			<input id="codigo" name="codigo" type="hidden"
				value="<%if (codigo != null) {
				out.print(codigo);
			}%>" />
			<input id="urlAnterior" name="urlAnterior" type="hidden"
				value="<%if (urlAnterior != null) {
				out.print(urlAnterior);
			}%>" />
		</form>
	</div>
</body>
</html>