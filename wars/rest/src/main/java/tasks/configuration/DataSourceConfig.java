package tasks.configuration;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    final YAMLProperties yamlProperties;

    public DataSourceConfig(YAMLProperties yamlProperties) {
        this.yamlProperties = yamlProperties;
    }

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url(yamlProperties.getUrl());
        dataSourceBuilder.username(yamlProperties.getUser());
        dataSourceBuilder.password(yamlProperties.getPassword());
        return dataSourceBuilder.build();
    }
}
