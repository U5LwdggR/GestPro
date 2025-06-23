package Presta_Steve.Gestionpersonnel.Interfaces;

import java.util.List;
import java.util.Map;

import Presta_Steve.Gestionpersonnel.entities.Utilisateurs;

public interface IUtilisateurService {
    void creerUtilisateur(Utilisateurs utilisateur);

    void activation(Map<String, String> activation);

    List<Utilisateurs> afficherTousLesUtilisateurs();

    Utilisateurs afficherUtilisateurParId(int idUser);

    void modifierUtilisateur(Utilisateurs utilisateur, int idUser);

    void BloquerUtilisateur(int idUser);

    void DebloquerUtilisateur(int idUser);

    Utilisateurs loadUserByUsername(String username);

    void activerCompteAvecCode(Map<String, String> activation);
} 
