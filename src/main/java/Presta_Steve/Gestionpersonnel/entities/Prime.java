package Presta_Steve.Gestionpersonnel.entities;



import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Setter;
import lombok.Getter;



@Setter
@Getter
@Table(name = "Prime")
@Entity
public class Prime {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idP;
  @Column(name = "valeur")
  private float valeur;//salire en chiffre
  @Column(name = "salaire")
  private String salaire;//salaire en lettre
  @Column(name = "dateP")
  private LocalDateTime dateP;//date et l'heure a laquelle la prime a ete attribuee
  @Column(name = "idPer")
  private int idPer;
}
