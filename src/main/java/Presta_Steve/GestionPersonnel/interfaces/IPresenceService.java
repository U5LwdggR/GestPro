package Presta_Steve.GestionPersonnel.interfaces;

import java.util.List;

import Presta_Steve.GestionPersonnel.entities.Presence;

public interface IPresenceService {

  void ajouterPresence(int code);
  List<Presence> AfficherToutesLesPresences();
  void SupprimerPresence(int id);
  void modifierPresence(Presence presence, int id);
}
