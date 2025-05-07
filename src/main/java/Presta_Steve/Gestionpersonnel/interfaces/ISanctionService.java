package Presta_Steve.Gestionpersonnel.interfaces;


import java.util.List;

import Presta_Steve.Gestionpersonnel.entities.Sanction;

public interface ISanctionService {

  void addSanction(Sanction sanction);
  void updateSanction(int id, Sanction updatedSanction);
  void deleteSanction(int id);
  List<Sanction> AfficherTousLesPostes();
  Sanction AfficherSanction(int id);
}
