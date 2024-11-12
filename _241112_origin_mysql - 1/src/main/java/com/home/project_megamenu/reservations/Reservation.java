package com.home.project_megamenu.reservations;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phonumber;
    private String email;
    private String gender;
    private String reservation_Date;
    private String hour_select;
    private String minutes_select;
    private String treatment1;
    private String treatment2;
    private String additionalText;

    // Getters and setters here...
}
