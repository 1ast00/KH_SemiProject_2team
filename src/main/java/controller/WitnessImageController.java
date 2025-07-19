package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import view.ModelAndView;
	
public class WitnessImageController implements Controller {
	
    private static final String IMAGE_DIR = "D:/uploads/witness/";
    
    
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        if (name == null || name.trim().isEmpty()) {
            name = "default.jpg";
        }

        File file = new File(IMAGE_DIR + name);
        if (!file.exists()) {
            file = new File(IMAGE_DIR + "default.jpg");
        }

        response.setContentType("image/jpeg");
        response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");

        try (FileInputStream fis = new FileInputStream(file);
             OutputStream out = response.getOutputStream()) {

            byte[] buffer = new byte[4096];
            int length;
            while ((length = fis.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;  // 직접 응답했기 때문에 JSP 이동 없음
    }
}