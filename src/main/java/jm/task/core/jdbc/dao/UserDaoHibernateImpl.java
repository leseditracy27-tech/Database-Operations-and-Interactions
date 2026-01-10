package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        executeSql("""
            CREATE TABLE IF NOT EXISTS users (
                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(50),
                lastName VARCHAR(50),
                age TINYINT
            )
        """);
    }

    @Override
    public void dropUsersTable() {
        executeSql("DROP TABLE IF EXISTS users");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session Null = null;
        Session session = Null.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.persist(new User(name, lastName, age));

        tx.commit();
        session.close();

    }

    @Override
    public void removeUserById(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        User user = session.get(User.class, id);
        if (user != null) {
            session.remove(user);
        }

        tx.commit();
        session.close();

    }

    @Override
    public List<User> getAllUsers() {  Session session = HibernateUtil.getSessionFactory().openSession();
        List<User> users = session
                .createQuery("from User", User.class)
                .getResultList();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        executeSql("TRUNCATE TABLE users");

    }
    private void executeSql(String sql) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.createNativeQuery(sql).executeUpdate();
        tx.commit();
        session.close();
    }
}
