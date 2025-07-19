package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dto.NoticeDTO;
import dto.PagingDTO;
import service.NoticeService;
import view.ModelAndView;

public class NoticeListController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 현재 페이지 번호 가져오기 (기본값 1)
        String pageStr = request.getParameter("page");
        int page = 1;
        if (pageStr != null && !pageStr.isEmpty()) {
            try {
                page = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
                // 유효하지 않은 페이지 번호인 경우 기본값 1 유지
                page = 1;
            }
        }

        int pageSize = 10; // 한 페이지에 보여줄 게시물 수

        NoticeService service = NoticeService.getInstance();

        // 1. 전체 공지사항 개수 조회
        int totalCount = service.getTotalNoticeCount();

        // 2. PagingDTO 객체 생성 및 페이징 정보 계산
        PagingDTO paging = new PagingDTO(page, pageSize, totalCount);

        // 3. 현재 페이지에 해당하는 공지사항 목록 조회
        List<NoticeDTO> noticeList = service.getNoticeList(page, pageSize);

        // 4. JSP로 전달할 데이터 설정
        request.setAttribute("noticeList", noticeList);
        request.setAttribute("paging", paging);

     // 5. ModelAndView 객체를 생성하여 반환 
        return new ModelAndView("notice_list.jsp", false);
    }
}
