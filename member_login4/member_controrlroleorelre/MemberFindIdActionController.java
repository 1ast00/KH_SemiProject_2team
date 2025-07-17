package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dto.MemberDTO;
import service.MemberService;
import view.ModelAndView;

public class MemberFindIdActionController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String name  = req.getParameter("member_name").trim();
        String email = req.getParameter("member_email").trim();

        
        MemberDTO dto = new MemberDTO();
        dto.setMember_name(name);
        dto.setMember_email(email);

        
        String foundId = MemberService.getInstance().findId(dto);

        req.setAttribute("foundId", foundId);

        return new ModelAndView("member_find_id_result.jsp", false);
    }
}
