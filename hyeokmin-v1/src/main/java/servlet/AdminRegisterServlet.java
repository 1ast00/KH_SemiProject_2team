package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.AdminDAO;
import dto.AdminDTO;

import java.io.IOException;

@WebServlet("/adminRegister")
public class AdminRegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");

        String admin_id = request.getParameter("admin_id");
        String admin_pw = request.getParameter("admin_pw");
        String admin_name = request.getParameter("admin_name");
        String admin_email = request.getParameter("admin_email");
        String admin_phone = request.getParameter("admin_phone");

        AdminDTO admin = new AdminDTO(admin_id, admin_pw, admin_name, admin_email, admin_phone);
        AdminDAO dao = new AdminDAO();
        int result = dao.insertAdmin(admin);

        if (result > 0) {
            response.sendRedirect("admin_login.jsp");
        } else {
            response.sendRedirect("admin_register.jsp?error=1");
        }
    }
}
