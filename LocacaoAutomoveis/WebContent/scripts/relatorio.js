function validarRelatorio()
{	
	if(document.formRelatorio.dataLocacao.value != "")
	{
		return true;
	}
	
	
	document.getElementById("erroData").innerHTML = "Informe a data da locação";
	document.formRelatorio.dataLocacao.focus();
	return false;
			
}

