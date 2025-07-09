package servlet;

import dao.AdminDAO;
import dto.AdminDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/adminRegister")
public class AdminRegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String admin_serialNum = request.getParameter("admin_serialNum");
        String admin_id = request.getParameter("admin_id");
        String admin_pw = request.getParameter("admin_pw");
        String admin_name = request.getParameter("admin_name");
        String admin_phone = request.getParameter("admin_phone");
        String admin_email = request.getParameter("admin_email");

        AdminDTO dto = new AdminDTO(admin_serialNum, admin_id, admin_pw, admin_name, admin_phone, admin_email);
        AdminDAO dao = new AdminDAO();
        int result = dao.registerAdmin(dto);

        if (result > 0) {
            response.sendRedirect("admin_login.jsp");
        } else {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<script>alert('등록 실패'); history.back();</script>");
        }
    }
}

