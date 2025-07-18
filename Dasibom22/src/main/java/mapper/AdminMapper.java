package mapper;

import java.util.Map;

import dto.AdminDTO;

public interface AdminMapper {

	int insertAdmin(AdminDTO dto);

	AdminDTO adminLogin(Map<String, Object> map);

}
