package mapper;

import java.util.Map;

import dto.MemberDTO;

public interface MemberMapper {

	int insertMember(MemberDTO dto);

	MemberDTO memberLogin(Map<String, Object> map);

}
