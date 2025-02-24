package controller_dbPractice;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConnectionTestServlet1
 */
@WebServlet("/test1")
public class ConnectionTestServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter writer = response.getWriter();

		//データベース接続のための情報を設定
		String url = "jdbc:mysql://127.0.0.1:3306/mydb"
				+ "?useUnicode=true"
				+ "&characterEncoding=utf8"
				+ "&serverTimezone=Asia/Tokyo";
		String user = "root";
		String password = "";

		//データベースへの接続
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //JDBCドライバをロード
			con = DriverManager.getConnection(url, user, password);
			writer.println("接続しました");
		} catch (Exception e) {
			writer.println("接続に失敗しました");
		} finally { //データベース接続を閉じる（切断処理）
			try {
				if (con != null) {
					con.close();
					writer.println("切断しました");
				}
			} catch (SQLException e) {
				writer.println("切断に失敗しました");
			}
		}

	}

}
