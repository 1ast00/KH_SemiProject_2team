package dto;

import java.sql.Date; // java.util.Date 대신 java.sql.Date를 사용하거나, util.Date를 사용하고 매퍼에서 포맷팅

public class NoticeDTO {
    private int num;
    private String adminSerialNum; // ADMIN_SERIALNUM
    private String title;
    private String content;
    private int views;
    private Date writeDate; // WRITE_DATE
    private Date writeUpdateDate; //WRITE_UPDATE_DATE

    // 기본 생성자
    public NoticeDTO() {}

    // 모든 필드를 포함하는 생성자
    public NoticeDTO(int num, String adminSerialNum, String title, String content, int views, Date writeDate, Date writeUpdateDate) {
        this.num = num;
        this.adminSerialNum = adminSerialNum;
        this.title = title;
        this.content = content;
        this.views = views;
        this.writeDate = writeDate;
        this.writeUpdateDate = writeUpdateDate;
    }


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getAdminSerialNum() {
        return adminSerialNum;
    }

    public void setAdminSerialNum(String adminSerialNum) {
        this.adminSerialNum = adminSerialNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public Date getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(Date writeDate) {
        this.writeDate = writeDate;
    }

    public Date getWriteUpdateDate() {
        return writeUpdateDate;
    }

    public void setWriteUpdateDate(Date writeUpdateDate) {
        this.writeUpdateDate = writeUpdateDate;
    }

    @Override
    public String toString() {
        return "NoticeDTO [num=" + num + ", adminSerialNum=" + adminSerialNum + ", title=" + title + ", content=" + content
                + ", views=" + views + ", writeDate=" + writeDate + ", writeUpdateDate=" + writeUpdateDate + "]"; // <-- toString도 수정!
    }
}
