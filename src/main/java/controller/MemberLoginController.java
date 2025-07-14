package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.MemberService;
import view.ModelAndView;

public class MemberLoginController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String member_id = request.getParameter("member_id").trim();
        String member_pw = request.getParameter("member_pw").trim();

        Map<String, Object> map = new HashMap<>();
        map.put("member_id", member_id);
        map.put("member_pw", member_pw);

        MemberDTO member = MemberService.getInstance().memberLogin(map);

        if (member == null) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<script>");
            response.getWriter().println("alert('아이디 또는 비밀번호가 틀렸습니다.');");
            response.getWriter().println("history.back();");
            response.getWriter().println("</script>");
            return null;
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("user", member);
            return new ModelAndView("/main.do", true);  // 로그인 성공 후 메인으로 리다이렉트
        }
    }
}

