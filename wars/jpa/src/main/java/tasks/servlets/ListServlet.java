package tasks.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import tasks.dao.EmployeeRepository;
import tasks.model.Employee;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "listServlet", urlPatterns = "/list")
public class ListServlet extends HttpServlet {
    @Autowired
    EmployeeRepository employeeRepository;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Iterable<Employee> list = employeeRepository.findAll();
        request.setAttribute("listEmployee", list);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("EmployeeList.jsp");
        requestDispatcher.forward(request, response);
    }
}
