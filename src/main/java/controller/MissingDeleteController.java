package controller;

import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MissingService;
import view.ModelAndView;

public class MissingDeleteController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String missingSerialNum = request.getParameter("missingSerialNum");

		if (missingSerialNum != null && !missingSerialNum.trim().isEmpty()) {
			int result = MissingService.getInstance().deleteMissingPerson(missingSerialNum);
			System.out.println("삭제 결과: " + result);
		}

		// 삭제 후 목록으로 리다이렉트
		return new ModelAndView("/missingList.do", true);
	}
}