package spring.formation.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import spring.formation.config.jwt.JwtHeaderAuthorizationFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, JwtHeaderAuthorizationFilter jwtFilter) throws Exception {
		http.authorizeHttpRequests(authorize -> {
			authorize.requestMatchers("/api/utilisateur/connexion").anonymous(); // Autorisé à tout le monde
			authorize.requestMatchers("/api/**").hasAnyRole("ADMIN", "SUPER_ADMIN");
			authorize.requestMatchers("/**").permitAll();
		});

		// Méthode d'authentification par HTTP Basic
		http.httpBasic(Customizer.withDefaults());

		// Désactiver la protection CSRF
		http.csrf(c -> c.disable()); // TODO

		// Configurer les CORS (Cross-Origine Resources Sharing)
		http.cors(c -> {
			CorsConfigurationSource source = request -> {
				CorsConfiguration config = new CorsConfiguration();

				// On autorise tout le monde
				config.setAllowedOrigins(List.of("*"));

				// On autorise toutes les commandes HTTP (GET, POST, PUT, ...)
				config.setAllowedMethods(List.of("*"));

				// On autorise toutes les en-têtes HTTP
				config.setAllowedHeaders(List.of("*"));

				return config;
			};

			c.configurationSource(source);
		});

		// Positionner le filtre JWT AVANT le filter
		// UsernamePasswordAuthenticationFilter
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		http.sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return http.build();
	}

	// Grace à ce Bean, on pourra injecter un AuthenticationManager directement
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		// Pas d'encadage sur les mots de passe - PAS BIEN
//		return NoOpPasswordEncoder.getInstance();

		// Encodage Blowfish
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	public UserDetailsService inMemory() {
//		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//
//		// Création d'un utilisateur
//		manager.createUser(User.withUsername("user").password(passwordEncoder().encode("123456")).roles("USER").build());
//
//		// Création d'un administrateur
//		manager.createUser(User.withUsername("admin").password(passwordEncoder().encode("123456")).roles("ADMIN").build());
//
//		return manager;
//	}

}
