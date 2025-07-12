package controller;

import dao.AdminDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/checkAdminId")
public class CheckAdminIdServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String admin_id = request.getParameter("admin_id");
        AdminDAO dao = new AdminDAO();
        boolean isDuplicate = dao.isIdDuplicate(admin_id);

        response.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(isDuplicate ? "duplicate" : "available");
    }
}