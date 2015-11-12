<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Login"%>
<%@ page import="model.Devolucao"%>
<%@ page import="model.Locacao"%>
<%@ page import="model.Automovel"%>

<%@ page import="model.GenericException"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Devolução</title>

<link rel="stylesheet" type="text/css"
	href="../bootstrap/css/bootstrap.min.css">
<script src="../scripts/jquery-1.11.3.min.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
<script src="../scripts/navegacao.js"></script>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/sidebar.css">

<script src="../scripts/devolucao.js" type="text/javascript"></script>

<script type="text/javascript" src="../scripts/jquery.js"></script>
<script type="text/javascript" src="../scripts/jquery.maskedinput.js"></script>

<script type="text/javascript" src="../scripts/jquery.maskMoney.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		//$('#txtDataDevolucao').mask('99/99/9999');
		$('#txtKMRodado').maskMoney({thousands:'', decimal:'.', symbol:'KM ', showSymbol:true, symbolStay:true});
	});
</script>

</head>
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

	<%
		String url = "";

		String pagEfetuado = (String) request.getAttribute("pagEfetuado");
		pagEfetuado = (pagEfetuado != null ? pagEfetuado : "");

		if (pagEfetuado == "YES") {

			Devolucao dev = (Devolucao) session.getAttribute("dadosDev");
			dev = (dev != null ? dev : null);

			Locacao loc = (Locacao) session.getAttribute("dadosLoc");
			loc = (loc != null ? loc : null);

			if (dev != null && loc != null) {

				try {
					if (dev.inserir(dev)) {

						Automovel aut = new Automovel();
						aut.setTPStatus(0);
						aut.setIDAutomovel(loc.getAutomovel()
								.getIDAutomovel());

						aut.alterarSTATUS();

						session.setAttribute("dadosDev", null);
						session.setAttribute("dadosLoc", null);

						url = "/devolucao/devolucao-sucesso.jsp";

					} else {

						url = "/devolucao/devolucao.jsp";
						request.setAttribute("error",
								"Devolução de automovel não realizada.");
					}

				} catch (GenericException e) {
					e.printStackTrace();
				}

			}
			RequestDispatcher view = request.getRequestDispatcher(url);
			view.forward(request, response);
		}
	%>
	<!-- Fim da navegacao em comum -->

	<div class="container">
		<div class="jumbotron">
			<h1>Devolução de Automóveis</h1>
			<p>Informe os dados para devolver o automovel</p>
		</div>
		<form name="devolucao" class="form-horizontal" method="post"
			onSubmit="return validarDevolucao();" action="devolver.do">

			<div class="row">
				<div class="col-md-4">

					<div class="form-group">
						<label for="lblNumeroLocacao">Número Locação: </label> <input
							name="txtNumeroLocacao" type="text" value="" class="form-control" /><span
							class="erro-form" id="erroNumeroLocacao"></span>
					</div>
					<div class="form-group">
						<label for="lblDataD">Data Devolução: </label> <input
							name="txtDataDevolucao" id="txtDataDevolucao"
							type="datetime-local" class="form-control" value="" /> <span
							class="erro-form" id="erroDataDevolucao"></span>
					</div>
					<div class="form-group">
						<label for="lblKMRodado">KM Rodado: </label> <input
							id="txtKMRodado" name=txtKMRodado type="text" value=""
							class="form-control" /> <span class="erro-form"
							id="erroKMRodado"></span>
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label for="lblLocalD">Local de Devolução:</label> <select
							name="cbLocalD" size="1" class="form-control">
							<option value="SELECIONE: "></option>
							<option value="COQUEIRAS DO NORTE">COQUEIRAS DO NORTE</option>
							<option value="CAMPOS DO JORDÃO">CAMPOS DO JORDÃO</option>
							<option value="FILIPINAS">FILIPINAS</option>
							<option value="MARENHAS DO SUL">MARENHAS DO SUL</option>
							<option value="ESCALONETA MARATE">ESCALONETA MARATE</option>
						</select> <span class="erro-form" id="erroLocalD"></span>
					</div>

					<div class="form-group">
						<label for="lblAgencia">Agência</label> <select name="cbAgencia"
							size="1" class="form-control">
							<option value="SELECIONE: "></option>
							<option value="2435">2435</option>
							<option value="7784">7784</option>
							<option value="1435">1435</option>
							<option value="0055">0055</option>
						</select> <span class="erro-form" id="erroAgencia"></span>
					</div>
				</div>
			</div>

			<button class="btn btn-success" id="btnConfirmar" name="btnConfirmar"
				type="submit" value="Confirmar" onClick="">
				<span class="glyphicon glyphicon-floppy-disk"></span> Confirmar
			</button>
			<button class="btn btn-danger" id="btnCancelar" name="btnCancelar"
				type="button" value="Cancelar" onClick="javascript:history.back(1);">
				<span class="glyphicon glyphicon-chevron-left"></span> Cancelar
			</button>
			<br /> <br /> <br /> <br /> <br />
		</form>
	</div>

</body>
</html>