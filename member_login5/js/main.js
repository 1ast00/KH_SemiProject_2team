document.addEventListener('DOMContentLoaded', function() {

    // 1. 검색어 칸 클릭 시 4개 입력칸으로 바뀌는 기능
    const mainSearchInput = document.getElementById('mainSearchInput');
    if (mainSearchInput) {
        const searchButton = document.getElementById('searchButton');
        const detailedSearchInputs = document.getElementById('detailedSearchInputs');
        const detailedSearchBtn = document.getElementById('detailedSearchBtn');

        mainSearchInput.addEventListener('focus', function() {
            detailedSearchInputs.style.display = 'flex';
        });

        detailedSearchBtn.addEventListener('click', function() {
            const name   = document.getElementById('searchName').value;
            const age    = document.getElementById('searchAge').value;
            const gender = document.getElementById('searchGender').value;
            const other  = document.getElementById('searchOther').value;

            const queryParams = new URLSearchParams();
            if (name)   queryParams.append('name', name);
            if (age)    queryParams.append('age', age);
            if (gender) queryParams.append('gender', gender);
            if (other)  queryParams.append('other', other);

            window.location.href = `/missing/search-results?${queryParams.toString()}`;
        });

        document.addEventListener('click', function(event) {
            const searchContainer = document.querySelector('.search-input-container');
            if (searchContainer &&
                !searchContainer.contains(event.target) &&
                detailedSearchInputs.style.display === 'flex'
            ) {
                detailedSearchInputs.style.display = 'none';
            }
        });
    }

    // 2. 드롭다운 메뉴 기능
    const dropdownToggles = document.querySelectorAll('.has-dropdown > a');
    dropdownToggles.forEach(toggle => {
        toggle.addEventListener('click', function(event) {
            if (this.getAttribute('href') === '#') {
                event.preventDefault();
            }
        });
    });

    // 3. 실종자 카드 동적 추가
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

    // 4. 로그인 사용자 환영 메시지
    const loggedInUser = document.body.getAttribute('data-user');
    if (loggedInUser) {
        document.write(loggedInUser + "님 환영합니다!");
    }

});

