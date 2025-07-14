package controller;

import java.io.IOException;
import dto.MissingPersonDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.MissingService;
import view.ModelAndView;

// 실종자 정보 삭제 요청 처리하는 컨트롤러(세션 확인 후 관리자 또는 본인(회원)이 작성한 글일 경우에만 삭제 가능
public class MissingDeleteController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1. 삭제할 실종자 번호와 세션 정보 가져오기
        String missingSerialNum = request.getParameter("missingSerialNum");
        HttpSession session = request.getSession(false); // 세션이 없으면 새로 생성하지 않음

        // 세션 또는 로그인 정보가 없는 경우 처리
        if (session == null || session.getAttribute("loginType") == null) {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("<script>alert('로그인이 필요합니다.'); location.href='memberLoginView.do';</script>");
            return null;
        }
        
        String loginType = (String) session.getAttribute("loginType");
        String loginSerial = (String) session.getAttribute("loginSerial");

        // 2. 권한 검사를 위해 DB에서 해당 실종자 정보 조회
        MissingPersonDTO person = MissingService.getInstance().selectMissingPersonBySerial(missingSerialNum);

        boolean hasPermission = false; // 권한 여부 플래그

        // 3. 권한 검사 로직
        if (person != null) {
            // 3-1. 로그인한 사용자가 'ADMIN'인 경우 -> 항상 삭제 가능(관리자의 힘)
            if ("ADMIN".equals(loginType)) {
                hasPermission = true;
            }
            // 3-2. 로그인한 사용자가 'MEMBER'인 경우 -> 본인이 작성한 글인지 확인
            else if ("MEMBER".equals(loginType)) {
                if (person.getMemberSerialNum().equals(loginSerial)) {
                    hasPermission = true;
                }
            }
        }
        
        // 4. 권한에 따른 처리
        if (hasPermission) {
            // 권한이 있으면 삭제 실행
            MissingService.getInstance().deleteMissingPerson(missingSerialNum);
        } else {
            // 권한이 없거나 대상 글이 없으면 에러 처리
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("<script>alert('삭제할 권한이 없습니다.'); history.back();</script>");
            return null; // 여기서 실행 종료
        }

        // 5. 삭제 후 목록 페이지로 리다이렉트
        return new ModelAndView("/missingList.do", true);
    }
}
