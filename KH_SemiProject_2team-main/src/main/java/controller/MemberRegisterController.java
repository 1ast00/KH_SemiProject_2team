package controller;

import java.io.IOException;
import java.sql.Date;

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
        String birth = request.getParameter("member_birth");
        String gender = request.getParameter("member_gender");
        String phone = request.getParameter("member_phone");
        String email = request.getParameter("member_email");
        String admin_serialNum = request.getParameter("admin_serialNum");

        MemberDTO dto = new MemberDTO();
        dto.setMember_id(id);
        dto.setMember_pw(pw);
        dto.setMember_name(name);
        dto.setMember_birth(Date.valueOf(birth));
        dto.setMember_gender(gender);
        dto.setMember_phone(phone);
        dto.setMember_email(email);
        dto.setAdmin_serialNum(admin_serialNum);

        int result = MemberService.getInstance().insertMember(dto);
        if (result > 0) {
            return new ModelAndView("/memberLoginView.do", true); 
        } else {
            request.setAttribute("msg", "È¸¿ø°¡ÀÔ ½ÇÆÐ");
            return new ModelAndView("member_register.jsp", false); 
        }
    }
}