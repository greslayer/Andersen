package tasks.tomcatjsp.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import tasks.tomcatjsp.dao.EmployeeDAO;
import tasks.tomcatjsp.model.Employee;

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
    EmployeeDAO employeeDAO;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> list = employeeDAO.getAllEmployees();
        request.setAttribute("listEmployee", list);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("EmployeeList.jsp");
        requestDispatcher.forward(request, response);
    }
}
