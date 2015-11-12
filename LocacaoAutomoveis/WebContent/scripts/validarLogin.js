/**
 * Arquivo para fazer validação 
 */
	function validacao(){
		
		if(document.formularioLogin.login.value == ""){
			
			alert("Favor preencher o campo login");
			document.formularioLogin.login.focus();
			return false;
		}
		
		if(document.formularioLogin.senha.value.length < 6){
			
			alert("A senha tem que ter no minimo 6 caracteres");
			document.formularioLogin.senha.focus();
			return false;
		}
		

	}
