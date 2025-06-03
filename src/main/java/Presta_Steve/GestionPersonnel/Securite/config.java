package Presta_Steve.GestionPersonnel.Securite;


import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.http.HttpMethod.POST;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.AllArgsConstructor;



@AllArgsConstructor
@EnableWebSecurity
@Configuration
public class config {

    private final JwtFiltre jwtFiltre;


    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
    
        //ancienne syntaxe
        // return http
        //         .cors() //  Active le support CORS
        //         .and()
        //         .csrf(AbstractHttpConfigurer::disable)
        //         .authorizeHttpRequests(auth -> auth
        //             .requestMatchers(POST, "/superadmin/inscription").permitAll()
        //             .requestMatchers(POST, "/superadmin/connexion").permitAll()
        //             .requestMatchers(POST, "/superadmin/activationCompte").permitAll()
        //             .requestMatchers(POST, "/superadmin/resetPassword").permitAll()
        //             .requestMatchers(POST, "/admin/connexion").permitAll()
        //             .requestMatchers(POST, "/admin/activationCompte").permitAll()
        //             .requestMatchers(POST, "/presence/marquerPresence").permitAll()
        //             .requestMatchers("/error").permitAll()
        //             .anyRequest().authenticated()
        //         )
        //         .sessionManagement(session -> session
        //             .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        //         )
        //         .addFilterBefore(jwtFiltre, UsernamePasswordAuthenticationFilter.class)
        //         .build();
        
        //nouvelle syntaxe
        return http
        .cors(cors -> {}) // active le support CORS avec configuration par défaut
        .csrf(AbstractHttpConfigurer::disable) // désactive CSRF
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(POST, "/superadmin/inscription").permitAll()
            .requestMatchers(POST, "/superadmin/connexion").permitAll()
            .requestMatchers(POST, "/superadmin/activationCompte").permitAll()
            .requestMatchers(POST, "/superadmin/resetPassword").permitAll()
            .requestMatchers(POST, "/admin/connexion").permitAll()
            .requestMatchers(POST, "/admin/activationCompte").permitAll()
            .requestMatchers(POST, "/presence/marquerPresence").permitAll()
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