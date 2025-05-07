package Presta_Steve.Gestionpersonnel.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import Presta_Steve.Gestionpersonnel.entities.Sanction;
import Presta_Steve.Gestionpersonnel.interfaces.ISanctionService;
import Presta_Steve.Gestionpersonnel.repositories.SanctionRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SanctionService implements ISanctionService {

  private final SanctionRepository sanctionRepository;

  //ajouter une sanction
  public void addSanction(Sanction sanction) {
    sanctionRepository.save(sanction);
  }
  //modifier une sanction
  public void updateSanction(int id, Sanction updatedSanction) {
    Sanction existingSanction = sanctionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Sanction not found with id: " + id));
    existingSanction.setMotif(updatedSanction.getMotif());
    existingSanction.setDateSanct(updatedSanction.getDateSanct());
    existingSanction.setSanction(updatedSanction.getSanction());
    sanctionRepository.save(existingSanction);
  }
  //supprimer une sanction
  public void deleteSanction(int id) {
    sanctionRepository.findById(id).orElseThrow(() -> new RuntimeException("la sanction avec l'id " + id + " n'existe pas"));
    sanctionRepository.deleteById(id);
  }
  //rechercher toutes les sanctions
  public List<Sanction> AfficherTousLesPostes() {
      Iterable<Sanction> iterable = sanctionRepository.findAll();
      List<Sanction> sanction = new ArrayList<>();
      iterable.forEach(sanction::add);
      return sanction;
  }
  //rechercher une sanction par id
  public Sanction AfficherSanction(int id) {
    return sanctionRepository.findById(id).orElseThrow(() -> new RuntimeException("la sanction avec l'id " + id + " n'existe pas"));
  }
}
