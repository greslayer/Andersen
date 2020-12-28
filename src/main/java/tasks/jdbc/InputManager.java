package tasks.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tasks.jdbc.operations.CRUDOperation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

@Service
public class InputManager {
    @Autowired
    Map<String, CRUDOperation> operations;
    @Autowired
    ConnectionPool pool;

    public void manage(String input, String[] args) {
        try (Connection connection = pool.getConnection()) {
            operations.get(input).execute(connection, args);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
