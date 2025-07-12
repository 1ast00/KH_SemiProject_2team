package controller;

import java.io.IOException;
import java.io.PrintWriter;

import dao.MemberDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/checkMemberId")
public class MemberIdCheckServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String member_id = request.getParameter("member_id");
        MemberDAO dao = new MemberDAO();
        boolean isDuplicate = dao.isMemberIdDuplicate(member_id);

        response.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = response.getWriter();

        if (isDuplicate) {
            out.print("duplicate");
        } else {
            out.print("available");
        }
    }
}
