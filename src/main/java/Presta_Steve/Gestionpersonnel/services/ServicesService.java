package Presta_Steve.Gestionpersonnel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Presta_Steve.Gestionpersonnel.entities.Services;
import Presta_Steve.Gestionpersonnel.interfaces.IServicesService;
import Presta_Steve.Gestionpersonnel.repositories.ServicesRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ServicesService implements IServicesService {
  // Injecter le repository
  private ServicesRepository serviceRepository;

  @Override
  public void addService(Services services) {
    serviceRepository.save(services);
  }

  @Override
  public void deleteService(int idSer) {
    Optional<Services> chercherService = serviceRepository.findById(idSer);
    if (chercherService.isPresent()) {
      serviceRepository.deleteById(idSer);
    } else {
      throw new IllegalArgumentException("ce service n'existe pas, donc il ne peut pas être supprimé");
    }
    
  }

  @Override
  public Services getServiceById(int idSer) {
    Optional<Services> chercherService = serviceRepository.findById(idSer);
    if (chercherService.isPresent()) {
      return chercherService.get();
    } else {
      throw new IllegalArgumentException("ce service n'existe pas, donc il ne peut pas être affiché");
    }
    
  }

  @Override
  public List<Services> getAllServices() {
    List<Services> services = (List<Services>) serviceRepository.findAll();
    if (services.isEmpty()) {
      throw new IllegalArgumentException("Aucun service n'est disponible");
    } else {
      return services;
    }
    
  }

  @Override
  public void updateService(Services service, int idSer) {
    Optional<Services> chercherService = serviceRepository.findById(idSer);
    if (chercherService.isPresent()) {
      Services serviceToUpdate = chercherService.get();
      serviceToUpdate.setNomSer(service.getNomSer());
      serviceRepository.save(serviceToUpdate);
    } else {
      throw new IllegalArgumentException("ce service n'existe pas, donc il ne peut pas être modifié");
    }
    
  }


}
