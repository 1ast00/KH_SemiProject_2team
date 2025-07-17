package controller;

import java.io.IOException;
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

        if (!ok) {
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().println("<script>alert('정보가 일치하지 않습니다.'); history.back();</script>");
            return null;
        }

        
        req.setAttribute("member_id", id);
        req.setAttribute("member_email", email);

        return new ModelAndView("/WEB-INF/views/member_reset_pw.jsp", false);
    }
}
