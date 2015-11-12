package control.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GenericException;
import model.Login;

@WebServlet("/login.do")
public class ConsultaLoginControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ConsultaLoginControl() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		Login login = new Login();

		login.setLoginUsuario(request.getParameter("login"));
		login.setSenhaUsuario(request.getParameter("senha"));

		try {
			login = login.consulta();
		} catch (GenericException e) {
			e.printStackTrace();
		}

		if (login.getNomeUsuario() != null) {
			System.out.println("entrou");

			HttpSession sessao = request.getSession();
			sessao.setAttribute("dadosAcesso", login);
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);

		} else {

			request.setAttribute("dadosInvalidos", "Login ou senha Invalido");
			RequestDispatcher view = request.getRequestDispatcher("login.jsp");
			view.forward(request, response);
		}

	}
}
