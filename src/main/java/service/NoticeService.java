package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import config.DBManager;
import dto.NoticeDTO;
import mapper.NoticeMapper;

// MyBatis SqlSession을 사용할 것이므로
import org.apache.ibatis.session.SqlSession; // 추가

public class NoticeService {

    private static NoticeService instance = new NoticeService();

    private NoticeService() {

    }

    public static NoticeService getInstance() {
        if (instance == null) {
            instance = new NoticeService();
        }
        return instance;
    }

    // --- 여기부터는 Mapper 인터페이스를 통해 DB 작업을 수행 ---

    // 전체 공지사항 개수 조회
    public int getTotalNoticeCount() {
        try (SqlSession session = DBManager.getInstance().getSession()) {
            NoticeMapper mapper = session.getMapper(NoticeMapper.class);
            return mapper.getTotalNoticeCount();
        } // try-with-resources가 자동으로 session.close()를 호출
    }

    // 공지사항 목록 조회 (페이징 적용)
    public List<NoticeDTO> getNoticeList(int page, int pageSize) {
        try (SqlSession session = DBManager.getInstance().getSession()) {
            NoticeMapper mapper = session.getMapper(NoticeMapper.class);
            Map<String, Integer> params = new HashMap<>();
            params.put("page", page);
            params.put("pageSize", pageSize);
            return mapper.getNoticeList(params);
        }
    }

    // 공지사항 상세 조회
    public NoticeDTO getNoticeByNum(int num) {
        try (SqlSession session = DBManager.getInstance().getSession()) {
            NoticeMapper mapper = session.getMapper(NoticeMapper.class);
            return mapper.getNoticeByNum(num);
        }
    }

    // 조회수 증가
    public void incrementViews(int num) {
        try (SqlSession session = DBManager.getInstance().getSession()) {
            NoticeMapper mapper = session.getMapper(NoticeMapper.class);
            mapper.incrementViews(num);
            // DBManager.getSession()이 auto-commit(true)를 반환하므로 명시적인 commit은 필요 없습니다.
            // 하지만 MemberService처럼 DML 후에는 commit/close하는 것이 일반적입니다.
            // 현재 DBManager가 openSession(true)이므로, commit()을 따로 안해도 됩니다.
            // session.close(); // try-with-resources가 자동으로 닫아줍니다.
        }
    }

    // 메인 페이지 최신 공지사항 목록 조회 (LIMIT 기능)
    public List<NoticeDTO> getLatestNoticeList(int limit) {
        try (SqlSession session = DBManager.getInstance().getSession()) {
            NoticeMapper mapper = session.getMapper(NoticeMapper.class);
            return mapper.getLatestNoticeList(limit);
        }
    }
}