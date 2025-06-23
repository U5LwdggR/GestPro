package Presta_Steve.Gestionpersonnel.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import Presta_Steve.Gestionpersonnel.Interfaces.ICorrectionService;
import Presta_Steve.Gestionpersonnel.entities.Correction;
import Presta_Steve.Gestionpersonnel.entities.Epreuve;
import Presta_Steve.Gestionpersonnel.repositories.CorrectionRepository;
import Presta_Steve.Gestionpersonnel.repositories.EpreuveRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CorrectionService implements ICorrectionService{
	

	  private final CorrectionRepository correctionRepository;
	  
	  private final EpreuveRepository epreuveRepository;




	public void creerEpreuve(String titre, String destription, String matiere, String classe, String etablissement, String typeExamen, String session, String option, String sequence, MultipartFile  fichier, int id) {
	    try {
	        // verification du type mime du fichier uploader
	        if (!"application/pdf".equals(fichier.getContentType())) {
	            throw new RuntimeException("Le fichier doit être un PDF.");
	        }
	        if (fichier != null && !fichier.isEmpty()) {
	            // Créer un dossier si non existant
	            String uploadDir = "uploads/";
	            Files.createDirectories(Paths.get(uploadDir));

	            // Créer un nom de fichier unique
	            String fileName = UUID.randomUUID() + "_" + fichier.getOriginalFilename();
	            Path filePath = Paths.get(uploadDir, fileName);

	            // Sauvegarder le fichier
	            fichier.transferTo(filePath);
	            

	            // Créer et sauvegarder l'épreuve
	            Correction epreuve = new Correction();
	            epreuve.setTitre(titre);
	            epreuve.setDestription(destription);
	            epreuve.setClasse(classe);
	            epreuve.setMatiere(matiere);
	            epreuve.setEtablissement(etablissement);
	            epreuve.setTypeExamen(typeExamen);
	            epreuve.setSession(session);
	            epreuve.setOptions(option);
	            epreuve.setSequence(sequence);
	            epreuve.setCorrection(fichier.getBytes()); // Enregistrer le chemin du fichier
	            
	            Optional<Epreuve> findEpreuve = this.epreuveRepository.findById(id);
	            if (findEpreuve.isEmpty()) {
					throw new RuntimeException("l'epreuve en question n'existe pas !!!");
				}
	            epreuve.setEpreuve(findEpreuve.get());
	            
	            correctionRepository.save(epreuve);
	        }
	    } catch (IOException e) {
	        e.printStackTrace(); 
	    }
	}

	public void SupprimerEpreuve(int id){

	    try {
	        Optional<Correction> findEpreuve =  this.correctionRepository.findById(id);
	        if (findEpreuve.isEmpty()) {
	            throw new RuntimeException("cette epreuve n'existe pas");
	        }
	        //supprimer l'epreuve de la bd
	        this.correctionRepository.deleteById(id);
	    } catch (Exception e) {
	        throw new RuntimeException("erreur: "+e.getMessage());
	    }
	}

	public void modifierEpreuve(int id, Correction epreuve, MultipartFile  fichier){
	    try {
	        Optional<Correction> findEpreuve =  this.correctionRepository.findById(id);
	        if (findEpreuve.isEmpty()) {
	            throw new RuntimeException("cette epreuve n'existe pas");
	        }
	        Correction epreuveModifie = new Correction();
	        epreuveModifie.setDestription(epreuve.getDestription());
	        epreuveModifie.setEtablissement(epreuve.getEtablissement());
	        epreuveModifie.setMatiere(epreuve.getMatiere());
	        epreuveModifie.setOptions(epreuve.getOptions());
	        epreuveModifie.setClasse(epreuve.getClasse());
	        epreuveModifie.setSequence(epreuve.getSequence());
	        epreuveModifie.setSession(epreuve.getSession());
	        epreuveModifie.setTitre(epreuve.getTitre());
	        epreuveModifie.setTypeExamen(epreuve.getTypeExamen());

	        //verification du type mime de l'epreuve uploader
	        if (!"application/pdf".equals(fichier.getContentType())) {
	            throw new RuntimeException("Le fichier doit être un PDF.");
	        }
	        if (fichier != null && !fichier.isEmpty()) {
	        String uploadDir = "uploads/";
	        Files.createDirectories(Paths.get(uploadDir));

	        // Créer un nom de fichier unique
	        String fileName = UUID.randomUUID() + "_" + fichier.getOriginalFilename();
	        Path filePath = Paths.get(uploadDir, fileName);

	        // Sauvegarder le fichier
	        fichier.transferTo(filePath);

	        epreuveModifie.setCorrection(fichier.getBytes());
	        }
	        
	        this.correctionRepository.save(epreuveModifie);
	        
	    } catch (Exception e) {
	        
	    }
	}

	public List<Correction> afficherToutesLesEpreuves(){

	    try {
	        List<Correction> epreuves = (List<Correction>) this.correctionRepository.findAll();

	    if (epreuves.isEmpty()) {
	        throw new RuntimeException("aucune epreuve trouve");
	    }
	    return epreuves;
	    } catch (Exception e) {
	        throw new RuntimeException("erreur: "+e.getMessage());
	    }
	    
	}

	public Correction afficherEpreuveParId(int id){
	    try {
	        Optional<Correction> findEpreuve = this.correctionRepository.findById(id);
	        if (findEpreuve.isEmpty()) {
	            throw new RuntimeException("cette epreuve n'existe pas");
	        }
	        Correction epreuveTrouve = findEpreuve.get();
	        return epreuveTrouve;
	    } catch (Exception e) {
	        throw new RuntimeException("erreur: "+e.getMessage());
	    }
	}

	// //methode pour afficher les epreuves dont le status et true
	// public List<Epreuve> ListeEpreuveAvecStatusTrue(){

	// }

	// //methode pour afficher les epreuves dont le status et true
	// public List<Epreuve> ListeEpreuveAvecStatusFalse(){

	// }

	/*public void BloquerPublicationEpreuve(int id){
	    try {
	        Optional<Correction> findEpreuve = this.correctionRepository.findById(id);
	        if (findEpreuve.isEmpty()) {
	            throw new RuntimeException("cette epreuve n'existe pas");
	        }
	        Correction epreuveTrouve = findEpreuve.get();
	        epreuveTrouve.setStatut(false);
	        this.correctionRepository.save(epreuveTrouve);
	    } catch (Exception e) {
	        throw new RuntimeException("erreur: "+e.getMessage());
	    }
	}

	public void DebloquerPublicationEpreuveBloquer(int id){
	    try {
	        Optional<Epreuve> findEpreuve = this.correctionRepository.findById(id);
	        if (findEpreuve.isEmpty()) {
	            throw new RuntimeException("cette epreuve n'existe pas");
	        }
	        Epreuve epreuveTrouve = findEpreuve.get();
	        epreuveTrouve.setStatut(true);
	        this.correctionRepository.save(epreuveTrouve);
	    } catch (Exception e) {
	        throw new RuntimeException("erreur: "+e.getMessage());
	    }
	}*/


	public byte[] getPdfContent (int id) {
		Optional<Correction> findEpreuve = this.correctionRepository.findByEpreuveId(id);
	    if (findEpreuve.isEmpty()) {
	        throw new RuntimeException("cette epreuve n'existe pas");
	    }
	    Correction epreuveTrouve = findEpreuve.get();
	    byte[] epreuve = epreuveTrouve.getCorrection();
	    return epreuve;
	}

}
