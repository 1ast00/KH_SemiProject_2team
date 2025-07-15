package mapper;

import java.util.List;
import dto.MissingPersonDTO;

public interface MissingMapper {

	List<MissingPersonDTO> selectMissingList();

	// DB에 데이터 삽입 메서드(삭제 메서드도 만들 예정), missing-mapper.xml에 작성된 SQL을 호출
	int insertMissingPerson(MissingPersonDTO person);
}