package com.y2k.hospital.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "enfermero")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Enfermero {
    @Id
    private Long ci;

    private Integer edad;
    private LocalDate fechaNacimiento;

    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;

}
