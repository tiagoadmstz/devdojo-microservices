package academy.devdojo.youtube.course;

import academy.devdojo.youtube.core.properties.JwtConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"academy.devdojo.youtube.core.models"})
@EnableJpaRepositories(basePackages = {"academy.devdojo.youtube.core.repositories"})
@EnableConfigurationProperties(value = JwtConfiguration.class)
@ComponentScan(value = {"academy.devdojo.youtube"})
public class CourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseApplication.class, args);
    }

}
