package tasks.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tasks.dao.VacationRepositoryJPA;
import tasks.model.Employee;
import tasks.model.Vacation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class VacationServiceImplTest {
    @InjectMocks
    private static VacationServiceImpl vacationService;
    @Mock
    private VacationRepositoryJPA vacationRepositoryJPA;

    private static Employee employee;
    private static Vacation oneDayVacation;
    private static Vacation twentySixDaysVacation;
    private static Vacation thirtyDaysVacation;

    @BeforeEach
    public void init() {
        employee = new Employee();
        oneDayVacation = parseDates("2021-01-02", "2021-01-03");
        twentySixDaysVacation = parseDates("2021-01-01", "2021-01-27");
        thirtyDaysVacation = parseDates("2021-01-01", "2021-01-30");
    }


    @Test
    void savesIfHasNoVacations() {
        vacationService.processVacation(employee, oneDayVacation);
        verify(vacationRepositoryJPA).save(oneDayVacation);
    }

    @Test
    void saveIfHasAlmostMaxVacations() {
        Set<Vacation> vacations = new HashSet<>();
        vacations.add(oneDayVacation);
        employee.setVacations(vacations);
        vacationService.processVacation(employee, twentySixDaysVacation);
        verify(vacationRepositoryJPA).save(twentySixDaysVacation);
    }

    @Test
    void notSave() {
        Employee employee = new Employee();
        vacationService.processVacation(employee, thirtyDaysVacation);
        verify(vacationRepositoryJPA, never()).save(any());
    }

    private static Vacation parseDates(String fromDate, String toDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date from = new Date();
        Date to = new Date();
        try {
            from = format.parse(fromDate);
            to = format.parse(toDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Vacation(employee, from, to);
    }
}