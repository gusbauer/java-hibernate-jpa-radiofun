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
            DescripcionEntity de = new DescripcionEntity();
            de.setDescripcion("Una canción sobre derribar las barreras que no nos dejan avanzar.");
            de.setAutor("Irene Cid");
            de.setPuntuacion(9);

            CancionEntity ce = new CancionEntity();
            ce.setAutor("Vetusta Morla");
            ce.setGenero("Indie");
            ce.setDuracion(5.18f);
            ce.setTitulo("Consejo de Sabios");

            ce.setDescripcion(de);

            RadioFunManager manager = new RadioFunManager();
            manager.setup();

            Session session = manager.getSessionFactory().openSession();
            session.beginTransaction();

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
            c1.setAutor("Vetusta Morla");
            c1.setDuracion(5.00f);
            c1.setGenero("Indie");

            cantante.addCancion(c1);

            RadioFunManager manager = new RadioFunManager();
            manager.setup();

            Session session = manager.getSessionFactory().openSession();
            session.beginTransaction();

            session.save(cantante); // CascadeType.ALL guardará la canción automáticamente

            session.getTransaction().commit();
            session.close();
            manager.exit();
            
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Error en el test CantanteCancionRelationTest: " + e.getMessage());
        }
    }
}