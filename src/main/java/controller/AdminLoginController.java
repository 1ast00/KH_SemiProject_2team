package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import dto.AdminDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.AdminService;
import view.ModelAndView;

public class AdminLoginController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView view = null;
		
		String admin_id = request.getParameter("admin_id");
        String admin_pw = request.getParameter("admin_pw");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("admin_id", admin_id);
        map.put("admin_pw", admin_pw);
        
        AdminDTO admin = AdminService.getInstance().adminLogin(map);
        
        // 관리자 정보가 없다면
        if(admin == null) {
        	// 로그인 실패 시 경고창 + 뒤로가기
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<script>");
            response.getWriter().println("alert('아이디 또는 비밀번호가 틀렸습니다.');");
            response.getWriter().println("history.back();");
            response.getWriter().println("</script>");
            return null;
        } else {
        	// 로그인 성공
            HttpSession session = request.getSession();
            session.setAttribute("user", admin);

            // 관리자 메인화면으로 리다이렉트
            view = new ModelAndView("/adminMain.do", true);
        }
		return view;
	}
}