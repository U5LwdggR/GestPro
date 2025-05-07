package Presta_Steve.Gestionpersonnel.interfaces;

import java.util.List;

import Presta_Steve.Gestionpersonnel.entities.MembrePersonnel;

public interface IMembrePersonnelService {
  void ajouterMembrePersonnel(MembrePersonnel membrePersonnel);
  void modifierMembrePersonnel(int id, MembrePersonnel membrePersonnel);
  MembrePersonnel afficherMembrePersonnelParId(int id);
  public List<MembrePersonnel> afficherTousLesMembresPersonnel();
}
