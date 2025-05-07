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


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "MembrePersonnel")
public class MembrePersonnel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idPer;
  @Column(name = "nomPer")
  private String nomPer;
  @Column(name = "emailPer")
  private String emailper; 
  @Column(name = "telPer")
  private int telPer;
  @Column(name = "idSer")
  private int idSer;
  @Column(name = "idPost")
  private Integer idPost;
  @Column(name = "idSup")
  private Integer idSup;
  @Column(name = "photoPer")
  private String photoPer;

}
