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
		if(instance == null)
			instance = new MemberService();
		return instance;
	}
	
	public int insertMember(MemberDTO dto) {
		return mapper.insertMember(dto);
	}

	public MemberDTO memberLogin(Map<String, Object> map) {
		return mapper.memberLogin(map);
	}
}
