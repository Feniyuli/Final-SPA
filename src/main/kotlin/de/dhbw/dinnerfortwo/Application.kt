package de.dhbw.dinnerfortwo

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class Application

private val log = LoggerFactory.getLogger(Application::class.java)

fun main(args: Array<String>) {
    log.info("Starting Dinner for Two Service.")
    runApplication<Application>(*args)
}
