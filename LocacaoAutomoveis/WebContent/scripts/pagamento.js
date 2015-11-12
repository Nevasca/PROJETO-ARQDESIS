var houveErro;
var focoInicial;

function validarPagamento(tipoPagamento) {

	var form = null;

	if (tipoPagamento == "C") {
		form = document.pagamentoCredito;
	} else if (tipoPagamento == "D") {
		form = document.pagamentoDebito;
	}

	houveErro = 0;
	focoInicial = null;
	
	verificaPreenchimento(form.txtNomeTitular, "erroTitular","Informe o nome do titular do cart�o.");
	verificaPreenchimento(form.txtCpf, "erroCpf","Informe o CPF do titular do cart�o.");
	verificaPreenchimento(form.txtValorPagamento, "erroValorPagamento","Informe o valor a ser pago.");
	verificaPreenchimento(form.txtDataPagamento, "erroDataPagamento","Informe a data de pagamento.");

	if (tipoPagamento == "C") {
		
		verificaPreenchimento(form.txtValidadeCartao, "erroValidadeCartao","Informe a data de validade do cart�o.");
		verificaPreenchimento(form.txtNumeroCartao, "erroNumeroCartao","Informe o n�mero do cart�o.");
		verificaPreenchimento(form.txtCodSeguranca, "erroCodSeguranca","Informe o c�digo de seguran�a do cart�o.");
		verificaSelecao(form.cbOperadoraCartao, "erroOperadoraCartao","Informe a operadora do cart�o.");
		
	} else if (tipoPagamento == "D") {
		
		verificaPreenchimento(form.txtNumAgenciaDebito, "erroNumAgenciaDebito","Informe o chassi do automovel");
		verificaPreenchimento(form.txtNumContaDebito, "erroNumContaDebito","Informe o n�mero da conta.");
		verificaPreenchimento(form.txtTelefone, "erroTelefone","Informe um telefone de refer�ncia do titular.");
		verificaSelecao(form.cbBancoDebito, "erroBancoDebito","Informe o banco do cart�o.");

	}

	if (houveErro == 0) {
		return true;
	}

	focoInicial.focus();
	return false;
}

function verificaPreenchimento(campo, idSpanErro, msgErro) {
	if (campo.value == "") {

		if (focoInicial == null) {
			focoInicial = campo;
		}

		document.getElementById(idSpanErro).innerHTML = msgErro;
		houveErro++;
		return false;

	} else {
		document.getElementById(idSpanErro).innerHTML = "";
		return true;
	}
}

function verificaSelecao(campo, idSpanErro, msgErro)
{
	if(campo.value == "SELECIONE:")
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
		return true;
	}
}

function mostrarMsgErroPagamento(erro)
{
	if(erro == "1")
	{
		document.getElementById("spanErroPagamento").innerHTML = "Ocorreu um erro ao cadastrar a loca��o";
	}
	else if(erro == "2") 
	{
		document.getElementById("spanErroPagamento").innerHTML = "Perído selecionado não está disponivel. Por favor, selecione outra data";
	}
	else if(erro == "3") 
	{
		document.getElementById("spanErroPagamento").innerHTML = "Cliente não encontrado. Por favor verifique se o CPF foi digitado corretamente" 
																+ " e o cliente já é cadastrado";
	}
}
