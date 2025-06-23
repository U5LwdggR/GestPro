package Presta_Steve.Gestionpersonnel.security;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.GET;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableMethodSecurity
public class Config {

  private final FiltreJwt jwtFiltre;

  @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
    
        return http
        .cors(cors -> {}) // active le support CORS avec configuration par défaut
        .csrf(AbstractHttpConfigurer::disable) // désactive CSRF
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(POST, "/utilisateurs/creation-de-compte").permitAll()
            .requestMatchers(POST, "/utilisateurs/activation-de-compte").permitAll()
            .requestMatchers(POST, "/utilisateurs/connexion").permitAll()
            .requestMatchers(GET, "/epreuve/liste-epreuve").permitAll()
            .requestMatchers(GET, "epreuve/{id}/pdf").permitAll()
            .requestMatchers(GET, "correction/{id}/pdf").permitAll()
            .requestMatchers(GET, "/classe/getAll").permitAll()
            .requestMatchers(GET, "/correction/ajouter").permitAll()
            .requestMatchers(GET, "/matiere/Lister-les-matieres").permitAll()
            .requestMatchers(GET, "/session/Lister-les-sessions").permitAll()
            .requestMatchers(GET, "/option/Lister-les-options").permitAll()
            .requestMatchers(GET, "/examen/lister-les-examens").permitAll()
            .requestMatchers(GET, "/sequence/Lister-les-sequences").permitAll()
            .requestMatchers(GET, "/etablissement/lister-les-etablissements").permitAll()
            .requestMatchers("/error").permitAll()
            .anyRequest().authenticated()
        )
        .sessionManagement(session -> 
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .addFilterBefore(jwtFiltre, UsernamePasswordAuthenticationFilter.class)
        .build();
    }
    


@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();

}


@Bean
public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder());
    return authProvider;
}



@Bean
public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOrigins(List.of("http://localhost:3000")); // Frontend
    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    config.setAllowedHeaders(List.of("*")); // Autorise tous les headers
    config.setAllowCredentials(true); // Important pour les cookies/token

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    return source;
}
}
