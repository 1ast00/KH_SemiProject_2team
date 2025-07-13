package mapper;

import java.util.List;

import dto.MissingPersonDTO;

public interface MissingMapper {

	List<MissingPersonDTO> selectMissingList();

}
