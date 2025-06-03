package Presta_Steve.GestionPersonnel.Securite;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import Presta_Steve.GestionPersonnel.entities.Utilisateur;
import Presta_Steve.GestionPersonnel.interfaces.ISuperAdminService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;




@Service
public class JwtService {

  private final ISuperAdminService superAdminService;
  //@Value("${secret_key}")
  private final String cle = "0f174d9f413d7f535b46920d821229ee6ce367a2816aff53269dad1f42619da4";
  public JwtService(ISuperAdminService superAdminService) {
    this.superAdminService = superAdminService;
  }

  public Map<String, String> generateJwt(Utilisateur utilisateur) {
    // Date de création et d'expiration du token
    final long heureActuelle = System.currentTimeMillis();
    final long heureExpiration = heureActuelle + 1000 * 60 * 1000; // 30 minutes

    // Création du token
    final String bearer = Jwts.builder()
        .setIssuedAt(new Date(heureActuelle))
        .setExpiration(new Date(heureExpiration))
        .setSubject(utilisateur.getEmailSup()) // Claim standard "sub"
        .claim("nom", utilisateur.getNomSup()) // Claim personnalisé
        .claim("role", utilisateur.getRole())
        .signWith(getKey(), SignatureAlgorithm.HS256)
        .compact();

    System.out.println("Token généré : " + bearer);

    return Map.of("bearer", bearer);
}


  public Map<String,String> generate(String email) {
    Utilisateur utilisateur = this.superAdminService.loadUserByUsername(email);
    return this.generateJwt(utilisateur);
  }

  private Key getKey() {
    final byte[] decoder = Decoders.BASE64.decode(cle);
    return Keys.hmacShaKeyFor(decoder);
  } 

  public String extraireUsername(String token) {
    return this.getClaims(token,Claims::getSubject);
  }
  

  public boolean isTokenExpired(String token) {
    Date expirationDate = this.getClaims(token,Claims::getExpiration);
    return expirationDate.before(new Date());
  }

  private <T> T getClaims(String token, Function<Claims, T> function) {
    Claims claims = getAllClaims(token);
    return function.apply(claims);
  }

  
  private Claims getAllClaims(String token){
    return Jwts.parserBuilder()
        .setSigningKey(this.getKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }



}