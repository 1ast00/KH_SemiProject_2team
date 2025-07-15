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
        	view = new ModelAndView("/adminloginView.do", true);
        } else {
        	view = new ModelAndView("/adminMain.do", true);
        	
        	// 로그인 정보 session 저장
        	HttpSession session = request.getSession();
			session.setAttribute("user", admin);
        }
		return view;
	}
}