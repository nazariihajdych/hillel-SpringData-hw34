package ua.hillel.springdataapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"ua.hillel.springdataapp.repo"})
public class SpringDataConfig {
}
