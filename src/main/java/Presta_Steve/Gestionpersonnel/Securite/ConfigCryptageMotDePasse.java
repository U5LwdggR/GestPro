package Presta_Steve.Gestionpersonnel.Securite;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ConfigCryptageMotDePasse {


  @Bean
  public BCryptPasswordEncoder PasswordEncoder(){
    return new BCryptPasswordEncoder();
  }

}
