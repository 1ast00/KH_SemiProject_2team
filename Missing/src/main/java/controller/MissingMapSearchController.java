package controller;

import java.io.IOException;

import dto.MissingPersonDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MissingAPIService;
import service.MissingService;
import view.ModelAndView;

public class MissingMapSearchController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ModelAndView view = null;

        String placeQuery = request.getParameter("missingPlace");
        
        // '수정 중' 유지하기 위해 모든 폼 파라미터를 받기
        String missingSerialNum = request.getParameter("missingSerialNum");
        String missingName = request.getParameter("missingName");
        String missingGender = request.getParameter("missingGender");
        String missingBirth = request.getParameter("missingBirth");
        String missingDate = request.getParameter("missingDate");
        String missingEtc = request.getParameter("missingEtc");

        try {
            String[] result = MissingAPIService.getCoordinates(placeQuery);

            if (result != null) {
                // 받은 모든 파라미터를 다시 request에 담아 map 페이지로 전달
                request.setAttribute("lat", result[0]);
                request.setAttribute("lng", result[1]);
                request.setAttribute("place", result[2]); // API에서 받은 정확한 주소

                request.setAttribute("missingSerialNum", missingSerialNum);
                request.setAttribute("missingName", missingName);
                request.setAttribute("missingGender", missingGender);
                request.setAttribute("missingBirth", missingBirth);
                request.setAttribute("missingDate", missingDate);
                request.setAttribute("missingEtc", missingEtc);

                view = new ModelAndView("missing_map.jsp", false);
            } else {
                // 검색 결과가 없을 경우에도 입력값 유지를 위해서 다시 전달
                request.setAttribute("errorMessage", "장소를 찾을 수 없습니다. 다른 검색어로 시도해보세요.");
                request.setAttribute("missingSerialNum", missingSerialNum);
                request.setAttribute("missingName", missingName);
                request.setAttribute("missingGender", missingGender);
                request.setAttribute("missingBirth", missingBirth);
                request.setAttribute("missingDate", missingDate);
                request.setAttribute("missingEtc", missingEtc);

                // 수정 중 유지하기 위해서 원본 데이터를 다시 조회해서 전달
                if (missingSerialNum != null && !missingSerialNum.isEmpty()) {
                    MissingPersonDTO person = MissingService.getInstance().getMissingPersonBySerialNum(missingSerialNum);
                    request.setAttribute("missingPerson", person);
                }
                
                view = new ModelAndView("missing_insert.jsp", false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "지도 API 서버 통신 중 오류가 발생했습니다: " + e.getMessage());
            view = new ModelAndView("missing_insert.jsp", false);
        }
        return view;
    }
}