package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import config.DBManager;
import dto.WitnessDTO;
import mapper.WitnessMapper;

public class WitnessService {

	private static WitnessService instance = new WitnessService();
	private WitnessMapper mapper;

	private WitnessService() {
		mapper = DBManager.getInstance().getSession().getMapper(WitnessMapper.class);
	}

	public static WitnessService getInstance() {
		if (instance == null)
			instance = new WitnessService();
		return instance;
	}

	// 전체 목록 조회
	public List<WitnessDTO> selectWitnessList() {
		return mapper.selectWitnessList();
	}

	// 등록
	public boolean insertWitness(WitnessDTO dto) {
		return mapper.insertWitness(dto) > 0;
	}

	// 단건 조회
	public WitnessDTO selectWitnessById(String id) {
		return mapper.selectWitnessById(id);
	}

	// 페이징 관련
	public int getTotalCount() {
		return mapper.getTotalCount();
	}

	public List<WitnessDTO> getPagedWitnessList(int start, int end) {
		Map<String, Integer> param = new HashMap<>();
		param.put("start", start);
		param.put("end", end); // XML과 일치해야 함
		return mapper.getPagedWitnessList(param);
	}

	// 삭제
	public boolean deleteWitness(String id) {
		return mapper.deleteWitness(id) > 0;
	}
}