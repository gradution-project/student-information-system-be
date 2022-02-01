package com.graduationproject.studentinformationsystem.common.config;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

@Slf4j
@Getter
@Builder
public class SisHikariConfigProfileDto {

    private final String username;
    private final String password;
    private final String maxPoolSize;
    private final String tnsJdbcUrl;
    private final String driverClassName;
    private final String connectionTimeout;
    private final String maxLifetime;

    public static void checkProfileVariables(final SisHikariConfigProfileDto hikariConfigProfile) {
        if (!StringUtils.hasText(hikariConfigProfile.getUsername())) {
            final String message = "Hikari username Cannot be Empty!";
            log.error(message);
            throw new NullPointerException(message);
        }
        if (!StringUtils.hasText(hikariConfigProfile.getPassword())) {
            final String message = "Hikari password Cannot be Empty!";
            log.error(message);
            throw new NullPointerException(message);
        }
        if (!StringUtils.hasText(hikariConfigProfile.getTnsJdbcUrl())) {
            final String message = "Hikari tnsJdbcUrl Cannot be Empty!";
            log.error(message);
            throw new NullPointerException(message);
        }
        if (!StringUtils.hasText(hikariConfigProfile.getMaxPoolSize())) {
            final String message = "Hikari maxPoolSize Cannot be Empty!";
            log.error(message);
            throw new NullPointerException(message);
        }
        if (!StringUtils.hasText(hikariConfigProfile.getConnectionTimeout())) {
            final String message = "Hikari connectionTimeout Cannot be Empty!";
            log.error(message);
            throw new NullPointerException(message);
        }
        if (!StringUtils.hasText(hikariConfigProfile.getMaxLifetime())) {
            final String message = "Hikari maxLifetime Cannot be Empty!";
            log.error(message);
            throw new NullPointerException(message);
        }
    }
}
