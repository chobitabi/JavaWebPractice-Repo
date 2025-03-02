package controller_practice10;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminDao;
import dao.DaoFactory;
import domain.Admin;

/**
 * Servlet implementation class LoginServlet2
 */
@WebServlet("/login2")
public class LoginServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/view/login2.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//フォームからデータを取得
		try {
			String loginId = request.getParameter("loginId");
			String loginPass = request.getParameter("loginPass");
			AdminDao adminDao = DaoFactory.createAdminDao(); //DAOを使ってデータをデータベースに追加
			Admin admin = adminDao.findByLoginIdAndLoginPass(loginId, loginPass); //DBのadminsテーブルを検索

			if (admin != null) { //ログイン成功
				request.getSession().setAttribute("loginId", admin.getLoginId());
				response.sendRedirect("listMember");
			} else { //ログイン失敗
				request.setAttribute("error", true); //エラーフラグをセット
				request.getRequestDispatcher("WEB-INF/view/login2.jsp")
						.forward(request, response);
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

}
