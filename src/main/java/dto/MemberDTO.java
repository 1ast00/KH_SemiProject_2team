package dto;

public class MemberDTO {
	private String member_serialNum;
	private String member_id;
	private String admin_serialNum;
	private String member_pw;
	private String member_email;
	private String member_name;
	private String member_phone;

	public MemberDTO() {
	}

	public MemberDTO(String member_serialNum, String member_id, String admin_serialNum, String member_pw,
			String member_email, String member_name, String member_phone) {
		super();
		this.member_serialNum = member_serialNum;
		this.member_id = member_id;
		this.admin_serialNum = admin_serialNum;
		this.member_pw = member_pw;
		this.member_email = member_email;
		this.member_name = member_name;
		this.member_phone = member_phone;
	}

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

	public String getAdmin_serialNum() {
		return admin_serialNum;
	}

	public void setAdmin_serialNum(String admin_serialNum) {
		this.admin_serialNum = admin_serialNum;
	}

	public String getMember_pw() {
		return member_pw;
	}

	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}

	public String getMember_email() {
		return member_email;
	}

	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getMember_phone() {
		return member_phone;
	}

	public void setMember_phone(String member_phone) {
		this.member_phone = member_phone;
	}

}