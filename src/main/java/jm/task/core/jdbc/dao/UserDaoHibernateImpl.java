package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
public class UserDaoHibernateImpl implements UserDao {


    public UserDaoHibernateImpl() {
    }


    private void rollbackQuietly(Transaction tx) {

        try {

            if (tx != null && tx.getStatus().canRollback()) {

                tx.rollback();

            }

        } catch (Exception ignored) {

            // не мешаем первопричине

        }

    }


    @Override

    public void createUsersTable() {

        String sql = """
                
                CREATE TABLE IF NOT EXISTS users (
                
                    id BIGINT NOT NULL AUTO_INCREMENT,
                
                    name VARCHAR(50),
                
                    lastName VARCHAR(50),
                
                    age TINYINT,
                
                    PRIMARY KEY(id)
                
                )
                
                """;


        Transaction tx = null;

        try (Session session = Util.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            session.createNativeQuery(sql).executeUpdate();

            tx.commit();

        } catch (Exception e) {

            rollbackQuietly(tx);

            throw new RuntimeException("createUsersTable failed", e);

        }

    }


    @Override

    public void dropUsersTable() {

        String sql = "DROP TABLE IF EXISTS users";


        Transaction tx = null;

        try (Session session = Util.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            session.createNativeQuery(sql).executeUpdate();

            tx.commit();

        } catch (Exception e) {

            rollbackQuietly(tx);

            throw new RuntimeException("dropUsersTable failed", e);

        }

    }


    @Override

    public void saveUser(String name, String lastName, byte age) {

        Transaction tx = null;

        try (Session session = Util.getSessionFactory().openSession()) {

            tx = session.beginTransaction();


            User user = new User(name, lastName, age);

            session.persist(user);


            tx.commit();

        } catch (Exception e) {

            rollbackQuietly(tx);

            throw new RuntimeException("saveUser failed", e);

        }

    }


    @Override

    public void removeUserById(long id) {

        Transaction tx = null;

        try (Session session = Util.getSessionFactory().openSession()) {

            tx = session.beginTransaction();


            User user = session.get(User.class, id);

            if (user != null) {

                session.remove(user);

            }


            tx.commit();

        } catch (Exception e) {

            rollbackQuietly(tx);

            throw new RuntimeException("removeUserById failed", e);

        }

    }


    @Override

    public List<User> getAllUsers() {

        try (Session session = Util.getSessionFactory().openSession()) {

            return session.createQuery("FROM User", User.class).list();

        } catch (Exception e) {

            throw new RuntimeException("getAllUsers failed", e);

        }

    }


    @Override

    public void cleanUsersTable() {

        String sql = "TRUNCATE TABLE users";


        Transaction tx = null;

        try (Session session = Util.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            session.createNativeQuery(sql).executeUpdate();

            tx.commit();

        } catch (Exception e) {

            rollbackQuietly(tx);

            throw new RuntimeException("cleanUsersTable failed", e);

        }

    }
}