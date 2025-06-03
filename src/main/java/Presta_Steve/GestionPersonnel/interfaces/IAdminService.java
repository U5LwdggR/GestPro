package Presta_Steve.GestionPersonnel.interfaces;

import java.util.Map;

import Presta_Steve.GestionPersonnel.entities.Utilisateur;

public interface IAdminService{
  void ajouterAdmin(Utilisateur admin);
  void connexionAdmin(String emailAd, String mdpAd);
  void activation(Map<String, String> activation);
}
