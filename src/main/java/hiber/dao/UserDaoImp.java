package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUserByCar(String name, int series) {
        Query<Car> query = sessionFactory.getCurrentSession().createQuery("FROM Car WHERE name = :paramName and  :paramSeries = :paramSeries");
        query.setParameter("paramName", name);
        query.setParameter("paramSeries", series);
        Car car = query.getSingleResult();
        Long id = (Long)car.getIdCar();
        TypedQuery<User> query1 = sessionFactory.getCurrentSession().createQuery("from User where id =" + id);
        return query1.getSingleResult();
    }
}
