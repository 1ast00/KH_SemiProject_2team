package mapper;

import java.util.Map;
import dto.MemberDTO;

public interface MemberMapper {

    
    MemberDTO memberLogin(Map<String, Object> map);

    
    MemberDTO selectById(String memberId);

    
    int insertMember(MemberDTO dto);
}
