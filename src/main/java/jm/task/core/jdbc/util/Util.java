package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;

import org.hibernate.boot.MetadataSources;

import org.hibernate.boot.registry.StandardServiceRegistry;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Util  {
    private static SessionFactory sessionFactory;



    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {

            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()

                    .applySetting("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")

                    .applySetting("hibernate.connection.url", "jdbc:mysql://localhost:3306/testdb")

                    .applySetting("hibernate.connection.username", "root")

                    .applySetting("hibernate.connection.password", "LeeTracy@271020")

                    .applySetting("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")

                    .applySetting("hibernate.hbm2ddl.auto", "none")

                    .applySetting("hibernate.show_sql", "true")

                    .applySetting("hibernate.format_sql", "true")

                    .build();



            sessionFactory = new MetadataSources(registry)

                    .addAnnotatedClass(User.class)

                    .buildMetadata()

                    .buildSessionFactory();

        }

        return sessionFactory;

    }

}

