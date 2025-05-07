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





@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SuperAdmin")
public class SuperAdmin implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSup;
    @Column(name = "nomSup")
    private String nomSup;
    @Column(name = "mdpSup")
    private String mdpSup;
    @Column(name = "emailSup")
    private String emailSup;
    @Column(name = "telSup")
    private int telSup;
    @Column(name = "role")
    private String role;
    @Column(name = "photoSup")
    private String photoSup;
    @Column(name = "actif")
    private boolean actif = false;

    
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

        return true;
    }
    @Override
    public String getPassword() {

        return this.mdpSup;
    }
    @Override
    public String getUsername() {

        return this.emailSup;
    }
    @Override
    public boolean isEnabled() {

        return this.actif;
    }


}
