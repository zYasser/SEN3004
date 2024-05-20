package sen3004.app10.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class App10SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http.authorizeHttpRequests(auth -> auth
    			.requestMatchers("/css/**", "/img/**", "/actuator/info", "/login.html").permitAll()
    			.anyRequest().authenticated());
    	
    	http.formLogin(login -> login
    			.loginPage("/login.html")
    			.loginProcessingUrl("/login")
    			.failureUrl("/login.html?loginFailed=true"));
   	
      return http.build();
    }
    
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("/h2-console/**");
    }
            
}
