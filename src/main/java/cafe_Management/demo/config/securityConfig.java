package cafe_Management.demo.config;

import cafe_Management.demo.jwt.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class securityConfig {
    private final JwtFilter jwtFilter;

    public securityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {})   // ✅ ADD THIS LINE
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login/**").permitAll() // allow login
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll() // swagger
                        .anyRequest().authenticated() // 🔒 protect all other APIs
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }
}
