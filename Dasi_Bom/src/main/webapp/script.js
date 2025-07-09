document.addEventListener('DOMContentLoaded', function() {
    // 1. 검색어 칸 클릭 시 4개 입력칸으로 바뀌는 기능
    const mainSearchInput = document.getElementById('mainSearchInput');
    const searchButton = document.getElementById('searchButton');
    const detailedSearchInputs = document.getElementById('detailedSearchInputs');
    const detailedSearchBtn = document.getElementById('detailedSearchBtn');

    // 메인 검색창 클릭 시 상세 검색창 표시
    mainSearchInput.addEventListener('focus', function() {
        detailedSearchInputs.style.display = 'flex'; // 상세 검색 입력칸 표시
    });

    // 상세 검색 버튼 클릭 시 (실제로는 백엔드와 통신하여 검색)
    detailedSearchBtn.addEventListener('click', function() {
        const name = document.getElementById('searchName').value;
        const age = document.getElementById('searchAge').value;
        const gender = document.getElementById('searchGender').value;
        const other = document.getElementById('searchOther').value;

        const queryParams = new URLSearchParams();
        if (name) queryParams.append('name', name);
        if (age) queryParams.append('age', age);
        if (gender) queryParams.append('gender', gender);
        if (other) queryParams.append('other', other);

        window.location.href = `/missing/search-results?${queryParams.toString()}`;
    });

    // 2. 외부 클릭 시 상세 검색창 다시 숨기기
    document.addEventListener('click', function(event) {
        const searchContainer = document.querySelector('.search-input-container');
        if (!searchContainer.contains(event.target) && detailedSearchInputs.style.display === 'flex') {
            detailedSearchInputs.style.display = 'none';
        }
    });

    // 3. 드롭다운 메뉴 기능
    // CSS :hover로 드롭다운이 작동하므로, JavaScript는 기본 동작을 방지하는 역할만 합니다.
    // 만약 모바일에서 클릭 시 드롭다운을 토글하고 싶다면 추가적인 로직이 필요합니다.
    const dropdownToggles = document.querySelectorAll('.has-dropdown > a');
    dropdownToggles.forEach(toggle => {
        toggle.addEventListener('click', function(event) {
            // 데스크톱 환경에서는 CSS :hover로 처리하므로 기본 링크 이동을 허용합니다.
            // 그러나 드롭다운을 가진 최상위 메뉴 항목 자체는 링크가 아니거나, 클릭 시 드롭다운을 토글해야 할 경우 preventDefault를 사용합니다.
            // 현재 요구사항은 "커서를 갖다댔을 때 드롭다운이 나와야 함" 이므로, 기본적으로 클릭 시 페이지 이동을 허용합니다.
            // 다만, # (해시) 링크인 경우에만 기본 동작을 방지하여 페이지 상단으로 스크롤되는 것을 막습니다.
            if (this.getAttribute('href') === '#') {
                event.preventDefault();
            }
        });
    });

    // 메인 페이지 로드 시 실종자 리스트를 동적으로 추가할 수 있도록 예시 데이터 확장
    // 이 부분은 백엔드 연동 시 제거되거나 수정됩니다.
    const missingCardsContainer = document.querySelector('.missing-cards-container');
    const additionalMissingPersons = [
        { name: "김민준", age: "12(실종당시)", location: "용산구", feature: "안경 착용", img: "person4.jpg", id: 4 },
        { name: "이지아", age: "65(실종당시)", location: "강남구", feature: "지팡이 사용", img: "person5.jpg", id: 5 },
        { name: "박서준", age: "28(실종당시)", location: "서대문구", feature: "왼쪽 팔 문신", img: "person6.jpg", id: 6 },
        { name: "최유리", age: "3(실종당시)", location: "송파구", feature: "빨간색 신발", img: "person7.jpg", id: 7 },
        { name: "정우성", age: "80(실종당시)", location: "종로구", feature: "회색 점퍼", img: "person8.jpg", id: 8 }
    ];

    // 기존 3개 카드 외에 추가 데이터만 렌더링하도록 수정 (총 8개 표시)
    // 실제 환경에서는 백엔드에서 모든 데이터를 받아 한 번에 렌더링합니다.
    // 여기서는 예시를 위해 기존 HTML에 있는 3개와 추가 데이터를 합쳐 렌더링합니다.
    if (missingCardsContainer) {
        // 기존 3개는 HTML에 있으므로, 추가 데이터만 동적으로 추가
        additionalMissingPersons.forEach(person => {
            const card = document.createElement('div');
            card.classList.add('missing-person-card');
            card.innerHTML = `
                <a href="/missing-person/detail/${person.id}">
                    <img src="${person.img}" alt="${person.name}">
                    <div class="person-info">
                        <p>이름 : ${person.name}</p>
                        <p>나이 : ${person.age}</p>
                        <p>실종장소 : ${person.location}</p>
                        <p>특징 : ${person.feature}</p>
                    </div>
                </a>
            `;
            missingCardsContainer.appendChild(card);
        });
    }
});