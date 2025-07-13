package controller;

import java.io.IOException;
import java.io.InputStream;

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

        request.setCharacterEncoding("UTF-8");

        // 1. 폼 데이터 받기
        String date = request.getParameter("date");
        String place = request.getParameter("place");
        String gender = request.getParameter("gender");
        String age = request.getParameter("age");
        String etc = request.getParameter("etc");

        // 2. 이미지 파일 처리 (파일 이름만 저장하는 예시)
        System.out.println("▶ witnessInsert.do 진입");

        try {
            Part part = request.getPart("image");
            String fileName = (part != null) ? part.getSubmittedFileName() : "파일 없음";
            System.out.println("▶ 파일 이름: " + fileName);

            WitnessDTO dto = new WitnessDTO(null, date, place, gender, age, etc, fileName);
            boolean result = WitnessService.getInstance().insertWitness(dto);

            if (result) {
                return new ModelAndView("witnessList.do", true);
            } else {
                request.setAttribute("error", "제보 등록 실패");
                return new ModelAndView("witness_insert.jsp", false);
            }
        } catch (Exception e) {
            e.printStackTrace(); // 서버 콘솔에 예외 출력
            request.setAttribute("error", "파일 업로드 중 오류: " + e.getMessage());
            return new ModelAndView("witness_insert.jsp", false);
        }
    }
}