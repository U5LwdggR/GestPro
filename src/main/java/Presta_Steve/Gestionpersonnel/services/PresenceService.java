package Presta_Steve.Gestionpersonnel.services;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import Presta_Steve.Gestionpersonnel.entities.Presence;
import Presta_Steve.Gestionpersonnel.repositories.PresenceRepository;
import Presta_Steve.Gestionpersonnel.interfaces.IPresenceService;

@AllArgsConstructor
@Service
public class PresenceService implements IPresenceService {

  private final PresenceRepository presenceRepository;


  public void ajouterPresence(Presence presence) {
    presenceRepository.save(presence);
  }
}
