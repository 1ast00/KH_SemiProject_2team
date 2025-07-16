<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>다시, 봄 - 메인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/main.css">
</head>
<body>
    <jsp:include page="./template/header.jsp" /> <section class="search-section">
        <div class="search-input-container" id="mainSearchContainer">
            <input type="text" id="mainSearchInput" placeholder="이름/나이/성별/실종장소를 입력해주세요">
            <button id="searchButton"><img src="${pageContext.request.contextPath}/resource/img/search_icon.png" alt="검색"></button>
        </div>

        <div id="detailedSearchInputs" class="detailed-search-container">
            <input type="text" id="searchName" placeholder="이름">
            <input type="text" id="searchAge" placeholder="나이">
            <input type="text" id="searchGender" placeholder="성별">
            <input type="text" id="searchLocation" placeholder="실종 위치">
            <input type="text" id="searchOther" placeholder="기타">
            <button id="detailedSearchBtn"><img src="${pageContext.request.contextPath}/resource/img/search_icon.png" alt="상세 검색"></button>
        </div>
    </section>

    <div class="main-content-wrapper">
        <section class="notice-section">
            <h2>공지사항</h2>
            <table>
                <thead>
                    <tr>
                        <th>글번호</th>
                        <th>제목</th>
                        <th>작성일</th>
                        <th>조회수</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td><a href="#">공지사항 1</a></td>
                        <td>2020-01-01</td>
                        <td>30</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td><a href="#">공지사항 2</a></td>
                        <td>2020-01-02</td>
                        <td>31</td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td><a href="#">공지사항 3</a></td>
                        <td>2020-01-03</td>
                        <td>32</td>
                    </tr>
                    <tr>
                        <td>4</td>
                        <td><a href="#">공지사항 4</a></td>
                        <td>2020-01-04</td>
                        <td>33</td>
                    </tr>
                    <tr>
                        <td>5</td>
                        <td><a href="#">공지사항 5</a></td>
                        <td>2020-01-05</td>
                        <td>34</td>
                    </tr>
                </tbody>
            </table>
        </section>

        <section class="missing-list-section">
            <h2>실종자 리스트</h2>
            <div class="missing-cards-container">
                <div class="missing-person-card">
                    <a href="/missing-person/detail/1">
                        <img src="${pageContext.request.contextPath}/resource/img/missing_person1.jpg" alt="김철수">
                        <div class="person-info">
                            <p>이름 : 김철수</p>
                            <p>나이 : 70(실종당시)</p>
                            <p>실종장소 : OO구</p>
                            <p>특징 : 치매</p>
                        </div>
                    </a>
                </div>
                <div class="missing-person-card">
                    <a href="/missing-person/detail/2">
                        <img src="${pageContext.request.contextPath}/resource/img/missing_person1.jpg" alt="어린이">
                        <div class="person-info">
                            <p>이름 : 어린이</p>
                            <p>나이 : 7(실종당시)</p>
                            <p>실종장소 : xx동</p>
                            <p>특징 : 없음</p>
                        </div>
                    </a>
                </div>
                <div class="missing-person-card">
                    <a href="/missing-person/detail/3">
                        <img src="${pageContext.request.contextPath}/resource/img/missing_person1.jpg" alt="이순신">
                        <div class="person-info">
                            <p>이름 : 이순신</p>
                            <p>나이 : 27(실종당시)</p>
                            <p>실종장소 : GG동</p>
                            <p>특징 : 다리 불편</p>
                        </div>
                    </a>
                </div>
            </div>
        </section>
    </div>

    <footer>
        <p>&copy; 2025 다시, 봄. All rights reserved.</p>
    </footer>

    <script src="${pageContext.request.contextPath}/resource/js/script.js"></script>
</body>