package com.dicampus.j2ee;

import java.util.Date;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

// Importamos la entidad desde el paquete model
import com.dicampus.j2ee.model.ProductEntity;

public class ProductEntityTest {

    @Test
    public void ProductoInsertTest() {
        Session session = null;
        try {
            RadioFunManager manager = new RadioFunManager();
            manager.setup();
            session = manager.getSessionFactory().openSession();

            ProductEntity pr1 = new ProductEntity();
            pr1.setEmpaquetado(true);
            pr1.setFechaCaducidad(new Date());
            pr1.setNombre("Queso en lonchas ");
            pr1.setMarca("Garcia Vaquero ");
            pr1.setPrecio(10.5f);
            session.save(pr1);

            ProductEntity pr2 = new ProductEntity();
            pr2.setEmpaquetado(true);
            pr2.setFechaCaducidad(new Date());
            pr2.setNombre("Queso en lonchas ");
            pr2.setMarca("Garcia Vaquero ");
            pr2.setPrecio(10.5f);
            session.save(pr2);

            session.close();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}