package Presta_Steve.Gestionpersonnel.entities;



import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Sanction")
public class Sanction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idSanct;
  @Column(name = "motif")
  private String motif;
  @Column(name = "dateSanct")
  private LocalDateTime dateSanct;
  @Column(name = "idPer")
  private int idPer;
  @Column(name = "sanction")
  private float sanction;


}
