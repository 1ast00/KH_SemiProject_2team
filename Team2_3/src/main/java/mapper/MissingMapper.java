package mapper;

import java.util.List;
import java.util.Map; // Map import 추가
import dto.MissingPersonDTO;

public interface MissingMapper {

	// 파라미터로 Map을 받도록 변경
	List<MissingPersonDTO> selectMissingList(Map<String, Object> params);

	int selectMissingCount(); // 전체 카운트를 위한 메소드 추가(페이징)

	int insertMissingPerson(MissingPersonDTO person);

	String selectAdminSerialNumByMember(String memberSerialNum);

	int deleteMissingPerson(String missingSerialNum);

	List<MissingPersonDTO> selectMissingListMain();
	
	// 조회 
	MissingPersonDTO selectMissingPersonBySerialNum(String serialNum);

	MissingPersonDTO selectMissingById(String serialNum);
	

    // 검색 조건과 페이징 파라미터를 함께 받는 메소드 추가
    List<MissingPersonDTO> searchMissingList(Map<String, Object> params);

    // 검색 결과의 전체 카운트를 위한 메소드 추가
    int searchMissingCount(Map<String, Object> params);


}