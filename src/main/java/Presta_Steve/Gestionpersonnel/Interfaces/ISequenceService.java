package Presta_Steve.Gestionpersonnel.Interfaces;

import java.util.List;

import Presta_Steve.Gestionpersonnel.entities.Sequence;

public interface ISequenceService {

    void creersequence(Sequence sequence);

    List<Sequence> afficherLessequences();

    Sequence affichersequenceParId(int idS);

    void modifiersequence(Sequence sequence, int idS);

    void suppressionsequence(int idS);
}
