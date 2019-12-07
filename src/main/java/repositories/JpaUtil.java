package repositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class JpaUtil {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    private EntityManager em;

    public JpaUtil() {
        em = emf.createEntityManager();
    }

    public void close(EntityManager em){
        em.close();
        emf.close();
    }
    public void salva(Object obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }
    public Object buscaPor(Integer id){
        em.getTransaction().begin();

    }
}
