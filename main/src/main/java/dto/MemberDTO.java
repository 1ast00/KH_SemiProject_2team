package dto;

public class MemberDTO {
    private String memberSerialNum;   // 회원 고유번호
    private String id;          // 아이디
    private String pw;          // 비밀번호
    private String name;        // 이름
    private String email;       // 이메일 (nullable)
    private String phone;       // 전화번호
    
    // 외래키
    private String adminSerialNum;    // 소속 관리자 번호

    // 기본 생성자
    public MemberDTO() {}

	public MemberDTO(String memberSerialNum, String id, String pw, String name, String email, String phone,
			String adminSerialNum) {
		super();
		this.memberSerialNum = memberSerialNum;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.adminSerialNum = adminSerialNum;
	}

	public String getMemberSerialNum() {
		return memberSerialNum;
	}

	public void setMemberSerialNum(String memberSerialNum) {
		this.memberSerialNum = memberSerialNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAdminSerialNum() {
		return adminSerialNum;
	}

	public void setAdminSerialNum(String adminSerialNum) {
		this.adminSerialNum = adminSerialNum;
	}
}
