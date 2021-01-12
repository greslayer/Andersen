package tasks.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Vacation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Employee employee;
    @Temporal(value = TemporalType.DATE)
    private Date fromDate;
    @Temporal(value = TemporalType.DATE)
    private Date toDate;

    public Vacation() {
    }

    public Vacation(Employee employee, Date fromDate, Date toDate) {
        this.employee = employee;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date from) {
        this.fromDate = from;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date to) {
        this.toDate = to;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
