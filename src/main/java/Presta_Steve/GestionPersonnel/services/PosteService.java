package Presta_Steve.GestionPersonnel.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Presta_Steve.GestionPersonnel.entities.Poste;
import Presta_Steve.GestionPersonnel.interfaces.IPosteService;
import Presta_Steve.GestionPersonnel.repositories.PosteRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PosteService implements IPosteService {

  private final PosteRepository posteRepository;

  //ajouter un poste
  @Override
  public void EnregistrerPoste(Poste poste) {
    posteRepository.save(poste);
  }
  //modifier un poste
  @Override
  public void ModifierPoste(int id, Poste posteDetails) {
    Poste poste = posteRepository.findById(id).orElseThrow(() -> new RuntimeException("le poste avec l'id: " + id +" n'existe pas"));
    poste.setNomPost(posteDetails.getNomPost());
    poste.setSalaire(posteDetails.getSalaire());
    posteRepository.save(poste);
  }
  //supprimer un poste
  @Override
  public void SupprimerPoste(int id) {
    Poste poste = posteRepository.findById(id).orElseThrow(() -> new RuntimeException("le poste avec l'id " + id + " n'existe pas"));
    posteRepository.delete(poste);
  }
  
  //rechercher tous les postes
  @Override
public List<Poste> AfficherTousLesPostes() {
    Iterable<Poste> iterable = posteRepository.findAll();
    List<Poste> postes = new ArrayList<>();
    iterable.forEach(postes::add);
    return postes;
}
  //rechercher un poste par id
  @Override
  public Poste AfficherPoste(int id) {
    Optional<Poste> chercherPoste = posteRepository.findById(id);
    if (chercherPoste.isPresent()) {
      return chercherPoste.get();
    } else {
      throw new IllegalArgumentException("ce poste n'existe pas, donc il ne peut pas être affiché");
    }
  }
}
