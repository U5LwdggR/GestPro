package Presta_Steve.Gestionpersonnel.entities;


import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Admin")
public class Admin implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAd;
    @Column(name = "nomAd")
    private String nomAd; 
    @Column(name = "mdpAd")
    private String mdpAd;
    @Column(name = "emailAd")
    private String emailAd;
    @Column(name = "typeAd")
    private String typeAd;
    @Column(name = "telAd")
    private String telAd; 
    @Column(name = "serviceAd")
    private String serviceAd;
    @Column(name = "statut")
    private int statut;
    @Column(name = "idSup")  
    private Integer idSup;
    @Column(name = "photoAd")
    private String photoAd;
    @Column(name = "actif")
    private boolean actif = false;
    @Column(name = "role")
    private String role = "ADMIN";
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.role));
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

        return false;
    }
    @Override
    public String getPassword() {

        return this.mdpAd;
    }
    @Override
    public String getUsername() {

        return this.emailAd;
    }
    @Override
    public boolean isEnabled() {

        return this.actif;
    }








} 