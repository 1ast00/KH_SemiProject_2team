package mapper;

import java.util.List;

import dto.MissingPersonDTO;

public interface MissingMapper {

	List<MissingPersonDTO> selectMissingList();

	int insertMissingPerson(MissingPersonDTO person);

	// 실종자 정보를 삭제하는 메서드
	int deleteMissingPerson(String missingSerialNum);

	// 권한 검사를 위해 특정 실종자 정보를 조회하는 메서드
	MissingPersonDTO selectMissingPersonBySerial(String missingSerialNum);
}
