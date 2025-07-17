package controller;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MemberService;
import view.ModelAndView;

public class CheckMemberIDController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String memberId = request.getParameter("member_id");

		boolean isDuplicate = MemberService.getInstance().isMemberIdDuplicate(memberId);

		response.setContentType("text/plain;charset=UTF-8");

		if (isDuplicate) {
			response.getWriter().write("duplicate");
		} else {
			response.getWriter().write("available");
		}

		// AJAX 요청이므로 JSP로 이동하지 않음
		return null;
	}
}
