package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dto.NoticeDTO;
import service.NoticeService;
import view.ModelAndView;

public class NoticeViewController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String numStr = request.getParameter("num");
        int num = 0;
        if (numStr != null && !numStr.isEmpty()) {
            try {
                num = Integer.parseInt(numStr);
            } catch (NumberFormatException e) {
                System.err.println("Invalid notice number: " + numStr);
                return new ModelAndView("noticeList.do", true);
            }
        } else {
            return new ModelAndView("noticeList.do", true);
        }

        NoticeService service = NoticeService.getInstance();
        
        // 조회수 증가
        service.incrementViews(num);

        // 공지사항 상세 정보 조회
        NoticeDTO notice = service.getNoticeByNum(num);

        request.setAttribute("notice", notice);

        return new ModelAndView("notice_view.jsp", false); 
    }
}