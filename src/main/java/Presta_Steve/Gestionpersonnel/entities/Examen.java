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
public class Examen {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idEx;

  private String typeEx;
  //les types sont:

  //-sequentiels //-1er seq  //-personnalise (controle continu)
                            //-harmonise  (sequence)

                //-2eme seq //-personnalise (controle continu)
                            //-harmonise  (sequence)

                //-3eme seq //-personnalise (controle continu)
                            //-harmonise  (sequence)

                //-4eme seq //-personnalise (controle continu)
                            //-harmonise  (sequence)

                //-5eme seq //-personnalise (controle continu)
                            //-harmonise  (sequence)

                //-6eme seq //-personnalise (controle continu)
                            //-harmonise  (sequence)

  //-officiels //-BEPC
              //-probatoire
              //-baccalaureat
}
