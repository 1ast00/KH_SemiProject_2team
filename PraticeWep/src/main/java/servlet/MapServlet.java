package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import service.Practice; // Pratice -> Practice (철자 수정)

import java.io.IOException;

//@WebServlet("/map")
public class MapServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");

        // --- 디버깅을 위한 로그 추가: 반드시 확인하세요! ---
        System.out.println("MapServlet - Received keyword: [" + keyword + "]");
        // -------------------------------------------------

        if (keyword == null || keyword.trim().isEmpty()) {
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().println("검색어를 입력하셈 ㅋ");
            return;
        }

        try {
            // service.Practice 클래스 호출 (철자 수정 반영)
            String[] result = Practice.getCoordinates(keyword);
            if (result != null) {
                request.setAttribute("lat", result[0]); // 위도
                request.setAttribute("lng", result[1]); // 경도
                request.setAttribute("place", result[2]); // 장소 이름
                request.getRequestDispatcher("/map.jsp").forward(request, response);
            } else {
                response.setContentType("text/plain;charset=UTF-8");
                response.getWriter().println("검색 결과 없음");
            }
        } catch (Exception e) {
            // 예외 발생 시 상세 로그 출력
            System.err.println("MapServlet - Error processing request: " + e.getMessage());
            e.printStackTrace(); // 스택 트레이스 출력
            throw new ServletException("Map Servlet Error", e); // 서블릿 예외로 다시 던짐
        }
    }
}