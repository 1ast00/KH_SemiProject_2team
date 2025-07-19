package service;

import java.util.List;
import java.util.Map; // Map import 추가
import config.DBManager;
import dto.MissingPersonDTO;
import mapper.MissingMapper;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;


// 컨트롤러와 매퍼사이의 다리 역할(로직 처리)
// MissingMapper인터페이스와 missing-mapper.xml 파일을 연결해서 SQL쿼리를 실행하게끔
public class MissingService {

    private static MissingService instance = new MissingService();
    private MissingMapper mapper;

    private MissingService() {
    	// DBManger를 통해 Mybatis세션을 얻고, MissingMapper인터페이스 가져옴
        mapper = DBManager.getInstance().getSession().getMapper(MissingMapper.class);
    }

    // 싱글톤 객체 반환
    public static MissingService getInstance() {
        if(instance == null)
            instance = new MissingService();
        return instance;
    }

    // 새로운 실종자 정보 등록을 매퍼에 요청
    public int insertMissingPerson(MissingPersonDTO person) {
        return mapper.insertMissingPerson(person);
    }
    // 매퍼에 삭제 요청
    public int deleteMissingPerson(String missingSerialNum) {
        return mapper.deleteMissingPerson(missingSerialNum);
    }
    // 실종자 정보 수정을 매퍼에 요청
    public int updateMissingPerson(MissingPersonDTO person) {
    	return mapper.updateMissingPerson(person); 
    }
    
    // 회원번호로 관리자 번호 조회를 매퍼에 요청
    public String getAdminSerialNumByMember(String memberSerialNum) {
        return mapper.selectAdminSerialNumByMember(memberSerialNum);
    }

    // 두 날짜사이의 만 나이 계산 로직(생년월일, 실종날짜)
    public String calculateAge(String birthDateStr, String standardDateStr) {
    	
    	// 날짜정보 없으면 계산 불가
        if (birthDateStr == null || standardDateStr == null) {
            return "계산 불가";
        }
        
        try {
        	// 문자열을 실제 날짜 객체로 변환
            LocalDate birthDate = LocalDate.parse(birthDateStr);
            LocalDate standardDate = LocalDate.parse(standardDateStr);
            // 두 날짜 사이 기간 계산해서 '년' 단위로 반환
            return String.valueOf(Period.between(birthDate, standardDate).getYears());
            
        } catch (DateTimeParseException e) {
            System.out.println("나이 계산 중 날짜 포맷 오류 발생: " + e.getMessage());
            return "계산 불가";
        }
    }
    
    // 날짜 변환 X -> 원본 그대로의 데이터(계산할 때)
    public MissingPersonDTO selectMissingById(String serialNum) {
    	return mapper.selectMissingById(serialNum);
    }
    // 날짜 변환(YYYY-MM-DD) -> 조회된 데이터를 View페이지에서 사용자에게 바로 보여줄 때 이쁘게
    public MissingPersonDTO getMissingPersonBySerialNum(String serialNum) {
    	System.out.println("MissingService - getMissingPersonBySerialNum(serialNum): " + serialNum); // 디버깅용
    	return mapper.selectMissingPersonBySerialNum(serialNum);
    }
    
    // 페이징 처리된 실종자 목록 조회를 매퍼에 요청
    public List<MissingPersonDTO> selectMissingList(Map<String, Object> params) {
        return mapper.selectMissingList(params);
    }
    
    // 전체 실종자 정보 개수 조회를 매퍼에 요청
    public int getMissingCount() {
        return mapper.selectMissingCount();
    }
    
    // 메인화면용 전체 실종자 목록 조회 매퍼에 요청 -> 메인페이지에 실종자목록 띄울 때 사용할것임, 최신 N건 정도만 보여주기, selectMissingList 메서드 활용할 것
    public List<MissingPersonDTO> selectMissingListMain() {
        return mapper.selectMissingListMain();
    }
    
}