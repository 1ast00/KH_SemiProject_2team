package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dto.MissingPersonDTO;
import dto.PagingDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.MissingService;
import view.ModelAndView;

public class MissingSearchController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // --- 디버깅을 위한 로그 추가 시작 ---
        System.out.println("MissingSearchController: 요청이 수신되었습니다.");
        System.out.println("요청 URI: " + request.getRequestURI());
        System.out.println("쿼리 스트링: " + request.getQueryString());
        // --- 디버깅을 위한 로그 추가 끝 ---

        HttpSession session = request.getSession();

        MissingService service = MissingService.getInstance();

        // 검색 파라미터 추출
        String searchName = request.getParameter("name");
        String searchAge = request.getParameter("age");
        String searchGender = request.getParameter("gender");
        String searchPlace = request.getParameter("place");
        String searchEtc = request.getParameter("etc");

        // 검색 조건 맵 생성
        Map<String, Object> searchParams = new HashMap<>();
        
        // 검색 파라미터가 유효한 경우에만 맵에 추가
        if (searchName != null && !searchName.trim().isEmpty()) {
            searchParams.put("name", searchName.trim());
        }
        if (searchAge != null && !searchAge.trim().isEmpty()) {
            searchParams.put("age", searchAge.trim());
        }
        if (searchGender != null && !searchGender.trim().isEmpty()) {
            searchParams.put("gender", searchGender.trim());
        }
        if (searchPlace != null && !searchPlace.trim().isEmpty()) {
            searchParams.put("place", searchPlace.trim());
        }
        if (searchEtc != null && !searchEtc.trim().isEmpty()) {
            searchParams.put("etc", searchEtc.trim());
        }

        // --- 검색 파라미터 로그 추가 시작 ---
        System.out.println("수신된 검색 파라미터: " + searchParams);
        // --- 검색 파라미터 로그 추가 끝 ---

        // 현재 페이지 번호 받기 (없으면 1로 초기화)
        int page = 1;
        String pageStr = request.getParameter("page");
        if (pageStr != null && !pageStr.isEmpty()) {
            page = Integer.parseInt(pageStr);
        }

        int pageSize = 6; // 한 페이지에 6개씩 표시
        int totalCount;
        List<MissingPersonDTO> list;

        // 검색 조건이 있을 때와 없을 때의 총 카운트 및 목록 조회 분리
        if (!searchParams.isEmpty()) { // 검색 파라미터가 하나라도 존재하면 검색 로직 수행
            totalCount = service.getSearchMissingCount(searchParams);
            System.out.println("검색된 총 실종자 수: " + totalCount);
        } else { // 이 MissingSearchController는 항상 검색 파라미터와 함께 호출되므로 이 else는 거의 실행되지 않습니다.
                 // 다만, 만약을 위해 기본 동작을 유지합니다.
            totalCount = service.getMissingCount();
            System.out.println("총 실종자 수 (검색 조건 없음): " + totalCount);
        }
        
        PagingDTO paging = new PagingDTO(page, pageSize, totalCount);

        // DB에서 해당 페이지의 목록만 조회하기 위한 파라미터 설정
        int startRow = (page - 1) * pageSize + 1;
        int endRow = page * pageSize;
        
        Map<String, Object> params = new HashMap<>();
        params.put("startRow", startRow);
        params.put("endRow", endRow);
        params.putAll(searchParams); // 검색 조건을 페이징 파라미터 맵에 추가

        if (!searchParams.isEmpty()) { // 검색 중이면 검색된 목록 가져오기
            list = service.searchMissingList(params);
        } else { // 검색 중이 아니면 (이 경우는 main.js 로직상 발생하지 않음)
            list = service.selectMissingList(params);
        }
        
        System.out.println("현재 페이지 목록 수 : " + list.size());

        request.setAttribute("missingList", list);
        request.setAttribute("paging", paging);

        // 검색 결과가 없을 경우 플래그 설정
        if (list.isEmpty()) {
            request.setAttribute("noResults", true);
            System.out.println("검색 결과 없음: noResults = true");
        } else {
            request.setAttribute("noResults", false);
            System.out.println("검색 결과 있음: noResults = false");
        }

        return new ModelAndView("missing_list.jsp", false);
    }
}