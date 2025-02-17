package Servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/upload")
@MultipartConfig(location = "C:/temp")

public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/upload.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// アップロードされたファイルの情報を取得
		Part part = request.getPart("upfile");
		long fileSize = part.getSize();
		String fileType = part.getContentType();
		String fileName = part.getSubmittedFileName();

		// アップロードされたファイルを保存
		if (fileSize > 0) {
			File filePath = getUploadDirectory(request);
			part.write(filePath + "/" + fileName);
		}

		//完了画面の表示
		request.setAttribute("fileSize", fileSize);
		request.setAttribute("fileType", fileType);
		request.setAttribute("fileName", fileName);
		request.getRequestDispatcher("WEB-INF/view/uploadDone.jsp")
				.forward(request, response);

	}

	private File getUploadDirectory(HttpServletRequest request) {
		ServletContext context = request.getServletContext();
		String path = context.getRealPath("/uploads");
		return new File(path);
	}

}
