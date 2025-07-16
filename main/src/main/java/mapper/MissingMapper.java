package mapper;

import java.util.List;

import dto.MissingPersonDTO;

public interface MissingMapper {

	List<MissingPersonDTO> selectMissingList();

	int insertMissingPerson(MissingPersonDTO person);

	String selectAdminSerialNumByMember(String memberSerialNum);

	int deleteMissingPerson(String missingSerialNum);
}
