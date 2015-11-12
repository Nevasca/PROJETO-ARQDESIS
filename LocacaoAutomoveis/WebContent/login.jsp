<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login de Acesso</title>
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<head>

<title>Login de Acesso</title>
<link rel="stylesheet" type="text/css" href="../bootstrap/css/Main.css" />
<script type="text/javascript" src="./js/validarLogin.js"></script>

</head>

<body>

	<h2>Login de Acesso</h2>
	<br></br>

	<fieldset>

		<div align="center">
			<br></br> <img src="/LocacaoAutomoveis/Imagens/login.jpg" /><br></br>
			<br></br>

			<form name="formularioLogin" method="post" action="/LocacaoAutomoveis/login.do"
				onSubmit="return validacao();">

				<label>Login:</label> <input type="text" name="login" id="login" /><br></br>
				<br></br> <label>Senha:</label> 
				<input type="password" name="senha"	id="senha" /><br></br>
				<br></br> <input id="entrar" type="submit" name="entrar"
					value="Entrar"><br></br>

			</form>

			<label>Digite o login e a senha</label><br></br>
		</div>

	</fieldset>

</body>


</html>
