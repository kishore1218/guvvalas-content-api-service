package com.guvvalas.api.config;

import com.guvvalas.api.service.ApiServiceImpl;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.Properties;

@Slf4j
@Configuration
public class AppConfig {


//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource.hikari")
//    public HikariConfig hikariConfig() {
//        return new HikariConfig();
//    }
//
//    @Bean
//    @Primary
//    public DataSource dataSource() {
//        return new HikariDataSource(hikariConfig());
//    }

//    @Bean
//    public DataSource dataSource(){
//        Properties poolProperties = new Properties();
//        poolProperties.setProperty("dataSourceClassName", "com.yugabyte.ysql.YBClusterAwareDataSource");
//        poolProperties.setProperty("maximumPoolSize", "10");
//        poolProperties.setProperty("dataSource.serverName", "127.0.0.1");
//        poolProperties.setProperty("dataSource.portNumber", "5433");
//        poolProperties.setProperty("dataSource.databaseName", "yugabyte");
//        poolProperties.setProperty("dataSource.user", "yugabyte");
//        poolProperties.setProperty("dataSource.password", "yugabyte");
//        //// If you want to provide additional end points
//        //        String additionalEndpoints = "127.0.0.2:5433,127.0.0.3:5433,127.0.0.4:5433,127.0.0.5:5433";
//        //        poolProperties.setProperty("dataSource.additionalEndpoints", additionalEndpoints);
//        //// If you want to load balance between specific geo locations using topology keys
//        //        String geoLocations = "cloud1.region1.zone1,cloud1.region2.zone2";
//        //        poolProperties.setProperty("dataSource.topologyKeys", geoLocations);
//
//        poolProperties.setProperty("poolName", "TEST-POOL");
//
//        HikariConfig config = new HikariConfig(poolProperties);
//        config.validate();
//        return  new HikariDataSource(config);
//    }


    @Bean
    public NamedParameterJdbcTemplate jdbcTemplate1(DataSource dataSource ){
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public ApiServiceImpl apiService(){

        return ApiServiceImpl.builder()
                .preHook(req->{
                    log.info("prehook-consumer-{}",req);
                })
                .postHook(res->{
                    log.info("posthook-consumer-{}",res);
                })
                .build();
    }

}
