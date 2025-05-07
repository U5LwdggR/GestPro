package Presta_Steve.Gestionpersonnel.Securite;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import Presta_Steve.Gestionpersonnel.entities.Admin;
import Presta_Steve.Gestionpersonnel.interfaces.IAdminService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;




@Service
public class JwtServiceAd {

  private IAdminService AdminService;
  //@Value("${secret_key}")
  private String cle = "0f174d9f413d7f535b46920d821229ee6ce367a2816aff53269dad1f42619da4";
  public JwtServiceAd(IAdminService AdminService) {
    this.AdminService = AdminService;
  }

  public Map<String, String> generateJwt(Admin Admin) {
    // Date de création et d'expiration du token
    final long heureActuelle = System.currentTimeMillis();
    final long heureExpiration = heureActuelle + 30 * 60 * 1000; // 30 minutes

    // Création du token
    final String bearer = Jwts.builder()
        .setIssuedAt(new Date(heureActuelle))
        .setExpiration(new Date(heureExpiration))
        .setSubject(Admin.getEmailAd()) // Claim standard "sub"
        .claim("nom", Admin.getEmailAd()) // Claim personnalisé
        .signWith(getKey(), SignatureAlgorithm.HS256)
        .compact();

    System.out.println("Token généré : " + bearer);

    return Map.of("bearer", bearer);
}


  public Map<String,String> generate(String email) {
    Admin Admin = this.AdminService.loadUserByUsername(email);
    return this.generateJwt(Admin);
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