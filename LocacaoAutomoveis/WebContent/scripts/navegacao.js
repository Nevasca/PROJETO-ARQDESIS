//Funcoes para escrever na pagina os itens de navegacao em comum


//Url completa do sistema
var dominio = "http://localhost:8080/LocacaoAutomoveis/";

//Escreve na pagina o HTML para a barra do topo
function setBarraTopo()
{
    var barra = "<nav class='navbar navbar-default'>" +
		"<div class='container-fluid'>" +
			"<div class='navbar-header'>" +
				"<a class='navbar-brand' href='" + dominio + "index.jsp'>BEG</a>" +
			"</div>" +
			"<div>" +
				"<ul class='nav navbar-nav navbar-left'>" +
					"<li>" +
						"<a href='#' id='menu-toggle'><span class='glyphicon glyphicon-th-list'></span> Menu</a>" +
					"</li>" +
				"</ul>" +
			"</div>" +
			"<div>" +
				"<ul class='nav navbar-nav navbar-right'>" +
					"<li>" +
						"<a href='#' onclick='document.formBuscaRapida.submit()'><span class='glyphicon glyphicon-search'></span> Buscar por modelo ou fabricante</a>" +
					"</li>" +
					"<li>" +
						"<form role='form' name='formBuscaRapida' method='post' action='" + dominio + "automovel/consultaRapida.do'>" +
							"<input type='text' class='form-control' name='buscaRapida'/>" +
						"</form>" +
					"</li>" +
					"<li>" +
						"<form role='form' name='formLogout' method='post' action='" + dominio + "logout.do' hidden></form>" +
							"<a href='#' onclick='document.formLogout.submit()'><span class='glyphicon glyphicon-log-out'></span> Sair</a>" +
					"</li>" +
				"</ul>" +
			"</div>" +
		"</div>" +
	"</nav>";
    
    document.getElementById("barraTopo").innerHTML = barra;
}

//Escreve na pagina o HTML para a barra de menu lateral com menus
//Atualiza krl!
function setMenu(nome)
{
    var menu = "<div id='wrapper'>" +
        "<div id='sidebar-wrapper'>" +
            "<ul class='sidebar-nav'>" +
                "<li class='sidebar-brand'>" +
                        "<span class='glyphicon glyphicon-user'></span> " + nome +                        
                "</li>" +
                "<li>" +
                    "<a href='" + dominio + "automovel/cadastro.jsp'><span class='glyphicon glyphicon-plus'></span> Cadastrar Automovel</a>" +
                "</li>" +
                "<li>" +
                    "<a href='" + dominio + "automovel/consulta.jsp'><span class='glyphicon glyphicon-search'></span> Consultar Automovel</a>" +
                "</li>" +
                "<li>" +
                    "<a href='" + dominio + "devolucao/devolucao.jsp'><span class='glyphicon glyphicon-repeat'></span>  Devolver Automovel</a>" +
                "</li>" +
                "<li>" +
                    "<a href='" + dominio + "cliente/cadastro.jsp'><span class='glyphicon glyphicon-plus'></span> Cadastrar Cliente</a>" +
                "</li>" +
                "<li>" +
                    "<a href='" + dominio + "cliente/consulta.jsp'><span class='glyphicon glyphicon-search'></span> Consultar Cliente</a>" +
                "</li>" +
                "<li>" +
                    "<a href='" + dominio + "relatorio/relatorio.jsp'><span class='glyphicon glyphicon-list-alt'></span> Relatorios</a>" +
                "</li>" +
            "</ul>" +
        "</div>" +
      "</div>";
	
    document.getElementById("menu").innerHTML = menu;
}