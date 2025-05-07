package Presta_Steve.Gestionpersonnel.interfaces;

import java.util.Map;

import Presta_Steve.Gestionpersonnel.entities.Admin;

public interface IAdminService{
  void ajouterAdmin(Admin admin);
  void connexionAdmin(String emailAd, String mdpAd);
  void activation(Map<String, String> activation);
  Admin loadUserByUsername(String emailAd);
}
