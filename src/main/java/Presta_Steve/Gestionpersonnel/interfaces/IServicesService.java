package Presta_Steve.Gestionpersonnel.interfaces;

import java.util.List;

import Presta_Steve.Gestionpersonnel.entities.Services;



public interface IServicesService {
    public void addService(Services service);
    public void deleteService(int idSer);
    public Services getServiceById(int idSer);
    public List<Services> getAllServices();
    public void updateService(Services service, int idSer);

}
