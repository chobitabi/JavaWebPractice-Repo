package controller2_cookieSession;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class WelcomeServlet
 */
@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//セッションにログイン済みであるというデータがあるか確認
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("sessionUser") == null) {
			response.sendRedirect("login");
			return;
		}
		request.getRequestDispatcher("/WEB-INF/view/welcome.jsp")
				.forward(request, response);
	}
}
