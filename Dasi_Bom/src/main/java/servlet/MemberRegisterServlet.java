package servlet;

import dao.MemberDAO;
import dto.MemberDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/memberRegister")
public class MemberRegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // 파라미터 수집
        String member_serialNum = request.getParameter("member_serialNum");
        String member_id = request.getParameter("member_id");
        String member_pw = request.getParameter("member_pw");
        String member_name = request.getParameter("member_name");
        String member_email = request.getParameter("member_email");
        String member_phone = request.getParameter("member_phone");
        String admin_serialNum = request.getParameter("admin_serialNum");

        // DTO에 담기
        MemberDTO dto = new MemberDTO(member_serialNum, member_id, member_pw,
                                      member_name, member_email, member_phone, admin_serialNum);

        // DAO 처리
        MemberDAO dao = new MemberDAO();
        int result = dao.registerMember(dto);

        if (result > 0) {
            // 성공
            response.sendRedirect("member_login.jsp");
        } else {
            // 실패
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<script>");
            response.getWriter().println("alert('회원가입 실패. 다시 시도하세요.');");
            response.getWriter().println("history.back();");
            response.getWriter().println("</script>");
        }
    }
}
