package tasks.jdbc.operations;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class Delete implements CRUDOperation {

    @Override
    public void execute(Connection connection, String[] args) throws SQLException {
        String query = "DELETE FROM Employee WHERE ID=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, Integer.parseInt(args[0]));
        if (statement.executeUpdate() < 1) {
            System.out.println("Not changed ID = " + args[0] + " not found");
        }
    }
}
