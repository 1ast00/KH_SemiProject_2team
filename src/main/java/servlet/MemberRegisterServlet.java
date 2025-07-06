package servlet;

import dao.MemberDAO;
import dto.MemberDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/memberRegister")
public class MemberRegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String member_id = request.getParameter("member_id");
        String member_pw = request.getParameter("member_pw");
        String member_name = request.getParameter("member_name");
        String member_phone = request.getParameter("member_phone");
        String member_email = request.getParameter("member_email");

        
        String member_serialNum = UUID.randomUUID().toString().substring(0, 10);

        
        MemberDTO dto = new MemberDTO(
            member_serialNum,
            member_id,
            member_pw,
            member_name,
            member_email,
            member_phone,
            null
        );

        MemberDAO dao = new MemberDAO();
        int result = dao.insertMember(dto);

        if (result > 0) {
            response.sendRedirect("member_login.jsp");
        } else {
            response.sendRedirect("member_register.jsp?error=1");
        }
    }
}
