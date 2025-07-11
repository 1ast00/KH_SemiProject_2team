package servlet;

import dao.MissingPersonDAO;
import dto.MissingPersonDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;

@WebServlet("/submitMissingPerson")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
        maxFileSize = 1024 * 1024 * 10,       // 10MB
        maxRequestSize = 1024 * 1024 * 50     // 50MB
)
public class MissingPersonServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        MissingPersonDTO person = new MissingPersonDTO();
        
        person.setName(request.getParameter("name"));
        person.setGender(request.getParameter("gender"));
        person.setBirth(request.getParameter("birth"));
        person.setEtc(request.getParameter("etc"));
        person.setPlace(request.getParameter("place"));
        person.setDate(request.getParameter("date"));

        Part filePart = request.getPart("img"); 
        if (filePart != null && filePart.getSize() > 0) {
            try (InputStream inputStream = filePart.getInputStream()) {
                person.setImg(inputStream.readAllBytes());
            }
        }

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "C##USER", "123456")) {
                MissingPersonDAO dao = new MissingPersonDAO(conn);
                
                String newSerialNum = dao.addMissingPerson(person);

                if (newSerialNum != null) {
                    request.setAttribute("serialNum", newSerialNum);
                    request.setAttribute("person", person); // 수정된 DTO 전달
                    request.getRequestDispatcher("/missing_view.jsp").forward(request, response);
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}