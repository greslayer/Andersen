package tasks.services;

import tasks.model.Employee;
import tasks.model.Vacation;

public interface VacationService {
    void processVacation(Employee employee, Vacation vacation);
}
