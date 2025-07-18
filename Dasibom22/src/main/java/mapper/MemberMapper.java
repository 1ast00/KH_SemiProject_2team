package mapper;

import java.util.Map;

import dto.MemberDTO;

public interface MemberMapper {

	MemberDTO memberLogin(Map<String, Object> map);

    Integer selectById(String memberId);

    int insertMember(MemberDTO dto);

    String findId(MemberDTO dto);

    int countByIdEmail(MemberDTO dto);

    int updatePassword(MemberDTO dto);

	int updateMemberInfo(MemberDTO member);
}