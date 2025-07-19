package controller;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import view.ModelAndView;

// 실종자 정보 입력을 위한 폼(JSP 화면으로 이동시키는 컨트롤러)
public class MissingInsertViewController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return new ModelAndView("missing_insert.jsp", false);
	}
}