package controller.member;

import java.io.IOException;

import controller.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dto.MemberDTO;
import service.MemberService;
import view.ModelAndView;

public class MemberFindPwActionController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String id    = req.getParameter("member_id").trim();
        String email = req.getParameter("member_email").trim();

        MemberDTO dto = new MemberDTO();
        dto.setMember_id(id);
        dto.setMember_email(email);

        boolean ok = MemberService.getInstance().verifyForPw(dto);

        resp.setContentType("application/json;charset=UTF-8");

        if (ok) {
            resp.getWriter().write("{\"result\":\"success\"}");
        } else {
            resp.getWriter().write("{\"result\":\"fail\", \"message\":\"회원정보가 일치하지 않습니다.\"}");
        }

        return null;
    }
}
