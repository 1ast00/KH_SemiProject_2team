package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import view.ModelAndView;
import controller.Controller;
import controller.HandlerMapping;
import util.EncryptUtil;

import java.io.IOException;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,
    maxFileSize = 1024 * 1024 * 10,
    maxRequestSize = 1024 * 1024 * 50
)
public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final String rootPath = "/WEB-INF/views/";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        int n = requestURI.lastIndexOf("/");
        String command = requestURI.substring(n + 1).replace(".do", "");

        System.out.println("[DispatcherServlet] 요청 URI : " + requestURI);
        System.out.println("[DispatcherServlet] 추출된 command : " + command);

        // 비밀번호 리셋 요청 시 SHA-512 암호화 처리
        if ("memberResetPwAction".equals(command)) {
            String rawPw = request.getParameter("new_pw");
            if (rawPw != null && !rawPw.isEmpty()) {
                String encPw = EncryptUtil.encryptSHA512(rawPw);
                request.setAttribute("encrypted_new_pw", encPw);
            }
        }

        Controller controller = HandlerMapping.getInstance().createController(command);

        if (controller != null) {
            System.out.println("[DispatcherServlet] 컨트롤러 생성됨 : " + controller.getClass().getSimpleName());
        } else {
            System.out.println("[DispatcherServlet] 컨트롤러 없음 (NULL)");
        }

        ModelAndView view = null;

        if (controller != null) {
            view = controller.execute(request, response);
        }

        if (view != null) {
            if (view.isRedirect()) {
                response.sendRedirect(request.getContextPath() + view.getPath());
            } else {
                request.getRequestDispatcher(rootPath + view.getPath()).forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);  // POST 요청도 doGet 로직으로 처리
    }
}



