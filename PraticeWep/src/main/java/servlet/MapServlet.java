package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import service.Pratice;

import java.io.IOException;

@WebServlet("/map")
public class MapServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = "오목교역"; // 원하는 검색어 request.getParameter('.!~~~!'); 로 받아서 넣기

        try {
            String[] result = Pratice.getCoordinates(keyword);
            if (result != null) {
                request.setAttribute("lat", result[0]); // 위도
                request.setAttribute("lng", result[1]); // 경도
                request.setAttribute("place", result[2]); // 장소 이름
                request.getRequestDispatcher("/map.jsp").forward(request, response); // view 부분에 포장해서 넣기
            } else {
                response.getWriter().println("검색 결과 없음");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
