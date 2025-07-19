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
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// 1. 요청 URL의 파라미터(missingSerialNum)를 가져오기
		String serialNum = request.getParameter("missingSerialNum");
		System.out.println("실종자 식별자: " + serialNum);
		
		// 2. 서비스 레이어를 통해 DB에서 실종자 정보를 조회
		MissingService service = MissingService.getInstance();
		MissingPersonDTO person = service.getMissingPersonBySerialNum(serialNum);

		if (person == null) {
			System.out.println("null");
		} else {
			System.out.println("실종자 정보: " + person.toString());
		}
		
		// 3. 실종 당시 나이와 현재 나이를 계산
		if (person != null) {
			String ageAtMissing = service.calculateAge(person.getBirth(), person.getMissingDate());
			System.out.println("실종자 실종 당시 나이 계산함");
			String currentAge = service.calculateAge(person.getBirth(), LocalDate.now().toString());
			System.out.println("실종자 현재 나이 계산함");

			// 4. 조회된 실종자 객체(person)와 계산된 나이 정보를 request에 담아 view로 전달
			request.setAttribute("missingPerson", person);
			request.setAttribute("ageAtMissing", ageAtMissing);
			request.setAttribute("currentAge", currentAge);
			
			System.out.println("실종자 정보 전달함");
		} else {
			// 조회된 데이터가 없을 경우를 대비한 처리
			request.setAttribute("errorMessage", "해당 실종자 정보를 찾을 수 없습니다.");
			System.out.println("실종자 정보 전달 못함");
		}

		// 5. 데이터를 표시할 view 페이지로 포워딩
		return new ModelAndView("missing_view.jsp", false);
	}
}