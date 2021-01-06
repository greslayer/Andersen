package tasks.jdbc.operations;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class Update implements CRUDOperation {

    @Override
    public void execute(Connection connection, String[] args) throws SQLException {
        String query = "UPDATE Employee SET firstName=?, lastName=?, salary=? WHERE ID=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(4, Integer.parseInt(args[0]));
        for (int i = 0; i < args.length - 1; i++) {
            statement.setString(i + 1, args[i + 1]);
        }
        if (statement.executeUpdate() < 1) {
            System.out.println("Not changed ID = " + args[0] + " not found");
        }
    }
}
