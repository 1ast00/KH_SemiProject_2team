package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

public class Pratice {
	
	// api 키 주소
    private static final String API_KEY = "KakaoAK 85cb97b7ab69b245bb503cf6154267da";

    public static String[] getCoordinates(String queryText) throws Exception {
        String query = URLEncoder.encode(queryText, "UTF-8");
        String apiUrl = "https://dapi.kakao.com/v2/local/search/keyword.json?query=" + query;

        HttpURLConnection conn = (HttpURLConnection) new URL(apiUrl).openConnection();
        conn.setRequestMethod("GET"); //get 
        conn.setRequestProperty("Authorization", API_KEY);

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String response = br.lines().collect(Collectors.joining());
        br.close();
        
        
        
        JSONObject json = new JSONObject(response);
        JSONArray documents = json.getJSONArray("documents");
        
        
        // 들어온 무언가가 있을때
        // json 객체 만들고 위도 경도 장소 이름을 담아서 배열형태로 저장
        if (documents.length() > 0) {
            JSONObject first = documents.getJSONObject(0);
            String lat = first.getString("y");
            String lng = first.getString("x");
            String placeName = first.getString("place_name");
            return new String[]{lat, lng, placeName};
        } else {
            return null;
        }
    }
}
