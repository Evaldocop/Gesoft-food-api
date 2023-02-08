package com.gesoft.food.config;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;



@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {
	
	/* 06-12 - Nao precisa quem faz a autenticacao eh o AuthorizationServer
	
	// configuração de usuários em memoria
	//buider
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		    .withUser("evaldo")
		        .password(passwordEncoder().encode("20131show"))
		        .roles("ADMIN")
		    .and()
		    .withUser("gustavo")
	        .password(passwordEncoder().encode("20131show"))
	        .roles("ADMIN");
	}
	
	*/

	//OpaqueToken
	protected void configure(HttpSecurity http) throws Exception{
		
		
		// linguagem fluida 
			http
					/* --- SETANDO PERMISSOES DIRETAMENTE
					 * .authorizeRequests()
					 * .antMatchers(HttpMethod.POST,"/cozinhas/**").hasAnyAuthority(
					 * "EDITAT_COZINHAS")
					 * .antMatchers(HttpMethod.PUT,"/cozinhas/**").hasAnyAuthority(
					 * "EDITAT_COZINHAS")
					 * .antMatchers(HttpMethod.GET,"/cozinhas/**").authenticated()
					 * .anyRequest().authenticated() .and()
					 */
			.csrf().disable()
			.cors()
			.and()
			   .oauth2ResourceServer()
			   .jwt()
			   .jwtAuthenticationConverter(jwtAuthenticationConverter());
	}
	
	private JwtAuthenticationConverter  jwtAuthenticationConverter() {
		var JwtAuthenticationConverter = new JwtAuthenticationConverter();
		
		JwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
			//PEGANDO AS PERMISSOES DO TOKEN
			var authorities = jwt.getClaimAsStringList("authorities");
			if(authorities==null) {
				authorities=Collections.emptyList();
			}
			//FCARREGANDO OS SCOPOS E AS PERMISSOES EXISTENTES
			//OS SCOPOS PODEM SER USADOS COMO AS PERMISSOES
			//(SCOPE_ É UMA PREFIXO ) -> 	@PreAuthorize("SCOPE_WRITE")
			var scopeAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
			Collection<GrantedAuthority> grantedAuthorities = scopeAuthoritiesConverter.convert(jwt);
			grantedAuthorities.addAll(authorities.stream()
					.map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList()));
			
			return grantedAuthorities;
			
			//FOI O PRIMEIRO PASSO RETORNANDO SOMENTE AS PERMISSOES EXISTENTES
			/*return authorities.stream()
					.map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList());*/
		});
		return JwtAuthenticationConverter;
	}
	
	/*
	 * @Bean public JwtDecoder jwtDecoder() { var secretKey = new
	 * SecretKeySpec("20131show20131show20131show20131show20131show".getBytes(),
	 * "HmacSHA256"); return NimbusJwtDecoder.withSecretKey(secretKey).build(); }
	 */
	/* 06-12 - Nao precisa quem faz a autenticacao eh o AuthorizationServer
	
	@Bean
	public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
	
	}
	*/
	
}
