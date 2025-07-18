package controller;

import java.io.IOException;

import dto.AdminDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.AdminService;
import view.ModelAndView;

public class AdminRegisterController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("admin_id");
        String pw = request.getParameter("admin_pw");
        String name = request.getParameter("admin_name");
        String phone = request.getParameter("admin_phone");

        AdminDTO dto = new AdminDTO();
        dto.setAdmin_id(id);
        dto.setAdmin_pw(pw);
        dto.setAdmin_name(name);
        dto.setAdmin_phone(phone);

        int result = AdminService.getInstance().insertAdmin(dto);

        if (result > 0) {
            return new ModelAndView("/adminLoginView.do", true);
        } else {
            
            request.setAttribute("msg", "È¸¿ø°¡ÀÔ ½ÇÆÐ");
            return new ModelAndView("admin_register.jsp", false);
        }
    }
}