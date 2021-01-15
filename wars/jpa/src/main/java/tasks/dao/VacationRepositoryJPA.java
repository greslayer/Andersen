package tasks.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import tasks.model.Vacation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class VacationRepositoryJPA {
    @PersistenceContext
    private final EntityManager entityManager;

    public VacationRepositoryJPA(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Vacation findById(int id) {
        return entityManager.unwrap(Session.class).get(Vacation.class, id);
    }

    public void save(Vacation vacation) {
        Session currentSession = entityManager.unwrap(Session.class);
        Transaction transaction = currentSession.beginTransaction();
        currentSession.save(vacation);
        transaction.commit();
        currentSession.close();
    }
}
