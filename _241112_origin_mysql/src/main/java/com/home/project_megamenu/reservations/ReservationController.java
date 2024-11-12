package com.home.project_megamenu.reservations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // 예약 폼을 보여주는 GET 요청 핸들러
    @GetMapping("/form")
    public String showReservationForm() {
        return "reservation-form";  // Thymeleaf 템플릿 이름 (예: reservation-form.html)
    }

    // 예약 폼을 처리하는 POST 요청 핸들러
    @PostMapping("/submit")
    public ResponseEntity<String> submitReservation(@RequestBody Reservation reservation) {
        System.out.println(reservation);

        // 예약 저장
        reservationService.saveReservation(reservation);

        // 예약 성공 응답 반환
        return ResponseEntity.ok("예약이 완료되었습니다.");
    }
}