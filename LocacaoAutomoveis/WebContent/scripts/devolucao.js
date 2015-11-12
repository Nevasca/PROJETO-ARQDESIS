var houveErro;
var focoInicial;

function validarDevolucao() {

	var form = document.devolucao;
	houveErro = 0;
	focoInicial = null;
		
	verificaPreenchimento(form.txtNumeroLocacao, "erroNumeroLocacao","Informe o c�digo identificador de loca��o do automovel.");
	verificaPreenchimento(form.txtDataDevolucao, "erroDataDevolucao","Informe a data de devolu��o do automovel.");
	verificaSelecao(form.cbLocalD, "erroLocalD","Informe o local de devolu��o do automovel.");
	verificaSelecao(form.cbAgencia, "erroAgencia","Informe o n�mero da ag�ncia para devolu��o do automovel.");
    verificaPreenchimento(form.txtKMRodado, "erroKMRodado","Informe o KM rodado atual com o automovel.");

	if (houveErro == 0) {
		return true;
	}

	focoInicial.focus();
	return false;
}

function verificaPreenchimento(campo, idSpanErro, msgErro) {
	
	campo.replace(" ","");
	
	if (campo.value == "" || campo.value == null) {

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