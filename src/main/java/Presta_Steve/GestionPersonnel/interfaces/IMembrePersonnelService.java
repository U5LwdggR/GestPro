package Presta_Steve.GestionPersonnel.interfaces;

import java.util.List;

import Presta_Steve.GestionPersonnel.entities.MembrePersonnel;

public interface IMembrePersonnelService {
  void ajouterMembrePersonnel(MembrePersonnel membrePersonnel);
  void modifierMembrePersonnel(int id, MembrePersonnel membrePersonnel);
  MembrePersonnel afficherMembrePersonnelParId(int id);
  List<MembrePersonnel> afficherTousLesMembresPersonnel();
  void supprimerMembrePersonnel(int id);
}
