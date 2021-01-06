package tasks.tomcatjsp.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import tasks.tomcatjsp.model.Employee;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDAO {
    final DataSource pool;
    private final Logger logger = LoggerFactory.getLogger(EmployeeDAO.class);

    public EmployeeDAO(DataSource pool) {
        this.pool = pool;
    }

    public void saveEmployee(Employee employee) {
        int status = 0;
        try (Connection connection = pool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Employee(firstName,lastName,salary) values (?,?,?)"
            );
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getSalary());
            status = statement.executeUpdate();
        } catch (SQLException throwables) {
            logger.error("Exception while inserting Employee", throwables);
        }
    }

    public int updateEmployee(Employee employee) {
        int status = 0;
        try (Connection connection = pool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Employee SET firstName=?, lastName=?, salary=? WHERE ID=?"
            );
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getSalary());
            statement.setInt(4, employee.getId());
            status = statement.executeUpdate();
        } catch (SQLException throwables) {
            logger.error("Error while updating employee", throwables);
        }
        return status;
    }

    public int deleteEmployeeById(int id) {
        int status = 0;
        try (Connection connection = pool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM Employee WHERE ID=?"
            );
            statement.setInt(1, id);
            status = statement.executeUpdate();
        } catch (SQLException throwables) {
            logger.error("Error while deleting Employee", throwables);
        }
        return status;
    }

    public Employee getEmployeeById(int id) {
        Employee employee = new Employee();
        try (Connection connection = pool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Employee WHERE ID=?"
            );
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employee.setId(resultSet.getInt(1));
                employee.setFirstName(resultSet.getString(2));
                employee.setLastName(resultSet.getString(3));
                employee.setSalary(resultSet.getString(4));
            }
        } catch (SQLException throwables) {
            logger.error("Error while looking for Employee by ID", throwables);
        }
        return employee;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        try (Connection connection = pool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Employee"
            );
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt(1));
                employee.setFirstName(resultSet.getString(2));
                employee.setLastName(resultSet.getString(3));
                employee.setSalary(resultSet.getString(4));
                list.add(employee);
            }
        } catch (SQLException throwables) {
            logger.error("Error while getting all Employees", throwables);
        }
        return list;
    }
}
