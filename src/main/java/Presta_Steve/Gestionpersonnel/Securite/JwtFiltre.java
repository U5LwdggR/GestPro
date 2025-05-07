package Presta_Steve.Gestionpersonnel.Securite;


import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import Presta_Steve.Gestionpersonnel.entities.SuperAdmin;
import Presta_Steve.Gestionpersonnel.interfaces.ISuperAdminService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class JwtFiltre extends OncePerRequestFilter {

  private ISuperAdminService superAdminService;
  private JwtService jwtService;


  @SuppressWarnings("null")
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
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
      SuperAdmin userDetails = this.superAdminService.loadUserByUsername(nomUtilisateur);
      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
  }
    filterChain.doFilter(request, response);
    
  }
  

}