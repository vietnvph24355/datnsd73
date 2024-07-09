package com.example.fploy.datn.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
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
//                        .requestMatchers(new AntPathRequestMatcher("/admin/api/file/**")).permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/admin/api/chi-tiet-san-pham/**")).permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/admin/api/hinh-anh-san-pham/**")).permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/admin/api/gio-hang/**")).permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/admin/api/gio-hang-chi-tiet/**")).permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/admin/api/hoa-don/**")).permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/client/api/don-hang/**")).permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/admin/api/giao-dich/**")).permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/admin/api/hoa-don-chi-tiet/**")).permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/api/dia-chi/**")).permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/admin/api/voucher-chi-tiet/**")).permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/admin/api/voucher/**")).permitAll()
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
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http.authorizeHttpRequests(auth -> auth.requestMatchers(new AntPathRequestMatcher("/api/**")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/admin/api/**")).hasAnyAuthority("ADMIN","STAFF")
                    .requestMatchers(new AntPathRequestMatcher("/api/**")).hasAnyAuthority("CLIENT")
                    .requestMatchers(new AntPathRequestMatcher("/admin/api/hoa-don/**")).hasAnyAuthority("CLIENT")
                    .requestMatchers(new AntPathRequestMatcher("/api/**", "/client/api/don-hang/**")).hasAnyAuthority("CLIENT")

                    .anyRequest().authenticated()
        )
                .oauth2Login(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .build();


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

}
