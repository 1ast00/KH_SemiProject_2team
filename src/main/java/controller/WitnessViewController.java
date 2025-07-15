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
        String id = request.getParameter("witnessSerialNum");

        WitnessDTO dto = WitnessService.getInstance().selectWitnessById(id);
        request.setAttribute("dto", dto);

        // 실종 장소 좌표만 전달 (필요 시)
//        request.setAttribute("m_lat", dto.getMissingLatitude());
//        request.setAttribute("m_lng", dto.getMissingLongitude());

        return new ModelAndView("witness_view.jsp", false);
    }
}
