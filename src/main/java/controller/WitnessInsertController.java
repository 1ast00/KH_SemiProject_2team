package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import dto.WitnessDTO;
import service.WitnessService;
import view.ModelAndView;

public class WitnessInsertController implements Controller {

	private static final String IMAGE_DIR = "D:/uploads/witness/";

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("✔ WitnessInsertController 들어옴");
		request.setCharacterEncoding("UTF-8");

		String dateStr = request.getParameter("date");
		String place = request.getParameter("place");
		String age = request.getParameter("age");
		String etc = request.getParameter("etc");
		String gender = request.getParameter("gender");

		if ("남성".equals(gender))
			gender = "M";
		if ("여성".equals(gender))
			gender = "F";

		if (!"M".equals(gender) && !"F".equals(gender)) {
			request.setAttribute("error", "성별은 M 또는 F로만 입력 가능합니다.");
			return new ModelAndView("witness_insert.jsp", false);
		}

		Integer parsedAge = null;
		if (age != null && !age.trim().isEmpty()) {
			try {
				parsedAge = Integer.parseInt(age.trim());
			} catch (NumberFormatException e) {
				request.setAttribute("error", "나이는 숫자로 입력해야 합니다.");
				return new ModelAndView("witness_insert.jsp", false);
			}
		}

		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date utilDate = sdf.parse(dateStr);
			date = new Date(utilDate.getTime());
		} catch (Exception e) {
			request.setAttribute("error", "날짜 형식이 잘못되었습니다. (예: 2025-07-15)");
			return new ModelAndView("witness_insert.jsp", false);
		}

		String fileName = null;

		try {
			// 폴더 자동 생성
			File uploadDir = new File(IMAGE_DIR);
			if (!uploadDir.exists()) {
				boolean created = uploadDir.mkdirs();
				if (!created) {
					throw new IOException("이미지 저장 폴더 생성 실패: " + IMAGE_DIR);
				}
				System.out.println("📁 폴더 자동 생성 완료: " + IMAGE_DIR);
			}

			// 이미지 파일 처리
			Part part = request.getPart("image");
			if (part != null && part.getSize() > 0) {
				String originalName = part.getSubmittedFileName();
				if (originalName != null && !originalName.trim().isEmpty()) {
					fileName = UUID.randomUUID().toString() + "_" + originalName;
					part.write(IMAGE_DIR + fileName);
					System.out.println("✔ 이미지 저장 완료: " + fileName);
				}
			}

			// 파일 존재 확인
			if (fileName != null) {
				File imgFile = new File(IMAGE_DIR + fileName);
				if (!imgFile.exists()) {
					System.out.println("⚠ 이미지 파일이 존재하지 않음 → 기본 이미지 사용");
					fileName = null;
				}
			}

			// DTO 생성
			String memberSerialNum = "MM10000001";
			String missingSerialNum = request.getParameter("missingSerialNum");
			if (missingSerialNum != null && missingSerialNum.trim().isEmpty()) {
				missingSerialNum = null;
			}

			WitnessDTO dto = new WitnessDTO(null, date, place, gender, parsedAge, etc, fileName, memberSerialNum,
					missingSerialNum);

			System.out.println("▶ DTO 생성 완료: " + dto);

			boolean result = WitnessService.getInstance().insertWitness(dto);
			System.out.println("✔ insert 성공 여부: " + result);

			if (result) {
				return new ModelAndView(request.getContextPath() + "/witnessList.do", true);
			} else {
				request.setAttribute("error", "제보 등록 실패");
				return new ModelAndView("witness_insert.jsp", false);
			}

		} catch (Exception e) {
			System.out.println("🔥 예외 발생: " + e.getMessage());
			e.printStackTrace();
			request.setAttribute("error", "파일 업로드 중 오류 발생: " + e.getMessage());
			return new ModelAndView("witness_insert.jsp", false);
		}
	}
}