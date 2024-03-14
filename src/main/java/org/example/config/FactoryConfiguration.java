package org.example.config;

import org.example.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    public SessionFactory sessionFactory;


    private FactoryConfiguration(){
        Configuration configuration = new Configuration().configure().addAnnotatedClass(Admin.class).addAnnotatedClass(Book.class).addAnnotatedClass(Branch.class).addAnnotatedClass(User.class).addAnnotatedClass(UserBook.class);
        sessionFactory = configuration.buildSessionFactory();
    }
    public static FactoryConfiguration getInstance(){
        return factoryConfiguration==null?factoryConfiguration = new FactoryConfiguration(): factoryConfiguration;
    }
    public Session getSession(){
        return sessionFactory.openSession();
    }
}
