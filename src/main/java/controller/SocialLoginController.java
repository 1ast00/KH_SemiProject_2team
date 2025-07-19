package controller;

import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import view.ModelAndView;

public class SocialLoginController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String type = request.getParameter("type");

        if ("kakao".equals(type)) {
            String clientId = "e859a2a1d17fcb50c5f3930a05c20cc5";
            String redirectUri = "http://localhost:8888/MissingProjact/kakaoCallback.do";

            String authUrl = "https://kauth.kakao.com/oauth/authorize"
                    + "?client_id=" + clientId
                    + "&redirect_uri=" + redirectUri
                    + "&response_type=code";

            response.sendRedirect(authUrl);
            return null;
        }

        if ("naver".equals(type)) {
            String clientId = "BJOoVFZIjqj9aUHYQi4r";
            String state = UUID.randomUUID().toString();
            String redirectUri = "http://localhost:8888/MissingProjact/naverCallback.do";

            String authUrl = "https://nid.naver.com/oauth2.0/authorize"
                    + "?response_type=code"
                    + "&client_id=" + clientId
                    + "&redirect_uri=" + redirectUri
                    + "&state=" + state;

            response.sendRedirect(authUrl);
            return null;
        }
        HttpSession session = request.getSession();
        String member = "socialLogin";
        session.setAttribute("member", member);
        return new ModelAndView("main.jsp", true);
    }
}