<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>로그인 페이지</title>
  
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/member_login.css">
</head>
<body>
  <div class="container">
    <div class="logo">
      <img src="${pageContext.request.contextPath}/resource/img/logo.png" alt="로고">
      <h2>다시, 봄<br><span>Dasi, Bom</span></h2>
    </div>

    
    <div id="error-msg" class="error-msg" style="display:none;">
      아이디 또는 비밀번호가 올바르지 않습니다.
    </div>

    <form action="${pageContext.request.contextPath}/memberLogin.do" method="post" class="login-box">
      <input type="text" id="member_id" name="member_id" placeholder="아이디" required />
      
      <input type="password" id="member_pw" name="member_pw" placeholder="비밀번호" required />

     
      <label style="display:block; font-size:0.85em; margin:5px 0 0;">
        <input type="checkbox" id="togglePassword" style="vertical-align:middle;"/> 비밀번호 표시
      </label>

      
      <div id="caps-warning" style="display:none; color:red; font-size:0.85em; margin:2px 0 10px;">
        Caps Lock이 켜져 있습니다.
      </div>

      <input type="submit" value="로그인" class="login-btn" />

      
      <div style="text-align: center; margin-top: 10px;">
        <a href="${pageContext.request.contextPath}/memberFindId.do" style="font-size: 0.85em; color: #FF69B4; text-decoration: underline;">아이디 찾기</a>
        |
        <a href="${pageContext.request.contextPath}/memberFindPw.do" style="font-size: 0.85em; color: #FF69B4; text-decoration: underline;">비밀번호 찾기</a>
      </div>

      
      <div style="text-align: center; margin-top: 10px;">
        <a href="${pageContext.request.contextPath}/memberRegisterView.do" style="font-size: 0.85em; color: #FF69B4; text-decoration: underline;">
          회원이 아니신가요? <strong>회원가입</strong>
        </a>
      </div>

      
      <div style="text-align: center; margin-top: 20px;">
        <a href="${pageContext.request.contextPath}/socialLogin.do?type=kakao">
          <button type="button" class="social-btn kakao-btn">
            <img src="${pageContext.request.contextPath}/resource/img/kakao.png" alt="카카오 아이콘" class="social-icon"/>
            카카오 로그인
          </button>
        </a>
        <br/>
        <a href="${pageContext.request.contextPath}/naverLogin.do">
          <button type="button" class="social-btn naver-btn">
            <img src="${pageContext.request.contextPath}/resource/img/naver.png" alt="네이버 아이콘" class="social-icon"/>
            네이버 로그인
          </button>
        </a>
      </div>
    </form>
  </div>
  
  <script src="${pageContext.request.contextPath}/resource/js/script.js" defer></script>
</body>
</html>