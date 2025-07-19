package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.WitnessService;
import view.ModelAndView;

public class WitnessDeleteController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
        String serialNum = request.getParameter("witnessSerialNum");

        if (serialNum != null && !serialNum.trim().isEmpty()) {
            boolean result = WitnessService.getInstance().deleteWitness(serialNum);
            if (result) {
                System.out.println("✔ 삭제 성공: " + serialNum);
            } else {
                System.out.println("⚠ 삭제 실패: " + serialNum);
            }
        }
        System.out.println("삭제 시도 witnessSerialNum = " + serialNum);

        return new ModelAndView("/witnessList.do", true);  // redirect to updated list
    }
}