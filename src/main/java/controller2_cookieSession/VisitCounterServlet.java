package controller2_cookieSession;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VisitCounterServlet
 */
@WebServlet("/visit")
public class VisitCounterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//クッキーから訪問回数を取得
		Cookie[] cookies = request.getCookies();
		int count = 1;
		if (cookies != null) { //1回目の訪問かどうか判定
			for (Cookie c : cookies) {
				if (c.getName().equals("number")) {
					count = Integer.parseInt(c.getValue()) + 1;
				}
			}
		}

		//クッキーをセット
		Cookie firstCookie = new Cookie("number", String.valueOf(count));
		firstCookie.setMaxAge(60 * 60 * 24 * 2); // 2日間有効
		response.addCookie(firstCookie);

		//リクエストをJSPに転送
		request.setAttribute("visitCount", count);
		request.getRequestDispatcher("/WEB-INF/view/visitCounter.jsp")
				.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//「リセットボタン」が押されたら、クッキーを削除
		Cookie cookie = new Cookie("number", "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		request.getRequestDispatcher("/WEB-INF/view/visitCounter.jsp")
				.forward(request, response);

	}
}
