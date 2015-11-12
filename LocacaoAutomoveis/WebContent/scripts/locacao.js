function selecionarOptionAgencia(agenciaLocacao, agenciaDevolucao)
{		
	document.getElementById("agLoc" + agenciaLocacao).selected = true;
	document.getElementById("agDev" + agenciaDevolucao).selected = true;
}

function selecionarModoTarifa(modoTarifa)
{
	if(modoTarifa == 0)
	{
		document.getElementById("radioTarifaSimples").checked = true;
	}
	else if(modoTarifa == 1)
	{
		document.getElementById("radioTarifaControlada").checked = true;
	}
	
}

function atualizarTarifa(tarifaSimples)
{
	var form = document.formLocacao;
	
	if(form.dataLocacao.value != "" && form.dataDevolucao.value != "")
	{
		var dataLocacao = new Date(form.dataLocacao.value);
		//Coloca mais um dia por causa da zona de fuso horario. Se nao somar um dia, fica aparecendo como data de ontem...
		dataLocacao.setDate(dataLocacao.getDate() + 1);  
		
		var dataDevolucao = new Date(form.dataDevolucao.value);
		dataDevolucao.setDate(dataDevolucao.getDate() + 1);
		
		var diferencaDias = Math.ceil(Math.abs(dataDevolucao.getTime() - dataLocacao.getTime()) / (1000 * 3600 * 24)); //1000 * 3600 * 24 = milisegundos por dia
		
		//alert("Dias de locacao: " + diferencaDias);
			
		document.getElementById("valorTarifa").innerHTML = "R$ " + (diferencaDias * tarifaSimples);
		//document.getElementById("valorTarifaControlada").innerHTML = "R$ " + tarifaControlada;
	}
}

//Verifica todos os preenchimentos e incrementa se houve algum erro
var houveErro;
//Usado para focar o primeiro input com erro
var focoInicial;

function validarDadosLocacao()
{	
	var form = document.formLocacao;
	houveErro = 0;
	focoInicial = null;
	
	verificarPreenchimento(form.dataLocacao, "erroDataLocacao", "Informe a data de locaÁ„o");
	verificarSelecao(form.agenciaLocacao, "erroAgenciaLocacao", "Selecione a ag√™ncia de locaÁ„o");
	verificarPreenchimento(form.cidadeLocacao, "erroCidadeLocacao", "Informe a cidade de locaÁ„o");
	verificarPreenchimento(form.dataDevolucao, "erroDataDevolucao", "Informe a data de devoluÁ„o");
	verificarSelecao(form.agenciaDevolucao, "erroAgenciaDevolucao", "Selecione a ag√™ncia de devoluÁ„o");
	verificarPreenchimento(form.cidadeDevolucao, "erroCidadeDevolucao", "Informe a cidade de devoluÁ„o");
	verificarPreenchimento(form.modoTarifa, "erroModoTarifa", "Selecione o modo de cobran√ßa");
	
	//Se deu tudo certo, submeter o formul√°rio
	if(houveErro == 0)
	{		
		form.acao.value = "validarLocacao";		
		form.submit();
	}
	
	//Se preencheu errado
	focoInicial.focus();	
}

function validarConsultaCliente()
{
	var form = document.formLocacao;
	houveErro = 0;
	focoInicial = null;
	
	verificarPreenchimento(form.cpfCliente, "erroCpfCliente", "Informe o cpf do cliente");
	
	//Se deu tudo certo, submeter o formul√°rio
	if(houveErro == 0)
	{		
		form.acao.value = "consultarCliente";		
		form.submit();
	}
	
	//Se preencheu errado
	focoInicial.focus();
}

function pagamento()
{
	var form = document.formLocacao;
	form.acao.value = "pagamento";
	form.submit();
}

function mostrarMsgErroLocacao(erro)
{
	//N√£o conseguiu cadastrar a locacao
	if(erro == "1")
	{
		document.getElementById("spanErroLocacao").innerHTML = "Ocorreu um erro ao cadastrar a locaÁ„o";
	}
	else if(erro == "2") //Periodo n√£o disponivel
	{
		document.getElementById("spanErroLocacao").innerHTML = "Per√≠do selecionado n√£o est√° disponivel. Por favor, selecione outra data";
	}
	else if(erro == "3") //Periodo n√£o disponivel
	{
		document.getElementById("spanErroCliente").innerHTML = "Cliente n√£o encontrado. Por favor verifique se o CPF foi digitado corretamente" 
																+ " e o cliente j√° √© cadastrado";
	}
}
