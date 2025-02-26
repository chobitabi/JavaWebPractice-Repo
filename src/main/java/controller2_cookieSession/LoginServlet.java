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

		//2回目の訪問時：クッキーの値を取り出す
		Cookie[] cookies = request.getCookies();
		String savedUser = null;

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("savedUser".equals(cookie.getName())) {
					savedUser = cookie.getValue();
				}
			}
		}

		//クッキーがある場合のみログイン状態にする
		if (savedUser != null) {
			HttpSession session = request.getSession();
			session.setAttribute("sessionUser", savedUser);
			
			//既存のクッキーを削除
			Cookie deleteCookie = new Cookie("savedUser", "");
			deleteCookie.setMaxAge(0);
			response.addCookie(deleteCookie);

			//クッキーの有効期限をリセットして7日間延長
			Cookie cookie = new Cookie("savedUser", savedUser);
			cookie.setMaxAge(60 * 60 * 24 * 7);
			response.addCookie(cookie);
			
			response.sendRedirect("welcome");
			return;
		}

		//クッキーがない場合
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
		String rememberMe = request.getParameter("rememberMe");

		//認証に失敗したら
		if (!("eri".equals(userName) && "Eri218".equals(password))) {
			request.setAttribute("message", "ログイン失敗");
			request.getRequestDispatcher("/WEB-INF/view/login.jsp")
					.forward(request, response);
			return;
		}

		//ログインに成功→セッションに保存
		HttpSession session = request.getSession();
		session.setAttribute("sessionUser", userName);

		//チェックボックスにチェックが入っていたら、クッキーにusernameを保存(クライアント側）
		if ("true".equals(rememberMe)) {
			Cookie cookie = new Cookie("savedUser", userName);
			cookie.setMaxAge(60 * 60 * 24 * 7);
			response.addCookie(cookie);
		} else {
			Cookie cookie = new Cookie("savedUser", "");
			cookie.setMaxAge(0); // クッキーを即時削除
			response.addCookie(cookie);
		}
		
		response.sendRedirect("welcome");
		return;


	}
}
