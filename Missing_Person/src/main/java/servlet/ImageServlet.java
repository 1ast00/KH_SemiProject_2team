package servlet;

import dao.MissingPersonDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

@WebServlet("/image")
public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String serialNum = request.getParameter("id");
        if (serialNum == null || serialNum.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID is required.");
            return;
        }

        byte[] imageBytes = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "C##USER", "123456")) {
                MissingPersonDAO dao = new MissingPersonDAO(conn);
                imageBytes = dao.getMissingImageById(serialNum);
            }
        } catch (Exception e) {
            throw new ServletException("DB Error", e);
        }

        if (imageBytes != null && imageBytes.length > 0) {
            response.setContentType("image/jpeg");
            response.setContentLength(imageBytes.length);
            try (OutputStream os = response.getOutputStream()) {
                os.write(imageBytes);
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}