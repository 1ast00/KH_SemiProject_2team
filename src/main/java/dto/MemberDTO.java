package dto;

import java.sql.Date;

public class MemberDTO {
    private String member_serialNum;   // 회원 고유번호
    private String member_id;          // 아이디
    private String member_pw;          // 비밀번호
    private String member_name;        // 이름
    private String member_email;       // 이메일 (nullable)
    private String member_phone;       // 전화번호
    private String admin_serialNum;    // 소속 관리자 번호
    private Date member_birth;        
    private String member_gender;  

    // 기본 생성자
    public MemberDTO() {}

    public MemberDTO(String member_serialNum, String member_id, String member_pw, String member_name,
			String member_email, String member_phone, String admin_serialNum, Date member_birth, String member_gender) {
		super();
		this.member_serialNum = member_serialNum;
		this.member_id = member_id;
		this.member_pw = member_pw;
		this.member_name = member_name;
		this.member_email = member_email;
		this.member_phone = member_phone;
		this.admin_serialNum = admin_serialNum;
		this.member_birth = member_birth;
		this.member_gender = member_gender;
	}

	// Getter & Setter
    public String getMember_serialNum() {
        return member_serialNum;
    }

    public void setMember_serialNum(String member_serialNum) {
        this.member_serialNum = member_serialNum;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getMember_pw() {
        return member_pw;
    }

    public void setMember_pw(String member_pw) {
        this.member_pw = member_pw;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getMember_email() {
        return member_email;
    }

    public void setMember_email(String member_email) {
        this.member_email = member_email;
    }

    public String getMember_phone() {
        return member_phone;
    }

    public void setMember_phone(String member_phone) {
        this.member_phone = member_phone;
    }

    public String getAdmin_serialNum() {
        return admin_serialNum;
    }

    public void setAdmin_serialNum(String admin_serialNum) {
        this.admin_serialNum = admin_serialNum;
    }

	public Date getMember_birth() {
		return member_birth;
	}

	public void setMember_birth(Date member_birth) {
		this.member_birth = member_birth;
	}

	public String getMember_gender() {
		return member_gender;
	}

	public void setMember_gender(String member_gender) {
		this.member_gender = member_gender;
	}
}
