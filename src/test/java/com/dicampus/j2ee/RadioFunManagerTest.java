package com.dicampus.j2ee;

import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

import com.dicampus.j2ee.model.CancionEntity;
import com.dicampus.j2ee.model.CantanteEntity;
import com.dicampus.j2ee.model.DescripcionEntity;

public class RadioFunManagerTest {

    // Test 1: Prueba la relación @OneToOne entre Cancion y Descripcion
    @Test
    public void CancionEntityTest() {
        try {
            CantanteEntity cantante = new CantanteEntity();
            cantante.setNombre("Vetusta Morla");
            cantante.setGenero("Indie");

            DescripcionEntity de = new DescripcionEntity();
            de.setDescripcion("Una canción sobre derribar las barreras que no nos dejan avanzar.");
            de.setAutor("Irene Cid");
            de.setPuntuacion(9);

            CancionEntity ce = new CancionEntity();
            ce.setAutor(cantante); // Ahora se le pasa el objeto CantanteEntity
            ce.setGenero("Indie");
            ce.setDuracion(5.18f);
            ce.setTitulo("Consejo de Sabios");
            ce.setDescripcion(de);

            RadioFunManager manager = new RadioFunManager();
            manager.setup();

            Session session = manager.getSessionFactory().openSession();
            session.beginTransaction();

            session.save(cantante);
            session.save(de);
            session.save(ce);

            session.getTransaction().commit();
            session.close();
            manager.exit();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Error en el test CancionEntityTest: " + e.getMessage());
        }
    }

    // Test 2: Prueba la relación @OneToMany entre Cantante y Cancion
    @Test
    public void CantanteCancionRelationTest() {
        try {
            CantanteEntity cantante = new CantanteEntity();
            cantante.setNombre("Vetusta Morla");
            cantante.setGenero("Indie");
            cantante.setIsGroup(true);

            CancionEntity c1 = new CancionEntity();
            c1.setTitulo("Copenhague");
            c1.setAutor(cantante); // Ahora se le pasa el objeto CantanteEntity
            c1.setDuracion(5.00f);
            c1.setGenero("Indie");

            cantante.addCancion(c1);

            RadioFunManager manager = new RadioFunManager();
            manager.setup();

            Session session = manager.getSessionFactory().openSession();
            session.beginTransaction();

            session.save(cantante);

            session.getTransaction().commit();
            session.close();
            manager.exit();
            
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Error en el test CantanteCancionRelationTest: " + e.getMessage());
        }
    }

    // Test 3: Colección y asignación completa de Vetusta Morla
    @Test
    public void VetustaMorlaCollectionCreateEntityTest() {
        try {
            // 1. Crear Cantante
            CantanteEntity c1 = new CantanteEntity();
            c1.setIsGroup(true);
            c1.setGenero("Indie");
            c1.setNombre("Vetusta Morla");
            c1.setFechaNacimiento(new java.util.Date());

            // 2. Crear Canción 1 con su descripción
            DescripcionEntity de1 = new DescripcionEntity();
            de1.setDescripcion("Una canción sobre derribar las barreras que no nos dejan avanzar.");
            de1.setAutor("Irene Cid");
            de1.setPuntuacion(9);

            CancionEntity ce1 = new CancionEntity();
            ce1.setGenero("Indie");
            ce1.setDuracion(5.18f);
            ce1.setTitulo("Valiente");
            ce1.setAutor(c1);
            ce1.setDescripcion(de1);

            // 3. Crear Canción 2 con su descripción
            DescripcionEntity de2 = new DescripcionEntity();
            de2.setDescripcion("Una canción sobre la migración, sobre viajeros que vienen y van.");
            de2.setAutor("Irene Cid");
            de2.setPuntuacion(7);

            CancionEntity ce2 = new CancionEntity();
            ce2.setGenero("Indie");
            ce2.setDuracion(5.03f);
            ce2.setTitulo("Copenhague");
            ce2.setAutor(c1);
            ce2.setDescripcion(de2);

            // 4. Guardar en base de datos
            RadioFunManager manager = new RadioFunManager();
            manager.setup();

            Session session = manager.getSessionFactory().openSession();
            session.beginTransaction();

            session.save(c1);
            session.save(de1);
            session.save(ce1);
            session.save(de2);
            session.save(ce2);

            session.getTransaction().commit();
            session.close();
            manager.exit();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Error en VetustaMorlaCollectionCreateEntityTest: " + e.getMessage());
        }
    }
}