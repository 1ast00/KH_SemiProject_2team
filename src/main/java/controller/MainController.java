package controller;

import java.io.IOException;
import java.util.List;

import dto.MissingPersonDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MissingService;
import view.ModelAndView;

public class MainController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 실종 정보 갖고 오기
		List<MissingPersonDTO> list = MissingService.getInstance().selectMissingListMain();
        request.setAttribute("missingList", list);
		return new ModelAndView("main.jsp", false);
	}
}