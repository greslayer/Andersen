package tasks.tomcatjsp.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    final YAMLConfig yamlConfig;

    public DataSourceConfig(YAMLConfig yamlConfig) {
        this.yamlConfig = yamlConfig;
    }

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url(yamlConfig.getUrl());
        dataSourceBuilder.username(yamlConfig.getUser());
        dataSourceBuilder.password(yamlConfig.getPassword());
        return dataSourceBuilder.build();
    }
}
