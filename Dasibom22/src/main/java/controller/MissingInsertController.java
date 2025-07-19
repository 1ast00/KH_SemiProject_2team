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

//실종자 정보 등록 요청을 처리하는 컨트롤러
public class MissingInsertController implements Controller { 

	// 이미지파일 저장할 폴더 새로 만들고 해당 경로로 설정(절대경로)
	private static final String UPLOAD_DIR = "C:/dasibom_uploads";

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// 세션에서 현재 로그인한 사용자 정보 조회
		HttpSession session = request.getSession();
		// 임시 로그인 정보 세션에 저장 (나중에 실제 로그인 로직으로 대체)
		session.setAttribute("loginRole", "admin");
		session.setAttribute("loginSerialNum", "MM10000001");

		// 세션에서 로그인한 사용자 번호를 가져옴
		String memberSerialNum = (String) session.getAttribute("loginSerialNum");
		MissingPersonDTO person = null;

		try {
			MissingService service = MissingService.getInstance();

			if (memberSerialNum == null) { // 로그인 여부 확인
				throw new Exception("로그인 정보가 없습니다. 다시 로그인해주세요.");
			}

			// 해당 회원을 담당하는 관리자 번호 조회
			String adminSerialNum = service.getAdminSerialNumByMember(memberSerialNum);
			if (adminSerialNum == null)
				throw new Exception("관리자 정보를 찾을 수 없습니다.");

			// 요청 정보(DTO) 생성 및 DB저장
			person = createPersonFromRequest(request, memberSerialNum, adminSerialNum);
			service.insertMissingPerson(person);

			// 나이 계산(실종당시, 현재)
			String ageAtMissing = service.calculateAge(person.getBirth(), person.getMissingDate());
			String currentAge = service.calculateAge(person.getBirth(), LocalDate.now().toString());

			// 나이계산 처리결과 View로 전달
			request.setAttribute("missingPerson", person);
			request.setAttribute("ageAtMissing", ageAtMissing);
			request.setAttribute("currentAge", currentAge);

			session.setAttribute("message", "정상적으로 등록되었습니다.");
			
			// 성공시 결과 페이지로 이동
			return new ModelAndView("missingView.do?missingSerialNum=" + person.getMissingSerialNum(), true);

			// 실패시 에러 메시지(이 전 페이지로 이동)
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "등록 중 오류: " + e.getMessage());
			if (person != null)
				request.setAttribute("submittedData", person);
			return new ModelAndView("missing_insert.jsp", false);
		}
	}

	// HTTP요청 파라미터와 세션정보로 MissingPersonDTO 객체 생성
	private MissingPersonDTO createPersonFromRequest(HttpServletRequest request, String memberSerialNum, String adminSerialNum) throws IOException, ServletException {
		
		//한글 깨짐 방지
		request.setCharacterEncoding(StandardCharsets.UTF_8.name());

		// 폼데이터 가져오기
		String missingName = request.getParameter("missingName");
		String missingGender = request.getParameter("missingGender");
		String missingBirth = request.getParameter("missingBirth");
		String missingDate = request.getParameter("missingDate");
		String missingEtc = request.getParameter("missingEtc");
		String missingPlace = request.getParameter("missingPlace");
		
		// 이미지파일 저장 후 저장된 파일명 반환
		String imageUrl = saveImageFile(request);

		// DTO객체에 데이터 할당
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

	// 요청에 포함된 이미지 파일 저장, 고유한 파일명 반환
	private String saveImageFile(HttpServletRequest request) throws IOException, ServletException {
		Part part = request.getPart("missingImg");
		
		//파일이 실제로 업로드 되었는지 확인
		if (part != null && part.getSize() > 0) {
			String originalFileName = part.getSubmittedFileName();
			if (originalFileName != null && !originalFileName.isEmpty()) {
				
				// 파일 확장자 추출
				String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
				// 중복을 피하기 위해 UUID로 고유한 파일명 생성
				String uniqueName = UUID.randomUUID().toString() + ext;
				// 저장할 디렉토리 경로(위에서 지정한 절대경로 사용)
				String uploadPath = UPLOAD_DIR; 
				File dir = new File(uploadPath);
				// 디렉토리가 없을 경우 생성
				if (!dir.exists())
					dir.mkdirs(); 

				// 파일을 실제 경로에 저장
				part.write(uploadPath + File.separator + uniqueName);
				return uniqueName;
			}
		}
		return null; // 업로드 된 파일 없으면 null 반환
	}
}