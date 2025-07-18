package controller.member;

import controller.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import view.ModelAndView;

public class MemberFindPwFormController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) {
       
    	return new ModelAndView("member_login_find_pw/member_find_pw.jsp", false);




    }
}
