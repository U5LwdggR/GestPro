package Presta_Steve.Gestionpersonnel.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Etablissement {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idE;

  private String nomE;
}
