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
    maxFileSize = 1024 * 1024 * 10,
    maxRequestSize = 1024 * 1024 * 50
)
public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String rootPath = "/WEB-INF/views/";

    public DispatcherServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 요청 경로 추출 예: /KH_SemiProject_2team/img/witness.do
        String uri = request.getRequestURI();             // 전체 요청 URI
        String context = request.getContextPath();        // /KH_SemiProject_2team
        String command = uri.substring(context.length() + 1).replace(".do", ""); // 예: img/witness

        // 로그로 확인
        System.out.println("🔗 Dispatcher command: " + command);

        // Controller 생성
        Controller controller = HandlerMapping.getInstance().createController(command);
        ModelAndView view = null;

        if (controller != null) {
            view = controller.execute(request, response);
        }

        // 뷰 이동 처리
        if (view != null) {
            if (view.isRedirect()) {
                response.sendRedirect(view.getPath());
            } else {
                request.getRequestDispatcher(rootPath + view.getPath()).forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}