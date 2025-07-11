package servlet;

import dao.MissingPersonDAO;
import dto.MissingPersonDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

@WebServlet("/missing/list")
public class MissingListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "C##USER", "123456")) {

                MissingPersonDAO dao = new MissingPersonDAO(conn);
                List<MissingPersonDTO> list = dao.getAllMissingPersons();

                request.setAttribute("missingList", list);
                request.getRequestDispatcher("/missing_list.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "데이터베이스 처리 중 오류 발생: " + e.getMessage());
        }
    }
}