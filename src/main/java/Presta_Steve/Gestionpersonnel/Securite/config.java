package Presta_Steve.Gestionpersonnel.Securite;


import static org.springframework.http.HttpMethod.POST;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

import lombok.AllArgsConstructor;



@AllArgsConstructor
@EnableWebSecurity
@Configuration
public class config {

    private final JwtFiltre jwtFiltre;


@Bean
public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
    
        return 
            http
                .csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(
                    auth ->
                        auth
                            .requestMatchers(POST,"superadmin/inscription").permitAll()
                            .requestMatchers(POST,"superadmin/connexion").permitAll()
                            .requestMatchers(POST,"superadmin/activationCompte").permitAll()
                            .requestMatchers(POST,"admin/connexion").permitAll()
                            .requestMatchers(POST,"admin/activationCompte").permitAll()
                            .anyRequest().authenticated()
                )
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFiltre,UsernamePasswordAuthenticationFilter.class)
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
}