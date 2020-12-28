package tasks.jdbc.operations;

import java.sql.Connection;
import java.sql.SQLException;

public interface CRUDOperation {
    void execute(Connection connection, String[] args) throws SQLException;
}
