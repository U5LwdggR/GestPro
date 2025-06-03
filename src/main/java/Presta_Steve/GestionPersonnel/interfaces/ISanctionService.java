package Presta_Steve.GestionPersonnel.interfaces;


import java.util.List;

import Presta_Steve.GestionPersonnel.entities.Sanction;

public interface ISanctionService {

  void addSanction(Sanction sanction);
  void updateSanction(int id, Sanction updatedSanction);
  void deleteSanction(int id);
  List<Sanction> AfficherTousLesPostes();
  Sanction AfficherSanction(int id);
}
