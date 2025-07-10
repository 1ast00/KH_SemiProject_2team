package servlet;

import dao.AdminDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/admin_login")
public class AdminLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String admin_id = request.getParameter("admin_id");
        String admin_pw = request.getParameter("admin_pw");

        AdminDAO dao = new AdminDAO();
        boolean isValid = dao.isValidLogin(admin_id, admin_pw);

        if (isValid) {
            // 로그인 성공 -> 세션 부여
            HttpSession session = request.getSession();
            session.setAttribute("admin_id", admin_id);

            response.sendRedirect("main.jsp");
        } else {
            // 로그인 실패
            response.sendRedirect("admin_login.jsp?error=1");
        }
    }
}
