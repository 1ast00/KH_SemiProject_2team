package controller;

import java.io.IOException;
import java.util.HashMap; // HashMap import
import java.util.List;
import java.util.Map; // Map import

import dto.MissingPersonDTO;
import dto.PagingDTO; // PagingDTO import
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.MissingService;
import view.ModelAndView;

public class MissingListController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.setAttribute("loginRole", "admin");
		session.setAttribute("loginSerialNum", "MM10000001");

		// 1. 서비스 객체 가져오기
		MissingService service = MissingService.getInstance();

		// 2. 현재 페이지 번호 받기 (없으면 1로 초기화)
		int page = 1;
		String pageStr = request.getParameter("page");
		if (pageStr != null && !pageStr.isEmpty()) {
			page = Integer.parseInt(pageStr);
		}

		// 3. 페이징 처리
		int pageSize = 6; // 한 페이지에 6개씩 표시
		int totalCount = service.getMissingCount(); // 전체 게시물 수
		PagingDTO paging = new PagingDTO(page, pageSize, totalCount);

		// 4. DB에서 해당 페이지의 목록만 조회하기 위한 파라미터 설정
		int startRow = (page - 1) * pageSize + 1;
		int endRow = page * pageSize;

		Map<String, Object> params = new HashMap<>();
		params.put("startRow", startRow);
		params.put("endRow", endRow);

		List<MissingPersonDTO> list = service.selectMissingList(params);

		// 5. JSP로 데이터 전달
		request.setAttribute("missingList", list);
		request.setAttribute("paging", paging); // 페이징 정보 전달

		System.out.println("총 실종자 수 : " + totalCount);
		System.out.println("현재 페이지 목록 수 : " + list.size());

		return new ModelAndView("missing_list.jsp", false);
	}
}