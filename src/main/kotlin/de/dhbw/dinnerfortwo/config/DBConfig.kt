package de.dhbw.dinnerfortwo.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@EnableJpaRepositories(basePackages = ["de.dhbw.dinnerfortwo.impl"])
@EnableTransactionManagement
@Configuration
class DBConfig
