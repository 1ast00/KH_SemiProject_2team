package controller;

import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import view.ModelAndView;

public class LogoutController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        
        return new ModelAndView("/main.do", true);
    }
}
