package service;


import domain.Livro;
import domain.Usuario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class UsuarioService {
    private static ArrayList<Usuario> usuarios = new ArrayList();
    private static Integer LIMITE_LIVROS = 2;

    //verifica se o usuário já fez algum emprestimo
    private void verificaJaFezEmprestimo(Usuario usuario){
        if(usuarios.contains(usuario))
            return;
        usuarios.add(usuario);
    }
    public boolean alugaLivro(Livro livro, Usuario usuario){
        verificaJaFezEmprestimo(usuario);
        if(usuario.verificaLimite(LIMITE_LIVROS)) {
            usuario.adiciona(livro);
            return true;
        }
        return false;
    }
    public boolean devolveLivro(Livro livro, Usuario usuario){
        verificaJaFezEmprestimo(usuario);
        if(usuario.verificaAluguel(livro)) {
            usuario.remove(livro);
            return true;
        }
        return false;
    }

}
