document.addEventListener('DOMContentLoaded', function() {
    const reservationDateInput = document.getElementById('reservation-date');
    const detailsContent = document.getElementById('details-content');

    // 예약일을 선택하면 details-content에 그 값을 표시
    reservationDateInput.addEventListener('change', function() {
        const reservationDate = reservationDateInput.value;
        detailsContent.innerHTML = `
            <p><strong>예약일:</strong> ${reservationDate}</p>
        `;
    });
});