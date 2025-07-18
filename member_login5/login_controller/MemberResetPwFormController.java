package controller.member;

import controller.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import view.ModelAndView;

public class MemberResetPwFormController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) {
        
    	return new ModelAndView("member_login_find_pw/reset_pw.jsp", false);

    }
}
