package mapper;

import java.util.List;
import java.util.Map;

import dto.WitnessDTO;

public interface WitnessMapper {
	List<WitnessDTO> selectWitnessList();

	int insertWitness(WitnessDTO dto);

	WitnessDTO selectWitnessById(String id);

	
	// 그 페이지 이동하는 애들임
	int getTotalCount();
	List<WitnessDTO> getPagedWitnessList(Map<String, Integer> param);

	int deleteWitness(String id);

}
