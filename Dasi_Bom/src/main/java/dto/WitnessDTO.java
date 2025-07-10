package dto;

public class WitnessDTO {
	private String date;
	private String place;
	private String gender;
	private String age;
	private String etc;
	private String image;

	public WitnessDTO(String date, String place, String gender, String age, String etc, String image) {
		this.date = date;
		this.place = place;
		this.gender = gender;
		this.age = age;
		this.etc = etc;
		this.image = image;
	}
	
	// Getter/Setter 생략
}
