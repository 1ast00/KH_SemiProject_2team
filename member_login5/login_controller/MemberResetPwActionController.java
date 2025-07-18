package controller.member;

import java.io.IOException;

import controller.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dto.MemberDTO;
import service.MemberService;
import view.ModelAndView;

public class MemberResetPwActionController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {

	    String id    = req.getParameter("member_id");
	    String email = req.getParameter("member_email");
	    String encPw = (String) req.getAttribute("encrypted_new_pw");

	    MemberDTO dto = new MemberDTO();
	    dto.setMember_id(id);
	    dto.setMember_email(email);
	    dto.setMember_pw(encPw);

	    int cnt = MemberService.getInstance().resetPassword(dto);

	    resp.setContentType("application/json;charset=UTF-8");

	    if (cnt == 1) {
	        resp.getWriter().write("{\"result\":\"success\"}");
	    } else {
	        resp.getWriter().write("{\"result\":\"fail\", \"message\":\"비밀번호 변경에 실패했습니다. 다시 시도해주세요.\"}");
	    }

	    return null; 
	}
}


