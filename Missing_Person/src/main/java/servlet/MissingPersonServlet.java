package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate; // LocalDate 클래스 import
import java.time.Period;    // Period 클래스 import
import java.time.format.DateTimeParseException; // 날짜 파싱 예외 처리

import java.util.UUID; // 고유한 파일 이름 생성을 위해

@WebServlet("/submitMissingPerson")
@MultipartConfig( // 파일 업로드를 위한 설정 이거 배운거임?
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB: 메모리 임시 저장 기준
    maxFileSize = 1024 * 1024 * 10,     // 10MB: 파일 하나당 최대 크기
    maxRequestSize = 1024 * 1024 * 50   // 50MB: 전체 요청 데이터 최대 크기
)
public class MissingPersonServlet extends HttpServlet {

	// 업로드 된 이미지 파일이 어떤 디렉토리에 저장될지 경로 정의하는 코드
    private static final String UPLOAD_DIR = "uploads"; 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	// ???, 글자깨짐 오류 나서 추가
        request.setCharacterEncoding("UTF-8");

        // 폼 데이터 가져오기
        String missingName = request.getParameter("missingName"); // 이르ㅜㅁ
        String missingGender = request.getParameter("missingGender"); // 성별
        String missingBirth = request.getParameter("missingBirth"); // 생년월일 : YYMMDD 
        String missingDate = request.getParameter("missingDate"); // 실종 날짜 : YYYY-MM-DD 
        String missingEtc = request.getParameter("missingEtc");  // 기타 특징
        String missingPlace = request.getParameter("missingPlace"); // 마지막 목격 장소

        String imageUrl = null; // 업로드된 이미지의 웹 접근 URL

        // 나이 계산을 위한 변수 초기화
        String ageAtMissing = " ";
        String currentAge = " ";

        try {
            // 1. 이미지 파일 처리
            Part missingImgPart = request.getPart("missingImg");
            if (missingImgPart != null && missingImgPart.getSize() > 0) {
                String fileName = missingImgPart.getSubmittedFileName();
                if (fileName != null && !fileName.isEmpty()) {
                    String fileExtension = "";
                    int dotIndex = fileName.lastIndexOf('.');
                    if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
                        fileExtension = fileName.substring(dotIndex);
                    }
                    String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

                    String applicationPath = request.getServletContext().getRealPath("");
                    String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;

                    File uploadDir = new File(uploadFilePath);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdirs();
                    }

                    String filePath = uploadFilePath + File.separator + uniqueFileName;
                    missingImgPart.write(filePath);
                    imageUrl = request.getContextPath() + "/" + UPLOAD_DIR + "/" + uniqueFileName;
                }
            }

            // 2. 나이 계산 로직
            try {
                // 생년월일 (YYMMDD -> YYYYMMDD 형태로 변환하여 LocalDate로 파싱)
                // 2000년대생을 고려하여 대략적인 연도 유추
                int birthYearPrefix;
                int currentYearTwoDigits = LocalDate.now().getYear() % 100;
                int birthYearTwoDigits = Integer.parseInt(missingBirth.substring(0, 2));

                // 현재 연도 기준으로 00~현재년도(두자리)+1이면 2000년대, 아니면 1900년대
                if (birthYearTwoDigits <= currentYearTwoDigits + 1) { // 25년 기준 00~26년생은 20xx년생
                    birthYearPrefix = 20;
                } else { // 그 외 (예: 99년생 등)는 19xx년생
                    birthYearPrefix = 19;
                }
                String fullBirthDateStr = birthYearPrefix + missingBirth; // 예: "19801231" 또는 "20050101"

                LocalDate birthDate = LocalDate.parse(fullBirthDateStr, java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"));
                LocalDate dateOfMissing = LocalDate.parse(missingDate);
                LocalDate currentDate = LocalDate.now(); // 현재 날짜 (서버 시간 기준)

                // 실종 당시 나이 계산 (만 나이)
                Period periodAtMissing = Period.between(birthDate, dateOfMissing);
                ageAtMissing = String.valueOf(periodAtMissing.getYears());

                // 현재 나이 계산 (만 나이)
                Period periodCurrent = Period.between(birthDate, currentDate);
                currentAge = String.valueOf(periodCurrent.getYears());

            } catch (DateTimeParseException | NumberFormatException e) {
                System.err.println("날짜 또는 숫자 파싱 오류 (나이 계산): " + e.getMessage());
                // 오류 발생 시 기본값 유지 ("알 수 없음")
            }

            // 3. 받은 데이터를 데이터베이스에 저장하는 등의 비즈니스 로직 추가 (필요시)
            // 예: MissingPersonDAO dao = new MissingPersonDAO();
            //     dao.saveMissingPerson(missingName, missingGender, missingBirth, missingEtc, missingDate, imageUrl, missingPlace, ageAtMissing, currentAge);

            // 4. 데이터를 request 속성에 담아 missing_list.jsp로 포워드
            request.setAttribute("missingName", missingName);
            request.setAttribute("missingGender", missingGender);
            request.setAttribute("missingBirth", missingBirth);
            request.setAttribute("ageAtMissing", ageAtMissing);   
            request.setAttribute("currentAge", currentAge);       
            request.setAttribute("missingEtc", missingEtc);
            request.setAttribute("missingPlace", missingPlace);
            request.setAttribute("missingDate", missingDate);
            request.setAttribute("missingImageUrl", imageUrl);

            request.getRequestDispatcher("/missing_view.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "실종자 정보 접수 중 오류가 발생했습니다: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response); // 오류 페이지로 이동
        }
    }
}