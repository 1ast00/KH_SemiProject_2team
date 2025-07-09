<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>실종자 접수 페이지</title>
  <style>
  body {
    font-family: 'Noto Sans KR', sans-serif;
    background-color: #fff;
    margin: 0;
    padding: 0;
    text-align: center;
  }

  header {
    background-color: #fefefe;
    padding: 30px 0 10px;
  }

  #formTitle {
    font-size: 30px;
    font-weight: bold;
    margin: 0;
  }

  main {
    max-width: 700px;
    margin: 30px auto;
    padding: 20px;
    border-radius: 10px;
    text-align: left;
  }

  form {
    margin-bottom: 30px;
  }

  .form_group {
    margin-bottom: 20px;
  }

  .form_group label {
    display: block;
    font-weight: bold;
    margin-bottom: 8px;
    font-size: 15px;
  }

  .form_group input[type="text"],
  .form_group input[type="date"],
  .form_group input[type="file"],
  .form_group select,
  .form_group textarea {
    width: 100%;
    padding: 10px;
    font-size: 14px;
    border: 1px solid #ccc;
    border-radius: 6px;
    box-sizing: border-box;
  }

  .form_group textarea {
    resize: vertical;
  }

  hr {
    border: none;
    border-top: 1px solid #ddd;
    margin: 30px 0;
  }

  .button-group {
    text-align: center; 
    margin-top: 20px; 
  }

  .submit-button {
    background-color: #f472b6;
    color: white;
    font-weight: bold;
    padding: 10px 20px;
    margin: 0 5px; 
    border: none;
    border-radius: 6px;
    cursor: pointer;
  }

  .submit-button:hover {
    background-color: #FCA5C1;
  }
  
  .cancel-button {
   background-color: rgb(128, 128, 128);
    color: white;
    font-weight: bold;
    padding: 10px 20px;
    margin: 0 5px; 
    border: none;
    border-radius: 6px;
    cursor: pointer;
  }
  
  .cancel-button:hover {
    background-color: rgb(70, 66, 66);
  }

#locationSearchForm .form_group label {
    display: block; 
    margin-bottom: 8px; 
    font-weight: bold; 
    font-size: 15px; 
}

#locationSearchForm .form_group input[type="text"] {
    width: calc(100% - 120px); 
    display: inline-block; 
    vertical-align: middle; 
    margin-right: 10px; 
}

#locationSearchForm .form_group button[type="submit"] {
    display: inline-block; 
    vertical-align: middle; 
    background-color: #D8578D;
    color: white;
    font-weight: bold;
    padding: 10px 20px;
    border: none;
    border-radius: 6px;
    cursor: pointer;
}

#locationSearchForm .form_group button[type="submit"]:hover {
    background-color: #ea7ba9;
}

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
      </form>

      <form action="${pageContext.request.contextPath}/mapsearch" method="get" id="locationSearchForm">
        <div class="form_group">
            <label for="placeInput">실종 추정 위치:</label>
            <input type="text" id="placeInput" name="place" required
                   value="${param.place != null ? param.place : ''}" />
            <button type="submit">위치 검색</button>
        </div>
      </form>

      <div class="button-group">
        <button type="submit" form="missingPersonForm" class="submit-button">접수</button>
        <button type="button" onclick="location.href='missing_insert.jsp'" class="cancel-button">취소</button>
      </div>

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