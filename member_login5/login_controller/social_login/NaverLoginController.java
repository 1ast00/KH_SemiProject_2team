package controller.social;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

import controller.Controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import view.ModelAndView;

public class NaverLoginController implements Controller {

    private static final String CLIENT_ID = "BJOoVFZIjqj9aUHYQi4r";
    private static final String REDIRECT_URI = "http://localhost:8888/MissingProjact/naverCallback.do";

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        
        String state = UUID.randomUUID().toString();
        request.getSession().setAttribute("state", state);

       
        String naverLoginUrl = "https://nid.naver.com/oauth2.0/authorize"
                + "?response_type=code"
                + "&client_id=" + CLIENT_ID
                + "&redirect_uri=" + URLEncoder.encode(REDIRECT_URI, "UTF-8")
                + "&state=" + state;

        
        response.sendRedirect(naverLoginUrl);

        
        return null;
    }
}
