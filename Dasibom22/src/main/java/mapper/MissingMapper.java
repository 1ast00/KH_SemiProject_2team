package mapper;

import java.util.List;

import java.util.Map; // Map import 추가
import dto.MissingPersonDTO;

// 자바코드랑 SQL쿼리(XML)을 연결하는 역할 *메서드명과 XML파일의 id랑 같아야함*
// Mybatis랑 연결되서 missing_info테이블의 데이터에 접근하는 인터페이스

public interface MissingMapper { // 페이징 처리된 실종자 목록 조회

	// 실종자정보 등록(실행 후 파라미터로 받은 person객체의 missingSerialNum필드에 생성된 고유번호가 담김)
	int insertMissingPerson(MissingPersonDTO person);

	// 실종자 정보 수정
	int updateMissingPerson(MissingPersonDTO person);
	
	// 실종자정보 삭제
	int deleteMissingPerson(String missingSerialNum);
	
	// 회원 고유번호를 이용해서 해당 회원의 관리자 조회
	String selectAdminSerialNumByMember(String memberSerialNum);
	
	// 고유번호로 실종자정보 조회(날짜 변환)
	MissingPersonDTO selectMissingPersonBySerialNum(String serialNum);
	// 날짜변환 없음
	MissingPersonDTO selectMissingById(String serialNum);
	
	// 전체 실종자 정보 개수 조회 위한 메소드(페이징 계산용)
	int selectMissingCount();
	
	// 실종자 목록 조회(페이징 처리)
	List<MissingPersonDTO> selectMissingList(Map<String, Object> params);
	// 실종자 목록 조회(페이징 없음) *메인화면 등에 사용
	List<MissingPersonDTO> selectMissingListMain();

}