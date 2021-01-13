package tasks.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tasks.model.Employee;

public interface EmployeeDAO extends JpaRepository<Employee,Long> {
}
