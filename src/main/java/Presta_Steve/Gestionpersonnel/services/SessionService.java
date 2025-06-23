package Presta_Steve.Gestionpersonnel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Presta_Steve.Gestionpersonnel.Interfaces.ISessionService;
import Presta_Steve.Gestionpersonnel.entities.Session;
import Presta_Steve.Gestionpersonnel.repositories.SessionRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SessionService implements ISessionService {

    //Injection de dependance
    //on utilise le repository pour acceder a la base de donnee
  private final SessionRepository sessionRepository;

    //creation d'une session
  public void creersession(Session session){
    try {
      this.sessionRepository.save(session);
    } catch (Exception e) {
      throw new RuntimeException("erreur a la creation d'une session");
    }
  }

  //afficher toutes les sessions
  public List<Session> afficherLessessions(){
    try {
      List<Session> sessionCherche = (List<Session>) this.sessionRepository.findAll();
      if (sessionCherche.isEmpty()) {
        System.out.println("aucune session n'a ete retouvee");
      }
      return sessionCherche;
    } catch (Exception e) {
      throw new RuntimeException("erreur a l'affichage des sessions: "+e);
    }
  }

  //afficher les sessions en fonction de leur id
  public Session affichersessionParId(int idM){
    try {
      Optional<Session> sessionCherhe = this.sessionRepository.findById(idM);
      if (sessionCherhe.isEmpty()) {
        throw new RuntimeException("cette session n'existe pas dans le systeme");
      }
      Session sessionTrouve = sessionCherhe.get();
      return sessionTrouve;
    } catch (Exception e) {
      throw new RuntimeException("erreur a l'affichage de la session: "+e);
    }
  }

  //modifier un session
  public void modifiersession(Session session, int idM){
    try {
      Optional<Session> sessionCherhe = this.sessionRepository.findById(idM);
      if (sessionCherhe.isEmpty()) {
        throw new RuntimeException("cette session n'existe pas dans le systeme");
      }
      Session sessionTrouve = sessionCherhe.get();
      sessionTrouve.setPeriode(session.getPeriode());
      this.sessionRepository.save(sessionTrouve);
    } catch (Exception e) {
      throw new RuntimeException("erreur a la modification de la session: "+e);
    }
  }

  //supprimer un session
  public void suppressionsession(int idM){
    try {
      Optional<Session> sessionCherhe = this.sessionRepository.findById(idM);
      if (sessionCherhe.isEmpty()) {
        throw new RuntimeException("cette session n'existe pas dans le systeme");
      }
      this.sessionRepository.deleteById(idM);
    } catch (Exception e) {
      throw new RuntimeException("erreur a la suppression de la session: "+e);
    }
  }

}
