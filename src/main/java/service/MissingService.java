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
}