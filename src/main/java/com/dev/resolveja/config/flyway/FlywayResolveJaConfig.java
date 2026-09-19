package com.dev.resolveja.config.flyway;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration 
public class FlywayResolveJaConfig {

    
    @Primary 
    @Bean("flywayresolvejabean")
    Flyway flywayResolveJa(DataSource dataSource) {
        Flyway flyway = Flyway.configure()
        .schemas("sc_resolveja")
        .dataSource(dataSource)
        .createSchemas(true)
        .locations("classpath:db/migration")
        .baselineOnMigrate(true)
        .table("flyway_schema_history_resolveja")
        .load();

        flyway.migrate();
        flyway.repair();

        return flyway;
    }

}
