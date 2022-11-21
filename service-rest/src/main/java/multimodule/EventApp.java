package multimodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"multimodule"})
@EntityScan(basePackages = {"multimodule"})
@EnableJpaRepositories(basePackages = {"multimodule.repository"})
@Import({SwaggerConfig.class})
public class EventApp {
    public static void main(String[] args) {
        SpringApplication.run(EventApp.class, args);
    }

}
