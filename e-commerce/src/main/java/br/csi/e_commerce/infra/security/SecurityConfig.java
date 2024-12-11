package br.csi.e_commerce.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AutenticacaiFilter autenticacaoFilter;
    public SecurityConfig(AutenticacaiFilter filtro){
        this.autenticacaoFilter = filtro;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(crsf-> crsf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth->
                        auth.requestMatchers(HttpMethod.POST,"/login").permitAll()
                                .requestMatchers(HttpMethod.GET,"/cliente/listar").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.POST,"/cliente").permitAll()
                                .requestMatchers(HttpMethod.GET,"/cliente/uuid/{uuid}").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET,"/usuario").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET,"/aluno").hasAnyAuthority("ROLE_ALUNO","ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET,"/produto/listar").hasAnyAuthority("ROLE_CLIENTE", "ROLE_ADMIN")
                                .requestMatchers(HttpMethod.POST,"/produto").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET,"/produto/uuid/{uuid}").hasAuthority("ROLE_CLIENTE")
                                .requestMatchers(HttpMethod.POST,"/compra/comprar").hasAnyAuthority("ROLE_CLIENTE")
                                .anyRequest().authenticated())
                .addFilterBefore(this.autenticacaoFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
            throws Exception{
        return configuration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }}

