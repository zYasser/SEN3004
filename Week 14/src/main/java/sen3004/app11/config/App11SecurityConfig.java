package sen3004.app11.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class App11SecurityConfig {
	
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http.authorizeHttpRequests(auth -> auth
    			.requestMatchers("/css/**", "/img/**", "/actuator/info", "/login.html").permitAll()
    			.requestMatchers("/user-page.html").hasRole("USER")
    			.requestMatchers("/editor-page.html").hasRole("EDITOR")
    			.requestMatchers("/actuator/**").hasRole("ADMIN")
    			.anyRequest().authenticated());
    	
    	http.formLogin(login -> login
    			.loginPage("/login.html")
    			.loginProcessingUrl("/login")
    			.failureUrl("/login.html?loginFailed=true"));
    	
    	http.exceptionHandling(exc -> exc.accessDeniedPage("/access-denied.html"));
    	   	
		return http.build();
    }
    
    @Bean
    public WebSecurityCustomizer  webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("/h2-console/**");
    }
    
	@Bean
	public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}
            
}
