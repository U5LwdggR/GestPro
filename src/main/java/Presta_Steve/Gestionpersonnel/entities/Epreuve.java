package Presta_Steve.Gestionpersonnel.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Epreuve {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String titre;

  private String destription; 

  private String matiere;

  private String etablissement; 
  
  private String classe;

  private String typeExamen;

  private String session;

  private String options;

  private String sequence; 

  @Lob
  private byte[] Epreuve;

  private Boolean Statut;
  //avoir les auteurs de l'épreuve mais plustard je dois faire une entité auteur 
}
