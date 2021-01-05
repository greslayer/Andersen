package tasks.tomcatjsp.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import tasks.tomcatjsp.dao.EmployeeDAO;
import tasks.tomcatjsp.model.Employee;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/insert")
public class InsertServlet extends HttpServlet {
    @Autowired
    EmployeeDAO employeeDAO;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String salary = request.getParameter("salary");

        employeeDAO.save(new Employee(id, firstName, lastName, salary));
        response.sendRedirect("list");
    }
}
