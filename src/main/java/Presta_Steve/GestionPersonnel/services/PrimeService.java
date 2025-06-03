package Presta_Steve.GestionPersonnel.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import Presta_Steve.GestionPersonnel.entities.Prime;
import Presta_Steve.GestionPersonnel.interfaces.IPrimeService;
import Presta_Steve.GestionPersonnel.repositories.PrimeRpository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PrimeService implements IPrimeService {

  private final PrimeRpository primeRpository;

  @Override
  //ajouter une prime
  public void ajouterPrime(Prime prime) {
    primeRpository.save(prime);
  }
  @Override
  //rechercher une prime par id
  public Prime afficherPrime(int id) {
    return primeRpository.findById(id).orElseThrow(() -> new RuntimeException("la prime avec l'id " + id + " n'existe pas"));
  }
  @Override
  //modifier une prime
  public Prime modifierPrime(int id, Prime primeDetails) {
    Prime prime = primeRpository.findById(id).orElseThrow(() -> new RuntimeException("la prime avec l'id " + id + " n'existe pas"));
    prime.setSalaire(primeDetails.getSalaire());
    prime.setValeur(primeDetails.getValeur());
    prime.setDateP(primeDetails.getDateP());
    return primeRpository.save(prime);
  }
  @Override
  //supprimer une prime
  public void annulerPrime(int id) {
    Prime prime = primeRpository.findById(id).orElseThrow(() -> new RuntimeException("la prime avec l'id " + id + " n'existe pas"));
    primeRpository.delete(prime);
  }
  @Override
  //afficher toutes les primes
  public List<Prime> afficherToutesLesPrimes() {
    Iterable<Prime> prime = primeRpository.findAll();
    List<Prime> primes = new ArrayList<>();
    prime.forEach(primes::add);
    return primes;
  }
}
