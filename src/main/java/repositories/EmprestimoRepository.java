package repositories;

import domains.Emprestimo;
import repositories.interfaces.iEmprestimoDAO;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class EmprestimoRepository implements iEmprestimoDAO {
    private EntityManager em;

    public EmprestimoRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void salva(Emprestimo emprestimo) {
        em.getTransaction().begin();
        em.merge(emprestimo);
        em.getTransaction().commit();
    }

    @Override
    public void atualiza(Emprestimo emprestimo) {
        em.getTransaction().begin();
        em.merge(emprestimo);
        em.getTransaction().commit();
    }

    @Override
    public Emprestimo buscaPor(Integer id) {
        Query query = em.createQuery("SELECT c FROM EMPRESTIMO WHERE id=:id");
        query.setParameter("id", id);
        try {
            return (Emprestimo) query.getSingleResult();
        }catch (Exception e){
            e.getStackTrace();
            return null;
        }
    }

    @Override
    public List<Emprestimo> buscaTodos() {
        Query query = em.createQuery("SELECT c FROM EMPRESTIMO");
        try {
            return (List<Emprestimo>) query.getResultList();
        }catch (Exception e){
            e.getStackTrace();
            return null;
        }
    }
    
}
