package controller;

import java.io.IOException;
<<<<<<< HEAD

=======
>>>>>>> 583fe526a5b73f5e3c272cc5058100d3a7083794
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MissingAPIService;
import view.ModelAndView;

public class MissingMapSearchController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView view = null;
<<<<<<< HEAD
		
		String place = request.getParameter("place");
		
		if (place == null || place.trim().isEmpty()) {
            response.getWriter().println("장소명을 입력해주세요.");
        } else {
        	try {
        		String[] result = MissingAPIService.getCoordinates(place);

                if (result != null) {
                    request.setAttribute("lat", result[0]);
                    request.setAttribute("lng", result[1]);
                    request.setAttribute("place", result[2]); // 검색된 장소 이름 
                    view = new ModelAndView("missing_map.jsp", false);
                } else {
                    response.getWriter().println("장소를 찾을 수 없습니다.");
                    view = new ModelAndView("missing_insert.jsp", false);
                }
        	} catch (Exception e) {
                e.printStackTrace();
                response.getWriter().println("오류 발생: " + e.getMessage());
            }
        }
		
=======

		String place = request.getParameter("place");
		
		// missing_insert.jsp에서 추가된 input태그 hidden타입으로 전송받음
		String missingName = request.getParameter("missingName");
		String missingGender = request.getParameter("missingGender");
		String missingBirth = request.getParameter("missingBirth");
		String missingEtc = request.getParameter("missingEtc");
		String missingDate = request.getParameter("missingDate");

		if (place == null || place.trim().isEmpty()) {
			response.getWriter().println("장소명을 입력해주세요.");
		} else {
			try {
				String[] result = MissingAPIService.getCoordinates(place);

				if (result != null) {
					request.setAttribute("lat", result[0]);
					request.setAttribute("lng", result[1]);
					request.setAttribute("place", result[2]); // 검색된 장소 이름

					// missing_map.jsp로 전달하기위해 request에 다시 담기 -> 
					// missing_map.jsp에서 이 값들로 다시 hidden input으로 만들어서 missing_insert.jsp로 보냄
					request.setAttribute("missingName", missingName);
					request.setAttribute("missingGender", missingGender);
					request.setAttribute("missingBirth", missingBirth);
					request.setAttribute("missingEtc", missingEtc);
					request.setAttribute("missingDate", missingDate);
					

					view = new ModelAndView("missing_map.jsp", false);
				} else {
					response.getWriter().println("장소를 찾을 수 없습니다.");
					view = new ModelAndView("missing_insert.jsp", false);
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().println("오류 발생: " + e.getMessage());
			}
		}
>>>>>>> 583fe526a5b73f5e3c272cc5058100d3a7083794
		return view;
	}
}
