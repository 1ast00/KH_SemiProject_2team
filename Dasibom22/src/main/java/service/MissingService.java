package service;

import java.util.List;
import java.util.Map; // Map import 추가
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

    // 페이징 파라미터를 받는 메소드로 변경
    public List<MissingPersonDTO> selectMissingList(Map<String, Object> params) {
        return mapper.selectMissingList(params);
    }
    
    // 전체 카운트를 위한 서비스 메소드 추가
    public int getMissingCount() {
        return mapper.selectMissingCount();
    }

    // 추가
    public int insertMissingPerson(MissingPersonDTO person) {
        return mapper.insertMissingPerson(person);
    }
    
    // 관리자 정보
    public String getAdminSerialNumByMember(String memberSerialNum) {
        return mapper.selectAdminSerialNumByMember(memberSerialNum);
    }

    // 나이 계산 로직 
    public String calculateAge(String birthDateStr, String standardDateStr) {
    	System.out.println("나이 계산");
        if (birthDateStr == null || standardDateStr == null) {
        	System.out.println("null - 계산 실패");
            return "계산 불가";
        }
        try {
            LocalDate birthDate = LocalDate.parse(birthDateStr);
            LocalDate standardDate = LocalDate.parse(standardDateStr);
            return String.valueOf(Period.between(birthDate, standardDate).getYears());
        } catch (DateTimeParseException e) {
            System.out.println("나이 계산 중 날짜 포맷 오류 발생: " + e.getMessage());
            return "계산 불가";
        }
    }
    
    // 삭제
    public int deleteMissingPerson(String missingSerialNum) {
        return mapper.deleteMissingPerson(missingSerialNum);
    }

    // main 실종자 리스트
    public List<MissingPersonDTO> selectMissingListMain() {
        return mapper.selectMissingListMain();
    }
    
    // 실종자 식별자
    public MissingPersonDTO getMissingPersonBySerialNum(String serialNum) {
    	System.out.println("MissingService - getMissingPersonBySerialNum(serialNum): " + serialNum);
		return mapper.selectMissingPersonBySerialNum(serialNum);
	}
    
    // 실종자 식별자
    public MissingPersonDTO selectMissingById(String serialNum) {
        return mapper.selectMissingById(serialNum);
    }
    
    // 수정
    public int updateMissingPerson(MissingPersonDTO person) {
		return mapper.updateMissingPerson(person); 
	}
}