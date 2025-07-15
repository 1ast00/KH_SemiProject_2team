// ✅ WitnessInsertController.java (Controller 수정)
package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import dto.WitnessDTO;
import service.WitnessService;
import view.ModelAndView;

public class WitnessInsertController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("✔ 컨트롤러 들어옴");
        request.setCharacterEncoding("UTF-8");

        String dateStr = request.getParameter("date");
        String place = request.getParameter("place");
        String age = request.getParameter("age");
        String etc = request.getParameter("etc");
        String gender = request.getParameter("gender");

        if ("남성".equals(gender)) gender = "M";
        if ("여성".equals(gender)) gender = "F";

        if (!"M".equals(gender) && !"F".equals(gender)) {
            request.setAttribute("error", "성별은 M 또는 F로만 입력 가능합니다.");
            return new ModelAndView("witness_insert.jsp", false);
        }

        Integer parsedAge = null;
        if (age != null && !age.trim().isEmpty()) {
            try {
                parsedAge = Integer.parseInt(age.trim());
            } catch (NumberFormatException e) {
                request.setAttribute("error", "나이는 숫자로 입력해야 합니다.");
                return new ModelAndView("witness_insert.jsp", false);
            }
        }

        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(dateStr);
            date = new Date(utilDate.getTime());
        } catch (Exception e) {
        	 System.out.println("🔥 날짜 파싱 실패: " + dateStr); // ← 로그 추가!
        	    e.printStackTrace(); // ← 무조건 필요
            request.setAttribute("error", "날짜 형식이 잘못되었습니다. (예: 2025-07-15)");
            return new ModelAndView("witness_insert.jsp", false);
        }

        try {
            Part part = request.getPart("image");
            String fileName = (part != null) ? part.getSubmittedFileName() : null;
            System.out.println("▶ 파일 이름: " + fileName);

            String memberSerialNum = "MM10000001";
            String adminSerialNum = "AA10000001";
            String missingSerialNum = "MP10000004";

            WitnessDTO dto = new WitnessDTO(null, date, place, gender, parsedAge, etc, fileName,
                    memberSerialNum, adminSerialNum, missingSerialNum);

            System.out.println("▶ DTO 생성 완료: " + dto);

            boolean result = WitnessService.getInstance().insertWitness(dto);
            System.out.println("✔ insert 성공 여부: " + result);

            if (result) {
                return new ModelAndView("/witnessList.do", true);
            } else {
                request.setAttribute("error", "제보 등록 실패");
                return new ModelAndView("/witness_insert.jsp", false);
            }
        } catch (Exception e) {
            System.out.println("🔥 예외 발생: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("error", "파일 업로드 중 오류: " + e.getMessage());
            return new ModelAndView("/witness_insert.jsp", false);
        }
    }
}
