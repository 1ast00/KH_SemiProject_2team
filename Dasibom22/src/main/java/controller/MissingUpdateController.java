package controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import dto.MissingPersonDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import service.MissingService;
import view.ModelAndView;

// 실종자 정보 수정 폼에서 전송된 데이터를 DB에 반영(UPDATE)하는 컨트롤러(MissingInsert컨트롤러와 비슷)
public class MissingUpdateController implements Controller {

	// 이미지 파일이 저장될 절대경로
    private static final String UPLOAD_DIR = "C:/dasibom_uploads";

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	
    	// 수정 폼에서 전송된 정보로 DTO객체 생성
        MissingPersonDTO person = createPersonFromUpdateRequest(request);

        // 서비스 호출해서 DB에 수정 내용 반영
        int result = MissingService.getInstance().updateMissingPerson(person);

        // 수정 완료 후 상세보기 페이지로 리다이렉트
        return new ModelAndView("missingView.do?missingSerialNum=" + person.getMissingSerialNum(), true);
    }

    // HTTP 수정요청 파라미터로 MissingPersonDTO객체 생성
    private MissingPersonDTO createPersonFromUpdateRequest(HttpServletRequest request) throws IOException, ServletException {
    	
    	// 한글 깨짐 방지
        request.setCharacterEncoding(StandardCharsets.UTF_8.name());

        // 폼 데이터 가져오기(hidden타입 포함)
        String missingSerialNum = request.getParameter("missingSerialNum");
        String missingName = request.getParameter("missingName");
        String missingGender = request.getParameter("missingGender");
        String missingBirth = request.getParameter("missingBirth");
        String missingDate = request.getParameter("missingDate");
        String missingEtc = request.getParameter("missingEtc");
        String missingPlace = request.getParameter("missingPlace");

        // 새 이미지가 제출되었을 때만 저장하고, 아니면 null을 반환
        String imageUrl = saveImageFile(request);

        // DTO객체에 데이터 할당
        MissingPersonDTO person = new MissingPersonDTO();
        person.setMissingSerialNum(missingSerialNum); 
        person.setName(missingName);
        person.setGender(missingGender);
        person.setBirth(missingBirth);
        person.setMissingDate(missingDate);
        person.setEtc(missingEtc);
        person.setPlace(missingPlace);
        
        // 새 이미지가 있을 때만 이미지 정보 설정
        if (imageUrl != null) {
            person.setImage(imageUrl);
        }

        return person;
    }

    // 요청에 포함된 이미지파일 저장, 고유한 파일명 반환(MissingInsertController랑 동일한 로직)
    private String saveImageFile(HttpServletRequest request) throws IOException, ServletException {
        Part part = request.getPart("missingImg");
        if (part != null && part.getSize() > 0) {
            String originalFileName = part.getSubmittedFileName();
            if (originalFileName != null && !originalFileName.isEmpty()) {
                String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
                String uniqueName = UUID.randomUUID().toString() + ext;
                
                String uploadPath = UPLOAD_DIR;
                
                File dir = new File(uploadPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                
                part.write(uploadPath + File.separator + uniqueName);
                return uniqueName;
            }
        }
        return null;
    }
}