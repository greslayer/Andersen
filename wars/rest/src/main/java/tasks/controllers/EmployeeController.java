package tasks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import tasks.dao.EmployeeDAO;
import tasks.model.Employee;
import tasks.model.EmployeeEntityModelAssembler;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class EmployeeController {
    final EmployeeDAO employeeDAO;
    final EmployeeEntityModelAssembler assembler;

    @Autowired
    public EmployeeController(EmployeeDAO employeeDAO, EmployeeEntityModelAssembler assembler) {
        this.employeeDAO = employeeDAO;
        this.assembler = assembler;
    }

    @PostMapping(value = "/create")
    public EntityModel<Employee> createEmployee(@RequestBody Employee employee) {
        employeeDAO.save(employee);
        return getEmployeeById(employee.getId());
    }

    @PutMapping(value = "/employee/{id}")
    EntityModel<Employee> replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
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
    public CollectionModel<EntityModel<Employee>> deleteById(@PathVariable Long id){
        employeeDAO.deleteById(id);
        return getAll();
    }

    @GetMapping(value = "/employee/{id}")
    public EntityModel<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeDAO.findById(id)
                .orElseGet(Employee::new);

        return assembler.toModel(employee);
    }


    @GetMapping(value = "/employee")
    public CollectionModel<EntityModel<Employee>> getAll() {
        List<EntityModel<Employee>> employees = employeeDAO.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).getAll()).withSelfRel());
    }
}
