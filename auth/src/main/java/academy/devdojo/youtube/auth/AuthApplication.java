package academy.devdojo.youtube.auth;

import academy.devdojo.youtube.core.properties.JwtConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableEurekaClient
@SpringBootApplication
@EnableConfigurationProperties(value = JwtConfiguration.class)
@ComponentScan({"academy.devdojo.youtube"})
@EntityScan(basePackages = {"academy.devdojo.youtube.core.models"})
@EnableJpaRepositories(basePackages = {"academy.devdojo.youtube.core.repositories"})
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}
