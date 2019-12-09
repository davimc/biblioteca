import domains.Cliente;
import domains.Livro;
import org.junit.Assert;
import org.junit.Test;
import repositories.LivroRepository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RepositoryTest {

    private Livro l1 = new Livro("Autor A", "Livro 1");
    private Livro l2 = new Livro("Autor B", "Livro 2");
    private Livro l4 = new Livro("Autor A", "Livro 4");
    private Livro l3 = new Livro("Autor C", "Livro 3");

    private Cliente u1 = new Cliente("Davi", "5298");
    private Cliente u2 = new Cliente("Carol", "8525");
    private Cliente u3 = new Cliente("Jenifer", "1303");

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    @Test
    public void testaSalvarLivro(){
        LivroRepository lr = new LivroRepository(emf.createEntityManager());
        Assert.assertEquals(true, lr.salva(l1));

    }
}
