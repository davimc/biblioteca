package repositories;

import domains.Livro;
import repositories.interfaces.iLivroDAO;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;


public class LivroRepository implements iLivroDAO{
    private EntityManager em;

    public LivroRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public boolean salva(Livro livro) {
        em.getTransaction().begin();
        em.merge(livro);
        em.getTransaction().commit();
        return true;
    }

    @Override
    public void atualiza(Livro livro) {
        em.getTransaction().begin();
        em.merge(livro);
        em.getTransaction().commit();
    }

    @Override
    public Livro buscaPor(Integer id) {
        Query query = em.createQuery("SELECT c FROM LIVRO WHERE id=:id");
        query.setParameter("id", id);
        try {
            return (Livro) query.getSingleResult();
        }catch (Exception e){
            e.getStackTrace();
            return null;
        }
    }

    @Override
    public List<Livro> buscaTodos() {
        Query query = em.createQuery("SELECT c FROM LIVRO");
        try {
            return (List<Livro>) query.getResultList();
        }catch (Exception e){
            e.getStackTrace();
            return null;
        }
    }
}
