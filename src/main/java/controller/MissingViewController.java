package controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.UUID;

import dto.MissingPersonDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import service.MissingService;
import view.ModelAndView;

public class MissingViewController implements Controller {
	private static final String UPLOAD_DIR = "uploads";

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 1. 폼 데이터 가져오기
		request.setCharacterEncoding("UTF-8");
		String missingName = request.getParameter("missingName");
		String missingGender = request.getParameter("missingGender");
		String missingBirth = request.getParameter("missingBirth");
		String missingDate = request.getParameter("missingDate");
		String missingEtc = request.getParameter("missingEtc");
		String missingPlace = request.getParameter("missingPlace");
		String imageUrl = null;

		try {
			// 2. 이미지 파일 처리
			Part missingImgPart = request.getPart("missingImg");
			if (missingImgPart != null && missingImgPart.getSize() > 0) {
				String fileName = missingImgPart.getSubmittedFileName();
				if (fileName != null && !fileName.isEmpty()) {
					String fileExtension = "";
					int dotIndex = fileName.lastIndexOf('.');
					if (dotIndex > 0) {
						fileExtension = fileName.substring(dotIndex);
					}
					String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
					String applicationPath = request.getServletContext().getRealPath("");
					String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
					File uploadDir = new File(uploadFilePath);
					if (!uploadDir.exists()) {
						uploadDir.mkdirs();
					}
					missingImgPart.write(uploadFilePath + File.separator + uniqueFileName);
                    //이미지 URL을 DB에 저장될 전체 경로로 변경 -> 이미지 저장할 폴더 만들어야함!
					imageUrl = request.getContextPath() + "/" + UPLOAD_DIR + "/" + uniqueFileName;
				}
			}

			// 3. DTO 객체 생성 및 데이터 설정
			MissingPersonDTO person = new MissingPersonDTO();
			person.setName(missingName);
			person.setGender(missingGender);
			if (missingBirth != null && !missingBirth.isEmpty()) {
				person.setBirth(missingBirth.replace("-", ""));
			}
			person.setMissingDate(missingDate);
			person.setEtc(missingEtc);
			person.setPlace(missingPlace);
			person.setImage(imageUrl);
			person.setMemberSerialNum("MM10000001");
			person.setAdminSerialNum("AA10000001");

			// 4. Service를 통해 DB에 저장
			MissingService.getInstance().insertMissingPerson(person);

			// 5. 나이 계산 로직
			String ageAtMissing = "계산 불가";
			String currentAge = "계산 불가";
			try {
				LocalDate birthDate = LocalDate.parse(missingBirth);
				LocalDate dateOfMissing = LocalDate.parse(missingDate);
				LocalDate currentDate = LocalDate.now();

				Period periodAtMissing = Period.between(birthDate, dateOfMissing);
				ageAtMissing = String.valueOf(periodAtMissing.getYears());

				Period periodCurrent = Period.between(birthDate, currentDate);
				currentAge = String.valueOf(periodCurrent.getYears());
			} catch (DateTimeParseException | NullPointerException e) {
				System.err.println("나이 계산 중 오류 발생: " + e.getMessage());
			}

			// 6. 계산된 정보와 입력 데이터를 request에 담아 view 페이지로 전달
			request.setAttribute("missingName", missingName);
			request.setAttribute("missingGender", missingGender);
			request.setAttribute("missingBirth", missingBirth);
			request.setAttribute("ageAtMissing", ageAtMissing);
			request.setAttribute("currentAge", currentAge);
			request.setAttribute("missingEtc", missingEtc);
			request.setAttribute("missingPlace", missingPlace);
			request.setAttribute("missingDate", missingDate);
			request.setAttribute("missingImageUrl", imageUrl);

			// 7. 상세 보기 페이지로 포워딩하기
			return new ModelAndView("missing_view.jsp", false);

		} catch (Exception e) {
			System.out.println("--- MissingViewController 오류 발생 ---");
			e.printStackTrace();
			request.setAttribute("errorMessage", "데이터 저장 중 심각한 오류가 발생했습니다. 서버 로그를 확인해주세요. 오류: " + e.getMessage());
			return new ModelAndView("missing_insert.jsp", false);
		}
	}
}