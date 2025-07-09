package servlet;

import dao.AdminDAO;
import dto.AdminDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin_register")
public class AdminRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        AdminDTO dto = new AdminDTO();
        dto.setAdmin_serialNum(String.valueOf(System.currentTimeMillis() % 100000)); // 예시 ID
        dto.setAdmin_id(request.getParameter("admin_id"));
        dto.setAdmin_pw(request.getParameter("admin_pw"));
        dto.setAdmin_name(request.getParameter("admin_name"));
        dto.setAdmin_phone(request.getParameter("admin_phone"));
        dto.setAdmin_email(request.getParameter("admin_email"));

        AdminDAO dao = new AdminDAO();
        int result = dao.insertAdmin(dto);

        if (result > 0) {
            response.sendRedirect("admin_login.jsp");
        } else {
            response.sendRedirect("admin_register.jsp?error=1");
        }
    }
}