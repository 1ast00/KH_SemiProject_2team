<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>실종자 접수 페이지</title>
    <style>
      body {
        display: flex;
        flex-direction: column;
        align-items: center;
      }

      header {
        background-color: #fca5c1;
        width: 100%;
        text-align: center;
        color: white;
      }

      main {
        padding: 20px;
        margin: 20px;
        border-radius: 10px;
        width: 90%;
        max-width: 600px;
      }

      .form_group {
        margin-bottom: 15px;
      }

      label {
        display: block;
        margin-bottom: 5px;
        font-weight: bold;
        color: #555;
      }

      input[type="text"],
      input[type="date"],
      select,
      textarea {
        width: calc(100% - 22px);
        padding: 10px;
        border: 1px solid #fca5c1;
        border-radius: 5px;
        box-sizing: border-box;
        margin-top: 5px;
        margin-bottom: 5px;
      }

      textarea {
        resize: vertical;
      }

      input[type="file"] {
        border: none;
        padding: 0;
      }

      form[action="${pageContext.request.contextPath}/mapsearch"] {
        display: flex;
        align-items: center;
        margin-bottom: 15px;
      }

      form[action="${pageContext.request.contextPath}/mapsearch"]
        input[type="text"] {
        flex-grow: 1;
        margin-right: 10px;
      }

      button {
        background-color: #87ceeb;
        color: white;
        padding: 10px 15px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 15px;
        margin-right: 10px;
        transition: background-color 0.3s ease;
      }

      button:hover {
        background-color: #6aade4;
      }

      button[type="submit"] {
        background-color: #fca5c1;
      }

      button[type="submit"]:hover {
        background-color: pink;
      }

      button[onclick="history.back()"] {
        background-color: #cccccc;
      }

      button[onclick="history.back()"]:hover {
        background-color: #aaaaaa;
      }

      nav {
        margin-top: 20px;
        display: flex;
        justify-content: center;
        gap: 15px; /* Space between nav items */
      }

      nav a {
        color: #87ceeb;
        text-decoration: none;
        font-weight: bold;
      }

      nav a:hover {
        text-decoration: underline;
      }

      #logoutBtn {
        background-color: #cccccc;
        color: white;
        border: none;
        border-radius: 4px;
        padding: 10px 15px;
        cursor: pointer;
        font-size: 14px;
      }

      #logoutBtn:hover {
        background-color: #aaaaaa;
      }
    </style>
  </head>
  <body>
    <header>
      <h1 id="formTitle">실종자 접수</h1>
    </header>
    <main>
   <!-- <form action="실종자 정보 접수 처리 할 페이지(실종자 리스트) URL" method="post" enctype="multipart/form-data"> -->
      <div class="form_group">
        <label for="missingName">이름:</label>
        <input type="text" id="missingName" name="missingName" required />
      </div>
      <div class="form_group">
        <label for="missingGender">성별:</label>
        <select id="missingGender" name="missingGender" required>
          <option value="">선택</option>
          <option value="남">남</option>
          <option value="여">여</option>
        </select>
      </div>
      <div class="form_group">
        <label for="missingBirth">생년월일:</label>
        <input
          type="text"
          id="missingBirth"
          name="missingBirth"
          placeholder="주민번호 앞자리 6자리 입력"
          required
        />
      </div>
      <div class="form_group">
        <label for="missingEtc">기타 특징:</label>
        <textarea id="missingEtc" name="missingEtc" rows="3"></textarea>
      </div>
      <div class="form_group">
        <label for="missingImg">이미지 업로드:</label>
        <input type="file" id="missingImg" name="missingImg" accept="image/*" />
      </div>
      <form action="${pageContext.request.contextPath}/mapsearch" method="get">
        <label>실종 추정 위치: </label>
        <input type="text" name="place" required />
        <button type="submit">위치 검색</button>
      </form>
      <div class="form_group">
        <label for="missingDate">실종 날짜:</label>
        <input type="date" id="missingDate" name="missingDate" />
      </div>
      <button type="submit">접수</button>
      <button type="button" onclick="history.back()">취소</button>
     <!-- </form> -->
      <nav>
        <a href="index.html">홈</a> <a href="mypage.html">마이페이지</a>
        <button id="logoutBtn">로그아웃</button>
      </nav>
    </main>
  </body>
</html>
