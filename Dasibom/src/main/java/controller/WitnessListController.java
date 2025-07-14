package controller;

import java.io.IOException;
import java.util.List;

import dto.WitnessDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.WitnessService;
import view.ModelAndView;

public class WitnessListController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<WitnessDTO> list = WitnessService.getInstance().selectWitnessList();
		request.setAttribute("witnessList", list);
		return new ModelAndView("witness_list.jsp", false);
	}

}
