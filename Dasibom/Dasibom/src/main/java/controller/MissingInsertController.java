package controller;

import java.io.IOException;

import dto.MissingPersonDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.MissingService;
import view.ModelAndView;

public class MissingInsertController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 인코딩
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 세션에서 로그인한 회원 번호 가져오기 (임시 세션 설정이 없으면 직접 입력해도 됨)
        HttpSession session = request.getSession(false);
        String memberSerialNum = null;
        String adminSerialNum = null;

        if (session != null) {
            memberSerialNum = (String) session.getAttribute("loginSerial");
            // 임시로 관리자 번호 고정 (실제로는 로그인된 관리자 번호 필요)
            adminSerialNum = "AA10000001"; 
        } else {
            // 임시값 (테스트용)
            memberSerialNum = "MM10000001";
            adminSerialNum = "AA10000001";
        }

        // 폼 데이터 받기
        String missingName = request.getParameter("missingName");
        String missingGender = request.getParameter("missingGender");
        String missingBirth = request.getParameter("missingBirth"); // YYYY-MM-DD 형식
        String missingDate = request.getParameter("missingDate");   // YYYY-MM-DD 형식
        String missingEtc = request.getParameter("missingEtc");
        String missingPlace = request.getParameter("missingPlace");
        String missingImageUrl = request.getParameter("missingImageUrl");

        // birth DB용 숫자 변환 (YYYYMMDD -> int)
        int birthForDB = 0;
        if (missingBirth != null && !missingBirth.isEmpty()) {
            birthForDB = Integer.parseInt(missingBirth.replace("-", ""));
        }

        // DTO에 데이터 세팅
        MissingPersonDTO dto = new MissingPersonDTO();
        dto.setMemberSerialNum(memberSerialNum);
        dto.setAdminSerialNum(adminSerialNum);
        dto.setName(missingName);
        dto.setGender(missingGender);
        dto.setBirth(String.valueOf(birthForDB)); // DB에는 NUMBER 타입, DTO는 String 타입 유지
        dto.setMissingDate(missingDate);
        dto.setEtc(missingEtc);
        dto.setPlace(missingPlace);
        dto.setImage(missingImageUrl); // 이미지 URL 저장

        // DB 저장 시도
        int result = MissingService.getInstance().insertMissingPerson(dto);

        if (result > 0) {
            // 저장 성공 -> 목록 페이지로 리다이렉트
            return new ModelAndView("/missingList.do", true);
        } else {
            // 저장 실패 -> 에러 메시지와 함께 접수 페이지로 다시 이동
            request.setAttribute("errorMessage", "실종자 정보 저장에 실패했습니다.");
            return new ModelAndView("missing_insert.jsp", false);
        }
    }
}
