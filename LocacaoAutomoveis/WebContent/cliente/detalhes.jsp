<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="model.Cliente"%>
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
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="../bootstrap/css/bootstrap.min.css">
<script src="../scripts/jquery-1.11.3.min.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
<script src="../scripts/validacaoCliente.js"></script>
<script src="../scripts/validacao.js"></script>

<script src="../scripts/navegacao.js"></script>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/sidebar.css">
<title>Detalhes Cliente</title>
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

	<%
		//Verifica se há um cliente consultado anteriormente
		Cliente cliente = new Cliente();
		String nome = null;
		String uf = null;
        String dataNasc = "";
        String dataValidadeCNH = "";
		
		if (request.getAttribute("clienteConsultado") != null) {
			cliente = (Cliente) request.getAttribute("clienteConsultado");
			nome = cliente.getNomeCliente();
			uf = cliente.getUfCliente();
			
			DateFormat data = new SimpleDateFormat("yyyy-MM-dd");
	        dataNasc = data.format(cliente.getDataNascCliente());
	        dataValidadeCNH = data.format(cliente.getDataValidadeCNH());	        	        
	    
			
		} else {
			RequestDispatcher view = request
					.getRequestDispatcher("consulta.jsp");
			view.forward(request, response);
		}
	%>
	<div class="container">
		<%
			//Verifica se é um retorno de alteração e dá a mensagem de acordo
			if (request.getAttribute("alterado") != null) {

				boolean alterado = (Boolean) request.getAttribute("alterado");
				if (alterado) {
					out.print("<div class='jumbotron jumb-sucesso-sm'>Cliente alterado com sucesso!</div>");
				} else {
					out.print("<div class='jumbotron jumb-alerta'>Não foi possível alterar o cliente</div>");
				}
			}
		%>
		<div class="row">
			<div class="col-md-12">
				<div class="jumbotron">
					<h1>
						<%
							out.print(cliente.getNomeCliente());
						%>
					</h1>
					<p>
						<%
							out.print(cliente.getCpfCliente());
						%>
					</p>
				</div>
			</div>
		</div>



		<form name="cadastroCli" method="post">
			<input type="hidden" name="idCliente"
				value="<%out.print(cliente.getIDCliente());%>">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label>Nome:</label> <input type="text" name="nome"
							value="<%out.print(cliente.getNomeCliente());%>"
							class="form-control" onkeyup="numeros( this );" maxlength="50" />
						<span class="erro-form" id="erroNome"></span>
					</div>
					<div class="form-group">
						<label>CPF:</label> <input type="text" name="cpf"
							value="<%out.print(cliente.getCpfCliente());%>"
							class="form-control" onkeypress="mascara(this, cpf_mask);"
							maxlength="14" /> <span class="erro-form" id="erroCpf"></span>
					</div>
					<div class="form-group">
						<label>RG:</label> <input type="text" name="rg"
							value="<%out.print(cliente.getRgCliente());%>"
							class="form-control" onkeypress="mascara(this, rg_mask);"
							maxlength="11" /> <span class="erro-form" id="erroRg"></span>
					</div>
					<div class="form-group">
						<label>Email:</label> <input type="text" name="email"
							value="<%out.print(cliente.getEmailCliente());%>"
							class="form-control" maxlength="50" /> <span class="erro-form"
							id="erroEmail"></span>
					</div>
					<div class="form-group">
						<label>Telefone:</label> <input type="text" name="telefone"
							value="<%out.print(cliente.getTelCliente());%>"
							class="form-control" onkeypress="mascara(this, telefone_mask);"
							maxlength="13" /> <span class="erro-form" id="erroTelefone"></span>
					</div>
					<div class="form-group">
						<label>Estado:</label>
						<!-- Verifica qual a UF do automovel e coloca como selecionado se for o mesmo do option -->
						<select name="uf" class="form-control">
							<option value="AC"
								<%if (uf.equals("AC")) {
				out.print("selected");
			}%>>AC</option>
							<option value="AL"
								<%if (uf.equals("AL")) {
				out.print("selected");
			}%>>AL</option>
							<option value="AP"
								<%if (uf.equals("AP")) {
				out.print("selected");
			}%>>AP</option>
							<option value="AM"
								<%if (uf.equals("AM")) {
				out.print("selected");
			}%>>AM</option>
							<option value="BA"
								<%if (uf.equals("BA")) {
				out.print("selected");
			}%>>BA</option>
							<option value="CE"
								<%if (uf.equals("CE")) {
				out.print("selected");
			}%>>CE</option>
							<option value="DF"
								<%if (uf.equals("DF")) {
				out.print("selected");
			}%>>DF</option>
							<option value="ES"
								<%if (uf.equals("ES")) {
				out.print("selected");
			}%>>ES</option>
							<option value="GO"
								<%if (uf.equals("GO")) {
				out.print("selected");
			}%>>GO</option>
							<option value="MA"
								<%if (uf.equals("MA")) {
				out.print("selected");
			}%>>MA</option>
							<option value="MT"
								<%if (uf.equals("MT")) {
				out.print("selected");
			}%>>MT</option>
							<option value="MS"
								<%if (uf.equals("MS")) {
				out.print("selected");
			}%>>MS</option>
							<option value="MG"
								<%if (uf.equals("MG")) {
				out.print("selected");
			}%>>MG</option>
							<option value="PR"
								<%if (uf.equals("PR")) {
				out.print("selected");
			}%>>PR</option>
							<option value="PB"
								<%if (uf.equals("PB")) {
				out.print("selected");
			}%>>PB</option>
							<option value="PA"
								<%if (uf.equals("PA")) {
				out.print("selected");
			}%>>PA</option>
							<option value="PE"
								<%if (uf.equals("PE")) {
				out.print("selected");
			}%>>PE</option>
							<option value="PI"
								<%if (uf.equals("PI")) {
				out.print("selected");
			}%>>PI</option>
							<option value="RJ"
								<%if (uf.equals("RJ")) {
				out.print("selected");
			}%>>RJ</option>
							<option value="RN"
								<%if (uf.equals("RN")) {
				out.print("selected");
			}%>>RN</option>
							<option value="RS"
								<%if (uf.equals("RS")) {
				out.print("selected");
			}%>>RS</option>
							<option value="RO"
								<%if (uf.equals("RO")) {
				out.print("selected");
			}%>>RO</option>
							<option value="RR"
								<%if (uf.equals("RR")) {
				out.print("selected");
			}%>>RR</option>
							<option value="SC"
								<%if (uf.equals("SC")) {
				out.print("selected");
			}%>>SC</option>
							<option value="SE"
								<%if (uf.equals("SE")) {
				out.print("selected");
			}%>>SE</option>
							<option value="SP"
								<%if (uf.equals("SP")) {
				out.print("selected");
			}%>>SP</option>
							<option value="TO"
								<%if (uf.equals("TO")) {
				out.print("selected");
			}%>>TO</option>
						</select> <span class="erro-form" id="erroEstado"></span>
					</div>
				</div>
				<!-- fim coluna -->
				<div class="col-md-6">
					<div class="form-group">
						<label>Número Registro:</label> <input type="text"
							name="numeroRegistro"
							value="<%out.print(cliente.getRegistroCliente());%>"
							class="form-control" onkeyup="letras( this );" maxlength="20" />
						<span class="erro-form" id="erroNumeroRegistro"></span>
					</div>
					<div class="form-group">
						<label>Nascimento:</label> <input type="date" name="nascimento"
							value="<%out.print(dataNasc);%>"
							class="form-control"
							maxlength="10" /> <span class="erro-form" id="erroNascimento"></span>
					</div>

					<div class="form-group">
						<label>Sexo:</label>
						<%
							if (cliente.getGeneroCliente().equals("Masculino")) {

								out.print("<input type='radio' name='sexo' value='Masculino' value='sexo' checked>Masculino");
								out.print("<input type='radio' name='sexo' value='Masculino' value='sexo' >Feminino");

							}

							else {

								out.print("<input type='radio' name='sexo' value='Masculino' value='sexo' checked>Feminino");
								out.print("<input type='radio' name='sexo' value='Masculino' value='sexo' >Masculino");
							}
						%>
						<span class="erro-form" id="erroSexo"></span>
					</div>

					<div class="form-group">
						<label>Validade CNH:</label> <input type="date" name="validadeCnh"
							value="<%out.print(dataValidadeCNH);%>"
							class="form-control" maxlength="10" /> <span class="erro-form" id="erroValidadeCnh"></span>
					</div>
					<div class="form-group">
						<label>CNH:</label> <input type="text" name="cnh"
							value="<%out.print(cliente.getCNHCliente());%>"
							class="form-control" maxlength="9" />
						<span class="erro-form" id="erroCnh"></span>
					</div>
				</div>
				<!-- fim coluna -->
			</div>
		</form>
		<div class="row">
			<div class="col-md-12">
				<button type="button" class="btn btn-warning"
					onclick="alterarCliente()">
					<span class="glyphicon glyphicon-pencil"></span> Alterar
				</button>
				<button type="button" class="btn btn-danger"
					onclick="$('#caixaExclusao').fadeIn('fast')">
					<span class="glyphicon glyphicon-trash"></span> Excluir
				</button>
				<div id="caixaExclusao" class="jumbotron jumb-exclusao">
					O cliente será excluído do sistema. Se houver locações, será apenas
					desativado. Deseja prosseguir com a exclusão?
					<button class="btn btn-default" type="button"
						onclick="excluirCliente()">
						<b>Excluir</b>
					</button>
					<button class="btn btn-default" type="button"
						onclick="$('#caixaExclusao').fadeOut('fast')">Cancelar</button>
				</div>

			</div>
		</div>

	</div>

</body>
</html>