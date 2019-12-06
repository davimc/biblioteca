package service;

import domain.Emprestimo;
import domain.Livro;
import domain.Usuario;

import java.time.LocalDate;

import java.util.ArrayList;

public class EmprestimoService {
    private static ArrayList<Emprestimo> historico = new ArrayList();
    private UsuarioService usuario = new UsuarioService();


    public ArrayList<Emprestimo> getHistorico() {
        return historico;
    }


    public void setHistorico(ArrayList<Emprestimo> historico) {
        historico = historico;
    }

    public LocalDate emprestaLivro(Livro livro, Usuario usuario){
        Emprestimo emp = new Emprestimo();



        /*não empresta se:
         *   Já estiver emprestado
         *   Se o período coincidir com uma reserva
         *   Se o período de devolução for depois da reserva
         *   Se o usuário já chegou no limite de livros*/
        if(!livro.isEmprestado() && !livro.isReservado()) {
            if(this.usuario.alugaLivro(livro,usuario)) {
                emp.setDataEmprestimo(LocalDate.now());
                emp.setDataPrevista(LocalDate.now().plusDays(7));
                emp.setLivro(livro);
                emp.setStatus(true);
                emp.setusuario(usuario);
                historico.add(emp);
                return emp.getDataPrevista();
            }
            throw new ArrayIndexOutOfBoundsException("Usuario já está no limite permitido de solicitações");
        }
        throw new IllegalArgumentException("Livro não está dispnível");
    }
    public LocalDate emprestaLivro(Livro livro, Usuario usuario, LocalDate dataEmprestimo) {
        Emprestimo emp = new Emprestimo();
        if(!livro.isEmprestado() && !livro.isReservado()) {
            if(this.usuario.alugaLivro(livro,usuario)) {
                emp.setDataEmprestimo(dataEmprestimo);
                emp.setDataPrevista(dataEmprestimo.plusDays(7));
                emp.setLivro(livro);
                emp.setusuario(usuario);
                emp.setStatus(true);
                historico.add(emp);
                return emp.getDataPrevista();
            }
            throw new ArrayIndexOutOfBoundsException("Usuario já está no limite permitido de solicitações");
        }
        throw new IllegalArgumentException("Livro não está dispnível");
    }
    //Métodos para devolução

    public double devolveLivro(Livro livro){

        for(int i = 0; i < historico.size(); i++) {

            if(!historico.get(i).statusEmprestimo())
                continue;
            if(historico.get(i).getLivro().equals(livro)) {

                return historico.get(i).finalizaEmprestimo();

            }

        }
        throw new IllegalArgumentException("Usuario não alugou esse livro");

    }

}
