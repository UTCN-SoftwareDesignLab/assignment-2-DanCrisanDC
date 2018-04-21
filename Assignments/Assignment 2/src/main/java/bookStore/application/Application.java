package bookStore.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages ={"bookStore.model"})
@SpringBootApplication(scanBasePackages = {"bookStore.application", "bookStore.model", "bookStore.repository", "bookStore.service", "bookStore.controller", "bookStore.dto"})
@EnableJpaRepositories(basePackages = {"bookStore.repository"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}