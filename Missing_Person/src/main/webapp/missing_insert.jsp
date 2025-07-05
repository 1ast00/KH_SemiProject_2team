<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>실종자 접수 페이지</title>
    <style>
    </style>
  </head>
  <body>
    <header>
      <h1 id="formTitle">실종자 접수</h1>
    </header>
    <main>
      <form id="missingPersonForm" action="${pageContext.request.contextPath}/submitMissingPerson" method="post" enctype="multipart/form-data">
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
            maxlength="6"
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

        <input type="hidden" id="missingPlaceText" name="missingPlace"
               value="${param.place != null ? param.place : ''}" />

        <div class="form_group">
          <label for="missingDate">실종 날짜:</label>
          <input type="date" id="missingDate" name="missingDate" />
        </div>
        <button type="submit">접수</button>
        <button type="button" onclick="history.back()">취소</button>
      </form>

      <hr>

      <form action="${pageContext.request.contextPath}/mapsearch" method="get" id="locationSearchForm">
        <div class="form_group">
            <label for="placeInput">실종 추정 위치: </label>
            <input type="text" id="placeInput" name="place" required
                   value="${param.place != null ? param.place : ''}" />
            <button type="submit">위치 검색</button>
        </div>
      </form>

      <nav>
        <a href="홈 URL">홈</a> <a href="마이페이지 URL">마이페이지</a>
        <button id="logoutBtn">로그아웃</button>
      </nav>
    </main>

    <script>
    // 실종자 위치 정보 값을 txt로 넘겨버리기
    document.addEventListener('DOMContentLoaded', function() {
    	
        const missingPersonForm = document.getElementById('missingPersonForm');

        missingPersonForm.addEventListener('submit', function(event) {
        	
            const placeInputValue = document.getElementById('placeInput').value;
            document.getElementById('missingPlaceText').value = placeInputValue;
        });
    });
    </script>

  </body>
</html>