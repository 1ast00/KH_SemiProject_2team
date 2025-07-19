package controller;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import view.ModelAndView;

public class MemberLogoutController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		System.out.println("로그아웃 컨트롤러 실행됨");
	    
	    HttpSession session = request.getSession(false);
	    if (session != null) {
	        System.out.println("세션 무효화 전");
	        session.invalidate();
	        System.out.println("세션 무효화 완료");
	    } else {
	        System.out.println("세션이 이미 없음");
	    }
	    
	    System.out.println("main.do로 리다이렉트 시도");
	    return new ModelAndView("main.do", true);
	}
}
