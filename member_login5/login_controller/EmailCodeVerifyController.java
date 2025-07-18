package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import view.ModelAndView;

public class EmailCodeVerifyController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userCode = request.getParameter("verifyCode");
        String sessionCode = (String) request.getSession().getAttribute("emailAuthCode");  // 세션키 통일

        response.setContentType("application/json;charset=UTF-8");

        if (sessionCode != null && sessionCode.equals(userCode)) {
            response.getWriter().write("{\"result\":\"success\", \"message\":\"인증 성공\"}");
        } else {
            response.getWriter().write("{\"result\":\"fail\", \"message\":\"인증코드가 일치하지 않습니다.\"}");
        }

        return null;  
    }
}

