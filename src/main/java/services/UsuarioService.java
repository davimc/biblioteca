package services;


import domains.Livro;
import domains.Cliente;

import java.util.ArrayList;

public class UsuarioService {
    private static ArrayList<Cliente> clientes = new ArrayList();
    private static Integer LIMITE_LIVROS = 2;

    //verifica se o usuário já fez algum emprestimo
    private void verificaJaFezEmprestimo(Cliente cliente){
        if(clientes.contains(cliente))
            return;
        clientes.add(cliente);
    }
    public boolean alugaLivro(Livro livro, Cliente cliente){
        verificaJaFezEmprestimo(cliente);
        if(cliente.verificaLimite(LIMITE_LIVROS)) {
            cliente.adiciona(livro);
            return true;
        }
        return false;
    }
    public boolean devolveLivro(Livro livro, Cliente cliente){
        verificaJaFezEmprestimo(cliente);
        if(cliente.verificaAluguel(livro)) {
            cliente.remove(livro);
            return true;
        }
        return false;
    }

}
