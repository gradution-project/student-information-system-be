package com.graduationproject.studentinformationsystem.common.config;

import com.zaxxer.hikari.HikariConfig;

public class SisHikariConfiguration {

    private SisHikariConfiguration() {
    }

    public static HikariConfig getHikariConfigBaseValues(final SisHikariConfigProfileDto hikariConfigProfileDto) {
        final HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setUsername(hikariConfigProfileDto.getUsername());
        hikariConfig.setPassword(hikariConfigProfileDto.getPassword());
        hikariConfig.setJdbcUrl(hikariConfigProfileDto.getUrl());
        hikariConfig.setMaximumPoolSize(Integer.parseInt(hikariConfigProfileDto.getMaxPoolSize()));
        hikariConfig.setConnectionTimeout(Long.parseLong(hikariConfigProfileDto.getConnectionTimeout()));
        hikariConfig.setMaxLifetime(Long.parseLong(hikariConfigProfileDto.getMaxLifetime()));
        hikariConfig.setDriverClassName(hikariConfigProfileDto.getDriverClassName());
        hikariConfig.setAutoCommit(true);

        return hikariConfig;
    }
}
