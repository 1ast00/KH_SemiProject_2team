package controller;

import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MissingAPIService;
import view.ModelAndView;

// 외부 지도 API호출, 기존 입력값 보관했다가 다시 전달
public class MissingMapSearchController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView view = null;

		// 장소 검색어와 다른 모든 입력값 받음
		String placeQuery = request.getParameter("missingPlace");
		
		// (수정/등록 상태 유지를 위한 고유번호와 폼 데이터들)
		String missingSerialNum = request.getParameter("missingSerialNum");
		String missingName = request.getParameter("missingName");
		String missingGender = request.getParameter("missingGender");
		String missingBirth = request.getParameter("missingBirth");
		String missingEtc = request.getParameter("missingEtc");
		String missingDate = request.getParameter("missingDate");

		// 장소 검색어가 비어있는지 확인
		if (placeQuery == null || placeQuery.trim().isEmpty()) {
			request.setAttribute("errorMessage", "장소명을 입력해주세요.");
			view = new ModelAndView("missing_insert.jsp", false);
		} else {
			
			try {
				// 외부 지도 API서비스 호출해서 위도, 경도 가져옴
				String[] result = MissingAPIService.getCoordinates(placeQuery);

				// API 호출 결과에 따라 나눠서 처리
				if (result != null) {
					// 검색 성공시 -> 좌표와 장소명, 기존 입력값들 request에 담아서 지도페이지로 전달
					request.setAttribute("lat", result[0]);
					request.setAttribute("lng", result[1]);
					request.setAttribute("place", result[2]); // API가 찾아준 정확한 장소 이름

					// 입력값들 그대로 다음 페이지로 전달
					request.setAttribute("missingSerialNum", missingSerialNum);
					request.setAttribute("missingName", missingName);
					request.setAttribute("missingGender", missingGender);
					request.setAttribute("missingBirth", missingBirth);
					request.setAttribute("missingEtc", missingEtc);
					request.setAttribute("missingDate", missingDate);

					view = new ModelAndView("missing_map.jsp", false);
				} else {
					// 검색 결과가 없을 경우, 입력값 유지를 위해 다시 전달
					request.setAttribute("errorMessage", "장소를 찾을 수 없습니다. 다른 검색어로 시도해보세요.");
					request.setAttribute("missingName", missingName);
					request.setAttribute("missingGender", missingGender);
					request.setAttribute("missingBirth", missingBirth);
					request.setAttribute("missingEtc", missingEtc);
					request.setAttribute("missingDate", missingDate);
					view = new ModelAndView("missing_insert.jsp", false);
				}
			} catch (Exception e) { // API 통신 중 예외발생 시 에러메시지 담아서 이전 폼페이지로 전달
				e.printStackTrace();
				request.setAttribute("errorMessage", "지도 API 서버 통신 중 오류가 발생했습니다: " + e.getMessage());
				view = new ModelAndView("missing_insert.jsp", false);
			}
		}
		return view;
	}
}