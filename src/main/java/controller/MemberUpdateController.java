package controller;

import java.io.BufferedReader;
import java.io.IOException;

import dto.MemberDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MemberService;
import view.ModelAndView;

public class MemberUpdateController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// 응답을 JSON 형태로 설정
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try {
			// 1. JSON 데이터 받기
			BufferedReader reader = request.getReader();
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			String jsonData = sb.toString();

			// 2. JSON 파싱 (수동으로 파싱)
			String memberSerialNum = extractValue(jsonData, "memberSerialNum");
			String memberName = extractValue(jsonData, "member_name");
			String memberPhone = extractValue(jsonData, "member_phone");
			String memberEmail = extractValue(jsonData, "member_email");

			// 3. Member 객체 생성 및 데이터 설정
			MemberDTO member = new MemberDTO();
			member.setMember_serialNum(memberSerialNum);
			member.setMember_name(memberName);
			member.setMember_phone(memberPhone);

			// 확인
			System.out.println(member.toString());
			
			// 이메일은 null일 수 있으므로 체크
			if (memberEmail != null && !memberEmail.isEmpty()) {
				member.setMember_email(memberEmail);
			}

			// 4. 업데이트 처리
			boolean success = MemberService.getInstance().updateMember(member);

			// 5. 응답 반환
			if (success) {
				response.getWriter().write("true");
			} else {
				response.getWriter().write("false");
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("오류 발생: " + e.getMessage());
		}
		return null;
	}

	// JSON에서 값을 추출하는 메서드
	private String extractValue(String json, String key) {
		try {
			String searchKey = "\"" + key + "\"";
			int startIndex = json.indexOf(searchKey);
			if (startIndex == -1) {
				return null;
			}

			int colonIndex = json.indexOf(":", startIndex);
			if (colonIndex == -1) {
				return null;
			}

			int valueStart = colonIndex + 1;
			while (valueStart < json.length() && (json.charAt(valueStart) == ' ' || json.charAt(valueStart) == '\t')) {
				valueStart++;
			}

			int valueEnd;
			if (json.charAt(valueStart) == '"') {
				// 문자열 값
				valueStart++; // 따옴표 스킵
				valueEnd = json.indexOf('"', valueStart);
			} else {
				// 숫자 값
				valueEnd = json.indexOf(',', valueStart);
				if (valueEnd == -1) {
					valueEnd = json.indexOf('}', valueStart);
				}
			}

			if (valueEnd == -1) {
				return null;
			}

			return json.substring(valueStart, valueEnd).trim();
		} catch (Exception e) {
			return null;
		}
	}
}