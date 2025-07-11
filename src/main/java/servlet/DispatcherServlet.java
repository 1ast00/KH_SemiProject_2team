package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import view.ModelAndView;

import java.io.IOException;

import controller.Controller;
import controller.HandlerMapping;

@WebServlet("*.do")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 2, 
		maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String rootPath = "/WEB-INF/views/";

	public DispatcherServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 사용자가 요청한 경로
		int n = request.getRequestURI().lastIndexOf("/");
		String command = request.getRequestURI().substring(n + 1).replace(".do", "");

		// 요청 전달
		Controller controller = HandlerMapping.getInstance().createController(command);
		ModelAndView view = null;

		if (controller != null)
			view = controller.execute(request, response);

		// 페이지 이동처리
		if (view != null) {
			if (view.isRedirect()) {
				response.sendRedirect(request.getContextPath() + view.getPath());
			} else {
				request.getRequestDispatcher(rootPath + view.getPath()).forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}