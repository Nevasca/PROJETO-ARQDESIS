function validarRelatorio()
{	
	if(document.formRelatorio.dataLocacao.value != "")
	{
		return true;
	}
	
	
	document.getElementById("erroData").innerHTML = "Informe a data da loca��o";
	document.formRelatorio.dataLocacao.focus();
	return false;
			
}

