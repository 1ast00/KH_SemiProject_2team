package mapper;

import java.util.List;
import java.util.Map;

import dto.NoticeDTO; // NoticeDTO 임포트

// 이 인터페이스의 메서드 이름과 파라미터 타입이 notice-mapper.xml의 id 및 parameterType과 일치해야 함.
public interface NoticeMapper {

    // noticeMapper.getTotalNoticeCount (selectOne)
    int getTotalNoticeCount();

    // noticeMapper.getNoticeList (selectList, Map 파라미터)
    List<NoticeDTO> getNoticeList(Map<String, Integer> params);

    // noticeMapper.getNoticeByNum (selectOne, int 파라미터)
    NoticeDTO getNoticeByNum(int num);

    // noticeMapper.incrementViews (update, int 파라미터)
    void incrementViews(int num); // update는 보통 int를 반환하지만, 여기서는 void로 처리 가능 (영향받은 행 수 반환하지 않아도 되면)

    // noticeMapper.getLatestNoticeList (selectList, int 파라미터)
    List<NoticeDTO> getLatestNoticeList(int limit);
}