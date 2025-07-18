package controller;

import java.io.IOException;

import dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import view.ModelAndView;

public class MemberMypageInfoView implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 로그인 정보
        HttpSession session = request.getSession();
        MemberDTO member = (MemberDTO) session.getAttribute("member"); // 로그인 정보 갖고 오기
        System.out.println(member.toString());

        // 임시 코드
        // session.setAttribute("user", "MM10000001");
		return new ModelAndView("member_mypage_info.jsp", false);
	}
}
