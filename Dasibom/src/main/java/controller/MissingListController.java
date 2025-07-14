package controller;

import java.io.IOException;
import java.util.List;

import dto.MissingPersonDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession; // HttpSession 임포트
import service.MissingService;
import view.ModelAndView;

public class MissingListController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 로그인 시뮬레이션
        HttpSession session = request.getSession();
        // 세션에 로그인 정보가 없을 경우에만 임시 정보 설정
        if (session.getAttribute("loginType") == null) {
            // 'MM10000001' 회원이 로그인했다고 가정 -> 재경님이 넣은 샘플 데이터
            session.setAttribute("loginType", "MEMBER"); // 사용자 타입 (MEMBER 또는 ADMIN)
            session.setAttribute("loginSerial", "MM10000001"); // 사용자의 고유 번호

            // 관리자로 테스트하기(MEMBER, MM10000001 부분 주석 처리하고 해보면 됨
            // session.setAttribute("loginType", "ADMIN");
            // session.setAttribute("loginSerial", "AA10000001");
        }
        // ------------------------------------

		List<MissingPersonDTO> list = MissingService.getInstance().selectMissingList();
		
		System.out.println("DB에서 가져온 실종자 목록 개수: " + list.size());
		
		request.setAttribute("missingList", list);
		return new ModelAndView("missing_list.jsp", false);
	}
}
