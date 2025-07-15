document.addEventListener('DOMContentLoaded', function() {
    // 1. 검색어 칸 클릭 시 상세 입력칸으로 바뀌는 기능 (수정됨)
    const mainSearchInput = document.getElementById('mainSearchInput');
    const searchButton = document.getElementById('searchButton'); // 돋보기 버튼
    const detailedSearchInputs = document.getElementById('detailedSearchInputs'); // 상세 검색 필드 컨테이너
    const detailedSearchBtn = document.getElementById('detailedSearchBtn'); // 상세 검색 버튼
    const searchInputContainer = document.querySelector('.search-input-container'); // 전체 검색 바 컨테이너

    // 메인 검색창 클릭(focus) 시 상세 검색창 표시 및 디자인 변경
    mainSearchInput.addEventListener('focus', function() {
        // 상세 검색 입력칸 표시
        detailedSearchInputs.style.display = 'flex'; // CSS에서 none으로 숨겨져 있던 것을 flex로 보이게 함

        // 메인 검색창 자체는 숨기거나, 아니면 상세 검색 필드가 메인 검색창을 대체하도록 디자인을 조정해야 합니다.
        // 스크린샷과 같은 통합된 바 형태를 만들려면, mainSearchInput은 실질적으로 사라지고
        // detailedSearchInputs가 그 자리를 차지하는 것이 더 자연스럽습니다.
        // 여기서는 mainSearchInput을 완전히 숨기고 detailedSearchInputs가 나타나도록 처리합니다.
        mainSearchInput.style.display = 'none';
        searchButton.style.display = 'none'; // 돋보기 버튼도 숨김

        // 상세 검색 필드가 나타날 때 .search-input-container의 디자인을 유지하거나 변경
        // (CSS에서 detailedSearchInputs에 테두리 등을 적용했으므로, 여기서는 특별히 searchInputContainer를 건드리지 않아도 됨)
    });

    // 상세 검색창 외부 클릭 시 다시 숨기기
    // 중요한 점: mainSearchInput 클릭 이벤트와 충돌하지 않도록 조정합니다.
    document.addEventListener('click', function(event) {
        // 클릭된 요소가 searchInputContainer 안에 포함되지 않거나,
        // mainSearchInput 자체가 아닌 경우 (mainSearchInput을 클릭하면 focus 이벤트가 처리하므로)
        // 그리고 detailedSearchInputs가 현재 보이는 상태일 때만 숨김
        if (!searchInputContainer.contains(event.target) && event.target !== mainSearchInput && detailedSearchInputs.style.display === 'flex') {
            detailedSearchInputs.style.display = 'none';
            mainSearchInput.style.display = 'block'; // 메인 검색창 다시 표시
            searchButton.style.display = 'block'; // 돋보기 버튼 다시 표시
            mainSearchInput.value = ''; // 메인 검색창 내용 초기화 (선택 사항)
            mainSearchInput.focus(); // 메인 검색창에 포커스 주기
        }
    });


    // 2. 상세 검색 버튼 클릭 시 (실제로는 백엔드와 통신하여 검색)
    detailedSearchBtn.addEventListener('click', function() {
        const name = document.getElementById('searchName').value;
        const age = document.getElementById('searchAge').value;
        const gender = document.getElementById('searchGender').value;
        const other = document.getElementById('searchOther').value;
        // 추가: 실종 위치 필드도 포함 (HTML에 searchLocation ID를 추가했다는 가정하에)
        const location = document.getElementById('searchLocation') ? document.getElementById('searchLocation').value : '';


        const queryParams = new URLSearchParams();
        if (name) queryParams.append('name', name);
        if (age) queryParams.append('age', age);
        if (gender) queryParams.append('gender', gender);
        if (other) queryParams.append('other', other);
        if (location) queryParams.append('location', location); // 위치 필드 추가

        window.location.href = `/missing/search-results?${queryParams.toString()}`;
    });


    // 3. 드롭다운 메뉴 기능
    // 이 부분은 기존과 동일하게 유지됩니다. CSS :hover로 드롭다운이 작동하므로, JavaScript는 기본 동작을 방지하는 역할만 합니다.
    const dropdownToggles = document.querySelectorAll('.has-dropdown > a');
    dropdownToggles.forEach(toggle => {
        toggle.addEventListener('click', function(event) {
            if (this.getAttribute('href') === '#') {
                event.preventDefault();
            }
        });
    });

    // 4. 메인 페이지 로드 시 실종자 리스트를 동적으로 추가 (기존과 동일하게 유지)
    const missingCardsContainer = document.querySelector('.missing-cards-container');
    const additionalMissingPersons = [
        { name: "김민준", age: "12(실종당시)", location: "용산구", feature: "안경 착용", img: "person4.jpg", id: 4 },
        { name: "이지아", age: "65(실종당시)", location: "강남구", feature: "지팡이 사용", img: "person5.jpg", id: 5 },
        { name: "박서준", age: "28(실종당시)", location: "서대문구", feature: "왼쪽 팔 문신", img: "person6.jpg", id: 6 },
        { name: "최유리", age: "3(실종당시)", location: "송파구", feature: "빨간색 신발", img: "person7.jpg", id: 7 },
        { name: "정우성", age: "80(실종당시)", location: "종로구", feature: "회색 점퍼", img: "person8.jpg", id: 8 }
    ];

    if (missingCardsContainer) {
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