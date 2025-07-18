package controller.social;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.json.JSONObject;

import controller.Controller;
import view.ModelAndView;

public class KakaoCallbackController implements Controller {

	private static final String CLIENT_ID = "e859a2a1d17fcb50c5f3930a05c20cc5";
    private static final String REDIRECT_URI = "http://localhost:8888/MissingProjact/kakaoCallback.do";

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        try {
            String code = request.getParameter("code");
            System.out.println("카카오 콜백으로 받은 code 값: " + code);
            
            URL url = new URL("https://kauth.kakao.com/oauth/token");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            
            String params = "grant_type=authorization_code"
                          + "&client_id=" + CLIENT_ID
                          + "&redirect_uri=" + REDIRECT_URI
                          + "&code=" + code;

            try (OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream())) {
                writer.write(params);
                writer.flush();
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder tokenResult = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) tokenResult.append(line);

           
            System.out.println("카카오 토큰 응답 : " + tokenResult.toString());

            JSONObject tokenJson = new JSONObject(tokenResult.toString());
            String accessToken = tokenJson.optString("access_token", null);

            if (accessToken == null || accessToken.isEmpty()) {
                System.out.println("카카오 access_token 발급 실패!");
                return new ModelAndView("error.jsp", true);
            }

            
            URL meUrl = new URL("https://kapi.kakao.com/v2/user/me");
            HttpURLConnection meConn = (HttpURLConnection) meUrl.openConnection();
            meConn.setRequestMethod("GET");
            meConn.setRequestProperty("Authorization", "Bearer " + accessToken);

            BufferedReader meReader = new BufferedReader(new InputStreamReader(meConn.getInputStream()));
            StringBuilder meResult = new StringBuilder();
            while ((line = meReader.readLine()) != null) meResult.append(line);

            
            System.out.println("카카오 사용자 정보 : " + meResult.toString());

            JSONObject userInfo = new JSONObject(meResult.toString());
            long kakaoId = userInfo.getLong("id");

            
            HttpSession session = request.getSession();
            session.setAttribute("user_id", "kakao_" + kakaoId);

        } catch (Exception e) {
            
            e.printStackTrace();
            throw new ServletException(e);
        }

        
        response.sendRedirect(request.getContextPath() + "/main.do");
        return null;

    }
}