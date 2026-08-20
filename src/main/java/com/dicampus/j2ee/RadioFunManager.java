package com.dicampus.j2ee;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class RadioFunManager {

    private SessionFactory sessionFactory;

    public void setup() {
        // Carga la configuración del archivo hibernate.cfg.xml
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            e.printStackTrace();
        }
    }

    public void exit() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    // Añade este método getter que faltaba
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}