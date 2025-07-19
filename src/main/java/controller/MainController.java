package controller;

import java.io.IOException;
import java.util.List;

import dto.MissingPersonDTO;
import dto.NoticeDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MissingService;
import service.NoticeService;
import view.ModelAndView;

public class MainController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 실종 정보 갖고 오기
		List<MissingPersonDTO> list = MissingService.getInstance().selectMissingListMain();
        request.setAttribute("missingList", list);
        
        // 추가 (최신 공지사항 리스트 가져오기)
        NoticeService noticeService = NoticeService.getInstance();
        int limit = 5; // 메인 페이지에 보여줄 최신 공지사항 개수
        List<NoticeDTO> latestNoticeList = noticeService.getLatestNoticeList(limit);
        request.setAttribute("latestNoticeList", latestNoticeList);
        
		return new ModelAndView("main.jsp", false);
	}
}