///Verifica todos os preenchimentos e incrementa se houve algum erro
var houveErro;
//Usado para focar o primeiro input com erro
var focoInicial;

function validarCadastroCliente()
{
	
	var form = document.cadastroCli;
	houveErro = 0;
	focoInicial = null;
	
	verificaPreenchimento(form.nome, "erroNome", "Informe o Nome do cliente");
	verificaPreenchimento(form.cpf, "erroCpf", "Informe o CPF do cliente");
	verificaPreenchimento(form.rg, "erroRg", "Informe o RG do cliente");
	verificaEmail(form.email, "erroEmail", "Informe um email valido");
	verificaPreenchimento(form.telefone, "erroTelefone", "Informe o telefone do cliente");
	verificaSelecao(form.uf ,"erroEstado", "Selecione o estado do cliente");
	verificaPreenchimento(form.numeroRegistro, "erroNumeroRegistro","Informe o numero de registro do cliente");
	verificaPreenchimento(form.nascimento, "erroNascimento", "Informe a data de nascimento do cliente");
	verificaPreenchimento(form.cnh, "erroCnh", "Informe a numero do CNH");
	verificaPreenchimento(form.validadeCnh, "erroValidadeCnh", "Informe a validade do CNH");

	//Se deu tudo certo, submeter o formulário
	if(houveErro == 0)
	{
		return true;
	}
	
	//Se preencheu errado
	focoInicial.focus();
	return false;
}

//Verifica se o campo está vazio
function verificaPreenchimento(campo, idSpanErro, msgErro)
{
	if(campo.value == "")
	{
		if(focoInicial == null)
		{
			focoInicial = campo;
		}
		document.getElementById(idSpanErro).innerHTML = msgErro;
		houveErro++;
		return false;
	}
	else
	{
		document.getElementById(idSpanErro).innerHTML = "";
		//Retorna true caso precise fazer uma outra verificação após a de preenchimento
		return true;
	}
}

//Verifica se foi selecionado algo no select
function verificaSelecao(campo, idSpanErro, msgErro)
{
	if(campo.value == "NaoSelecionado")
	{
		if(focoInicial == null)
		{
			focoInicial = campo;
		}
		document.getElementById(idSpanErro).innerHTML = msgErro;
		houveErro++;
		return false;
	}
	else
	{
		document.getElementById(idSpanErro).innerHTML = "";
		//Retorna true caso precise fazer uma outra verificação após a de preenchimento
		return true;
	}
}

function verificaEmail(campo, idSpanErro, msgErro){
		
		if(campo.value.indexOf('@') == -1 || campo.value.indexOf('.') == -1 || campo.value == null){
			
			if(focoInicial == null)
			{
				focoInicial = campo;
			}
			
			document.getElementById(idSpanErro).innerHTML = msgErro;
			houveErro++;
			return false;
		}		
			else
			{
				document.getElementById(idSpanErro).innerHTML = "";
				return true;
			}
		
		
	
		
}

function alterarCliente(){
		//Valida o preenchimento  
	
			if(validarCadastroCliente())
			{
				document.cadastroCli.action = "alteracao.do";
				document.cadastroCli.submit();
			}
			
	}

function excluirCliente(){
		document.cadastroCli.action = "exclusao.do";
		document.cadastroCli.submit();
	}


function numeros(campo) {
	
	if (isNaN(campo.value)) {

		campo.value = campo.value;
	} else {

		campo.value = "";
	}
	

}

function letras(campo) {
	
	if (!isNaN(campo.value)) {

		campo.value = campo.value;
	} else {

		campo.value = "";
	}
	

}

function mascara(o,f){
	v_obj=o
	v_fun=f
	setTimeout("execmascara()",1)
}

function execmascara(){
	v_obj.value=v_fun(v_obj.value)
}

function cpf_mask(v){
	v=v.replace(/\D/g,"")                 //Remove tudo o que não é dígito
	v=v.replace(/(\d{3})(\d)/,"$1.$2")    //Coloca ponto entre o terceiro e o quarto dígitos
	v=v.replace(/(\d{3})(\d)/,"$1.$2")    //Coloca ponto entre o setimo e o oitava dígitos
	v=v.replace(/(\d{3})(\d)/,"$1-$2")   //Coloca ponto entre o decimoprimeiro e o decimosegundo dígitos
	return v
}

function rg_mask(v){
	v=v.replace(/\D/g,"")                 //Remove tudo o que não é dígito
	v=v.replace(/(\d{2})(\d)/,"$1.$2")    //Coloca ponto entre o segundo e o terceiro dígitos
	v=v.replace(/(\d{3})(\d)/,"$1.$2")    //Coloca ponto entre o quinto e o sexto dígitos
	v=v.replace(/(\d{3})(\d)/,"$1-$2")   //Coloca ponto entre o oitava e o nono dígitos

	return v
}

function telefone_mask(v){
	v=v.replace(/\D/g,"")                 
	v=v.replace(/(\d{0})(\d)/,"$1($2")    
	v=v.replace(/(\d{2})(\d)/,"$1)$2")    
	v=v.replace(/(\d{4})(\d)/,"$1-$2") 

	return v
}

function data_mask(v){
	v=v.replace(/\D/g,"")                 
	v=v.replace(/(\d{4})(\d)/,"$1-$2")    
	v=v.replace(/(\d{2})(\d)/,"$1-$2")    

	return v
}
