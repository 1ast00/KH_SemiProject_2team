package servlet;

import dao.MissingPersonDAO;

import dto.MissingPersonDTO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;
import java.util.List;

@WebServlet("/missing/list")
public class MissingListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        try {
            // DB 연결
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@nam3324.synology.me:32800:XE", "c##dasibom22", "dasibom22" // DB 노트북에 연결되어있음(샘플데이터20
            );

            MissingPersonDAO dao = new MissingPersonDAO(conn);
            List<MissingPersonDTO> list = dao.getAllMissingPersons();

            request.setAttribute("missingList", list);
            request.getRequestDispatcher("/missing_list.jsp").forward(request, response);

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("DB 오류 발생: " + e.getMessage());
        }
    }
}