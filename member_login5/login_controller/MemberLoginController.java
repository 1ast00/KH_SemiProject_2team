package controller.member;

import java.io.IOException;

import controller.Controller;
import dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.MemberService;
import util.EncryptUtil;
import view.ModelAndView;

public class MemberLoginController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String member_id = request.getParameter("member_id").trim();
        String inputPw = request.getParameter("member_pw").trim();

        String hashedPw = EncryptUtil.encryptSHA512(inputPw);

        System.out.println("로그인 시도 아이디: [" + member_id + "]");
        System.out.println("로그인 시도 비밀번호(암호화 후): [" + hashedPw + "]");

        MemberDTO dto = new MemberDTO();
        dto.setMember_id(member_id);
        dto.setMember_pw(hashedPw);

        MemberDTO member = MemberService.getInstance().memberLogin(dto);

        if (member == null) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<script>");
            response.getWriter().println("alert('아이디 또는 비밀번호가 올바르지 않습니다.');");
            response.getWriter().println("history.back();");
            response.getWriter().println("</script>");
            return null;
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("loginMember", member);

            return new ModelAndView("/main.do", true);
        }
    }
}




