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
    	
        // view 페이지에서 보낸 고유 번호를 받기
        String missingSerialNum = request.getParameter("missingSerialNum");
        // ★★★★★ 디버깅 ★★★★★
        System.out.println("[MissingUpdateViewController 디버그] 전달받은 missingSerialNum: " + missingSerialNum);

        // DB를 통해 해당 번호의 데이터를 조회
        MissingPersonDTO person = MissingService.getInstance().getMissingPersonBySerialNum(missingSerialNum);
        // ★★★★★ 디버깅 ★★★★★
        System.out.println("[MissingUpdateViewController 디버그] DB에서 조회한 person 객체: " + person);
        
        // 조회한 데이터를 missingPerson이라는 이름으로 request에 담기
        request.setAttribute("missingPerson", person);
        
        // 데이터를 가지고 missing_insert.jsp로 이동
        return new ModelAndView("missing_insert.jsp", false);
    }
}