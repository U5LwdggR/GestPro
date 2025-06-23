package Presta_Steve.Gestionpersonnel.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import Presta_Steve.Gestionpersonnel.Interfaces.IUtilisateurService;
import Presta_Steve.Gestionpersonnel.entities.Utilisateurs;
import Presta_Steve.Gestionpersonnel.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FiltreJwt extends OncePerRequestFilter{

  private final IUtilisateurService utilisateurService;
  private final JwtService jwtService;


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws ServletException, IOException {

    String nomUtilisateur = null;
    String token = null;
    boolean isTokenExpired = true;

    final String authorization = request.getHeader("Authorization");
    if (authorization != null && authorization.startsWith("Bearer ")) {
      try {
          token = authorization.substring(7);
          isTokenExpired = this.jwtService.isTokenExpired(token); // Vérifie si le token est expiré
          nomUtilisateur = this.jwtService.extraireUsername(token);
      } catch (Exception e) {
          System.err.println("Erreur lors de la validation du token : " + e.getMessage());
      }
  }
    
    if (!isTokenExpired && nomUtilisateur != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      Utilisateurs userDetails = this.utilisateurService.loadUserByUsername(nomUtilisateur);
      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
  }
    filterChain.doFilter(request, response);
    
  }
  
  }
