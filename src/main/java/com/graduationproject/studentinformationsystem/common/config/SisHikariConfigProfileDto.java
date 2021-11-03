package com.graduationproject.studentinformationsystem.common.config;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

@Slf4j
@Getter
@Setter
@Builder
public class SisHikariConfigProfileDto {
    private String username;
    private String password;
    private String maxPoolSize;
    private String tnsJdbcUrl;
    private String driverClassName;
    private String connectionTimeout;
    private String maxLifetime;

    public static void checkProfileVariables(SisHikariConfigProfileDto hikariConfigProfile) {
        if (!StringUtils.hasText(hikariConfigProfile.getUsername())) {
            String message = "Hikari username Cannot be Empty!";
            log.error(message);
            throw new NullPointerException(message);
        }
        if (!StringUtils.hasText(hikariConfigProfile.getPassword())) {
            String message = "Hikari password Cannot be Empty!";
            log.error(message);
            throw new NullPointerException(message);
        }
        if (!StringUtils.hasText(hikariConfigProfile.getTnsJdbcUrl())) {
            String message = "Hikari tnsJdbcUrl Cannot be Empty!";
            log.error(message);
            throw new NullPointerException(message);
        }
        if (!StringUtils.hasText(hikariConfigProfile.getMaxPoolSize())) {
            String message = "Hikari maxPoolSize Cannot be Empty!";
            log.error(message);
            throw new NullPointerException(message);
        }
        if (!StringUtils.hasText(hikariConfigProfile.getConnectionTimeout())) {
            String message = "Hikari connectionTimeout Cannot be Empty!";
            log.error(message);
            throw new NullPointerException(message);
        }
        if (!StringUtils.hasText(hikariConfigProfile.getMaxLifetime())) {
            String message = "Hikari maxLifetime Cannot be Empty!";
            log.error(message);
            throw new NullPointerException(message);
        }
    }
}
