package mapper;

import dto.MemberDTO;

public interface MemberMapper {

    MemberDTO memberLogin(MemberDTO dto);

    Integer selectById(String memberId);

    int insertMember(MemberDTO dto);

    
    String findId(MemberDTO dto);

   
    int countByIdEmail(MemberDTO dto);

    
    int updatePassword(MemberDTO dto);
}
