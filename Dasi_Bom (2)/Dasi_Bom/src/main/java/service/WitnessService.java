package service;

import java.util.List;

import config.DBManager;
import dto.WitnessDTO;
import mapper.WitnessMapper;

public class WitnessService {
	
	private static WitnessService instance = new  WitnessService();
	private WitnessMapper mapper;
	
	private WitnessService() {
		mapper = DBManager.getInstance().getSession().getMapper(WitnessMapper.class);
	}
	
	public static WitnessService getInstance() {
		if(instance == null)
			instance = new WitnessService();
		return instance;
	}
	
	public List<WitnessDTO> selectWitnessList() {
		return mapper.selectWitnessList();
	}

}
