package Presta_Steve.Gestionpersonnel.entities;

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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RoleUtilisateur")
public class Role {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idR;
    @Column(name = "libelle")
    private String libelle;
}
