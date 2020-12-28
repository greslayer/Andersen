package tasks.jdbc.operations;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
@Component
public class Create implements CRUDOperation {

    @Override
    public void execute(Connection connection, String[] args) throws SQLException {
        String query = "INSERT INTO Employee values (?,?,?,?)";
        PreparedStatement statement =connection.prepareStatement(query);
        statement.setInt(1,Integer.parseInt(args[0]));
        for (int i=1;i<args.length;i++){
            statement.setString(i+1,args[i]);
        }
        statement.executeUpdate();

    }
}
