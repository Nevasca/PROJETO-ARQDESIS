//Usar essa variavel nas telas para verificar erros
var houveErro = 0;

//Verifica se o campo está vazio
function verificarPreenchimento(campo, idSpanErro, msgErro)
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
function verificarSelecao(campo, idSpanErro, msgErro)
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