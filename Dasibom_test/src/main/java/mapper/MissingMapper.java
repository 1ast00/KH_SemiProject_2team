package mapper;

import java.util.List;
import dto.MissingPersonDTO;

public interface MissingMapper {

    List<MissingPersonDTO> selectMissingList();

    int insertMissingPerson(MissingPersonDTO person);
    
    // 추가된 메소드
    String selectAdminSerialNumByMember(String memberSerialNum);
}
