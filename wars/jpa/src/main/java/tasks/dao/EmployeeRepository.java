package tasks.dao;

import org.springframework.data.repository.CrudRepository;
import tasks.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    void deleteEmployeeById(Long id);
}
