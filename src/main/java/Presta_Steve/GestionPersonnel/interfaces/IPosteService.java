package Presta_Steve.GestionPersonnel.interfaces;

import java.util.List;

import Presta_Steve.GestionPersonnel.entities.Poste;

public interface IPosteService {
  void EnregistrerPoste(Poste poste);
  List<Poste> AfficherTousLesPostes();
  void SupprimerPoste(int id);
  void ModifierPoste(int id, Poste posteDetails);
  Poste AfficherPoste(int id);


}
