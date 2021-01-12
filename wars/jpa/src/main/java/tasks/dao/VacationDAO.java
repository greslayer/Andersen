package tasks.dao;

import org.springframework.data.repository.CrudRepository;
import tasks.model.Vacation;

public interface VacationDAO extends CrudRepository<Vacation,Long>{
}
