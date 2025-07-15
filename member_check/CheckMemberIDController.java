package controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MemberService;
import view.ModelAndView;

public class CheckMemberIDController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String memberId = request.getParameter("member_id");
		
		// 받아오는지 확인
		System.out.println("getParameter: " + memberId);

        int isDuplicate =  MemberService.getInstance().selectById(memberId);

        response.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = response.getWriter();

        
        if (isDuplicate == 1) {
        	out.print("duplicate");
        } else {
        	out.print("available");
        }
		return null;
	}
}
