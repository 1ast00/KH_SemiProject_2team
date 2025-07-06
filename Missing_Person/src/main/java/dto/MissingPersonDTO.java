package dto;

public class MissingPersonDTO {

	private int id;
    private String name;
    private String gender;
    private String birth;
    private String missingDate;
    private String place;
    private String etc;
    private String image;
    
    public MissingPersonDTO() {}
    
	public MissingPersonDTO(int id, String name, String gender, String birth, String missingDate, String place, String etc, String image) {
		
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.birth = birth;
		this.missingDate = missingDate;
		this.place = place;
		this.etc = etc;
		this.image = image;
	}
	
	public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getBirth() { return birth; }
    public void setBirth(String birth) { this.birth = birth; }

    public String getMissingDate() { return missingDate; }
    public void setMissingDate(String missingDate) { this.missingDate = missingDate; }

    public String getPlace() { return place; }
    public void setPlace(String place) { this.place = place; }

    public String getEtc() { return etc; }
    public void setEtc(String etc) { this.etc = etc; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    
}
