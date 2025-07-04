package loginservlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String id = req.getParameter("member_id");
        String pw = req.getParameter("member_pw");

        if ("admin".equals(id) && "1234".equals(pw)) {
            HttpSession session = req.getSession();
            session.setAttribute("id", id);
            res.sendRedirect("main.jsp");
        } else {
            res.sendRedirect("login.jsp");
        }
    }
}
