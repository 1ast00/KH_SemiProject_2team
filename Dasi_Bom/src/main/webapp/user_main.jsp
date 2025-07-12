<!-- 로그인 이후 페이지 -->
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="dto.MemberDTO" %>
<%
    MemberDTO member = (MemberDTO) session.getAttribute("member");
    if (member == null) {
        response.sendRedirect("main.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>다시 봄 - 회원 메인</title>
</head>
<body>
    <h2><%= member.getMember_name() %>님 환영합니다.</h2>

    <div>
        <h3>실종정보</h3>
        <button onclick="location.href='missing_register.jsp'">실종자 제보(insert)</button>
        <button onclick="location.href='missing_list.jsp'">실종자 리스트 조회</button>
    </div>

    <div>
        <h3>목격정보</h3>
        <button onclick="location.href='witness_register.jsp'">목격자 제보(insert)</button>
        <button onclick="location.href='witness_list.jsp'">목격정보 목록 조회</button>
    </div>

    <div>
        <h3>공지사항</h3>
        <button onclick="location.href='notice_list.jsp'">공지사항</button>
        <button onclick="location.href='law.jsp'">법률 정보</button>
    </div>

    <div>
        <h3>마이페이지</h3>
        <button onclick="location.href='logout.jsp'">로그아웃</button>
        <button onclick="location.href='mypage.jsp'">마이페이지</button>
    </div>

    <form action="MissingSearchServlet" method="get">
        <input type="text" name="name" placeholder="이름" />
        <input type="text" name="age" placeholder="나이" />
        <input type="text" name="gender" placeholder="성별" />
        <input type="text" name="status" placeholder="상태" />
        <input type="text" name="location" placeholder="실종 위치" />
        <button type="submit">검색</button>
    </form>
</body>
</html>