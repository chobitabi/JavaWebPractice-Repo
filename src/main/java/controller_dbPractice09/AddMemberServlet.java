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
 * Servlet implementation class AddMemberServlet
 */
@WebServlet("/addMember")
public class AddMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/addMember.jsp")
				.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//バリデーション用のフラグ
		boolean isError = false;

		//フォームのデータを取得とバリデーション
		String name = request.getParameter("name");
		request.setAttribute("name", name); //再表示用
		if (name.isEmpty()) {
			//エラーメッセージの作成
			request.setAttribute("nameError", "名前が未入力です。");
			isError = true; //入力に不備ありと判定
		}

		//年齢は文字列として取得。未入力でなければ整数へ変換
		String strAge = request.getParameter("age");
		Integer age = null;
		request.setAttribute("age", strAge); //再表示用
		if (!strAge.isEmpty()) {
			try {
				age = Integer.parseInt(strAge);
			} catch (NumberFormatException e) {
				//整数に変換できない場合の処理
				request.setAttribute("ageError", "年齢は整数で入力してください。");
				isError = true;
			}
		}

		// 会員種別、住所はバリデーション不要
		Integer typeId = Integer.parseInt(request.getParameter("typeId"));
		String address = request.getParameter("address");
		request.setAttribute("typeId", typeId); //再表示用
		request.setAttribute("address", address); //再表示用 

		//入力に不備がある場合は、フォームを再表示し、処理を中断
		if (isError == true) {
			request.getRequestDispatcher("/WEB-INF/view/addMember.jsp")
					.forward(request, response);
			return;
		}

		//入力に不備がなければ、取得したデータを箱（Memberオブジェクト)に入れる
		Member member = new Member();
		member.setName(name);
		member.setAge(age);
		member.setTypeId(typeId);
		member.setAddress(address);

		try {
			//DAOを使ってデータをDBに保存
			MemberDao memberDao = DaoFactory.createMemberDao();
			memberDao.insert(member);
			request.getRequestDispatcher("/WEB-INF/view/addMemberDone.jsp")
					.forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
