package com.krab1.webapp.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.krab1.webapp.entities"})
@EnableJpaRepositories(basePackages = {"com.krab1.webapp.repositories"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}