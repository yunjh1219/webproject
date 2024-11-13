package com.home.project_megamenu.reservations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    // 예약 저장
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    // 모든 예약을 조회하는 메서드 추가
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();  // 모든 예약을 조회하여 반환
    }

    // 예약 ID로 예약을 조회하는 메서드 추가
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);  // 예약이 없으면 null 반환
    }

    // 예약 상태를 'y' (확정)로 변경
    public Reservation confirmReservation(Long id) {
        Reservation reservation = getReservationById(id);
        if (reservation != null) {
            reservation.setStatus("y");  // 상태를 'y'로 변경
            return saveReservation(reservation);  // 저장
        }
        return null;  // 예약이 존재하지 않으면 null 반환
    }

    // 예약 상태를 'n' (취소)로 변경
    public Reservation cancelReservation(Long id) {
        Reservation reservation = getReservationById(id);
        if (reservation != null) {
            reservation.setStatus("n");  // 상태를 'n'으로 변경
            return saveReservation(reservation);  // 저장
        }
        return null;  // 예약이 존재하지 않으면 null 반환
    }
}