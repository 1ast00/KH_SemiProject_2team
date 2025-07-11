package dto;

public class MissingPersonDTO {

    private String serialNum; 
    private String memberSerialNum;
    private String adminSerialNum;
    private String name;
    private String gender;
    private String birth;
    private String etc;
    private String place;
    private String date;
    private byte[] img;

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
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

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
}