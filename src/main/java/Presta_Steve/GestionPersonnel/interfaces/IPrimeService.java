package Presta_Steve.GestionPersonnel.interfaces;


import java.util.List;

import Presta_Steve.GestionPersonnel.entities.Prime;


public interface IPrimeService {

  void ajouterPrime(Prime prime);
  Prime afficherPrime(int id);
  Prime modifierPrime(int id, Prime primeDetails);
  void annulerPrime(int id);
  List<Prime> afficherToutesLesPrimes();
}
