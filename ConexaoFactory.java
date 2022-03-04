package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConexaoFactory {

    private static EntityManagerFactory factory;


    static {
        factory = Persistence.createEntityManagerFactory("cadastroclientejpa-PU");
    }

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();

    }

    public static void close(){
        factory.close();
    }
}
