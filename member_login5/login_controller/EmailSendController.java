package controller;

import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.EmailService;
import view.ModelAndView;

public class EmailSendController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String email = request.getParameter("email");

        response.setContentType("application/json;charset=UTF-8");

        if (email == null || email.trim().isEmpty()) {
            response.getWriter().write("{\"result\":\"fail\", \"message\":\"이메일이 비어 있습니다.\"}");
            return null;
        }

        EmailService emailService = new EmailService();
        String authCode = emailService.sendAuthCode(email);

        if (authCode != null) {
            HttpSession session = request.getSession();
            session.setAttribute("emailAuthCode", authCode);

            response.getWriter().write("{\"result\":\"success\", \"message\":\"인증 코드가 이메일로 발송되었습니다.\"}");
        } else {
            response.getWriter().write("{\"result\":\"fail\", \"message\":\"이메일 발송에 실패했습니다.\"}");
        }

        return null;
    }
}
