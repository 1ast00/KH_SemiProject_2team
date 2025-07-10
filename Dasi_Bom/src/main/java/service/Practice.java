package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

public class Practice { // Pratice -> Practice (철자 수정)

    private static final String API_KEY = "KakaoAK 85cb97b7ab69b245bb503cf6154267da"; // 본인의 API 키를 확인해주세요.

    public static String[] getCoordinates(String queryText) throws Exception {
        // 검색어를 UTF-8로 인코딩합니다.
        String query = URLEncoder.encode(queryText, "UTF-8");
        String apiUrl = "https://dapi.kakao.com/v2/local/search/keyword.json?query=" + query;

        HttpURLConnection conn = (HttpURLConnection) new URL(apiUrl).openConnection();
        conn.setRequestMethod("GET"); // GET 방식으로 요청
        conn.setRequestProperty("Authorization", API_KEY); // API 키 설정

        // --- 디버깅을 위한 로그 추가: API 요청 URL 확인 ---
        System.out.println("Practice - Kakao API Request URL: " + apiUrl);
        // -------------------------------------------------

        // HvTTP 응답 코드 확인
        int responseCode = conn.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) { // 200 OK가 아니면 에러
            try (BufferedReader errorBr = new BufferedReader(new InputStreamReader(conn.getErrorStream()))) {
                String errorResponse = errorBr.lines().collect(Collectors.joining());
                System.err.println("Practice - Kakao API Error Response (" + responseCode + "): " + errorResponse);
            }
            throw new IOException("Failed to get response from Kakao API. HTTP error code: " + responseCode);
        }

        // API 응답 읽기
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String response = br.lines().collect(Collectors.joining());
        br.close();

        // --- 디버깅을 위한 로그 추가: API 응답 내용 확인 ---
        System.out.println("Practice - Kakao API Raw Response: " + response);
        // -------------------------------------------------

        // JSON 파싱
        JSONObject json = new JSONObject(response);
        JSONArray documents = json.getJSONArray("documents");

        // 검색 결과(documents)가 있다면 첫 번째 결과의 위도, 경도, 장소 이름을 반환
        if (documents.length() > 0) {
            JSONObject first = documents.getJSONObject(0);
            String lat = first.getString("y"); // 'y'는 위도(latitude)
            String lng = first.getString("x"); // 'x'는 경도(longitude)
            String placeName = first.getString("place_name");
            
            // --- 디버깅을 위한 로그 추가: 추출된 좌표 및 장소 이름 확인 ---
            System.out.println("Practice - Extracted: Place=" + placeName + ", Lat=" + lat + ", Lng=" + lng);
            // -------------------------------------------------
            
            return new String[]{lat, lng, placeName};
        } else {
            System.out.println("Practice - No search results found for query: " + queryText);
            return null; // 검색 결과 없음
        }
    }
}