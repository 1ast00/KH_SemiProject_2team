package controller;

import java.io.IOException;

import dto.WitnessDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.WitnessService;
import view.ModelAndView;

public class WitnessViewController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("witnessSerialNum"); // ← 파라미터 이름 주의!
        WitnessDTO dto = WitnessService.getInstance().selectWitnessById(id);
        request.setAttribute("dto", dto);
        System.out.println(" view로 전달된 id = " + id);
        return new ModelAndView("witness_view.jsp", false);
    }
}