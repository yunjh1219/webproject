package com.home.project_megamenu.reservations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

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

    // 예약 현황 페이지를 보여주는 GET 요청 핸들러
    @GetMapping("/list")
    public String viewReservations(Model model) {
        // 예약 목록을 조회하여 모델에 추가
        List<Reservation> reservations = reservationService.getAllReservations();
        System.out.println(reservations);
        model.addAttribute("reservations", reservations);
        return "pages/user";  // Thymeleaf 템플릿 이름 (예: reservations.html)
    }

    // 예약 확정 처리
    @PostMapping("/confirm/{id}")
    public String confirmReservation(@PathVariable("id") Long id) {
        // 예약 정보 가져오기
        Reservation reservation = reservationService.getReservationById(id);
        if (reservation != null) {
            // 예약 상태를 'y'로 업데이트 (확정)
            reservation.setStatus("y");
            reservationService.saveReservation(reservation);  // 상태 저장
        }
        return "redirect:/reservation/list";  // 예약 현황 페이지로 리다이렉트
    }

    // 예약 취소 처리
    @PostMapping("/cancel/{id}")
    public String cancelReservation(@PathVariable("id") Long id) {
        // 예약 정보 가져오기
        Reservation reservation = reservationService.getReservationById(id);
        if (reservation != null) {
            // 예약 상태를 'n'으로 업데이트 (취소)
            reservation.setStatus("n");
            reservationService.saveReservation(reservation);  // 상태 저장
        }
        return "redirect:/reservation/list";  // 예약 현황 페이지로 리다이렉트
    }
}