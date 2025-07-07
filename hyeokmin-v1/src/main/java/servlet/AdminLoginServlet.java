package servlet;

import dao.AdminDAO;
import dto.AdminDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/adminLogin")
public class AdminLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String admin_id = request.getParameter("admin_id");
        String admin_pw = request.getParameter("admin_pw");

        AdminDAO dao = new AdminDAO();
        AdminDTO dto = dao.loginAdmin(admin_id, admin_pw);

        if (dto != null) {
            HttpSession session = request.getSession();
            session.setAttribute("admin_id", dto.getAdmin_id());
            session.setAttribute("admin_name", dto.getAdmin_name());
            session.setAttribute("admin_serialNum", dto.getAdmin_serialNum());
            response.sendRedirect("admin_main.jsp");
        } else {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<script>alert('로그인 실패'); history.back();</script>");
        }
    }
}

