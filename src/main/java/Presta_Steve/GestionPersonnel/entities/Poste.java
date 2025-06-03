package Presta_Steve.GestionPersonnel.entities;

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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Poste")
public class Poste {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idPost;
  @Column(name = "nomPost")
  private String nomPost;
  @Column(name = "salaire")
  private float salaire;
  
}
