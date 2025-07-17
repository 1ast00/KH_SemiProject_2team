package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.json.JSONObject;

import view.ModelAndView;

public class NaverCallbackController implements Controller {

    private static final String CLIENT_ID = "BJOoVFZIjqj9aUHYQi4r";
    private static final String CLIENT_SECRET = "syIIYhT8up";
    private static final String REDIRECT_URI = "http://localhost:8888/MissingProjact/naverCallback.do";

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession();
        String sessionState = (String) session.getAttribute("state");
        String requestState = request.getParameter("state");

        if (sessionState == null || !sessionState.equals(requestState)) {
            System.out.println("네이버 로그인 state 값 불일치 또는 세션 만료");
            return new ModelAndView("error.jsp", true);
        }

        try {
            String code = request.getParameter("code");
            String state = request.getParameter("state");

            String redirectUri = URLEncoder.encode(REDIRECT_URI, "UTF-8");

            URL url = new URL("https://nid.naver.com/oauth2.0/token"
                    + "?grant_type=authorization_code"
                    + "&client_id=" + CLIENT_ID
                    + "&client_secret=" + CLIENT_SECRET
                    + "&code=" + code
                    + "&state=" + state
                    + "&redirect_uri=" + redirectUri);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder tokenResult = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) tokenResult.append(line);

            System.out.println("네이버 토큰 응답 : " + tokenResult.toString());

            JSONObject tokenJson = new JSONObject(tokenResult.toString());
            String accessToken = tokenJson.optString("access_token", null);

            if (accessToken == null || accessToken.isEmpty()) {
                System.out.println("네이버 access_token 발급 실패!");
                return new ModelAndView("error.jsp", true);
            }

            URL meUrl = new URL("https://openapi.naver.com/v1/nid/me");
            HttpURLConnection meConn = (HttpURLConnection) meUrl.openConnection();
            meConn.setRequestMethod("GET");
            meConn.setRequestProperty("Authorization", "Bearer " + accessToken);

            BufferedReader meReader = new BufferedReader(new InputStreamReader(meConn.getInputStream()));
            StringBuilder meResult = new StringBuilder();
            while ((line = meReader.readLine()) != null) meResult.append(line);

            System.out.println("네이버 사용자 정보 : " + meResult.toString());

            JSONObject userInfo = new JSONObject(meResult.toString());
            JSONObject responseObj = userInfo.getJSONObject("response");
            String naverId = responseObj.getString("id");

            session.setAttribute("user_id", "naver_" + naverId);

        } catch (Exception e) {
            throw new ServletException(e);
        }

       
        response.sendRedirect(request.getContextPath() + "/main.do");
        return null;
    }
}


