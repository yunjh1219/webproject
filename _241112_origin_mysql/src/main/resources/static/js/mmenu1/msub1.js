document.addEventListener('DOMContentLoaded', function () {
    const reservationDateInput = document.getElementById('reservation_Date');
    const hourSelect = document.getElementById('hour_select');
    const minutesSelect = document.getElementById('minutes_select');
    const genderInputs = document.querySelectorAll('input[name="gender"]');
    const treatment1Select = document.getElementById('treatment1');
    const treatment2Select = document.getElementById('treatment2');
    const submitButton = document.getElementById("submit-button");
    const reservationForm = document.getElementById("reservation-form");

    // 초기화: "1차 분류"가 선택되면 "2차 분류"에 맞는 옵션을 설정
    updatetreatment2Options(treatment1Select.value); // 페이지 로드 시 기본값에 맞게 업데이트
    updateDetails(); // 기본값에 맞게 예약 상세도 초기화

    // 시술부위 옵션 추가 (예시로 Treatment2를 '얼굴', '몸', '다리'로 동적으로 추가)
    treatment1Select.addEventListener('change', function () {
        const selectedTreatment = treatment1Select.value;
        updatetreatment2Options(selectedTreatment);
        updateDetails(); // 시술부위 선택 시 디테일 업데이트
    });

    // 예약일, 시간, 분, 성별, 시술부위를 선택하면 디테일에 그 값을 표시
    reservationDateInput.addEventListener('change', updateDetails);
    hourSelect.addEventListener('change', updateDetails);
    minutesSelect.addEventListener('change', updateDetails);
    genderInputs.forEach(input => {
        input.addEventListener('change', updateDetails);
    });
    treatment1Select.addEventListener('change', updateDetails);
    treatment2Select.addEventListener('change', updateDetails);

    // "2차 분류" 옵션 업데이트 함수
    function updatetreatment2Options(treatment) {
        let options = [];
        if (treatment === "얼굴") {
            options = ["눈", "코", "입"];
        } else if (treatment === "몸") {
            options = ["상체", "하체"];
        } else if (treatment === "다리") {
            options = ["허벅지", "종아리"];
        }

        // Treatment2의 옵션을 동적으로 변경
        treatment2Select.innerHTML = ''; // 기존 옵션을 제거

        // 기본값으로 '2차 분류' 옵션을 넣음
        const defaultOption = document.createElement("option");
        defaultOption.value = "2차 분류";
        defaultOption.textContent = "2차 분류";
        treatment2Select.appendChild(defaultOption);

        // 해당 1차 분류에 맞는 2차 옵션을 추가
        options.forEach(option => {
            const optionElement = document.createElement("option");
            optionElement.value = option;
            optionElement.textContent = option;
            treatment2Select.appendChild(optionElement);
        });
    }

    // 예약 상세 정보 업데이트 함수
    function updateDetails() {
        const reservationDate = reservationDateInput.value;
        const selectedHour = hourSelect.value;
        const selectedMinutes = minutesSelect.value;
        const selectedGender = document.querySelector('input[name="gender"]:checked');
        const selectedTreatment1 = treatment1Select.value;
        const selectedTreatment2 = treatment2Select.value;

        // 성별 처리
        const genderValue = selectedGender ? (selectedGender.value === 'female' ? '여(F)' : '남(M)') : '-';

        // 1차와 2차 분류 처리
        const treatment1Value = selectedTreatment1 !== "1차 분류" ? selectedTreatment1 : ""; // 1차 분류가 기본값이면 제외
        const treatment2Value = selectedTreatment2 !== "2차 분류" ? selectedTreatment2 : ""; // 2차 분류가 기본값이면 제외

        // 예약일, 시간, 분을 "예약일: 시간: 분" 형식으로 표시
        const formattedTime = `${selectedHour}:${selectedMinutes}`;

        // 예약 상세에 표시
        document.getElementById('details-gender').textContent = genderValue;
        document.getElementById('details-reservation-date').textContent = reservationDate ? reservationDate : '-';
        document.getElementById('details-reservation-time').textContent = reservationDate ? formattedTime : '-';

        // 1차와 2차 분류가 모두 선택되었을 때만 디테일에 표시
        if (treatment1Value && treatment2Value) {
            document.getElementById('details-treatment').textContent = `${treatment1Value} -> ${treatment2Value}`;
        } else {
            document.getElementById('details-treatment').textContent = '-';
        }
    }

    // 폼 제출 처리
    submitButton.addEventListener("click", function (event) {
        event.preventDefault();

        // 폼 데이터 수집
        const formData = new FormData(reservationForm);
        const data = {};
        formData.forEach((value, key) => {
            data[key] = value;
        });

        // 백엔드로 데이터 전송 (Spring Boot 서버 예시)
        fetch("/reservation/submit", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        })
            .then(response => response.json())
            .then(data => {
                console.log("Success:", data);
                alert("예약이 완료되었습니다!");
            })
            .catch(error => {
                console.error("Error:", error);
                alert("예약에 실패했습니다. 다시 시도해주세요.");
            });
    });
});