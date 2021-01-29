package tasks.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tasks.dao.VacationRepositoryJPA;
import tasks.model.Employee;
import tasks.model.Vacation;

import java.util.concurrent.TimeUnit;

@Component
public class VacationServiceImpl implements VacationService {
    @Autowired
    VacationRepositoryJPA vacationRepositoryJPA;
    private static final long defaultAmountOfVacationDays = 28;

    @Override
    public void processVacation(Employee employee, Vacation vacation) {
        long registeredVacationDays = 0;
        for (Vacation registeredVacation : employee.getVacations()) {
            registeredVacationDays += getDaysFromVacation(registeredVacation);
        }

        if (registeredVacationDays + getDaysFromVacation(vacation) < defaultAmountOfVacationDays) {
            vacationRepositoryJPA.save(vacation);
        }
    }

    private long getDaysFromVacation(Vacation vacation) {
        long diffInMillis = Math.abs(vacation.getFromDate().getTime() - vacation.getToDate().getTime());
        return TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
    }
}
