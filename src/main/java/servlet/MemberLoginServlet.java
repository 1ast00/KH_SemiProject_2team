package servlet;

import dao.MemberDAO;
import dto.MemberDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/memberLogin")
public class MemberLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // ✅ JSP에서 넘겨주는 name과 일치
        String member_id = request.getParameter("member_id");
        String member_pw = request.getParameter("member_pw");

        MemberDAO dao = new MemberDAO();
        MemberDTO member = dao.login(member_id, member_pw);  // 로그인 메서드 사용

        if (member != null) {
            HttpSession session = request.getSession();
            // ✅ 필요한 정보만 저장
            session.setAttribute("member_id", member.getMember_id());
            session.setAttribute("member_name", member.getMember_name());

            response.sendRedirect("main.jsp");
        } else {
            response.sendRedirect("member_login.jsp?error=1");
        }
    }
}
