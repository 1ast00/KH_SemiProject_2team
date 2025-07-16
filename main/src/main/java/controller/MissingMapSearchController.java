package controller;

import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MissingAPIService;
import view.ModelAndView;

public class MissingMapSearchController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView view = null;

		String placeQuery = request.getParameter("missingPlace");

		String missingName = request.getParameter("missingName");
		String missingGender = request.getParameter("missingGender");
		String missingBirth = request.getParameter("missingBirth");
		String missingEtc = request.getParameter("missingEtc");
		String missingDate = request.getParameter("missingDate");

		if (placeQuery == null || placeQuery.trim().isEmpty()) {
			request.setAttribute("errorMessage", "장소명을 입력해주세요.");
			view = new ModelAndView("missing_insert.jsp", false);
		} else {
			try {
				String[] result = MissingAPIService.getCoordinates(placeQuery);

				if (result != null) {
					request.setAttribute("lat", result[0]);
					request.setAttribute("lng", result[1]);
					request.setAttribute("place", result[2]); // API에서 반환된 정확한 장소 이름

					// 다른 입력값들도 그대로 전달
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
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "지도 API 서버 통신 중 오류가 발생했습니다: " + e.getMessage());
				view = new ModelAndView("missing_insert.jsp", false);
			}
		}
		return view;
	}
}