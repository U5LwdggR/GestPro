package Presta_Steve.Gestionpersonnel.Interfaces;

import java.util.List;

import Presta_Steve.Gestionpersonnel.entities.Session;

public interface ISessionService {

    void creersession(Session session);

    List<Session> afficherLessessions();

    Session affichersessionParId(int idM);

    void modifiersession(Session session, int idM);

    void suppressionsession(int idM);
}
