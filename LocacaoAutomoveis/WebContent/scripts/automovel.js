//Verifica todos os preenchimentos e incrementa se houve algum erro
var houveErro;
//Usado para focar o primeiro input com erro
var focoInicial;

function validarCadastroAutomovel()
{
	
	var form = document.cadastroAuto;
	houveErro = 0;
	focoInicial = null;
	
	verificarSelecao(form.grupo, "erroGrupo", "Selecione o grupo do automovel");
	verificarPreenchimento(form.chassi, "erroChassi", "Informe o chassi do automovel");
	verificarPreenchimento(form.placa, "erroPlaca", "Informe a placa do automovel");
	verificarPreenchimento(form.cidade, "erroCidade", "Informe a cidade do automovel");
	verificarSelecao(form.uf, "erroEstado", "Selecione o estado do automovel");
	verificarPreenchimento(form.quilometragem, "erroQuilometragem", "Informe a quilometragem do automovel");
	verificarPreenchimento(form.modelo, "erroModelo", "Informe o modelo do automovel");
	verificarPreenchimento(form.fabricante, "erroFabricante", "Informe o fabricante do automovel");
	verificarPreenchimento(form.tarifa, "erroTarifa", "Informe o valor da tarifa do automovel");
	verificarPreenchimento(form.tarifaControlada, "erroTarifaControlada", "Informe o valor da tarifa controlada do automovel");
	
	//Se deu tudo certo, submeter o formulário
	if(houveErro == 0)
	{
		return true;
	}
	
	//Se preencheu errado
	focoInicial.focus();
	return false;
}

function alterarAutomovel()
{
	//Valida o preenchimento  		
	if(validarCadastroAutomovel())
	{
		document.cadastroAuto.action = "alteracao.do";
  		document.cadastroAuto.submit();
	}		
}

function excluirAutomovel()
{
	document.cadastroAuto.action = "exclusao.do";
	document.cadastroAuto.submit();
}
	
function locarAutomovel()
{
	document.cadastroAuto.action = "../locacao/locacao.do";
	document.cadastroAuto.submit();
}