package service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import config.DBManager;
import dto.MemberDTO;
import mapper.MemberMapper;

public class MemberService {

    private static MemberService instance = new MemberService();

    private MemberService() {}

    public static MemberService getInstance() {
        return instance;
    }

  
    public int insertMember(MemberDTO dto) {
        SqlSession session = DBManager.getInstance().getSession();
        MemberMapper mapper = session.getMapper(MemberMapper.class);

        int result = mapper.insertMember(dto);
        session.commit();
        session.close();

        return result;
    }

    
    public MemberDTO memberLogin(Map<String, Object> map) {
        SqlSession session = DBManager.getInstance().getSession();
        MemberMapper mapper = session.getMapper(MemberMapper.class);

        MemberDTO result = mapper.memberLogin(map);   
        session.close();        

        return result;
    }

    
    public boolean isMemberIdDuplicate(String memberId) {
        SqlSession session = DBManager.getInstance().getSession();
        MemberMapper mapper = session.getMapper(MemberMapper.class);

        MemberDTO dto = mapper.selectById(memberId);  
        session.close();

        return dto != null;
    }
}

