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
	
	verificaPreenchimento(form.txtNomeTitular, "erroTitular","Informe o nome do titular do cartão.");
	verificaPreenchimento(form.txtCpf, "erroCpf","Informe o CPF do titular do cartão.");
	verificaPreenchimento(form.txtValorPagamento, "erroValorPagamento","Informe o valor a ser pago.");
	verificaPreenchimento(form.txtDataPagamento, "erroDataPagamento","Informe a data de pagamento.");

	if (tipoPagamento == "C") {
		
		verificaPreenchimento(form.txtValidadeCartao, "erroValidadeCartao","Informe a data de validade do cartão.");
		verificaPreenchimento(form.txtNumeroCartao, "erroNumeroCartao","Informe o número do cartão.");
		verificaPreenchimento(form.txtCodSeguranca, "erroCodSeguranca","Informe o código de segurança do cartão.");
		verificaSelecao(form.cbOperadoraCartao, "erroOperadoraCartao","Informe a operadora do cartão.");
		
	} else if (tipoPagamento == "D") {
		
		verificaPreenchimento(form.txtNumAgenciaDebito, "erroNumAgenciaDebito","Informe o chassi do automovel");
		verificaPreenchimento(form.txtNumContaDebito, "erroNumContaDebito","Informe o número da conta.");
		verificaPreenchimento(form.txtTelefone, "erroTelefone","Informe um telefone de referência do titular.");
		verificaSelecao(form.cbBancoDebito, "erroBancoDebito","Informe o banco do cartão.");

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
		document.getElementById("spanErroPagamento").innerHTML = "Ocorreu um erro ao cadastrar a locação";
	}
	else if(erro == "2") 
	{
		document.getElementById("spanErroPagamento").innerHTML = "PerÃ­do selecionado nÃ£o estÃ¡ disponivel. Por favor, selecione outra data";
	}
	else if(erro == "3") 
	{
		document.getElementById("spanErroPagamento").innerHTML = "Cliente nÃ£o encontrado. Por favor verifique se o CPF foi digitado corretamente" 
																+ " e o cliente jÃ¡ Ã© cadastrado";
	}
}
