-- 관리자 정보 테이블
CREATE TABLE admin_info (
    admin_serialNum    CHAR(10)        NOT NULL,    -- 관리자 고유 번호
    admin_id           CHAR(128)      NOT NULL,    -- 관리자 로그인 ID
    admin_pw           CHAR(128)      NOT NULL,    -- 관리자 비밀번호
    admin_name         CHAR(128)      NOT NULL,    -- 관리자 이름
    admin_phone        CHAR(128)      NOT NULL,    -- 관리자 전화번호
    admin_email        CHAR(128)      NULL         -- 관리자 이메일
);

-- 관리자 기본키 및 유니크 제약
ALTER TABLE admin_info ADD CONSTRAINT PK_admin_info PRIMARY KEY (admin_serialNum);
ALTER TABLE admin_info ADD CONSTRAINT UK_admin_info_id UNIQUE (admin_id);

-- 회원 정보 테이블
CREATE TABLE member_info (
    member_serialNum   CHAR(10)      NOT NULL,    -- 회원 고유 번호
    member_id          CHAR(128)   NOT NULL,    -- 회원 로그인 ID
    admin_serialNum    CHAR(10)        NOT NULL,    -- 소속 관리자 번호
    member_pw          CHAR(128)   NOT NULL,    -- 회원 비밀번호
    member_email       CHAR(128)   NULL,        -- 이메일
    member_name        CHAR(128)   NOT NULL,    -- 이름
    member_phone       CHAR(128)         NOT NULL     -- 전화번호
);

-- 회원 기본키 및 유니크 제약
ALTER TABLE member_info ADD CONSTRAINT PK_member_info PRIMARY KEY (member_serialNum);
ALTER TABLE member_info ADD CONSTRAINT UK_member_info_id UNIQUE (member_id);

-- 관리자 -> 회원 외래키
ALTER TABLE member_info ADD CONSTRAINT FK_admin_to_member FOREIGN KEY (admin_serialNum)
REFERENCES admin_info (admin_serialNum);

-- 공지사항 테이블
CREATE TABLE notice (
    num                NUMBER          NOT NULL,    -- 공지사항 고유 번호
    admin_serialNum    CHAR(10)         NOT NULL,    -- 작성 관리자 번호
    title              VARCHAR2(50)    NOT NULL,    -- 제목
    content            CLOB            NOT NULL,    -- 본문 내용
    views              NUMBER DEFAULT 0 NOT NULL,   -- 조회수
    write_date         DATE DEFAULT SYSDATE NOT NULL,  -- 작성일
    write_update_date  DATE DEFAULT SYSDATE NOT NULL   -- 수정일
);

-- 공지사항 기본키
ALTER TABLE notice ADD CONSTRAINT PK_notice PRIMARY KEY (num);

-- 관리자 -> 공지사항 외래키
ALTER TABLE notice ADD CONSTRAINT FK_admin_to_notice FOREIGN KEY (admin_serialNum)
REFERENCES admin_info (admin_serialNum);

-- 법률 테이블
CREATE TABLE law (
    num                NUMBER          NOT NULL,    -- 법률 고유 번호
    admin_serialNum    CHAR(10)         NOT NULL,    -- 작성 관리자 번호
    title              VARCHAR2(50)    NOT NULL,    -- 제목
    content            CLOB            NOT NULL,    -- 본문 내용
    views              NUMBER DEFAULT 0 NOT NULL,   -- 조회수
    write_date         DATE DEFAULT SYSDATE NOT NULL,  -- 작성일
    write_update_date  DATE DEFAULT SYSDATE NOT NULL   -- 수정일
);

-- 법률 기본키
ALTER TABLE law ADD CONSTRAINT PK_law PRIMARY KEY (num);

-- 실종자 정보 테이블
CREATE TABLE missing_info (
    missing_serialNum  CHAR(10)       NOT NULL,    -- 실종자 고유 번호
    member_serialNum   CHAR(10)       NOT NULL,    -- 작성 회원 번호
    admin_serialNum    CHAR(10)         NOT NULL,    -- 관리자 번호
    missing_name       VARCHAR2(10)    NOT NULL,    -- 실종자 이름
    missing_gender     CHAR(1)         NOT NULL,    -- 성별
    missing_birth      NUMBER          NOT NULL,    -- 생년월일
    missing_etc        VARCHAR2(200)   NULL,        -- 기타 정보
    missing_place      VARCHAR2(100)   NULL,        -- 실종 위치
    missing_date       DATE            NOT NULL,    -- 실종 날짜
    missing_img        VARCHAR2(100)            NULL         -- 이미지
);

-- 실종자 기본키
ALTER TABLE missing_info ADD CONSTRAINT PK_missing_info PRIMARY KEY (missing_serialNum);

