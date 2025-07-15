document.addEventListener('DOMContentLoaded', function() {
    const mainSearchInput = document.getElementById('mainSearchInput');
    const searchButton = document.getElementById('searchButton');
    const detailedSearchInputs = document.getElementById('detailedSearchInputs');
    const detailedSearchBtn = document.getElementById('detailedSearchBtn');
    const mainSearchContainer = document.getElementById('mainSearchContainer');

    // 초기 상태 설정: 상세 검색창은 숨김
    detailedSearchInputs.style.display = 'none';

    // mainSearchInput의 'focus' 이벤트를 'click' 이벤트로 변경 ((중요중요중요시발))
    mainSearchInput.addEventListener('click', function(event) {
        // 이 클릭 이벤트가 상위(document)로 전파되는 것을 즉시 막음
        event.stopPropagation();

        // 메인 검색창을 숨기고 상세 검색창을 표시
        mainSearchContainer.style.display = 'none'; // 메인 검색 바 컨테이너 숨김
        detailedSearchInputs.style.display = 'flex'; // 상세 검색 입력칸 표시 (flex로)

        // (선택 사항) 상세 검색창이 나타난 후 첫 번째 입력 필드에 바로 포커스 이동
        // 이렇게 하면 사용자가 바로 입력할 수 있어 편리합니다.
        document.getElementById('searchName').focus();
    });

    // 상세 검색창 외부 클릭 시 다시 숨기기
    document.addEventListener('click', function(event) {
        // 클릭된 요소가 mainSearchContainer나 detailedSearchInputs 안에 포함되지 않는 경우
        // 즉, 검색 영역(메인 또는 상세) 외부를 클릭했을 때만 숨김
        // mainSearchInput을 클릭하여 focus가 발생한 클릭은 이미 위에서 stopPropagation()으로 막았으므로,
        // 여기서는 외부 클릭만 처리
        if (!mainSearchContainer.contains(event.target) && !detailedSearchInputs.contains(event.target)) {
            // 상세 검색창이 현재 보이고 있다면 숨김
            if (detailedSearchInputs.style.display === 'flex') {
                detailedSearchInputs.style.display = 'none'; // 상세 검색 입력칸 숨김
                mainSearchContainer.style.display = 'flex'; // 메인 검색 바 컨테이너 다시 표시 (flex로)
                mainSearchInput.value = ''; // 메인 검색창 내용 초기화 (선택 사항)
            }
        }
    });

    // 상세 검색 입력 필드나 버튼 클릭 시에도 닫히지 않도록 이벤트 전파 막기
    detailedSearchInputs.addEventListener('click', function(event) {
        event.stopPropagation();
    });
    mainSearchContainer.addEventListener('click', function(event) {
        event.stopPropagation();
    });


    // 2. 상세 검색 버튼 클릭 시 (실제 백엔드와 통신해 검색)
    detailedSearchBtn.addEventListener('click', function() {
        const name = document.getElementById('searchName').value;
        const age = document.getElementById('searchAge').value;
        const gender = document.getElementById('searchGender').value; // 라디오 버튼 적용 안된 상태로 유지
        const location = document.getElementById('searchLocation').value;
        const other = document.getElementById('searchOther').value;

        const queryParams = new URLSearchParams();
        if (name) queryParams.append('name', name);
        if (age) queryParams.append('age', age);
        if (gender) queryParams.append('gender', gender);
        if (location) queryParams.append('location', location);
        if (other) queryParams.append('other', other);

        // 실제 검색을 수행하는 URL로 이동
        window.location.href = `/missing/search-results?${queryParams.toString()}`;
    });

    // 3. 드롭다운 메뉴 기능
    const dropdownToggles = document.querySelectorAll('.has-dropdown > a');
    dropdownToggles.forEach(toggle => {
        toggle.addEventListener('click', function(event) {
            if (this.getAttribute('href') === '#') {
                event.preventDefault();
            }
        });
    });

    // 4. 메인 페이지 로드 시 실종자 리스트를 동적으로 추가
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
                    <img src="${typeof contextPath !== 'undefined' ? contextPath : ''}/resources/img/${person.img}" alt="${person.name}">
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