document.addEventListener('DOMContentLoaded', function () {
    const reservationDateInput = document.getElementById('reservation-date');
    const detailsContent = document.getElementById('details-content');

    // 예약일을 선택하면 details-content에 그 값을 표시
    reservationDateInput.addEventListener('change', function () {
        const reservationDate = reservationDateInput.value;
        detailsContent.innerHTML = `
            <p><strong>예약일:</strong> ${reservationDate}</p>
        `;
    });
});


// 카테고리 1차 선택에 따라 2차에 뜨는 거 다르게-----------------
    document.addEventListener('DOMContentLoaded', function () {
    const treatment1 = document.getElementById('Treatment1');
    const treatment2 = document.getElementById('Treatment2');

    const options = {
    '얼굴': ['얼굴1', '얼굴2', '얼굴3'],
    '몸': ['몸1', '몸2', '몸3'],
    '다리': ['다리1', '다리2', '다리3']
};

    function updateTreatment2() {
    // 현재 선택된 옵션을 가져옵니다.
    const selectedOption = treatment1.value;

    // Treatment2의 모든 옵션을 제거합니다.
    treatment2.innerHTML = '';

    // 새로운 옵션을 추가합니다.
    if (options[selectedOption]) {
    options[selectedOption].forEach(function (option) {
    const newOption = document.createElement('option');
    newOption.value = option;
    newOption.textContent = option;
    treatment2.appendChild(newOption);
});
}
}

    // 초기화: 페이지 로드 시 초기 옵션을 설정합니다.
    updateTreatment2();

    // Treatment1의 선택이 변경될 때마다 Treatment2를 업데이트합니다.
    treatment1.addEventListener('change', updateTreatment2);
});
// 카테고리 1차 선택에 따라 2차에 뜨는 거 다르게-----------------