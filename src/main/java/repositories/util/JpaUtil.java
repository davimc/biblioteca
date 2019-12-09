package repositories.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");


    public static EntityManager criaEntityManager() {
        return emf.createEntityManager();
    }

    public static void close(EntityManager em){
        em.close();
        emf.close();
    }

}
