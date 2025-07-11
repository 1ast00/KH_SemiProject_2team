package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.WitnessAPIService;
import view.ModelAndView;

public class WitnessMapSearchController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String keyword = request.getParameter("keyword");
		String missingPlace = "서울역";
		ModelAndView view = null;
		
		// --- 디버깅을 위한 로그 추가: 반드시 확인하세요! ---
		System.out.println("MapServlet - Received keyword: [" + keyword + "]");
		// -------------------------------------------------

		if (keyword == null || keyword.trim().isEmpty()) {
			response.setContentType("text/plain;charset=UTF-8");
			response.getWriter().println("검색어를 입력하셈 ㅋ");
		}

		try {
			// service.Practice 클래스 호출 (철자 수정 반영)
			String[] result = WitnessAPIService.getCoordinates(keyword);
			String[] missingResult = WitnessAPIService.getCoordinates(missingPlace);

			if (result != null && missingResult != null) {
				request.setAttribute("lat", result[0]); // 위도
				request.setAttribute("lng", result[1]); // 경도
				request.setAttribute("place", result[2]); // 장소 이름
				request.setAttribute("m_lat", missingResult[0]);
				request.setAttribute("m_lng", missingResult[1]);
				request.setAttribute("missingPlace", missingResult[2]);
				view = new ModelAndView("missing_map.jsp", false);
			} else {
				response.setContentType("text/plain;charset=UTF-8");
				response.getWriter().println("검색 결과 없음");
			}
		} catch (Exception e) {
			// 예외 발생 시 상세 로그 출력
			System.err.println("MapServlet - Error processing request: " + e.getMessage());
			e.printStackTrace(); // 스택 트레이스 출력
			// throw new ServletException("Map Servlet Error", e); // 서블릿 예외로 다시 던짐
		}
		return view;
	}
}
