package com.dicampus.j2ee.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        try {
            // Carga hibernate.cfg.xml y crea la sesión
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();
            
            System.out.println("\n-------------------------------------------");
            System.out.println("¡CONEXIÓN EXITOSA CON MYSQL Y HIBERNATE!");
            System.out.println("-------------------------------------------\n");
            
            session.close();
            sessionFactory.close();
        } catch (Exception e) {
            System.err.println("\nError en la conexión:");
            e.printStackTrace();
        }
    }
}