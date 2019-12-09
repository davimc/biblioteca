package repositories;

import domains.Pagamento;
import repositories.interfaces.iPagamentoDAO;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class PagamentoRepository implements iPagamentoDAO , Serializable {
    private static final long serialVersionUID = 1L;
    private EntityManager em;

    public PagamentoRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void salva(Pagamento pagamento) {
        em.getTransaction().begin();
        em.merge(pagamento);
        em.getTransaction().commit();
    }

    @Override
    public void atualiza(Pagamento pagamento) {
        em.getTransaction().begin();
        em.merge(pagamento);
        em.getTransaction().commit();
    }

    @Override
    public Pagamento buscaPor(Integer id) {
        Query query = em.createQuery("SELECT p FROM PAGAMENTO WHERE id=:id");
        query.setParameter("id", id);
        try {
            return (Pagamento) query.getSingleResult();
        }catch (Exception e){
            e.getStackTrace();
            return null;

        }
    }

    @Override
    public List<Pagamento> buscaTodos() {
        Query query = em.createQuery("SELECT p FROM PAGAMENTO");
        try {
            return (List<Pagamento>) query.getResultList();
        }catch (Exception e){
            e.getStackTrace();
            return null;
        }
    }
}
