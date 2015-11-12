//Verifica todos os preenchimentos e incrementa se houve algum erro
var houveErro;
//Usado para focar o primeiro input com erro
var focoInicial;

function validarCadastroAutomovel()
{
	
	var form = document.cadastroAuto;
	houveErro = 0;
	focoInicial = null;
	
	verificaSelecao(form.grupo, "erroGrupo", "Selecione o grupo do automóvel");
	verificaPreenchimento(form.chassi, "erroChassi", "Informe o chassi do automóvel");
	verificaPreenchimento(form.placa, "erroPlaca", "Informe a placa do automóvel");
	verificaPreenchimento(form.cidade, "erroCidade", "Informe a cidade do automóvel");
	verificaSelecao(form.uf, "erroEstado", "Selecione o estado do automóvel");
	verificaPreenchimento(form.quilometragem, "erroQuilometragem", "Informe a quilometragem do automóvel");
	verificaPreenchimento(form.modelo, "erroModelo", "Informe o modelo do automóvel");
	verificaPreenchimento(form.fabricante, "erroFabricante", "Informe o fabricante do automóvel");
	verificaPreenchimento(form.tarifa, "erroTarifa", "Informe o valor da tarifa do automóvel");
	verificaPreenchimento(form.tarifaControlada, "erroTarifaControlada", "Informe o valor da tarifa controlada do automóvel");
	
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

function mascara(o,f){
	v_obj=o
	v_fun=f
	setTimeout("execmascara()",1)
}

function execmascara(){
	v_obj.value=v_fun(v_obj.value)
}