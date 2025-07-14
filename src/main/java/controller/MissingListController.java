package controller;

import java.io.IOException;
import java.util.List;

import dto.MissingPersonDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MissingService;
import view.ModelAndView;

public class MissingListController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<MissingPersonDTO> list = MissingService.getInstance().selectMissingList();
		
		request.setAttribute("missingList", list);
		
		return new ModelAndView("missing_list.jsp", false);
	}

}
