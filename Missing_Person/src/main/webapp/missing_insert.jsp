<%@ page contentType="text/html;charset=UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
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
        <c:set var="name" value="${param.name}" /> 
        <c:set var="gender" value="${param.gender}" /> 
        <c:set var="birth" value="${param.birth}" /> 
        <c:set var="etc" value="${param.etc}" /> 
        <c:set var="date" value="${param.date}" /> 
        <c:set var="place" value="${param.place}" /> 

        <form id="missingPersonForm" action="${pageContext.request.contextPath}/submitMissingPerson" method="post" enctype="multipart/form-data">
            <div class="form_group"> 
                <label for="nameInput">이름:</label> 
                <input type="text" id="nameInput" name="name" required value="${name}" /> 
            </div>
            <div class="form_group"> 
                <label for="genderSelect">성별:</label> 
                <select id="genderSelect" name="gender" required> 
                    <option value="">선택</option> 
                    <option value="남" ${gender == '남' ? 'selected' : ''}>남</option> 
                    <option value="여" ${gender == '여' ? 'selected' : ''}>여</option> 
                </select>
            </div>
            <div class="form_group">
                <label for="birthInput">생년월일:</label> 
                <input type="date" id="birthInput" name="birth" required value="${birth}" /> 
            </div>
            <div class="form_group"> 
                <label for="dateInput">실종 날짜:</label> 
                <input type="date" id="dateInput" name="date" value="${date}" /> 
            </div>
            <div class="form_group"> 
                <label for="etcTextarea">기타 특징:</label> 
                <textarea id="etcTextarea" name="etc" rows="3">${etc}</textarea>
            </div>
            <div class="form_group"> 
                <label for="imgUpload">이미지 업로드:</label> 
                <input type="file" id="imgUpload" name="img" accept="image/*" /> 
            </div>
            <%-- 이 숨겨진 필드는 자바스크립트에 의해 '실종 추정 위치' 값이 채워져서 메인 폼과 함께 제출 --%>
            <input type="hidden" id="placeText" name="place" value="${place}" />
        </form>

        <form action="${pageContext.request.contextPath}/mapsearch" method="get" id="locationSearchForm">
            <div class="form_group">
                <label for="placeInput">실종 추정 위치:</label> 
                <input type="text" id="placeInput" name="place" required value="${place}" /> 
                <button type="submit">위치 검색</button> 
            </div>
            <input type="hidden" name="name" />
            <input type="hidden" name="gender" />
            <input type="hidden" name="birth" />
            <input type="hidden" name="etc" />
            <input type="hidden" name="date" />
        </form>

        <div class="button-group"> 
            <button type="submit" form="missingPersonForm" class="submit-button">접수</button>
            <button type="button" onclick="location.href='${pageContext.request.contextPath}/missing_insert.jsp'" class="cancel-button">취소</button>
        </div>
    </main>
    <script> 
        document.addEventListener('DOMContentLoaded', function() { // 윈도우온로드같은거
            const missingPersonForm = document.getElementById('missingPersonForm'); // id로 메인 폼 요소를 찾아서 변수에 저장합니다.
            const locationSearchForm = document.getElementById('locationSearchForm'); // id로 위치 검색 폼 요소를 찾아서 변수에 저장합니다.

            // 위치 검색 폼(locationSearchForm)이 제출될 때 실행될 함수를 등록합니다.
            locationSearchForm.addEventListener('submit', function(event) {
                // 메인 폼에 입력된 값들을 위치 검색 폼의 숨겨진 필드들로 복사합니다.
                // 이렇게 하면 위치를 검색해도 기존에 입력했던 정보가 사라지지 않습니다.
                locationSearchForm.name.value = missingPersonForm.name.value;
                locationSearchForm.gender.value = missingPersonForm.gender.value;
                locationSearchForm.birth.value = missingPersonForm.birth.value;
                locationSearchForm.etc.value = missingPersonForm.etc.value;
                locationSearchForm.date.value = missingPersonForm.date.value;
            });

            // 메인 폼(missingPersonForm)이 '접수'될 때 실행될 함수를 등록합니다.
            missingPersonForm.addEventListener('submit', function(event) {
                // '실종 추정 위치' 입력 필드(placeInput)의 현재 값을 가져옵니다.
                const placeInputValue = document.getElementById('placeInput').value;
                // 가져온 위치 값을 메인 폼 안의 숨겨진 필드(placeText)에 넣어줍니다.
                document.getElementById('placeText').value = placeInputValue;
            });
        });
    </script>
</body>
</html>