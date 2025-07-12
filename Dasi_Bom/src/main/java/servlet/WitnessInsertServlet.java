package servlet;

import java.io.IOException;
import java.util.ArrayList; // 필요 없지만 일단 남겨둡니다.
import java.util.List;     // 필요 없지만 일단 남겨둡니다.
import java.util.UUID;     // 필요 없지만 일단 남겨둡니다.

import dto.WitnessDTO; // 필요 없지만 일단 남겨둡니다.
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/witnessInsert.do")
@MultipartConfig 
public class WitnessInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	 private List<WitnessDTO> witnessList = new ArrayList<>();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		 String date = request.getParameter("date");
		 String place = request.getParameter("place");
		 String gender = request.getParameter("gender");
		 String age = request.getParameter("age");
		 String etc = request.getParameter("etc");
		 String name = request.getParameter("name");
		 String imageFileName = "default.jpg";
		 String id = UUID.randomUUID().toString();
		 WitnessDTO dto = new WitnessDTO(id, date, place, gender, age, etc, imageFileName);
		 request.setAttribute("witnessList", witnessList); // 이 부분도 잠시 비활성화

		// ⭐ witness_list.jsp로 바로 포워딩합니다. (데이터 없이)
		request.getRequestDispatcher("witness_list.jsp").forward(request, response);
	}
}