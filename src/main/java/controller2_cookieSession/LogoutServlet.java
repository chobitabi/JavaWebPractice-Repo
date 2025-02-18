package controller2_cookieSession;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッションを破棄
		HttpSession session = request.getSession();
		session.invalidate();
		
		 // クライアントのクッキーを削除
        Cookie cookie = new Cookie("savedUser", "");
        cookie.setMaxAge(0); // クッキーを即時削除
        response.addCookie(cookie);
		
		response.sendRedirect("login");
		return;
		
	}

}
