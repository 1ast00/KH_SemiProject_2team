package servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;

import dao.WitnessDAO;
import dto.WitnessDTO;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/witnessInsert.do")
public class WitnessInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. 파일 저장 경로 설정
		String uploadPath = getServletContext().getRealPath("/upload");
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) uploadDir.mkdir();

		String date = null, place = null, gender = null, age = null, etc = null, imageFileName = null;

		// 2. multipart 요청인지 확인
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setHeaderEncoding("UTF-8");

				List<FileItem> items = upload.parseRequest(request);

				for (FileItem item : items) {
					if (item.isFormField()) {
						String fieldName = item.getFieldName();
						String value = item.getString("UTF-8");

						switch (fieldName) {
							case "date": date = value; break;
							case "place": place = value; break;
							case "gender": gender = value; break;
							case "age": age = value; break;
							case "etc": etc = value; break;
						}
					} else {
						// 파일 업로드 처리
						if (!item.getName().isEmpty()) {
							String originalName = new File(item.getName()).getName();
							String ext = originalName.substring(originalName.lastIndexOf("."));
							String uuid = UUID.randomUUID().toString();
							imageFileName = uuid + ext;

							File uploadedFile = new File(uploadPath, imageFileName);
							item.write(uploadedFile);
						}
					}
				}

				// 3. DTO 생성 및 DAO 저장
				WitnessDTO dto = new WitnessDTO(date, place, gender, age, etc, imageFileName);
				WitnessDAO dao = new WitnessDAO();
				dao.insertWitness(dto);

				// 4. 결과 이동
				response.sendRedirect("witness_list.jsp");

			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().println("업로드 오류 발생: " + e.getMessage());
			}
		}
	}
}
