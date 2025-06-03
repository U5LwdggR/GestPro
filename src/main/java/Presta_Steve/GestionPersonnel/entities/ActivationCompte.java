package Presta_Steve.GestionPersonnel.entities;

import java.time.Instant;

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



@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "ActivationCompte")
public class ActivationCompte {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idActivation;
  @Column(name = "creationDate")
  private Instant creationDate;
  @Column(name = "expirationDate")
  private Instant expirationDate;
  @Column(name = "activationDate")
  private Instant activationDate;
  @Column(name = "code")
  private String code;
  @Column(name = "idSup", nullable = true)
  private Integer idSuperAdmin;

}
