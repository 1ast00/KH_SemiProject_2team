package controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

import dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MemberService;
import view.ModelAndView;

public class MemberRegisterController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String id = request.getParameter("member_id");
        String pw = request.getParameter("member_pw");  
        String name = request.getParameter("member_name");
        String phone = request.getParameter("member_phone");
        String email = request.getParameter("member_email");

        String admin_serialNum = "Admin";
        String encPw = encryptSHA512(pw);

        MemberDTO dto = new MemberDTO();
        dto.setMember_id(id);
        dto.setMember_pw(encPw);
        dto.setMember_name(name);
        dto.setMember_phone(phone);
        dto.setMember_email(email);
        dto.setAdmin_serialNum(admin_serialNum);

        
        boolean isDuplicate = MemberService.getInstance().isMemberIdDuplicate(id);
        if (isDuplicate) {
            
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<script>");
            response.getWriter().println("alert('이미 사용중인 아이디입니다.');");
            response.getWriter().println("history.back();");
            response.getWriter().println("</script>");
            return null;
        }

       
        int result = MemberService.getInstance().insertMember(dto);

        if (result > 0) {
            return new ModelAndView("/memberLoginView.do", true);
        } else {
            request.setAttribute("msg", "회원가입 실패. 다시 시도하세요.");
            return new ModelAndView("member_register.jsp", false);
        }
    }

    private String encryptSHA512(String pw) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = pw.getBytes(StandardCharsets.UTF_8);
            byte[] hashedPw = md.digest(bytes);
            return Base64.getEncoder().encodeToString(hashedPw);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}