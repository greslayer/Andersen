package tasks.tomcatjsp.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

@Component
public class ConnectionPool {
    private static HikariDataSource pool;

    public ConnectionPool() {
        Properties properties = new Properties();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            FileReader reader = new FileReader("C:\\Users\\gresl\\IdeaProjects\\Andersen\\src\\main\\resources\\db.properties");
            properties.load(reader);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(properties.getProperty("url"));
        config.setUsername(properties.getProperty("user"));
        config.setPassword(properties.getProperty("password"));
        pool = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return pool.getConnection();
    }
}
