package Presta_Steve.Gestionpersonnel.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import Presta_Steve.Gestionpersonnel.entities.Prime;
import Presta_Steve.Gestionpersonnel.interfaces.IPrimeService;
import Presta_Steve.Gestionpersonnel.repositories.PrimeRpository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PrimeService implements IPrimeService {

  private final PrimeRpository primeRpository;

  //ajouter une prime
  public void ajouterPrime(Prime prime) {
    primeRpository.save(prime);
  }
  //rechercher une prime par id
  public Prime afficherPrime(int id) {
    return primeRpository.findById(id).orElseThrow(() -> new RuntimeException("la prime avec l'id " + id + " n'existe pas"));
  }
  //modifier une prime
  public Prime modifierPrime(int id, Prime primeDetails) {
    Prime prime = primeRpository.findById(id).orElseThrow(() -> new RuntimeException("la prime avec l'id " + id + " n'existe pas"));
    prime.setSalaire(primeDetails.getSalaire());
    prime.setValeur(primeDetails.getValeur());
    prime.setDateP(primeDetails.getDateP());
    return primeRpository.save(prime);
  }
  //supprimer une prime
  public void annulerPrime(int id) {
    Prime prime = primeRpository.findById(id).orElseThrow(() -> new RuntimeException("la prime avec l'id " + id + " n'existe pas"));
    primeRpository.delete(prime);
  }
  //afficher toutes les primes
  public List<Prime> afficherToutesLesPrimes() {
    Iterable<Prime> prime = primeRpository.findAll();
    List<Prime> primes = new ArrayList<>();
    prime.forEach(primes::add);
    return primes;
  }
}
