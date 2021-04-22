package spring.project.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter{

	@Autowired
	private ImplementacaoUserDatailService implementacaoUserDatailService; 
	
	@Override // Configura as solicitações de acesso por HTTP
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf()
		.disable() //Desativa as configurações padrão de memória do spring
		.authorizeRequests() // Permitir restringir acessos
		.antMatchers(HttpMethod.GET, "/").permitAll() // Quaquer usuário acessa esta página
		.anyRequest().authenticated()
		.and().formLogin().permitAll() // permiti qualquer usuário
		.and().logout() // mapeia URL de logout e invalida usuário autenticado
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	@Override //  Cria autenticação de usuário com o banco de dados ou em memória
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(implementacaoUserDatailService)
		.passwordEncoder(new BCryptPasswordEncoder());
		
		/*
		 * auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
		 * .withUser("admin")
		 * .password("$2a$10$RsUmpdMTIHuTEVZxEtFOH.nfVu8UoiisKoIfBDxRABita0KbJ2f1.")
		 * .roles("ADMIN");
		 */
		
	}
	
	@Override // ignora url Especificas
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/materialize/**");
	}
	
}
