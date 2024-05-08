package adpro.b10.epicarcade_functional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("adpro.b10.epicarcade_functional.review.model")
public class EpicarcadeFunctionalApplication {

	public static void main(String[] args) {
		SpringApplication.run(EpicarcadeFunctionalApplication.class, args);
	}

}
