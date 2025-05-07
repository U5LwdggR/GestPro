package Presta_Steve.Gestionpersonnel.interfaces;

import java.util.List;

import Presta_Steve.Gestionpersonnel.entities.Poste;

public interface IPosteService {
  void EnregistrerPoste(Poste poste);
  List<Poste> AfficherTousLesPostes();
  void SupprimerPoste(int id);
  void ModifierPoste(int id, Poste posteDetails);
  Poste AfficherPoste(int id);


}
