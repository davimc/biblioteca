package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private final Integer LIMITE_listaLivros= 2;

    private String nome;
    private String matricula;
    private List<Livro> listaLivros;

    public Usuario(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
        listaLivros = new ArrayList();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<Livro> getListaLivros() {
        return listaLivros;
    }

    public void setListaLivros(List<Livro> listaLivros) {
        this.listaLivros = listaLivros;
    }

    public void adiciona(Livro livro){
        this.listaLivros.add(livro);

    }
    public void remove(Livro livro){
        this.listaLivros.remove(livro);
    }
    public boolean verificaAluguel(Livro livro){
        return listaLivros.contains(livro) ? true:false;
    }

    public boolean verificaLimite(int limitelistaLivros){
        return listaLivros.size()<limitelistaLivros?true:false;
    }
}
