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

public class MissingUpdateController implements Controller {

    private static final String UPLOAD_DIR = "C:/dasibom_uploads";

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        MissingPersonDTO person = createPersonFromUpdateRequest(request);

        int result = MissingService.getInstance().updateMissingPerson(person);

        // 수정 완료 후, 수정된 내용이 반영된 상세 보기 페이지로 이동 (PRG 패턴)
        return new ModelAndView("/missingView.do?missingSerialNum=" + person.getMissingSerialNum(), true);
    }

    private MissingPersonDTO createPersonFromUpdateRequest(HttpServletRequest request) throws IOException, ServletException {
        request.setCharacterEncoding(StandardCharsets.UTF_8.name());

        // hidden으로 넘어온 missingSerialNum을 받습니다.
        String missingSerialNum = request.getParameter("missingSerialNum");
        String missingName = request.getParameter("missingName");
        String missingGender = request.getParameter("missingGender");
        String missingBirth = request.getParameter("missingBirth");
        String missingDate = request.getParameter("missingDate");
        String missingEtc = request.getParameter("missingEtc");
        String missingPlace = request.getParameter("missingPlace");

        // 새 이미지가 제출되었을 때만 저장하고, 아니면 null을 반환합니다.
        String imageUrl = saveImageFile(request);

        MissingPersonDTO person = new MissingPersonDTO();
        person.setMissingSerialNum(missingSerialNum); // 받은 고유번호를 set
        person.setName(missingName);
        person.setGender(missingGender);
        person.setBirth(missingBirth);
        person.setMissingDate(missingDate);
        person.setEtc(missingEtc);
        person.setPlace(missingPlace);
        
        // 새 이미지가 있을 때만 이미지 정보를 업데이트합니다.
        if (imageUrl != null) {
            person.setImage(imageUrl);
        }

        return person;
    }

    // MissingInsertController에서 가져온 파일 저장 메서드
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