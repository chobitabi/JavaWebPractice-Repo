package controller_dbPractice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import domain.Member;

/**
 * Servlet implementation class ListMemberServlet
 */
@WebServlet("/listMember")
public class ListMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//JNDIを使ってDBに接続
		Connection con = null;
		try {
			InitialContext ctx = new InitialContext(); //InitialContextを使ってJNDIにアクセス
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mydb"); //JNDI からリソースを探す
			con = ds.getConnection(); //DB接続
			System.out.println("接続成功");

			//DBから会員（member）の情報を取得
			String sql = "SELECT"
					+ " members.id, members.name, members.age,"
					+ " members.address, members.type_id, members.created,"
					+ " member_types.name as type_name"
					+ " FROM members"
					+ " JOIN member_types"
					+ " ON members.type_id = member_types.id";

			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(); //SQL文を実行

			List<Member> memberList = new ArrayList<>();
			while (rs.next()) { //データを1行ずつ処理
				// 取得したデータから各カラムの値を読み出す
				Integer id = (Integer) rs.getObject("id");
				String name = rs.getString("name");
				Integer age = (Integer) rs.getObject("age");
				String address = rs.getString("address");
				Integer typeId = (Integer) rs.getObject("type_id");
				Date created = rs.getTimestamp("created");
				String typeName = rs.getString("type_name");

				//Memberオブジェクトを作成してリストに追加
				Member member = new Member(id, name, age, address, typeId, created, typeName);
				memberList.add(member);

			}

			//JSPへフォワード
			request.setAttribute("memberList", memberList);
			request.getRequestDispatcher("/WEB-INF/view/listMember.jsp")
					.forward(request, response);

		} catch (Exception e) {
			throw new ServletException(e);
		} finally { //必ず実行される
			try {
				if (con != null) { //接続が成功している場合
					con.close();
				}
			} catch (SQLException e) {
				throw new ServletException(e);
			}
		}

	}

}
