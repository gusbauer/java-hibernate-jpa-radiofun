package com.dicampus.j2ee;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

import com.dicampus.j2ee.model.CancionEntity;
import com.dicampus.j2ee.model.CantanteEntity;
import com.dicampus.j2ee.model.DescripcionEntity;
import com.dicampus.j2ee.model.ListaReproduccionEntity;

public class RadioFunManagerTest {

    @Test
    public void VetustaMorlaCollectionCreateEntityTest() {
        try {
            CantanteEntity c1 = new CantanteEntity();
            c1.setIsGroup(true);
            c1.setGenero("Indie");
            c1.setNombre("Vetusta Morla");
            c1.setFechaNacimiento(new Date());

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

            DescripcionEntity de2 = new DescripcionEntity();
            de2.setDescripcion("Una canción sobre la migración, sobre viajeros que vienen y van.");
            de2.setPuntuacion(7);
            de2.setAutor("Irene Cid");

            CancionEntity ce2 = new CancionEntity();
            ce2.setGenero("Indie");
            ce2.setDuracion(5.03f);
            ce2.setTitulo("Copenhague");
            ce2.setAutor(c1);
            ce2.setDescripcion(de2);

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
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void BillieEllishCollectionCreateEntityTest() {
        try {
            CantanteEntity c1 = new CantanteEntity();
            c1.setIsGroup(false);
            c1.setGenero("Pop alternativo");
            c1.setNombre("Billie Eilish");
            c1.setFechaNacimiento(new Date());

            DescripcionEntity de1 = new DescripcionEntity();
            de1.setDescripcion("Una canción sobre un chico duro.");
            de1.setAutor("Irene Cid");
            de1.setPuntuacion(9);

            CancionEntity ce1 = new CancionEntity();
            ce1.setGenero("Pop alternativo");
            ce1.setDuracion(3.14f);
            ce1.setTitulo("Bad Guy");
            ce1.setAutor(c1);
            ce1.setDescripcion(de1);

            DescripcionEntity de2 = new DescripcionEntity();
            de2.setDescripcion("Una canción sobre tener todo lo que se quiere");
            de2.setPuntuacion(7);
            de2.setAutor("Irene Cid");

            CancionEntity ce2 = new CancionEntity();
            ce2.setGenero("Pop alternativo");
            ce2.setDuracion(4.05f);
            ce2.setTitulo("Everything i wanted");
            ce2.setAutor(c1);
            ce2.setDescripcion(de2);

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
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void createPlayLists() {
        try {
            VetustaMorlaCollectionCreateEntityTest();
            BillieEllishCollectionCreateEntityTest();

            RadioFunManager manager = new RadioFunManager();
            manager.setup();
            Session session = manager.getSessionFactory().openSession();

            @SuppressWarnings("unchecked")
            List<CancionEntity> ce = session.createQuery("FROM CancionEntity").list();

            ListaReproduccionEntity listaCompleta = new ListaReproduccionEntity();
            listaCompleta.setNombre("lista Completa");
            listaCompleta.setCanciones(new HashSet<>(ce));

            @SuppressWarnings("unchecked")
            List<CancionEntity> ceMejores = session.createQuery("FROM CancionEntity c WHERE c.descripcion.puntuacion > 7").list();

            ListaReproduccionEntity listaMejores = new ListaReproduccionEntity();
            listaMejores.setNombre("lista Mejores");
            listaMejores.setCanciones(new HashSet<>(ceMejores));

            session.beginTransaction();
            session.save(listaCompleta);
            session.save(listaMejores);

            session.getTransaction().commit();
            session.close();
            manager.exit();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
}