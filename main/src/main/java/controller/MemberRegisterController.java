package controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

import dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MemberService;
import view.ModelAndView;

public class MemberRegisterController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("member_id");
		String pw = request.getParameter("member_pw");
		String name = request.getParameter("member_name");
		String phone = request.getParameter("member_phone");
		String email = request.getParameter("member_email");

		String adminSerialNum = "Admin";

		String encPw = encryptSHA512(pw);

		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setPw(encPw);
		dto.setName(name);
		dto.setPhone(phone);
		dto.setEmail(email);
		dto.setAdminSerialNum(adminSerialNum);

		int result = MemberService.getInstance().insertMember(dto);

		System.out.println("회원가입 시도: id=" + id);
		System.out.println("DB 저장 결과 (성공: 1 / 실패: 0) : " + result);

		if (result > 0) {
			return new ModelAndView("/memberLoginView.do", true);
		} else {
			request.setAttribute("msg", "회원 가입 실패");
			return new ModelAndView("member_register.jsp", false);
		}
	}

	// 암호화
	private String encryptSHA512(String pw) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] bytes = pw.getBytes(StandardCharsets.UTF_8);
			byte[] hashedPw = md.digest(bytes);
			return Base64.getEncoder().encodeToString(hashedPw);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}