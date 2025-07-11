package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.MemberService;
import view.ModelAndView;

public class MemberLoginController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView view = null;
		
		String member_id = request.getParameter("member_id");
        String member_pw = request.getParameter("member_pw");
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("member_id", member_id);
        map.put("member_pw", member_pw);
		
        MemberDTO member = MemberService.getInstance().memberLogin(map);
        
        // 회원 정보가 없다면
        if(member == null) {
        	// 경고창 + 뒤로가기
        	response.setContentType("text/html;charset=UTF-8");
        	response.getWriter().println("<script>");
        	response.getWriter().println("alert('아이디 또는 비밀번호가 틀렸습니다.');");
        	response.getWriter().println("history.back();");
        	response.getWriter().println("</script>");
        	// view = new ModelAndView("/adminloginView.do", true);
        } else { 
        	view = new ModelAndView("/adminMain.do", true);
        	
        	// 로그인 정보 session 저장
        	HttpSession session = request.getSession();
			session.setAttribute("user", member);
        }
		return view;
	}
}
