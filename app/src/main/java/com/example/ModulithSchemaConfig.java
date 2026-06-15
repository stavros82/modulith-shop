package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class ModulithSchemaConfig {

    @Bean
    public CommandLineRunner initializeModulithSchema(JdbcTemplate jdbcTemplate) {
        return args -> {
            System.out.println("Forcing creation of Spring Modulith EVENT_PUBLICATION table...");
            jdbcTemplate.execute("""
                CREATE TABLE IF NOT EXISTS EVENT_PUBLICATION (
                  ID UUID NOT NULL,
                  EVENT_TYPE VARCHAR(512) NOT NULL,
                  LISTENER_ID VARCHAR(512) NOT NULL,
                  PUBLICATION_DATE TIMESTAMP WITH TIME ZONE NOT NULL,
                  COMPLETION_DATE TIMESTAMP WITH TIME ZONE,
                  SERIALIZED_EVENT VARCHAR(4000) NOT NULL,
                  PRIMARY KEY (ID)
                );
            """);
        };
    }
}