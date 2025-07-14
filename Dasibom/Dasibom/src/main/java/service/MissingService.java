package service;

import java.util.List;

import config.DBManager;
import dto.MissingPersonDTO;
import mapper.MissingMapper;

public class MissingService {

	private static MissingService instance = new MissingService();
	private MissingMapper mapper;
	
	private MissingService() {
		mapper = DBManager.getInstance().getSession().getMapper(MissingMapper.class);
	}
	
	public static MissingService getInstance() {
		if(instance == null)
			instance = new MissingService();
		return instance;
	}

	public List<MissingPersonDTO> selectMissingList() {
		return mapper.selectMissingList();
	}

	public int insertMissingPerson(MissingPersonDTO person) {
		return mapper.insertMissingPerson(person);
	}

	// ++추가 : 실종자 정보 삭제 서비스 메서드
	public int deleteMissingPerson(String missingSerialNum) {
		return mapper.deleteMissingPerson(missingSerialNum);
	}

	// ++추가 : 권한 검사를 위한 특정 실종자 정보 조회 서비스 메서드
	public MissingPersonDTO selectMissingPersonBySerial(String missingSerialNum) {
		return mapper.selectMissingPersonBySerial(missingSerialNum);
	}
}
