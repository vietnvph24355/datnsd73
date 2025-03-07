package com.example.fploy.datn.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration{

    private JwtuthenticationFilter jwtuthenticationFilter;

    public SecurityConfiguration(JwtuthenticationFilter jwtuthenticationFilter) {
        this.jwtuthenticationFilter = jwtuthenticationFilter;
    }

    @Bean
    public UserDetailsService userDetailsService(){return new UserInforDetailService();}

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.cors().and().csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request.requestMatchers(new AntPathRequestMatcher("/api/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/admin/api/san-pham/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/admin/api/vn-pay/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/admin/api/dia-chi/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/admin/api/don-hang/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/admin/api/giao-dich/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/admin/api/voucher/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/admin/api/mau-sac/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/admin/api/loai-de/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/admin/api/dia-hinh-san/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/admin/api/kich-co/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/admin/api/file/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/admin/api/chi-tiet-san-pham/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/admin/api/hinh-anh-san-pham/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/admin/api/gio-hang/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/admin/api/gio-hang-chi-tiet/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/admin/api/hoa-don/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/client/api/don-hang/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/admin/api/giao-dich/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/admin/api/hoa-don-chi-tiet/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/dia-chi/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/admin/api/voucher-chi-tiet/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/admin/api/voucher/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/**", "/api/sign-up")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/**", "/api/google")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/admin/api/**")).hasAnyAuthority("ADMIN","STAFF")
                        .requestMatchers(new AntPathRequestMatcher("/admin/api/hoa-don/**")).hasAnyAuthority("CLIENT")
                        .requestMatchers(new AntPathRequestMatcher("/api/**", "/client/api/don-hang/**")).hasAnyAuthority("CLIENT")

                        .anyRequest().authenticated())

                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider()).addFilterBefore(
                        jwtuthenticationFilter, UsernamePasswordAuthenticationFilter.class
                ).build();

    }

    @Bean
    public SecurityFilterChain securityFilterChain_GG(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(new AntPathRequestMatcher("/")).permitAll()
                                .anyRequest().authenticated()
                )
                .cors().and()
                .oauth2Login(oauth2Login ->
                        oauth2Login
                                .userInfoEndpoint(userInfoEndpoint ->
                                        userInfoEndpoint.oidcUserService(this.oidcUserService())
                                )
                );
        return http.build();
    }




    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)throws Exception{
        return config.getAuthenticationManager();
    }

    private OidcUserService oidcUserService() {
        return new OidcUserService();
    }

}
