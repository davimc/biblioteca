import domains.Livro;
import domains.Cliente;
import org.junit.Assert;
import org.junit.Test;
import services.EmprestimoService;
import services.UsuarioService;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmprestimoTest {

    private Livro l1 = new Livro("Autor A", "Livro 1");
    private Livro l2 = new Livro("Autor B", "Livro 2");
    private Livro l3 = new Livro("Autor C", "Livro 3");
    private Livro l4 = new Livro("Autor A", "Livro 4");

    private Cliente u1 = new Cliente("Davi", "5298");
    private Cliente u2 = new Cliente("Carol", "8525");
    private Cliente u3 = new Cliente("Jenifer", "1303");

    @Test
    public void testaEmprestimoLivroSemReserva() {

        EmprestimoService em = new EmprestimoService();


        Assert.assertEquals(true, em.emprestaLivro(l1, u2, LocalDate.now()));
    }
    @Test
    public void testaEmprestimoLivroComReserva(){
        EmprestimoService ems = new EmprestimoService();
        l3.setReservado(true);
        Assert.assertEquals(false, ems.emprestaLivro(l3,u2, LocalDate.now()));
    }
    @Test
    public void testaUsuarioNenhumEmprestimo(){
        EmprestimoService ems = new EmprestimoService();


        Assert.assertEquals(true, ems.emprestaLivro(l4,u2, LocalDate.now()));

    }
    @Test
    public void testaUsuarioComUmEmprestimo(){
        EmprestimoService ems = new EmprestimoService();
        UsuarioService users = new UsuarioService();
        users.alugaLivro(l1,u3);

        Assert.assertEquals(true, ems.emprestaLivro(l4,u2, LocalDate.now()));

    }
    @Test
    public void testaUsuarioComDoisEmprestimo(){
        EmprestimoService ems = new EmprestimoService();

        Assert.assertEquals(false, ems.emprestaLivro(l4,u3, LocalDate.now()));

    }
    @Test
    public void testaDevolucaoAntes(){
        EmprestimoService ems = new EmprestimoService();
        UsuarioService users = new UsuarioService();
        users.alugaLivro(l1,u3);
        ems.emprestaLivro(l1,u3);
        double valor = ems.devolveLivro(l1);

        Assert.assertEquals(BigDecimal.valueOf(5.0),BigDecimal.valueOf(valor));
    }
    @Test
    public void testaDevolucaoNaDataPrevista(){
        EmprestimoService ems = new EmprestimoService();
        UsuarioService users = new UsuarioService();

        users.alugaLivro(l2,u2);

        ems.emprestaLivro(l2,u2, LocalDate.now().minusDays(7));
        Assert.assertEquals(5.0, ems.devolveLivro(l2));
    }
    @Test
    public void testaDevolucaoComUmDiaAtraso(){
        EmprestimoService ems = new EmprestimoService();
        UsuarioService users = new UsuarioService();

        users.alugaLivro(l2,u2);

        ems.emprestaLivro(l2,u2, LocalDate.now().minusDays(8));
        Assert.assertEquals(5.4, ems.devolveLivro(l2),0.00001);
    }
    @Test
    public void testaDevolucaoComTrintaDiasAtraso(){
        EmprestimoService ems = new EmprestimoService();
        UsuarioService users = new UsuarioService();

        users.alugaLivro(l2,u2);

        ems.emprestaLivro(l2,u2, LocalDate.now().minusDays(37));
        Assert.assertEquals(8.0, ems.devolveLivro(l2),0.00001);
    }
}
