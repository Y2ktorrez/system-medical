package com.y2k.hospital.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "insumoTratamiento")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InsumoTratamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer costoTotal;
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "id_insumoMedico", referencedColumnName = "id")
    private InsumoMedico insumoMedico;

    @ManyToOne
    @JoinColumn(name = "id_tratamiento", referencedColumnName = "id")
    private Tratamiento tratamiento;
}
