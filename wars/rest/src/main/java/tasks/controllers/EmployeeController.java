package tasks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tasks.dao.EmployeeDAO;
import tasks.model.Employee;

import java.util.List;



@RestController
public class EmployeeController {
    final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeController(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        employeeDAO.save(employee);
        return getEmployeeById(employee.getId());
    }

    @PutMapping(value = "/employee/{id}")
    ResponseEntity<Employee> replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return employeeDAO.findById(id)
                .map(employee -> {
                            employee.setFirstName(newEmployee.getFirstName());
                            employee.setLastName(newEmployee.getLastName());
                            employee.setSalary(newEmployee.getSalary());
                            employeeDAO.save(employee);
                            return getEmployeeById(id);
                        }
                ).orElseGet(() -> {
                    newEmployee.setId(id);
                    employeeDAO.save(newEmployee);
                    return getEmployeeById(id);
                });
    }

    @DeleteMapping(value = "/employee/{id}")
    public ResponseEntity<List<Employee>> deleteById(@PathVariable Long id) {
        employeeDAO.deleteById(id);
        return getAll();
    }

    @GetMapping(value = "/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.of(employeeDAO.findById(id));
    }


    @GetMapping(value = "/employee")
    public ResponseEntity<List<Employee>> getAll() {
        List<Employee> employees = employeeDAO.findAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}
