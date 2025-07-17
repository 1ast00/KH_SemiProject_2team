package controller;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import view.ModelAndView;

public class MemberMypageInfoView implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 로그인 정보
        HttpSession session = request.getSession();

        session.setAttribute("user", "MM10000001");
		return new ModelAndView("member_mypage_info.jsp", false);
	}
}
