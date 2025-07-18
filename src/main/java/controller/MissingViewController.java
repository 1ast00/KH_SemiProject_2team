package controller;

import java.io.IOException;
import java.time.LocalDate;

import dto.MissingPersonDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MissingService;
import view.ModelAndView;

public class MissingViewController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 목록에서 항목 클릭 시 상세 정보를 처리하는 로직 전체 추가 
        
        // 1. 요청 URL의 파라미터(missingSerialNum)를 가져오기
        String serialNum = request.getParameter("missingSerialNum");

        System.out.println(serialNum);
        // 2. 서비스 레이어를 통해 DB에서 실종자 정보를 조회
        MissingService service = MissingService.getInstance();
        MissingPersonDTO person = service.getMissingPersonBySerialNum(serialNum);

        // 3. 실종 당시 나이와 현재 나이를 계산
        if (person != null) {
            String ageAtMissing = service.calculateAge(person.getBirth(), person.getMissingDate());
            String currentAge = service.calculateAge(person.getBirth(), LocalDate.now().toString());
            
            // 4. 조회된 실종자 객체(person)와 계산된 나이 정보를 request에 담아 view로 전달
            request.setAttribute("missingPerson", person);
            request.setAttribute("ageAtMissing", ageAtMissing);
            request.setAttribute("currentAge", currentAge);
        } else {
            // 조회된 데이터가 없을 경우를 대비한 처리
            request.setAttribute("errorMessage", "해당 실종자 정보를 찾을 수 없습니다.");
        }

        // 5. 데이터를 표시할 view 페이지로 포워딩
        return new ModelAndView("missing_view.jsp", false);
    }
}