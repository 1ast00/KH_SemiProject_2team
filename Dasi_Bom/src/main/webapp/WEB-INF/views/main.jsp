<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/css/main.css">
</head>
<body>
<body>
	<jsp:include page="./template/header.jsp"></jsp:include>
    <section class="search-section">
        <div class="search-input-container">
            <input type="text" id="mainSearchInput" placeholder="이름/나이/성별/기타정보 검색">
            <button id="searchButton"><img src="search_icon.png" alt="검색"></button>
            
            <div id="detailedSearchInputs" class="hidden-search-details">
                <input type="text" id="searchName" placeholder="이름">
                <input type="text" id="searchAge" placeholder="나이">
                <input type="text" id="searchGender" placeholder="성별">
                <input type="text" id="searchOther" placeholder="기타 정보">
                <button id="detailedSearchBtn">상세 검색</button>
            </div>
        </div>
    </section>
    <main class="main-content-wrapper">
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
                        <td><a href="/notice/1">공지사항 1</a></td>
                        <td>2020-01-01</td>
                        <td>30</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td><a href="/notice/2">공지사항 2</a></td>
                        <td>2020-01-02</td>
                        <td>31</td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td><a href="/notice/3">공지사항 3</a></td>
                        <td>2020-01-03</td>
                        <td>32</td>
                    </tr>
                    <tr>
                        <td>4</td>
                        <td><a href="/notice/4">공지사항 4</a></td>
                        <td>2020-01-04</td>
                        <td>33</td>
                    </tr>
                    <tr>
                        <td>5</td>
                        <td><a href="/notice/5">공지사항 5</a></td>
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
                        <img src="person1.jpg" alt="김철수">
                        <div class="person-info">
                            <p>이름 : 김갑순</p>
                            <p>나이 : 71(실종당시)</p>
                            <p>실종장소 : OO구</p>
                            <p>특징 : 치매</p>
                        </div>
                    </a>
                </div>
                <div class="missing-person-card">
                    <a href="/missing-person/detail/2">
                        <img src="person2.jpg" alt="어린이">
                        <div class="person-info">
                            <p>이름 : 어린이</p>
                            <p>나이 : 7(실종당시)</p>
                            <p>실종장소 : XX동</p>
                            <p>특징 : 없음</p>
                        </div>
                    </a>
                </div>
                <div class="missing-person-card">
                    <a href="/missing-person/detail/3">
                        <img src="person3.jpg" alt="이순신">
                        <div class="person-info">
                            <p>이름 : 이순신</p>
                            <p>나이 : 27(실종당시)</p>
                            <p>실종장소 : GG동</p>
                            <p>특징 : 외향적</p>
                        </div>
                    </a>
                </div>
                <div class="missing-person-card">
                    <a href="/missing-person/detail/4">
                        <img src="person4.jpg" alt="박영희">
                        <div class="person-info">
                            <p>이름 : 박영희</p>
                            <p>나이 : 16(실종당시)</p>
                            <p>실종장소 : YY시</p>
                            <p>특징 : 키 160cm</p>
                        </div>
                    </a>
                </div>
                <div class="missing-person-card">
                    <a href="/missing-person/detail/5">
                        <img src="person5.jpg" alt="최지우">
                        <div class="person-info">
                            <p>이름 : 최지우</p>
                            <p>나이 : 45(실종당시)</p>
                            <p>실종장소 : JJ군</p>
                            <p>특징 : 검정색 코트</p>
                        </div>
                    </a>
                </div>
            </div>
        </section>
    </main>
    <footer>
        <p>&copy; 2025 다시, 봄. All rights reserved.</p>
    </footer>
    <script src="script.js"></script>
</body>
</html>