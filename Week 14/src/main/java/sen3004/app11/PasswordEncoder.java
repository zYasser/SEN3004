package sen3004.app11;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

	@Test
	public void generateEncodedPasswords() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.printf("{bcrypt}%s\n", encoder.encode("secret2"));
		System.out.printf("{bcrypt}%s\n", encoder.encode("secret3"));
	}

}
