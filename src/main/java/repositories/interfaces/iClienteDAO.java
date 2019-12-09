package repositories.interfaces;

import domains.Cliente;
import domains.Livro;

import java.util.List;


public interface iClienteDAO {

    public void salva(Cliente cliente);

    public void atualiza(Cliente cliente);

    public Cliente buscaPor(Integer id);
    public List<Cliente> buscaTodos();
}
