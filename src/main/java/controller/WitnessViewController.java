package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dto.WitnessDTO;
import dto.MissingPersonDTO;
import service.WitnessService;
import service.MissingService;
import view.ModelAndView;

public class WitnessViewController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("witnessSerialNum");

        WitnessDTO dto = WitnessService.getInstance().selectWitnessById(id);
        request.setAttribute("dto", dto);

        // 실종 장소 키워드 가져오기
        if (dto != null && dto.getMissingSerialNum() != null) {
            MissingPersonDTO missingDto = MissingService.getInstance()
                    .selectMissingById(dto.getMissingSerialNum());

            if (missingDto != null) {
                request.setAttribute("missingPlace", missingDto.getPlace()); // 장소 키워드
            }
        }

        return new ModelAndView("witness_view.jsp", false);
    }
}