package mapper;

import java.util.List;

import dto.WitnessDTO;

public interface WitnessMapper {
	List<WitnessDTO> selectWitnessList();

	int insertWitness(WitnessDTO dto);

	WitnessDTO selectWitnessById(String id);

}
