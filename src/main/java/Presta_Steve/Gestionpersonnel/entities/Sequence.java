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
public class Sequence {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idSeq;

  private String numSeq;//ex: 1 sequence, 2 sequence ...

  //private String typeDevoir; // personnalise ou harmonise
 
}
 