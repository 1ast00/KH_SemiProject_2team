package dto;

public class MissingPersonDTO {

	private String missingSerialNum;
	private String name;
	private String gender;
	private String birth;
	private String missingDate;
	private String place;
	private String etc;
	private String image;
	
	// 외래키
	private String memberSerialNum;
	private String adminSerialNum;

	public MissingPersonDTO() {
	}

	public MissingPersonDTO(String missingSerialNum, String name, String gender, String birth, String missingDate, String place,
			String etc, String image, String memberSerialNum, String adminSerialNum) {
		super();
		this.missingSerialNum = missingSerialNum;
		this.name = name;
		this.gender = gender;
		this.birth = birth;
		this.missingDate = missingDate;
		this.place = place;
		this.etc = etc;
		this.image = image;
		this.memberSerialNum = memberSerialNum;
		this.adminSerialNum = adminSerialNum;
	}

	public String getMissingSerialNum() {
		return missingSerialNum;
	}

	public void setMissingSerialNum(String missingSerialNum) {
		this.missingSerialNum = missingSerialNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getMissingDate() {
		return missingDate;
	}

	public void setMissingDate(String missingDate) {
		this.missingDate = missingDate;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getMemberSerialNum() {
		return memberSerialNum;
	}

	public void setMemberSerialNum(String memberSerialNum) {
		this.memberSerialNum = memberSerialNum;
	}

	public String getAdminSerialNum() {
		return adminSerialNum;
	}

	public void setAdminSerialNum(String adminSerialNum) {
		this.adminSerialNum = adminSerialNum;
	}

	@Override
	public String toString() {
		return "MissingPersonDTO [missingSerialNum=" + missingSerialNum + ", name=" + name + ", gender=" + gender + ", birth=" + birth
				+ ", missingDate=" + missingDate + ", place=" + place + ", etc=" + etc + ", image=" + image
				+ ", memberSerialNum=" + memberSerialNum + ", adminSerialNum=" + adminSerialNum + "]";
	}
	
}