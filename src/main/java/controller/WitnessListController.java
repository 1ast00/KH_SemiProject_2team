package controller;

import java.io.IOException;
import java.util.List;

import dto.WitnessDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.WitnessService;
import view.ModelAndView;

public class WitnessListController implements Controller {
	private static final int PAGE_SIZE = 8;

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int page = 1;

		// 현재 페이지 파라미터 처리
		String pageParam = request.getParameter("page");
		if (pageParam != null && !pageParam.trim().isEmpty()) {
			try {
				page = Integer.parseInt(pageParam);
			} catch (NumberFormatException e) {
				page = 1;
			}
		}

		// 전체 게시물 수 및 총 페이지 계산
		int totalCount = WitnessService.getInstance().getTotalCount();
		int totalPages = (int) Math.ceil(totalCount / (double) PAGE_SIZE);

		// 페이징 범위 계산 (오라클 기준)
		int start = (page - 1) * PAGE_SIZE;
		int end = page * PAGE_SIZE;

		// 목록 조회 (Map 아님!)
		List<WitnessDTO> list = WitnessService.getInstance().getPagedWitnessList(start, end);

		// 데이터 전달
		request.setAttribute("witnessList", list);
		request.setAttribute("page", page);
		request.setAttribute("totalPages", totalPages);

		return new ModelAndView("witness_list.jsp", false);
	}
}