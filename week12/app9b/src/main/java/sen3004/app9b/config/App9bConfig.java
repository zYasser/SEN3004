package sen3004.app9b.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class App9bConfig {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
