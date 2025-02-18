package controller2_cookieSession;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getAttribute("message") == null) {
			request.setAttribute("message", "");
		}
		
		request.getRequestDispatcher("/WEB-INF/view/login.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//ユーザーからログインフォームが送信された後の処理
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//フォームからの情報を取得
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		
		// 仮のユーザーデータ (本番ではDBを使う)
		String validUser = "eri";
		String validPass = "Eri218";

		//認証成功したらセッションにデータを保存
		if (userName.equals(validUser) && password.equals(validPass)) {
			HttpSession session = request.getSession();
			session.setAttribute("userName", userName);
			response.sendRedirect("welcome");
		} else {

			//認証に失敗したら
			request.setAttribute("message", "ログイン失敗");
			request.getRequestDispatcher("/WEB-INF/view/login.jsp")
					.forward(request, response);

		}
	}

}
