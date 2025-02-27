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
		 //フォームのデータを取得
		String name = request.getParameter("name");
		Integer age = Integer.parseInt(request.getParameter("age"));
		Integer typeId = Integer.parseInt(request.getParameter("typeId"));
		String address = request.getParameter("address");

		//取得したデータを箱（Memberオブジェクト)に入れる
		Member member = new Member();
		member.setName(name);
		member.setAge(age);
		member.setTypeId(typeId);
		member.setAddress(address);
		
		try {
			//DAOを使ってデータをDB二保存
			MemberDao memberDao = DaoFactory.createMemberDao();
			memberDao.insert(member);
			request.getRequestDispatcher("/WEB-INF/view/addMemberDone.jsp")
				.forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
