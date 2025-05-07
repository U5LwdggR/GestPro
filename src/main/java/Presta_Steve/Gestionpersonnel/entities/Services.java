package Presta_Steve.Gestionpersonnel.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
@Table(name = "Service")
public class Services {
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
  @Id
  @Column(name = "idSer")
  private int idSer;
  @Column(name = "nomSer")
  private String nomSer;
  
}
