package dto;

public class AdminDTO {
    private String admin_serialNum;   // 관리자 고유 번호
    private String admin_id;          // 로그인 ID
    private String admin_pw;          // 비밀번호
    private String admin_name;        // 이름
    private String admin_phone;       // 전화번호
    private String admin_email;       // 이메일 (nullable)

    // 기본 생성자
    public AdminDTO() {}

    // 전체 필드 초기화 생성자
    public AdminDTO(String admin_serialNum, String admin_id, String admin_pw,
                    String admin_name, String admin_phone, String admin_email) {
        this.admin_serialNum = admin_serialNum;
        this.admin_id = admin_id;
        this.admin_pw = admin_pw;
        this.admin_name = admin_name;
        this.admin_phone = admin_phone;
        this.admin_email = admin_email;
    }

    // Getter & Setter
    public String getAdmin_serialNum() {
        return admin_serialNum;
    }

    public void setAdmin_serialNum(String admin_serialNum) {
        this.admin_serialNum = admin_serialNum;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_pw() {
        return admin_pw;
    }

    public void setAdmin_pw(String admin_pw) {
        this.admin_pw = admin_pw;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getAdmin_phone() {
        return admin_phone;
    }

    public void setAdmin_phone(String admin_phone) {
        this.admin_phone = admin_phone;
    }

    public String getAdmin_email() {
        return admin_email;
    }

    public void setAdmin_email(String admin_email) {
        this.admin_email = admin_email;
    }
}

