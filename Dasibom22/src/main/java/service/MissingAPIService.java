package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

// 카카오 API와 통신해서 장소 검색 기능 제공하는 서비스 클래스
public class MissingAPIService {
	
	// 카카오 로컬 API 인증을 위한 REST API키(카카오개발자센터에 있음) *보안에 취약하므로 주의
	private static final String API_KEY = "KakaoAK d5c855388fd4a8a6282347245ad7c01a";

	// 장소 이름(키워드)을 받아서 카카오 API를 통해 좌표(위도,경도)와 정식 장소명 반환
	public static String[] getCoordinates(String queryText) throws Exception {
		
		// 검색어를 URL에 포함할 수 있도록 UTF-8로 인코딩
		String query = URLEncoder.encode(queryText, "UTF-8");
		String apiUrl = "https://dapi.kakao.com/v2/local/search/keyword.json?query=" + query;

		// 카카오 로컬 API에 HTTP GET 요청 보내기
		HttpURLConnection conn = (HttpURLConnection) new URL(apiUrl).openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Authorization", API_KEY); // 요청 헤더에 API키 추가

		// API 응답(JSON형식)을 모두 문자열로 읽어오기
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String response = br.lines().collect(Collectors.joining());
		br.close();

		// JSON 문자열 파싱해서 필요한 데이터 추출 준비
		JSONObject json = new JSONObject(response);
		JSONArray documents = json.getJSONArray("documents");

		// 검색결과가 있는지 확인
		if (documents.length() > 0) {
			// 검색결과가 있으면 가장 정확도가 높은 첫번째 결과에서 데이터 추출
			JSONObject first = documents.getJSONObject(0);
			String lat = first.getString("y"); // 위도
			String lng = first.getString("x"); // 경도
			String placeName = first.getString("place_name"); // API가 찾아준 정식 장소명
			return new String[] { lat, lng, placeName };
		} else {
			return null;
		}
	}
}