package service;

import java.util.List;
import config.DBManager;
import dto.MissingPersonDTO;
import mapper.MissingMapper;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;


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
    
    // 추가된 메소드
    public String getAdminSerialNumByMember(String memberSerialNum) {
        return mapper.selectAdminSerialNumByMember(memberSerialNum);
    }

    // 나이 계산 로직 
    public String calculateAge(String birthDateStr, String standardDateStr) {
        if (birthDateStr == null || standardDateStr == null) {
            return "계산 불가";
        }
        try {
            LocalDate birthDate = LocalDate.parse(birthDateStr);
            LocalDate standardDate = LocalDate.parse(standardDateStr);
            return String.valueOf(Period.between(birthDate, standardDate).getYears());
        } catch (DateTimeParseException e) {
            System.err.println("나이 계산 중 날짜 포맷 오류 발생: " + e.getMessage());
            return "계산 불가";
        }
    }
    
    // ++추가(삭제)
    public int deleteMissingPerson(String missingSerialNum) {
        return mapper.deleteMissingPerson(missingSerialNum);
    }

}
