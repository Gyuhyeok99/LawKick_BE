package azaza.lawkick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LawkickApplication {

	public static void main(String[] args) {
		SpringApplication.run(LawkickApplication.class, args);
	}

}
