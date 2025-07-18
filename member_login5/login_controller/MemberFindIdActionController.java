package controller.member;

import java.io.IOException;

import controller.Controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dto.MemberDTO;
import service.MemberService;
import view.ModelAndView;

public class MemberFindIdActionController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

	    String name  = req.getParameter("member_name").trim();
	    String email = req.getParameter("member_email").trim();

	    MemberDTO dto = new MemberDTO();
	    dto.setMember_name(name);
	    dto.setMember_email(email);

	    String foundId = MemberService.getInstance().findId(dto);

	    resp.setContentType("application/json;charset=UTF-8");

	    if (foundId != null) {
	        resp.getWriter().write("{\"result\":\"success\", \"member_id\":\"" + foundId + "\"}");
	    } else {
	        resp.getWriter().write("{\"result\":\"fail\"}");
	    }

	    return null;  // JSP 이동 없이 JSON 응답 후 종료
	}
}

