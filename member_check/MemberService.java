package service;

import java.util.Map;

import config.DBManager;
import dto.MemberDTO;
import mapper.MemberMapper;

public class MemberService {

	private static MemberService instance = new MemberService();
	private MemberMapper mapper;

	private MemberService() {
		mapper = DBManager.getInstance().getSession().getMapper(MemberMapper.class);
	}

	public static MemberService getInstance() {
		if (instance == null)
			instance = new MemberService();
		return instance;
	}

	public int insertMember(MemberDTO dto) {
		return mapper.insertMember(dto);
	}

	public MemberDTO memberLogin(Map<String, Object> map) {
		return mapper.memberLogin(map);
	}

	public int selectById(String memberId) {
		// 받아오는지 확인
		System.out.println("selectById: " + memberId);
		
	    // 파라미터 검증
	    if (memberId == null || memberId.trim().isEmpty()) {
	        System.out.println("memberId가 null 또는 빈 문자열입니다.");
	        return 0;
	    }
	    
	    try {
	        int ck = mapper.selectById(memberId);
	        System.out.println("값 확인: " + ck);
	        System.out.println("전달된 ID: " + memberId);
	    
	        if(ck == 1)  
	        	return 1;
	        else
	        	return 0;
	        
	    } catch (Exception e) {
	        System.out.println("DB 조회 중 오류 발생: " + e.getMessage());
	        return 0;
	    }
	}
}
