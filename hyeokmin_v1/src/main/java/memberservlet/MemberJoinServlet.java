package memberservlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/signup")
public class MemberJoinServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String id = req.getParameter("member_id");
        String pw = req.getParameter("member_pw");
        String email = req.getParameter("member_email");
        String name = req.getParameter("member_name");

        // 실제로는 DB에 저장
        System.out.println("회원가입 성공: " + id + ", " + email);

        res.sendRedirect("login.jsp");
    }
}
