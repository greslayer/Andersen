package tasks.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import tasks.model.Employee;
@Transactional
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    void deleteEmployeeById(Long id);
}
