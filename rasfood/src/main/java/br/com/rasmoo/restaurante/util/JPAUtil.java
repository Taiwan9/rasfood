package br.com.rasmoo.restaurante.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private static final EntityManagerFactory RASFOOD = Persistence.createEntityManagerFactory("rasfood");

    public static EntityManager getEntityManagerRasood(){
        return RASFOOD.createEntityManager();
    }
}
