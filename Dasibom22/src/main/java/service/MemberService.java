package service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import config.DBManager;
import dto.MemberDTO;
import mapper.MemberMapper;

public class MemberService {

	private static MemberService instance = new MemberService();

	private MemberService() {
	}

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

		int count = mapper.selectById(memberId);
		session.close();

		return count >= 1;
	}

	public String findId(MemberDTO dto) {
		SqlSession session = DBManager.getInstance().getSession();
		MemberMapper mapper = session.getMapper(MemberMapper.class);

		String foundId = mapper.findId(dto);

		session.close();
		return foundId;
	}

	public boolean verifyForPw(MemberDTO dto) {
		SqlSession session = DBManager.getInstance().getSession();
		MemberMapper mapper = session.getMapper(MemberMapper.class);

		int count = mapper.countByIdEmail(dto);

		session.close();
		return count == 1;
	}

	public int resetPassword(MemberDTO dto) {
		SqlSession session = DBManager.getInstance().getSession();
		MemberMapper mapper = session.getMapper(MemberMapper.class);

		int result = mapper.updatePassword(dto);

		session.commit();
		session.close();
		return result;
	}

	public boolean updateMember(MemberDTO member) {
		SqlSession session = DBManager.getInstance().getSession();
		MemberMapper mapper = session.getMapper(MemberMapper.class);

		try {
			int result = mapper.updateMemberInfo(member); // int 반환

			if (result > 0) {
				session.commit();
				return true;
			} else {
				session.rollback();
				return false;
			}
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}
}