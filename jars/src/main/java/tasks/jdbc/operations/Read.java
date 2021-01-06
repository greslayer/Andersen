package tasks.jdbc.operations;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.StringJoiner;

@Component
public class Read implements CRUDOperation {

    @Override
    public void execute(Connection connection, String[] args) throws SQLException {
        String query = "SELECT * FROM Employee";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        StringJoiner joiner;
        while (resultSet.next()) {
            joiner = new StringJoiner(", ");
            joiner.add(resultSet.getString(1))
                    .add(resultSet.getString(2))
                    .add(resultSet.getString(3))
                    .add(resultSet.getString(4));
            System.out.println("Employee: " + joiner);
        }

    }
}
