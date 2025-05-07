package Presta_Steve.Gestionpersonnel.interfaces;


import java.util.List;

import Presta_Steve.Gestionpersonnel.entities.Prime;


public interface IPrimeService {

  void ajouterPrime(Prime prime);
  Prime afficherPrime(int id);
  Prime modifierPrime(int id, Prime primeDetails);
  void annulerPrime(int id);
  List<Prime> afficherToutesLesPrimes();
}
