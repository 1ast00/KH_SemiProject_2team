package controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.UUID;

import dto.MissingPersonDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import service.MissingService;
import view.ModelAndView;

public class MissingInsertController implements Controller {

	// 이미지파일 저장할 폴더 새로 만들고 해당 경로로 설정
	private static final String UPLOAD_DIR = "C:/dasibom_uploads";

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 추가된 세션 처리 로직
		HttpSession session = request.getSession();
		// 임시 로그인 정보 세션에 저장 (나중에 실제 로그인 로직으로 대체)
		session.setAttribute("loginRole", "admin");
		session.setAttribute("loginSerialNum", "MM10000001");

		// 세션에서 로그인한 사용자 번호를 가져오도록 변경
		String memberSerialNum = (String) session.getAttribute("loginSerialNum");
		MissingPersonDTO person = null;

		try {
			MissingService service = MissingService.getInstance();
			
			if (memberSerialNum == null) {
				throw new Exception("로그인 정보가 없습니다. 다시 로그인해주세요.");
			}
			
			String adminSerialNum = service.getAdminSerialNumByMember(memberSerialNum);
			if (adminSerialNum == null)
				throw new Exception("관리자 정보를 찾을 수 없습니다.");

			person = createPersonFromRequest(request, memberSerialNum, adminSerialNum);
			service.insertMissingPerson(person);

			// 나이 계산
			String ageAtMissing = service.calculateAge(person.getBirth(), person.getMissingDate());
			String currentAge = service.calculateAge(person.getBirth(), LocalDate.now().toString());

			// View로 전달
			request.setAttribute("missingPerson", person);
			request.setAttribute("ageAtMissing", ageAtMissing);
			request.setAttribute("currentAge", currentAge);

			return new ModelAndView("missing_view.jsp", false);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "등록 중 오류: " + e.getMessage());
			if (person != null)
				request.setAttribute("submittedData", person);
			return new ModelAndView("missing_insert.jsp", false);
		}
	}

	private MissingPersonDTO createPersonFromRequest(HttpServletRequest request, String memberSerialNum,
			String adminSerialNum) throws IOException, ServletException {
		request.setCharacterEncoding(StandardCharsets.UTF_8.name());

		String missingName = request.getParameter("missingName");
		String missingGender = request.getParameter("missingGender");
		String missingBirth = request.getParameter("missingBirth");
		String missingDate = request.getParameter("missingDate");
		String missingEtc = request.getParameter("missingEtc");
		String missingPlace = request.getParameter("missingPlace");
		String imageUrl = saveImageFile(request);

		MissingPersonDTO person = new MissingPersonDTO();
		person.setName(missingName);
		person.setGender(missingGender);
		person.setBirth(missingBirth);
		person.setMissingDate(missingDate);
		person.setEtc(missingEtc);
		person.setPlace(missingPlace);
		person.setImage(imageUrl);
		person.setMemberSerialNum(memberSerialNum);
		person.setAdminSerialNum(adminSerialNum);

		return person;
	}

	// 이미지 저장
	private String saveImageFile(HttpServletRequest request) throws IOException, ServletException {
		Part part = request.getPart("missingImg");
		if (part != null && part.getSize() > 0) {
			String originalFileName = part.getSubmittedFileName();
			if (originalFileName != null && !originalFileName.isEmpty()) {
				String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
				String uniqueName = UUID.randomUUID().toString() + ext;
				String uploadPath = UPLOAD_DIR; // 위에서 지정한 절대경로 사용

				File dir = new File(uploadPath);
				if (!dir.exists())
					dir.mkdirs(); // 폴더가 없으면 생성

				part.write(uploadPath + File.separator + uniqueName);
				return uniqueName;
			}
		}
		return null;
	}
}