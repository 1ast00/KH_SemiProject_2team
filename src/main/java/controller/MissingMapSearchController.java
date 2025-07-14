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
		
		return view;
	}
}
