package repositories;

import domains.Cliente;
import repositories.interfaces.iClienteDAO;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class ClienteRepository implements iClienteDAO, Serializable {
    private static final long serialVersionUID = 1L;
    private EntityManager em;

    public ClienteRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void salva(Cliente cliente) {
        em.getTransaction().begin();
        em.merge(cliente);
        em.getTransaction().commit();
    }

    @Override
    public void atualiza(Cliente cliente) {
        em.getTransaction().begin();
        em.merge(cliente);
        em.getTransaction().commit();
    }

    @Override
    public Cliente buscaPor(Integer id) {
        Query query = em.createQuery("SELECT c FROM CLIENTE WHERE id=:id");
        query.setParameter("id", id);
        try {
            return (Cliente) query.getSingleResult();
        }catch (Exception e){
            e.getStackTrace();
            return null;

        }
    }

    @Override
    public List<Cliente> buscaTodos() {
        Query query = em.createQuery("SELECT c FROM CLIENTE");
        try {
            return (List<Cliente>) query.getResultList();
        }catch (Exception e){
            e.getStackTrace();
            return null;
        }
    }
}
