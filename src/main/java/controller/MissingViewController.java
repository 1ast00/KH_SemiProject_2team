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
import jakarta.servlet.http.HttpSession; // HttpSession import
import jakarta.servlet.http.Part;
import service.MissingService;
import view.ModelAndView;

public class MissingViewController implements Controller {
    private static final String UPLOAD_DIR = "uploads";

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        // --- 세션에서 로그인 정보 확인 ---
        HttpSession session = request.getSession(false); // false: 세션이 없으면 새로 만들지 않음
        // "loginMember"는 로그인 성공 시 세션에 저장한 회원 정보 객체의 이름이라고 가정합니다.
        // 실제 프로젝트에서 사용하는 이름으로 변경해야 합니다.
        // 여기서는 임시로 DTO를 사용했지만, 보통은 회원 정보만 담는 별도의 로그인용 객체를 사용합니다.
        // MemberDTO loginMember = (MemberDTO) session.getAttribute("loginMember");
        
        // 임시로 사용할 로그인 회원 번호 (실제로는 세션에서 가져와야 함)
        // 로그인 기능이 완성되면 아래 코드는 세션에서 값을 가져오는 코드로 대체되어야 합니다.
        String memberSerialNum = "MM10000001"; // 예시: 로그인된 회원 번호
        
        // if (session == null || session.getAttribute("loginMember") == null) {
        //     // 로그인이 안된 경우 로그인 페이지로 리다이렉트
        //     return new ModelAndView("memberLoginView.do", true);
        // }
        // String memberSerialNum = ((MemberDTO) session.getAttribute("loginMember")).getMemberSerialNum();
        
        MissingPersonDTO person = null;
        try {
            // 1. Service를 통해 회원의 관리자 번호 조회
            MissingService service = MissingService.getInstance();
            String adminSerialNum = service.getAdminSerialNumByMember(memberSerialNum);

            if (adminSerialNum == null) {
                throw new Exception("소속된 관리자 정보를 찾을 수 없습니다.");
            }

            // 2. DTO 객체 생성 및 데이터 바인딩
            person = createPersonFromRequest(request, memberSerialNum, adminSerialNum);

            // 3. Service를 통해 DB에 저장
            service.insertMissingPerson(person);

            // 4. 나이 계산
            String ageAtMissing = service.calculateAge(person.getBirth(), person.getMissingDate());
            String currentAge = service.calculateAge(person.getBirth(), LocalDate.now().toString());

            // 5. 계산된 정보와 DTO를 request에 담아 View 페이지로 전달
            request.setAttribute("missingPerson", person);
            request.setAttribute("ageAtMissing", ageAtMissing);
            request.setAttribute("currentAge", currentAge);

            // 6. 상세 보기 페이지로 포워딩
            return new ModelAndView("missing_view.jsp", false);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "데이터 저장 중 오류가 발생했습니다: " + e.getMessage());
            if (person != null) {
                request.setAttribute("submittedData", person);
            }
            return new ModelAndView("missing_insert.jsp", false);
        }
    }

    private MissingPersonDTO createPersonFromRequest(HttpServletRequest request, String memberSerialNum, String adminSerialNum) throws IOException, ServletException {
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

        // --- 수정된 부분 ---
        // 하드코딩 대신 파라미터로 받은 값을 설정
        person.setMemberSerialNum(memberSerialNum);
        person.setAdminSerialNum(adminSerialNum);

        return person;
    }

    private String saveImageFile(HttpServletRequest request) throws IOException, ServletException {
        Part missingImgPart = request.getPart("missingImg");
        if (missingImgPart != null && missingImgPart.getSize() > 0) {
            String originalFileName = missingImgPart.getSubmittedFileName();
            if (originalFileName != null && !originalFileName.isEmpty()) {
                String fileExtension = "";
                int dotIndex = originalFileName.lastIndexOf('.');
                if (dotIndex > 0) {
                    fileExtension = originalFileName.substring(dotIndex);
                }
                String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
                String uploadPath = request.getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
                missingImgPart.write(uploadPath + File.separator + uniqueFileName);
                return uniqueFileName;
            }
        }
        return null;
    }
}