package repositories.interfaces;

import domains.Emprestimo;
import domains.Livro;

import java.util.List;

public interface iEmprestimoDAO {
    public void salva(Emprestimo emprestimo);

    public void atualiza(Emprestimo emprestimo);

    public Emprestimo buscaPor(Integer id);
    public List<Emprestimo> buscaTodos();
}
