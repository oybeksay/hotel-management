package revolusion.hotelmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import revolusion.hotelmanagement.config.security.SessionUser;

import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class HotelManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelManagementApplication.class, args);
	}

	@Bean
	public AuditorAware<Integer> auditorAware(SessionUser sessionUser) {
		return () -> Optional.of(sessionUser.id());
	}

}
