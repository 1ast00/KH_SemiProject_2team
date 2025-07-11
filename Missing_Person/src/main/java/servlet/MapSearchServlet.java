package servlet;

import dto.MissingPersonDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.Practice;

import java.io.IOException;

@WebServlet("/mapsearch")
public class MapSearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String birth = request.getParameter("birth");
        String etc = request.getParameter("etc");
        String date = request.getParameter("date");
        String place = request.getParameter("place");

        MissingPersonDTO tempPerson = new MissingPersonDTO();
        
        tempPerson.setName(name);
        tempPerson.setGender(gender);
        tempPerson.setBirth(birth);
        tempPerson.setEtc(etc);
        tempPerson.setDate(date);
        tempPerson.setPlace(place);

        HttpSession session = request.getSession();
        session.setAttribute("tempMissingPerson", tempPerson);

        if (place == null || place.trim().isEmpty()) {
            response.getWriter().println("장소명을 입력해주세요.");
            return;
        }

        try {
            String[] result = Practice.getCoordinates(place);

            if (result != null) {
                request.setAttribute("lat", result[0]);
                request.setAttribute("lng", result[1]);
                request.setAttribute("place", result[2]);
                request.getRequestDispatcher("/map.jsp").forward(request, response);
            } else {
                response.getWriter().println("장소를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("오류 발생: " + e.getMessage());
        }
    }
}