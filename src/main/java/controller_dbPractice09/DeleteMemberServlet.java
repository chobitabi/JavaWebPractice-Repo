package controller_dbPractice09;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.MemberDao;
import domain.Member;

/**
 * Servlet implementation class DeleteMemberServlet
 */
@WebServlet("/deleteMember")
public class DeleteMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 削除する会員のIDの取得
		String strId = request.getParameter("id");
		Integer id = Integer.parseInt(strId);

		try {
			//削除する会員データの取得
			MemberDao memberDao = DaoFactory.createMemberDao();
			Member member = memberDao.findById(id);

			//削除ページの表示
			request.setAttribute("member", member);
			request.getRequestDispatcher("/WEB-INF/view/deleteMember.jsp")
					.forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 削除する会員のIDの取得
		String strId = request.getParameter("id");
		Integer id = Integer.parseInt(strId);

		// 削除メソッドの引数用にMemberオブジェクトを作成
		Member member = new Member();
		member.setId(id);

		try {
			//データの削除
			MemberDao memberDao = DaoFactory.createMemberDao();
			memberDao.delete(member);

			//削除完了ページの表示
			request.getRequestDispatcher("/WEB-INF/view/deleteMemberDone.jsp")
					.forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}

	}
}
