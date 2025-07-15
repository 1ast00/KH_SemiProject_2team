package controller;

import java.io.IOException;
import java.util.List;

import dto.WitnessDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.WitnessService;
import view.ModelAndView;

public class WitnessListController implements Controller {

	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		   System.out.println("✔ 리스트 컨트롤러 들어옴");
		
	    List<WitnessDTO> list = WitnessService.getInstance().selectWitnessList();
	    request.setAttribute("witnessList", list);
	    System.out.println("✔ 리스트 개수: " + list.size());
	    return new ModelAndView("witness_list.jsp", false);
	}


}
