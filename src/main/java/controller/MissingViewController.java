package controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.UUID;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import view.ModelAndView;

public class MissingViewController implements Controller {
	// 업로드 된 이미지 파일이 어떤 디렉토리에 저장될지 경로 정의하는 코드
	private static final String UPLOAD_DIR = "uploads";

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView view = null;

		// 글자깨짐 방지를 위한 인코딩 설정
		request.setCharacterEncoding("UTF-8");

		// 폼 데이터 가져오기
		String missingName = request.getParameter("missingName");     // 이름
		String missingGender = request.getParameter("missingGender");   // 성별
		String missingBirth = request.getParameter("missingBirth");   // 생년월일 : YYYY-MM-DD 형식으로 받음
		String missingDate = request.getParameter("missingDate");     // 실종 날짜 : YYYY-MM-DD
		String missingEtc = request.getParameter("missingEtc");       // 기타 특징
		String missingPlace = request.getParameter("missingPlace");   // 마지막 목격 장소

		String imageUrl = null; // 업로드된 이미지의 웹 접근 URL

		try {
			// 이미지 파일 처리
			Part missingImgPart = request.getPart("missingImg");

			if (missingImgPart != null && missingImgPart.getSize() > 0) {
				String fileName = missingImgPart.getSubmittedFileName();

				if (fileName != null && !fileName.isEmpty()) {
					String fileExtension = "";
					int dotIndex = fileName.lastIndexOf('.');
					if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
						fileExtension = fileName.substring(dotIndex);
					}

					String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
					String applicationPath = request.getServletContext().getRealPath("");
					String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;

					File uploadDir = new File(uploadFilePath);
					if (!uploadDir.exists()) {
						uploadDir.mkdirs();
					}

					String filePath = uploadFilePath + File.separator + uniqueFileName;
					missingImgPart.write(filePath);
					imageUrl = request.getContextPath() + "/" + UPLOAD_DIR + "/" + uniqueFileName;
				}
			}
			
			// 나이 계산을 위한 변수를 미리 선언하고 기본값을 할당하기
			String ageAtMissing = " "; // 실종 당시 나이
			String currentAge = " ";   // 현재 나이

			try {
			    // DB의 missing_birth 컬럼(NUMBER 타입)에 저장할 숫자 값을 준비
			    // birthDB 변수를 나중에 DB에 INSERT하는 SQL에 사용!
			    int birthForDB = 0;
			    if (missingBirth != null && !missingBirth.isEmpty()) {
			        // 하이픈(-)을 모두 제거하고 ("1995-01-10" -> "19950110")
			        // 정수(int)로 변환합니다. (19950110)
			        birthForDB = Integer.parseInt(missingBirth.replace("-", ""));
			    }
			    // 사용자가 입력한 원본 문자열 : String missingBirth = "1990-02-10";
			    // DB 저장을 위해 번역 된 숫자 : int birthDB = 19900210; (하이픈 제거, 아래에 있음)
			    // DB로 보낼 때 missing_birth컬럼에는 birthDB 담으면 됨!(컬럼타입이 NUMBER이기 때문에)
			    
			    // 나이 계산을 위해 날짜 문자열들을 LocalDate 객체로 변환
			    LocalDate birthDate = LocalDate.parse(missingBirth);
			    LocalDate dateOfMissing = LocalDate.parse(missingDate);
			    LocalDate currentDate = LocalDate.now();

			    // 실종 당시 나이
			    //두 날짜 사이의 기간(년,월,일)을 계산
			    Period periodAtMissing = Period.between(birthDate, dateOfMissing);
			    // 계산 된 기간에서 '년'만 추출하기(만 나이)
			    ageAtMissing = String.valueOf(periodAtMissing.getYears());

			    // 현재 나이
			    Period periodCurrent = Period.between(birthDate, currentDate);
			    currentAge = String.valueOf(periodCurrent.getYears());

			} catch (DateTimeParseException | NumberFormatException e) {
				// 날짜 형식이 아니거나 숫자로 변환할 수 없는 값이 들어올 경우
			    System.err.println("날짜 또는 숫자 파싱 오류 (나이 계산): " + e.getMessage());
			    // 오류가 발생하면 나이 값은 맨 처음에 설정한 기본값(" ")을 유지
			}
			
			// 데이터를 request 속성에 담아 다음 페이지(missing_view.jsp)로 전달합니다.
			request.setAttribute("missingName", missingName);
			request.setAttribute("missingGender", missingGender);
			request.setAttribute("missingBirth", missingBirth); // JSP에서 값 유지를 위해 YYYY-MM-DD 원본 문자열 전달
			request.setAttribute("ageAtMissing", ageAtMissing);
			request.setAttribute("currentAge", currentAge);
			request.setAttribute("missingEtc", missingEtc);
			request.setAttribute("missingPlace", missingPlace);
			request.setAttribute("missingDate", missingDate);
			request.setAttribute("missingImageUrl", imageUrl);

			view = new ModelAndView("missing_view.jsp", false);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "실종자 정보 접수 중 오류가 발생했습니다: " + e.getMessage());
			view = new ModelAndView("missing_insert.jsp", false);
		}
		return view;
	}
}