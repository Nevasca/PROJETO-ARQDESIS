<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.DateFormat"%>
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


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css"
	href="../bootstrap/css/bootstrap.min.css">

<script src="../scripts/jquery-1.11.3.min.js"></script>
<script src="../scripts/navegacao.js"></script>
<script src="../scripts/locacao.js"></script>
<script src="../scripts/validacao.js"></script>
<script src="../scripts/validacaoCliente.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/sidebar.css">

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
		String url = "";

		String pagEfetuado = (String) request.getAttribute("pagEfetuado");
		pagEfetuado = (pagEfetuado != null ? pagEfetuado : "");

		Locacao dadosLoc = (Locacao) session.getAttribute("dadosLoc");
		dadosLoc = (dadosLoc != null ? dadosLoc : null);

		if (pagEfetuado == "YES") {

			if (dadosLoc.getIDLocacao() != 0) {

				Automovel aut = new Automovel();
				aut.setTPStatus(1); //TP_STATUS = 1 ALUGADO
				aut.setIDAutomovel(dadosLoc.getAutomovel().getIDAutomovel());

				aut.alterarSTATUS();

				url = "/locacao/locacao-sucesso.jsp";

				RequestDispatcher view = request.getRequestDispatcher(url);
				view.forward(request, response);

			}

		}
	%>

	<%
		Automovel auto = null;
		Locacao loc = null;
		Cliente cli = null;
		String cidadeLocacao = "";
		String cidadeDevolucao = "";
		String agenciaLocacao = "0";
		String agenciaDevolucao = "0";
		int modoTarifa = -1;
		String dataLocacao = "";
		String dataDevolucao = "";

		int idCliente = 0;
		String cpfCliente = "";
		String erro = "0";

		//Contém código em javascript para focar a linha correta de formulario.
		//Se acabou de consultar o cliente, focar a linha de pagamento para nao ter que descer toda a página, por exemplo
		String foco = "'#linhaLocacao'";

		if (request.getAttribute("automovel") != null) {
			auto = (Automovel) request.getAttribute("automovel");
		} else {
			if (dadosLoc.getIDLocacao() == 0) {
				RequestDispatcher view = request
						.getRequestDispatcher("/automovel/consulta.jsp");
				view.forward(request, response);
			}
		}

		if (request.getAttribute("locacao") != null) {
			loc = (Locacao) request.getAttribute("locacao");
			cidadeLocacao = loc.getLocalEmprestimoLocacao();
			cidadeDevolucao = loc.getLocalPrevistaDevolucaoLocacao();
			agenciaLocacao = loc.getAgenciaEmprestimoLocacao();
			agenciaDevolucao = loc.getAgenciaPrevistaDevolucaoLocacao();
			modoTarifa = loc.getTipoTaxaEmprestarLocacao();

			//2014-01-02T13:42 --exemplo de formato do datetime-local input
			//Formata a data de locacoa e devolucao para colocar como value no input
			DateFormat data = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat hora = new SimpleDateFormat("hh:mm");
			dataLocacao = data.format(loc.getDataEmprestimoLocacao()) + "T"
					+ hora.format(loc.getDataEmprestimoLocacao());
			dataDevolucao = data.format(loc
					.getDataPrevistaDevolucaoLocacao())
					+ "T"
					+ hora.format(loc.getDataPrevistaDevolucaoLocacao());
		}

		if (request.getAttribute("cliente") != null) {
			cli = (Cliente) request.getAttribute("cliente");
			idCliente = cli.getIDCliente();
			cpfCliente = cli.getCpfCliente();
			foco = "'#linhaPagamento'";
		}

		if (request.getAttribute("erro") != null) {
			erro = (String) request.getAttribute("erro");

			//2 = Periodo não disponível. 1 = Erro banco
			if (erro.equals("2") || erro.equals("1")) {
				foco = "'#linhaLocacao'";
			} else if (erro.equals("3")) //Cliente não encontrado
			{
				foco = "'#linhaCliente'";
			}
		}
	%>


	<script>
		
		//Foca a linha de forma suave
		$(document).ready(function (){          
             $('html, body').animate({scrollTop: $(<%out.print(foco);%>).offset().top}, 500);                
        });        
	</script>

	<div class="container">

		<div class="col-md-12">
			<div class="jumbotron">
				<h1>Nova Locação</h1>
			</div>
			<form name="formLocacao" method="post" action="locacao.do">
				<input type="hidden" name="acao">
				<div class="row well">
					<h4>
						<span class="glyphicon glyphicon-wrench"></span> Dados do
						Automóvel
					</h4>
					<input type="hidden" name="idAutomovel"
						value="<%if (auto != null) {
				out.print(auto.getIDAutomovel());
			}%>">
					<div class="col-md-6">
						<b>Modelo: </b><br>
						<%
							if (auto != null) {
								out.print(auto.getModeloAutomovel());
							}
						%><br> <b>Fabricante: </b><br>
						<%
							if (auto != null) {
								out.print(auto.getFabricanteAutomovel());
							}
						%><br> <b>Acessório: </b><br>
						<%
							if (auto != null) {
								out.print(auto.getNomeAcessorio());
							}
						%><br> <b>Grupo: </b><br>
						<%
							if (auto != null) {
								out.print(auto.getNomegruposAutomovel());
							}
						%><br> <b>Placa: </b><br>
						<%
							if (auto != null) {
								out.print(auto.getPlacaAutomovel());
							}
						%><br>
					</div>
					<div class="col-md-6">
						<b>Cidade: </b><br>
						<%
							if (auto != null) {
								out.print(auto.getCidadeAutomovel());
							}
						%><br> <b>Estado: </b><br>
						<%
							if (auto != null) {
								out.print(auto.getUFAutomovel());
							}
						%><br> <b>Tarifa simples:<br>
						</b>
						<%
							if (auto != null) {
								out.print(auto.getTarifaAutomovel());
							}
						%><br> <b>Tarifa controlada:<br>
						</b>
						<%
							if (auto != null) {
								out.print(auto.getTarifaControladaAutomovel());
							}
						%><br>
					</div>
				</div>
				<div class="row well" id="linhaLocacao">
					<h4>
						<span class="glyphicon glyphicon-calendar"></span> Dados da
						Locação e Devolução
					</h4>
					<div class="col-md-6">
						<div class="form-group">
							<label>Data e hora da locação:</label> <input name="dataLocacao"
								type="datetime-local" class="form-control"
								onchange="atualizarTarifa(<%if (auto != null) {
				out.print(auto.getTarifaAutomovel());
			}%>)"
								value="<%if (auto != null) {
				out.print(dataLocacao);
			}%>"
								placeholder="MM/dd/AAAA hh:mm">
							<!-- value="2014-01-02T13:42" -->
							<span class="erro-form" id="erroDataLocacao"></span>
						</div>
						<div class="form-group">
							<label>Agência de locação:</label> <select name="agenciaLocacao"
								class="form-control">
								<option value="NaoSelecionado">Selecione...</option>
								<option value="2435" <% if(agenciaLocacao.equals("2435")){ out.print("selected");} %>>2435</option>
								<option value="7784" <% if(agenciaLocacao.equals("7784")){ out.print("selected");} %>>7784</option>
								<option value="1435" <% if(agenciaLocacao.equals("1435")){ out.print("selected");} %>>1435</option>
								<option value="0055" <% if(agenciaLocacao.equals("0055")){ out.print("selected");} %>>0055</option>
							</select> <span class="erro-form" id="erroAgenciaLocacao"></span>
						</div>
						<div class="form-group">
							<label>Cidade de locação:</label> <input name="cidadeLocacao"
								type="text" class="form-control"
								value="<%if (auto != null) {
				out.print(cidadeLocacao);
			}%>">
							<span class="erro-form" id="erroCidadeLocacao"></span>
						</div>
						
					</div>

					<div class="col-md-6">
						<div class="form-group">
							<label>Data e hora da devolução:</label> <input
								name="dataDevolucao" type="datetime-local" class="form-control"
								onchange="atualizarTarifa(<%if (auto != null) {
				out.print(auto.getTarifaAutomovel());
			}%>)"
								value="<%if (auto != null) {
				out.print(dataDevolucao);
			}%>">
							<span class="erro-form" id="erroDataDevolucao"></span>
						</div>
						<div class="form-group">
							<label>Agência de devolução:</label> <select
								name="agenciaDevolucao" class="form-control">
								<option value="NaoSelecionado">Selecione...</option>
								<option value="2435" <% if(agenciaDevolucao.equals("2435")){ out.print("selected");} %>>2435</option>
								<option value="7784" <% if(agenciaDevolucao.equals("7784")){ out.print("selected");} %>>7784</option>
								<option value="1435" <% if(agenciaDevolucao.equals("1435")){ out.print("selected");} %>>1435</option>
								<option value="0055" <% if(agenciaDevolucao.equals("0055")){ out.print("selected");} %>>0055</option>
							</select> <span class="erro-form" id="erroAgenciaDevolucao"></span>
						</div>
						<div class="form-group">
							<label>Cidade de devolução:</label> <input name="cidadeDevolucao"
								type="text" class="form-control"
								value="<%if (auto != null) {
				out.print(cidadeDevolucao);
			}%>">
							<span class="erro-form" id="erroCidadeDevolucao"></span>
						</div>
					</div>
					<div class="row">
						<!-- <h4><span class="glyphicon glyphicon-credit-card"></span> Tarifa e Pagamento</h4> -->
						<div class="col-md-6">
							<div class="container-fluid">
								<div class="col-md-2">
									<input id="radioTarifaSimples" type="radio" name="modoTarifa"
										value="simples" class="form-control" <% if(modoTarifa == 0){ out.print("checked");} %>>
								</div>
								<div class="col-md-6">
									<b>Tarifa Simples</b>
									<p>De acordo com o número de dias alugado</p>
								</div>
								<div class="col-md-4">
									<h3 id="valorTarifa">
										R$
										<%
										if (auto != null) {
											out.print(auto.getTarifaAutomovel());
										}
									%>
									</h3>
								</div>
							</div>
							<span class="erro-form" id="erroModoTarifa"></span>
						</div>

						<div class="col-md-6">
							<div class="container-fluid">
								<div class="col-md-2">
									<input id="radioTarifaControlada" type="radio"
										name="modoTarifa" value="controlada" class="form-control" <% if(modoTarifa == 1){ out.print("checked");} %>>
								</div>
								<div class="col-md-6">
									<b>Tarifa Controlada</b>
									<p>De acordo com a quilometragem rodada</p>
								</div>
								<div class="col-md-4">
									<h3 id="valorTarifaControlada">
										R$
										<%
										if (auto != null) {
											out.print(auto.getTarifaControladaAutomovel());
										}
									%>
									</h3>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 text-center">
							<button type="button" onclick="validarDadosLocacao()"
								class="btn btn-primary">
								<span class="glyphicon glyphicon-ok"></span> Validar Locação
							</button>
						</div>
					</div>
					<div class="row" style="margin-top: 20px;">
						<div class="col-md-12 text-center">
							<span id="spanErroLocacao" class="erro-form"></span>
						</div>
					</div>
				</div>



				<div class="row well"
					<%if (loc == null || erro.equals("2")) {
				out.print("hidden");
			}%>
					id="linhaCliente">
					<input type="hidden" name="idCliente"
						value="<%out.print(idCliente);%>">
					<h4>
						<span class="glyphicon glyphicon-user"></span> Dados do Cliente
					</h4>
					<div class="col-md-6">
						<div class="form-group">
							<label>CPF:</label> <input type="text" name="cpfCliente"
								class="form-control" onkeypress="mascara(this, cpf_mask);"
								maxlength="14" value="<%out.print(cpfCliente);%>"> <span
								class="erro-form" id="erroCpfCliente"></span><br>
							<button type="button" onclick="validarConsultaCliente()"
								class="btn btn-primary">
								<span class="glyphicon glyphicon-search"></span> Buscar
							</button>
						</div>

					</div>
					<div class="row"></div>
					<%
						if (cli != null) {

							String dataNasc = "";
							String dataValidadeCNH = "";

							DateFormat data = new SimpleDateFormat("dd/MM/yyyy");
							dataNasc = data.format(cli.getDataNascCliente());
							dataValidadeCNH = data.format(cli.getDataValidadeCNH());

							//Escrever no html os dados do cliente
							String cod = "<div class='row'>";
							cod += "<div class='col-md-6'>";
							cod += "<b>Nome: </b><br/>" + cli.getNomeCliente() + "<br>";
							cod += "<b>CPF: </b><br/>" + cli.getCpfCliente() + "<br>";
							cod += "<b>Data de Nascimento: </b><br/>" + dataNasc + "<br>";
							cod += "<b>Sexo: </b><br/>" + cli.getGeneroCliente() + "<br>";
							cod += "<b>E-mail: </b><br/>" + cli.getEmailCliente() + "<br>";
							cod += "</div>";

							cod += "<div class='col-md-6'>";
							cod += "<b>Telefone: </b><br/>" + cli.getTelCliente() + "<br>";
							cod += "<b>RG: </b><br/>" + cli.getRgCliente() + "<br>";
							cod += "<b>Estado Emissor: </b><br/>" + cli.getUfCliente()
									+ "<br>";
							cod += "<b>Reg: </b><br/>" + cli.getRegistroCliente() + "<br>";
							cod += "<b>CNH: </b><br/>" + dataValidadeCNH + "<br>";
							cod += "</div>";
							cod += "</div>";

							out.print(cod);
						}
					%>
					<div class="row" style="margin-top: 20px;">
						<div class="col-md-12 text-center">
							<span id="spanErroCliente" class="erro-form"></span>
						</div>
					</div>
				</div>

				<div class="row well"
					<%if (cli == null) {
				out.print("hidden");
			}%>
					id="linhaPagamento">
					<h4>
						<span class="glyphicon glyphicon-credit-card"></span> Forma de
						Pagamento
					</h4>
					<div class="col-md-12 text-center">
						<h3 style="margin-bottom: 30px">R$ <% if (loc != null) { out.print(loc.getValorLocacao()); } %></h3>
					</div>
					<div class="row">
						<div class="col-md-12 text-center">
							<button type="button" class="btn btn-primary btn-lg"
								onclick="pagamento()">
								<span class="glyphicon glyphicon-credit-card"></span> Pagar
							</button>
						</div>
					</div>
				</div>
			</form>
			<script>
				selecionarOptionAgencia(<%out.print(agenciaLocacao + "," + agenciaDevolucao);%>);
				selecionarModoTarifa(<%out.print(modoTarifa);%>);
				mostrarMsgErroLocacao("<%out.print(erro);%>");
			</script>
		</div>
	</div>
</body>
</html>