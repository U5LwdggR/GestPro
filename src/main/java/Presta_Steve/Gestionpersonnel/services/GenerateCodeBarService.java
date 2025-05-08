package Presta_Steve.Gestionpersonnel.services;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import io.jsonwebtoken.io.IOException;

@Service
public class GenerateCodeBarService {

    private final String imagePath = "G:\\projet\\projet_ecole\\projet_soutenance\\Gestionpersonnel\\src\\main\\java\\Presta_Steve\\Gestionpersonnel\\Assets\\Transfo africa Inc.2.png";
    private final String outputPath = "G:\\projet\\projet_ecole\\projet_soutenance\\Gestionpersonnel\\src\\main\\java\\Presta_Steve\\Gestionpersonnel\\Assets\\Transfo africa Inc.png";
    private final int barcodeWidth = 400;
    private final int barcodeHeight = 100;
    private final int positionX = 300;
    private final int positionY = 280;

    public void generateBarcodeToImage(String id) {
        try {
            validateId(id);
            
            // 1. Générer le code-barres
            BufferedImage barcodeImage = generateBarcode(id, barcodeWidth, barcodeHeight);

            // 2. Charger l'image de base
            BufferedImage baseImage = ImageIO.read(new File(imagePath));

            // 3. Fusionner les images
            BufferedImage combined = new BufferedImage(
                    baseImage.getWidth(), 
                    baseImage.getHeight(), 
                    baseImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : baseImage.getType());
            
            Graphics2D g = combined.createGraphics();
            g.drawImage(baseImage, 0, 0, null);
            g.drawImage(barcodeImage, positionX, positionY, null);
            g.dispose();

            // 4. Sauvegarder
            String formatName = outputPath.substring(outputPath.lastIndexOf('.') + 1);
            ImageIO.write(combined, formatName, new File(outputPath));
            
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la génération du code-barres: " + e.getMessage(), e);
        }
    }

    private BufferedImage generateBarcode(String text, int width, int height) throws Exception {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        hints.put(EncodeHintType.MARGIN, 1);

        // Utilisation de CODE_128 qui accepte tout texte
        BitMatrix matrix = new MultiFormatWriter().encode(
                text, 
                BarcodeFormat.CODE_128, 
                width, 
                height,
                hints);

        return MatrixToImageWriter.toBufferedImage(matrix);
    }

    private void validateId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("L'ID ne peut pas être vide");
        }
        // Ajoutez ici d'autres validations si nécessaire
    }

    //methode pour ecrire les informations du membre personnel sur la la face recto de sa carte 
    public void WriteInformationMembrePersonnel(String nom, String service, String poste, int tel, String email, String photoProfil) throws java.io.IOException {
        try {
            // 1. Charger le modèle de carte (image de fond)
            String templatePath = "G:\\projet\\projet_ecole\\projet_soutenance\\Gestionpersonnel\\src\\main\\java\\Presta_Steve\\Gestionpersonnel\\Assets\\Transfo africa Inc 1.png";
            BufferedImage carteImage = ImageIO.read(new File(templatePath));
            
            // 2. Créer un contexte graphique pour dessiner sur l'image
            Graphics2D g = carteImage.createGraphics();
            
            // Configurer la qualité de rendu
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            // 3. Charger et dessiner la photo de profil
            if(photoProfil != null && !photoProfil.isEmpty()) {
                BufferedImage photo = ImageIO.read(new File(photoProfil));
                // Redimensionner si nécessaire (ex: 150x150 pixels)
                Image scaledPhoto = photo.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                g.drawImage(scaledPhoto, 50, 50, null); // Position de la photo

            }
            
            // 4. Configurer la police et les couleurs
            Font fontTitre = new Font("Montserrat", Font.BOLD, 50);
            Font fontNormal = new Font("Montserrat", Font.PLAIN, 30);
            g.setColor(Color.BLUE); // Couleur du texte
            
            // 5. Écrire les informations (positions à ajuster selon votre template)
            g.setFont(fontTitre);
            g.drawString(nom.toUpperCase(), 120, 240); // Position du nom

            g.setColor(Color.BLACK); // Couleur du texte pour les autres infos
            g.setFont(fontNormal);
            g.drawString("Service: " + service, 120, 280);
            g.drawString("Poste: " + poste, 120, 320);
            g.drawString("Tél: " + tel, 180, 380);
            g.drawString("Email: " + email, 180, 430);
            g.drawString("site de l'entreprise: transfoafricainc.com"  , 180, 470);
            
            // 7. Libérer les ressources graphiques
            g.dispose();
            
            // 8. Sauvegarder la carte générée
            String outputPath = "G:\\projet\\projet_ecole\\projet_soutenance\\Gestionpersonnel\\src\\main\\java\\Presta_Steve\\Gestionpersonnel\\Assets\\Transfo africa Inc1.png";
            ImageIO.write(carteImage, "png", new File(outputPath));
            
            System.out.println("Carte de membre générée avec succès !");
            
        } catch (IOException e) {
            System.err.println("Erreur lors de la génération de la carte: " + e.getMessage());
            e.printStackTrace();
        }
    }


    // Méthode pour chiffrer un ID avec le chiffrement de César
    public int chiffrerIdCesar(int id) {
        String strId = Integer.toString(id); // Convertir l'ID en chaîne
        StringBuilder resultat = new StringBuilder();

        for (char c : strId.toCharArray()) {
            int chiffre = Character.getNumericValue(c); // Convertir le caractère en chiffre
            int chiffreChiffre = (chiffre + 3) % 10; // Appliquer le décalage
            resultat.append(chiffreChiffre); // Ajouter au résultat
        }

        return Integer.parseInt(resultat.toString()); // Convertir en entier
    }


    // Déchiffrer l'id chiffré avec l'algorithme de César (k = 3)
    public int dechiffrerIdCesar(int code) {
        String strN = Integer.toString(code); // Convertir le nombre en chaîne
        StringBuilder resultat = new StringBuilder();

        for (char c : strN.toCharArray()) {
            int chiffre = Character.getNumericValue(c); // Convertir le caractère en chiffre
            int chiffreDechiffre = (chiffre - 3 + 10) % 10; // Déchiffrer avec k = 3
            resultat.append(chiffreDechiffre); // Ajouter au résultat
        }

        return Integer.parseInt(resultat.toString()); // Convertir en entier
    }
}