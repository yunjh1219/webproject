package com.home.project_megamenu.reservations.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import com.home.project_megamenu.reservations.dto.ReservationDTO;

@Controller
@RequestMapping("/booking")
public class ReservationController {
    @PostMapping("/submit-reservation")
    public String submitReservation(@ModelAttribute ReservationDto reservationDto, Model model) {
        // reservationDto에 입력된 정보는 이제 DTO에 담겨 있음.

        // 이곳에서 DB에 저장하거나, 다른 로직을 추가할 수 있습니다.

        // 예시로, 예약 정보를 모델에 담아서 결과 페이지로 전달
        model.addAttribute("reservationDetails", ReservationDto);

        return "reservationConfirmation"; // 예약 확인 페이지로 이동
    }
}
