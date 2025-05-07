package Presta_Steve.Gestionpersonnel.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import Presta_Steve.Gestionpersonnel.entities.Poste;
import Presta_Steve.Gestionpersonnel.interfaces.IPosteService;
import Presta_Steve.Gestionpersonnel.repositories.PosteRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PosteService implements IPosteService {

  private final PosteRepository posteRepository;

  //ajouter un poste
  public void EnregistrerPoste(Poste poste) {
    posteRepository.save(poste);
  }
  //modifier un poste
  public void ModifierPoste(int id, Poste posteDetails) {
    Poste poste = posteRepository.findById(id).orElseThrow(() -> new RuntimeException("le poste avec l'id: " + id +" n'existe pas"));
    poste.setNomPost(posteDetails.getNomPost());
    poste.setSalaire(posteDetails.getSalaire());
    posteRepository.save(poste);
  }
  //supprimer un poste
  public void SupprimerPoste(int id) {
    Poste poste = posteRepository.findById(id).orElseThrow(() -> new RuntimeException("le poste avec l'id " + id + " n'existe pas"));
    posteRepository.delete(poste);
  }
  //rechercher tous les postes
public List<Poste> AfficherTousLesPostes() {
    Iterable<Poste> iterable = posteRepository.findAll();
    List<Poste> postes = new ArrayList<>();
    iterable.forEach(postes::add);
    return postes;
}
  //rechercher un poste par id
  public Poste AfficherPoste(int id) {
    return posteRepository.findById(id).orElseThrow(() -> new RuntimeException("le poste avec l'id " + id + " n'existe pas"));
  }
}
