package tasks.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import tasks.dao.EmployeeRepository;
import tasks.model.Employee;
import tasks.model.Vacation;
import tasks.services.VacationService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = "/update")
public class UpdateServlet extends HttpServlet {
    private Logger logger;
    @Autowired
    VacationService vacationService;
    @Autowired
    EmployeeRepository employeeRepository;


    public void init(ServletConfig config) throws ServletException {
        logger = LoggerFactory.getLogger(UpdateServlet.class);
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String salary = request.getParameter("salary");
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date from = new Date();
        Date to = new Date();
        try {
            to = format.parse(toDate);
            from = format.parse(fromDate);
        } catch (ParseException e) {
            logger.error("Error while parsing dates, set today's date", e);
        }

        Employee employee = employeeRepository.findById(id).orElse(new Employee(id, firstName, lastName, salary));
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setSalary(salary);

        Vacation vacation = new Vacation(employee, from, to);

        vacationService.processVacation(employee, vacation);
        employeeRepository.save(employee);
        response.sendRedirect("list");
    }
}
