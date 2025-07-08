<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${place} 지도</title>
    <script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1953e4a69f69ba521e119d6eca3efa47&libraries=services"></script>
    <script>
        window.onload = () => {
            // JSP에서 서블릿이 전달한 위도/경도 값을 가져와서 숫자로 변환합니다.
            const lat = parseFloat("${lat}");
            const lng = parseFloat("${lng}");

            // 지도를 표시할 div 요소를 가져옵니다.
            const mapContainer = document.getElementById('map'); 
            
            // mapContainer가 존재하는지 확인하는 디버그 코드 추가
            if (!mapContainer) {
                console.error("Error: Map container div with ID 'map' not found!");
                return; // div가 없으면 지도를 그릴 수 없으므로 함수 종료
            }

            // 지도 옵션을 설정합니다. (중심 좌표, 확대 레벨)
            const mapOption = {
                center: new kakao.maps.LatLng(lat, lng),
                level: 6 // 지도의 확대 레벨 (숫자가 작을수록 확대)
            };

            // 지도를 생성합니다.
            const map = new kakao.maps.Map(mapContainer, mapOption);

            // 원 (Circle) 오버레이를 생성하고 지도에 표시합니다.
            const circle = new kakao.maps.Circle({
                center: new kakao.maps.LatLng(lat, lng), // 중심 좌표
                radius: 300, // 원의 반경 (m 단위)
                strokeWeight: 3, // 선 두께
                strokeColor: '#00aaff', // 선 색상
                fillColor: '#aaffff', // 채우기 색상
                fillOpacity: 0.5 // 채우기 불투명도
            });
            circle.setMap(map); // 지도에 원 표시

            // 마커 (Marker) 오버레이를 생성하고 지도에 표시합니다.
            const marker = new kakao.maps.Marker({
                position: new kakao.maps.LatLng(lat, lng) // 마커의 위치
            });
            marker.setMap(map); // 지도에 마커 표시
        };
    </script>

    <style>
        /* 기본 여백 및 패딩 제거 */
        * {
            margin : 0;
            padding : 0;
            box-sizing: border-box; /* 패딩과 보더가 너비/높이에 포함되도록 설정 */
        }
        
        body {
            width : 80%; /* body 너비 조정 */
            margin : 50px auto; /* 상하 50px, 좌우 중앙 정렬 */
            border : 1px solid #eee; /* 테두리 색상 연하게 */
            padding: 20px; /* body 내부에 여백 추가 */
        }
        
        h2 {
            margin-bottom: 20px;
            color: #333;
        }

        p {
            margin-bottom: 10px;
            color: #555;
        }

        form {
            margin-top: 30px;
            display: flex; /* 폼 요소들을 한 줄에 정렬 */
            gap: 10px; /* 폼 요소들 사이 간격 */
        }

        form input[type="text"] {
            flex-grow: 1; /* 입력 필드가 가능한 공간을 채우도록 */
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        form button {
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        form button:hover {
            background-color: #0056b3;
        }

        /* 지도가 표시될 영역 */
        #map {
            width: 80%; /* body 대비 너비 조정 */
            height: 500px; /* 지도 높이 고정 (중요!) */
            margin : 50px auto 0; /* 위 50px, 좌우 중앙, 아래 0 */
            border: 1px solid #ccc; /* 지도 테두리 */
            border-radius: 8px; /* 모서리 둥글게 */
            background-color: #f0f0f0; /* 로딩 중 배경색 */
        }
    </style>

</head>
<body>

<h2>목격자 제보</h2>

<p>추정 나이 : 5세</p>
<p>성별 : 남</p>
<p>목격 장소 : 어디역 근처 </p>
<p>목격 날짜 : 2024-10-08 </p>

<form method="get" action="${pageContext.request.contextPath}/map">
    <input type="text" name="keyword" placeholder="장소를 입력하세요" required>
    <button type="submit">전송</button>
</form>

<div id="map"></div> </body>
</html>