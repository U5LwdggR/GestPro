package Presta_Steve.Gestionpersonnel.entities;

import java.time.Instant;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActivationCompte {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idAc;

  private Instant creationDate;

  private Instant expirationDate;

  private Instant activationDate;

  private String code;

  @OneToOne(cascade =  CascadeType.ALL )
  private Utilisateurs utilisateur;
}
