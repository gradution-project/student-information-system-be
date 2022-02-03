package com.graduationproject.studentinformationsystem.common.config;

import com.graduationproject.studentinformationsystem.common.util.constant.SisConstants.DbProfile;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.sql2o.Sql2o;

import javax.sql.DataSource;

@Slf4j
@Profile({DbProfile.MYSQL, DbProfile.MYSQL_LIVE, DbProfile.DEFAULT})
@Configuration
@RequiredArgsConstructor
public class SisMysqlHikariConfiguration {

    private final Environment environment;

    @Bean
    public DataSource hikariMysql() {
        log.debug("Hikari MySQL Datasource Configuration Call Started!");

        final SisHikariConfigProfileDto hikariConfigMysqlProfile = getHikariConfigProfile(environment);

        SisHikariConfigProfileDto.checkProfileVariables(hikariConfigMysqlProfile);

        final HikariConfig hikariConfigMysql = getHikariConfig(hikariConfigMysqlProfile);

        log.debug("Hikari MySQL Datasource Successfully Configured!");
        return new HikariDataSource(hikariConfigMysql);
    }

    @Bean
    Sql2o sql2oMysql(DataSource hikariMysql) {
        return new Sql2o(hikariMysql);
    }

    private static SisHikariConfigProfileDto getHikariConfigProfile(final Environment environment) {
        return SisHikariConfigProfileDto.builder()
                .username(environment.getProperty("hikari-mysql.username"))
                .password(environment.getProperty("hikari-mysql.password"))
                .url(environment.getProperty("hikari-mysql.url"))
                .maxPoolSize(environment.getProperty("hikari-mysql.max-pool-size"))
                .connectionTimeout(environment.getProperty("hikari-mysql.connection-timeout"))
                .maxLifetime(environment.getProperty("hikari-mysql.max-lifetime"))
                .driverClassName("com.mysql.cj.jdbc.Driver").build();
    }

    private static HikariConfig getHikariConfig(final SisHikariConfigProfileDto hikariConfigProfileDto) {
        return SisHikariConfiguration.getHikariConfigBaseValues(hikariConfigProfileDto);
    }
}
