package Presta_Steve.Gestionpersonnel.entities;



import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Presence")
public class Presence {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codePre")
    private int codePre;
    @Column(name = "idPer")
    private int idPer;
    @Column(name = "heureArrive")
    private LocalTime heureArrive;
    @Column(name = "heureDepart")
    private LocalTime heureDepart;
    @Column(name = "periode")
    private LocalDate periode;
    @Column(name = "heureStandard")
    private LocalTime heureStandard = LocalTime.parse("09:00:00");
    @Column(name = "enRetard")
    private Boolean enRetard;



}
