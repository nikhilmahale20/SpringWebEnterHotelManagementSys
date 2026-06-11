package org.example.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class HikariConfigUtil {

    private static final HikariDataSource dataSource;

    static {

        HikariConfig config =
                new HikariConfig();

        config.setJdbcUrl(
                "jdbc:mysql://localhost:9090/WebEnterpriseHotelManagementSystem/"
        );

        config.setUsername(
                "root"
        );

        config.setPassword(
                "Bur@76943"
        );

        config.setDriverClassName(
                "com.mysql.cj.jdbc.Driver"
        );

        config.setMaximumPoolSize(10);

        config.setMinimumIdle(2);

        config.setIdleTimeout(30000);

        config.setPoolName(
                "HotelPool"
        );

        dataSource =
                new HikariDataSource(
                        config
                );
    }

    public static DataSource getDataSource() {

        return dataSource;
    }

    public static void shutdown() {

        dataSource.close();
    }
}