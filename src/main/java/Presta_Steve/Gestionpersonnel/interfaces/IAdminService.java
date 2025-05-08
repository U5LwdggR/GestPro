package Presta_Steve.Gestionpersonnel.interfaces;

import java.util.Map;

import Presta_Steve.Gestionpersonnel.entities.Utilisateur;

public interface IAdminService{
  void ajouterAdmin(Utilisateur admin);
  void connexionAdmin(String emailAd, String mdpAd);
  void activation(Map<String, String> activation);
}