-- 회원 -> 실종자 외래키
ALTER TABLE missing_info ADD CONSTRAINT FK_member_to_missing FOREIGN KEY (member_serialNum)
REFERENCES member_info (member_serialNum);

-- 관리자 -> 실종자 외래키
ALTER TABLE missing_info ADD CONSTRAINT FK_admin_to_missing FOREIGN KEY (admin_serialNum)
REFERENCES admin_info (admin_serialNum);

-- 목격자 정보 테이블
CREATE TABLE witness_info (
    witness_serialNum  CHAR(10)       NOT NULL PRIMARY KEY, -- 목격자 고유 번호
    member_serialNum   CHAR(10)       NOT NULL,    -- 작성 회원
    admin_serialNum    CHAR(5)         NOT NULL,    -- 관리자 번호
    witness_date       DATE            NOT NULL,    -- 목격 날짜
    witness_place      VARCHAR2(100)   NOT NULL,    -- 목격 장소
    witness_gender     CHAR(1)         NOT NULL,    -- 추정 성별
    witness_age        NUMBER          NULL,        -- 추정 나이
    witness_etc        VARCHAR2(200)   NULL,        -- 기타 설명
    witness_img        VARCHAR2(100)           NULL,        -- 이미지
    missing_serialNum  CHAR(10)       NULL         -- 연관된 실종자 번호 (nullable)
);

-- 회원 -> 목격자 외래키
ALTER TABLE witness_info ADD CONSTRAINT FK_member_to_witness FOREIGN KEY (member_serialNum)
REFERENCES member_info (member_serialNum);

-- 관리자 -> 목격자 외래키
ALTER TABLE witness_info ADD CONSTRAINT FK_admin_to_witness FOREIGN KEY (admin_serialNum)
REFERENCES admin_info (admin_serialNum);

-- 실종자 -> 목격자 외래키
ALTER TABLE witness_info ADD CONSTRAINT FK_missing_to_witness FOREIGN KEY (missing_serialNum)
REFERENCES missing_info (missing_serialNum);

-- 관리자 시퀀스 생성
CREATE SEQUENCE SEQ_ADMIN_serialNum
START WITH 10000001
INCREMENT BY 1
NOCACHE
NOCYCLE;

-- 회원 시퀀스 생성
CREATE SEQUENCE SEQ_MEMBER_serialNum
START WITH 10000001
INCREMENT BY 1
NOCACHE
NOCYCLE;

-- 공지사항 시퀀스 생성
CREATE SEQUENCE SEQ_notice_num
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

-- 법률 시퀀스 생성
CREATE SEQUENCE SEQ_law_num
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

-- 실종자 시퀀스 생성
CREATE SEQUENCE SEQ_Missing_serialNum
START WITH 10000001
INCREMENT BY 1
NOCACHE
NOCYCLE;

-- 목격자 시퀀스 생성
CREATE SEQUENCE SEQ_Witness_serialNum
START WITH 10000001
INCREMENT BY 1
NOCACHE
NOCYCLE;

-- 관리자 Insert 시 serialnum 자동
CREATE OR REPLACE TRIGGER TRG_ADMIN_SERIAL
BEFORE INSERT ON admin_info
FOR EACH ROW
BEGIN
  :NEW.admin_serialNum := 'AA' || TO_CHAR(SEQ_ADMIN_serialNum.NEXTVAL);
END;
/

-- 회원 Insert 시 serialnum 자동
CREATE OR REPLACE TRIGGER TRG_MEMBER_SERIAL
BEFORE INSERT ON member_info
FOR EACH ROW
BEGIN
  :NEW.admin_serialNum := 'MM' || TO_CHAR(SEQ_MEMBER_serialNum.NEXTVAL);
END;
/

-- 실종자 Insert 시 serialnum 자동
CREATE OR REPLACE TRIGGER TRG_MISSING_SERIAL
BEFORE INSERT ON missing_info
FOR EACH ROW
BEGIN
  :NEW.admin_serialNum := 'MP' || TO_CHAR(SEQ_MISSING_serialNum.NEXTVAL);
END;
/

-- 목격자 Insert 시 serialnum 자동
CREATE OR REPLACE TRIGGER TRG_WITNESS_SERIAL
BEFORE INSERT ON WITNESS_info
FOR EACH ROW
BEGIN
  :NEW.admin_serialNum := 'WP' || TO_CHAR(SEQ_WITNESS_serialNum.NEXTVAL);
END;
/

-- 테이블 삭제
drop TABLE admin_info CASCADE CONSTRAINTS;
drop TABLE member_info CASCADE CONSTRAINTS;
drop TABLE missing_info CASCADE CONSTRAINTS;
drop TABLE witness_info;
drop TABLE notice;
drop TABLE law;