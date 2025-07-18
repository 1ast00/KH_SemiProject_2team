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
            response.getWriter().write("{\"result\":\"fail\", \"message\":\"�̸����� ��� �ֽ��ϴ�.\"}");
            return null;
        }

        EmailService emailService = new EmailService();
        String authCode = emailService.sendAuthCode(email);

        if (authCode != null) {
            HttpSession session = request.getSession();
            session.setAttribute("emailAuthCode", authCode);

            response.getWriter().write("{\"result\":\"success\", \"message\":\"���� �ڵ尡 �̸��Ϸ� �߼۵Ǿ����ϴ�.\"}");
        } else {
            response.getWriter().write("{\"result\":\"fail\", \"message\":\"�̸��� �߼ۿ� �����߽��ϴ�.\"}");
        }

        return null;
    }
}
