package Presta_Steve.Gestionpersonnel.entities;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Utilisateurs implements UserDetails{
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idUser;

  private String nom;

  private String email;

  private String mdp;

  private String telephone;

  @ManyToOne(cascade = CascadeType.ALL)
  private Roles role;

  private boolean actif = false;

  private String photo;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.role.getLibelle()));
  }

  @Override
  public boolean isAccountNonExpired() {

      return this.actif;
  }
  @Override
  public boolean isAccountNonLocked() {

      return this.actif;
  }
  @Override
  public boolean isCredentialsNonExpired() {

      return true;
  }
  @Override
  public String getPassword() {

      return this.mdp;
  }
  @Override
  public String getUsername() {

      return this.email;
  }
  @Override
  public boolean isEnabled() {

      return this.actif;
  }
}
