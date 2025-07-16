package controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
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
        String inputPw = request.getParameter("member_pw").trim();

        
        String hashedPw = encryptSHA512(inputPw);
        System.out.println("로그인 시도 아이디: [" + member_id + "]");
        System.out.println("로그인 시도 비밀번호(암호화 후): [" + hashedPw + "]");

        
        Map<String, Object> map = new HashMap<>();
        map.put("member_id", member_id);
        map.put("member_pw", hashedPw);

       
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
            session.setAttribute("loginMember", member);
            return new ModelAndView("/main.do", true);
        }
    }

    
    private String encryptSHA512(String pw) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] hash = digest.digest(pw.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}



