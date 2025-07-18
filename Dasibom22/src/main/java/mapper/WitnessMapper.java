package mapper;

import java.util.List;
import java.util.Map;

import dto.WitnessDTO;

public interface WitnessMapper {
	List<WitnessDTO> selectWitnessList();

	int insertWitness(WitnessDTO dto);

	WitnessDTO selectWitnessById(String id);
	
	int getTotalCount();
	List<WitnessDTO> getPagedWitnessList(Map<String, Integer> param);

	int deleteWitness(String id);
}
