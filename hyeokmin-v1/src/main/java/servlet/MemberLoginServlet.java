package servlet;

import dao.MemberDAO;
import dto.MemberDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/memberLogin")
public class MemberLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String member_id = request.getParameter("member_id");
        String member_pw = request.getParameter("member_pw");

        MemberDAO dao = new MemberDAO();
        MemberDTO dto = dao.loginMember(member_id, member_pw);

        if (dto != null) {
            // 로그인 성공
            HttpSession session = request.getSession();
            session.setAttribute("member", dto);

            response.sendRedirect("main.jsp");
        } else {
            // 로그인 실패
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<script>");
            response.getWriter().println("alert('아이디 또는 비밀번호가 틀렸습니다.');");
            response.getWriter().println("history.back();");
            response.getWriter().println("</script>");
        }
    }
}
