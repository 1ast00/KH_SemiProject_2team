package mapper;

import java.util.List;
import dto.MissingPersonDTO;

public interface MissingMapper {

    List<MissingPersonDTO> selectMissingList();

    int insertMissingPerson(MissingPersonDTO person);
    
    String selectAdminSerialNumByMember(String memberSerialNum);

    // ++추가(삭제)
    int deleteMissingPerson(String missingSerialNum);
}
