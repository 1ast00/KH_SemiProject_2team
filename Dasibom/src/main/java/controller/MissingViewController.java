package controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.UUID;

import dto.MissingPersonDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import service.MissingService;
import view.ModelAndView;

public class MissingViewController implements Controller {
	private static final String UPLOAD_DIR = "uploads";

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			// 1. DTO 객체 생성 및 데이터 바인딩
			MissingPersonDTO person = createPersonFromRequest(request);

			// 2. Service를 통해 DB에 저장
			MissingService.getInstance().insertMissingPerson(person);

			// 3. 나이 계산 등 후처리 (Service에서 처리하게끔 옮기는게 좋음)
			String ageAtMissing = calculateAge(person.getBirth(), person.getMissingDate());
			String currentAge = calculateAge(person.getBirth(), LocalDate.now().toString());

			// 4. 계산된 정보와 DTO를 request에 담아 View 페이지로 전달
			request.setAttribute("missingPerson", person);
			request.setAttribute("ageAtMissing", ageAtMissing);
			request.setAttribute("currentAge", currentAge);
			
			// 5. 상세 보기 페이지로 포워딩
			return new ModelAndView("missing_view.jsp", false);

		} catch (Exception e) {
			e.printStackTrace(); // 서버 로그에 에러 출력
			request.setAttribute("errorMessage", "데이터 저장 중 오류가 발생했습니다: " + e.getMessage());
			// 오류 발생 시, 입력하던 데이터를 유지한 채로 다시 입력 폼으로 보냄
			return new ModelAndView("missing_insert.jsp", false);
		}
	}

	 // 요청(request)으로부터 파라미터와 파일을 추출해서 MissingPersonDTO 객체를 생성
	private MissingPersonDTO createPersonFromRequest(HttpServletRequest request) throws IOException, ServletException {
		// Multipart/form-data 요청의 한글 깨짐 처리
		request.setCharacterEncoding(StandardCharsets.UTF_8.name());
	
		String missingName = request.getParameter("missingName");
		String missingGender = request.getParameter("missingGender");
		String missingBirth = request.getParameter("missingBirth");
		String missingDate = request.getParameter("missingDate");
		String missingEtc = request.getParameter("missingEtc");
		String missingPlace = request.getParameter("missingPlace");
		
		// 파일 처리 및 저장
		String imageUrl = saveImageFile(request);

		// DTO 객체 생성 및 데이터 설정
		MissingPersonDTO person = new MissingPersonDTO();
		person.setName(missingName);
		person.setGender(missingGender);
		person.setBirth(missingBirth); // DB에서 TO_NUMBER, TO_DATE로 처리하므로 문자열 그대로 전달
		person.setMissingDate(missingDate);
		person.setEtc(missingEtc);
		person.setPlace(missingPlace);
		person.setImage(imageUrl);

		// 로그인 기능 구현 후 세션에서 가져와야함(아래는 임시)
		person.setMemberSerialNum("MM10000001"); // 임시 회원 번호
		person.setAdminSerialNum("AA10000001");	// 임시 관리자 번호

		return person;
	}

	private String saveImageFile(HttpServletRequest request) throws IOException, ServletException {
		Part missingImgPart = request.getPart("missingImg");
		if (missingImgPart != null && missingImgPart.getSize() > 0) {
			String originalFileName = missingImgPart.getSubmittedFileName();
			if (originalFileName != null && !originalFileName.isEmpty()) {
				// 파일 확장자 추출
				String fileExtension = "";
				int dotIndex = originalFileName.lastIndexOf('.');
				if (dotIndex > 0) {
					fileExtension = originalFileName.substring(dotIndex);
				}

				// 고유한 파일 이름 생성
				String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
				
				// 서버의 실제 파일 저장 경로
				String uploadPath = request.getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
				File uploadDir = new File(uploadPath);
				if (!uploadDir.exists()) {
					uploadDir.mkdirs(); // 폴더가 없으면 생성
				}

				// 파일 저장
				missingImgPart.write(uploadPath + File.separator + uniqueFileName);
				
				// DB에 저장될 이미지 경로 반환 (파일 이름만 저장)
				return uniqueFileName;
			}
		}
		return null; // 업로드된 파일이 없을 경우 null 반환
	}
	
	// 나이 계산하고 문자열로 반환하기
	private String calculateAge(String birthDateStr, String standardDateStr) {
		try {
			LocalDate birthDate = LocalDate.parse(birthDateStr);
			LocalDate standardDate = LocalDate.parse(standardDateStr);
			return String.valueOf(Period.between(birthDate, standardDate).getYears());
		} catch (DateTimeParseException | NullPointerException e) {
			System.err.println("나이 계산 중 오류 발생: " + e.getMessage());
			return "계산 불가";
		}
	}
}