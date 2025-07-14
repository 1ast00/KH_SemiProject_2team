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

        String admin_id = request.getParameter("admin_id").trim();
        String admin_pw = request.getParameter("admin_pw").trim();

        Map<String, Object> map = new HashMap<>();
        map.put("admin_id", admin_id);
        map.put("admin_pw", admin_pw);

        AdminDTO admin = AdminService.getInstance().adminLogin(map);

        if (admin == null) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<script>");
            response.getWriter().println("alert('아이디 또는 비밀번호가 틀렸습니다.');");
            response.getWriter().println("history.back();");
            response.getWriter().println("</script>");
            return null;
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("admin", admin);

            return new ModelAndView("/adminMain.do", true);
        }
    }
}
