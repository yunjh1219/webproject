package com.home.project_megamenu.reservations.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ReservationDTO {

    private String name; // 이름
    private String phonumber; // 연락처
    private String email; // 이메일
    private String gender; // 성별
    private String reservationDate; // 예약일
    private String reservationTime; // 예약 시간
    private String treatment1; // 1차 분류
    private String treatment2; // 2차 분류
    private String text; // 기타 요청사항
}