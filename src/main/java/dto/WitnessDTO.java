package dto;

import java.sql.Date;

public class WitnessDTO {
	private String witnessSerialNum;
	private Date date;
	private String place;
	private String gender;
	private Integer age;
	private String etc;
	private String image;
	
	// 외래키
	private String memberSerialNum;
	private String missingSerialNum;

	public WitnessDTO() {
	}

	public WitnessDTO(String witnessSerialNum, Date date, String place, String gender, Integer age, String etc,
			String image, String memberSerialNum, String missingSerialNum) {
		this.witnessSerialNum = witnessSerialNum;
		this.date = date;
		this.place = place;
		this.gender = gender;
		this.age = age;
		this.etc = etc;
		this.image = image;
		this.memberSerialNum = memberSerialNum;
		this.missingSerialNum = missingSerialNum;
	}

	public String getWitnessSerialNum() {
		return witnessSerialNum;
	}

	public void setWitnessSerialNum(String witnessSerialNum) {
		this.witnessSerialNum = witnessSerialNum;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
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

	public String getMissingSerialNum() {
		return missingSerialNum;
	}

	public void setMissingSerialNum(String missingSerialNum) {
		this.missingSerialNum = missingSerialNum;
	}

	@Override
	public String toString() {
		return "WitnessDTO [witnessSerialNum=" + witnessSerialNum + ", date=" + date + ", place=" + place + ", gender="
				+ gender + ", age=" + age + ", etc=" + etc + ", image=" + image + ", memberSerialNum=" + memberSerialNum
				+ ", missingSerialNum=" + missingSerialNum + "]";
	}
}