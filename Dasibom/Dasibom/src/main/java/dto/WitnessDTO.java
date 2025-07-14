package dto;

public class WitnessDTO {

	private String id;
	private String date;
	private String place;
	private String gender;
	private String age;
	private String etc;
	private String image;

	public WitnessDTO(String id, String date, String place, String gender, String age, String etc, String image) {
		super();
		this.id = id;
		this.date = date;
		this.place = place;
		this.gender = gender;
		this.age = age;
		this.etc = etc;
		this.image = image;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
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

	@Override
	public String toString() {
		return "WitnessDTO [id=" + id + ", date=" + date + ", place=" + place + ", gender=" + gender + ", age=" + age
				+ ", etc=" + etc + ", image=" + image + "]";
	}
}