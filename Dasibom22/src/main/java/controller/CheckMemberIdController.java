package controller;

import jakarta.servlet.http.*;
import java.io.IOException;
import service.MemberService;
import view.ModelAndView;

public class CheckMemberIdController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String memberId = request.getParameter("member_id");
        boolean isDuplicate = MemberService.getInstance().isMemberIdDuplicate(memberId);

        response.setContentType("text/plain;charset=UTF-8");

        if (isDuplicate) {
            response.getWriter().write("duplicate");
        } else {
            response.getWriter().write("available");
        }
        return null;
    }
}