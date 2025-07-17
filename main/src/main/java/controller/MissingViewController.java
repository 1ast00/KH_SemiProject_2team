package controller;

import java.io.IOException;
import dto.MissingPersonDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MissingService;
import view.ModelAndView;

public class MissingViewController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String serialNum = request.getParameter("missingSerialNum");

        if (serialNum == null || serialNum.isEmpty()) {
            request.setAttribute("errorMessage", "실종자 번호가 누락되었습니다.");
            return new ModelAndView("missing_list.jsp", false);
        }

        try {
            MissingService service = MissingService.getInstance();
            MissingPersonDTO person = service.getMissingPersonBySerialNum(serialNum);

            if (person == null) {
                request.setAttribute("errorMessage", "해당 실종자를 찾을 수 없습니다.");
                return new ModelAndView("missing_list.jsp", false);
            }

            String ageAtMissing = service.calculateAge(person.getBirth(), person.getMissingDate());
            String currentAge = service.calculateAge(person.getBirth(), java.time.LocalDate.now().toString());

            request.setAttribute("missingPerson", person);
            request.setAttribute("ageAtMissing", ageAtMissing);
            request.setAttribute("currentAge", currentAge);

            return new ModelAndView("missing_view.jsp", false);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "조회 중 오류 발생: " + e.getMessage());
            return new ModelAndView("missing_list.jsp", false);
        }
    }
}
