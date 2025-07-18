package controller;

import java.io.IOException;

import dto.MissingPersonDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MissingService;
import view.ModelAndView;

public class MissingUpdateViewController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 1. view 페이지에서 보낸 고유 번호를 받습니다.
        String missingSerialNum = request.getParameter("missingSerialNum");
        
        // 2. 서비스(DB)를 통해 해당 번호의 데이터를 조회합니다.
        MissingPersonDTO person = MissingService.getInstance().getMissingPersonBySerialNum(missingSerialNum);
        
        // 3. 조회한 데이터를 "missingPerson"이라는 이름으로 request에 담습니다. (가장 중요!)
        request.setAttribute("missingPerson", person);
        
        // 4. 데이터를 가지고 missing_insert.jsp로 이동합니다.
        return new ModelAndView("missing_insert.jsp", false);
    }
}