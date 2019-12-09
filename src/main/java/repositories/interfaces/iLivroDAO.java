package repositories.interfaces;

import domains.Livro;
import java.util.List;



public interface iLivroDAO {

    public boolean salva(Livro livro);

    public void atualiza(Livro livro);

    public Livro buscaPor(Integer id);
    public List<Livro> buscaTodos();
}
