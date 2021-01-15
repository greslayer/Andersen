package tasks.dao;

import org.springframework.data.repository.CrudRepository;
import tasks.model.Vacation;

public interface VacationRepository extends CrudRepository<Vacation,Long>{
}
