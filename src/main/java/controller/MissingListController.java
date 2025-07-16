package controller;

import java.io.IOException;
import java.util.List;

import dto.MissingPersonDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.MissingService;
import view.ModelAndView;

public class MissingListController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        // 테스트용 세션 (실제 로그인 구현시 삭제 및 대체)
        session.setAttribute("loginRole", "admin");  // "admin" 또는 "member"
        session.setAttribute("loginSerialNum", "MM10000001");  // 로그인한 회원 번호

        List<MissingPersonDTO> list = MissingService.getInstance().selectMissingList();

        request.setAttribute("missingList", list);
        System.out.println("접수 된 실종자 목록 : " + list.size());

        return new ModelAndView("missing_list.jsp", false);
    }
}