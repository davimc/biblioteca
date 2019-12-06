package domain;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
@Entity
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(mappedBy = "emprestimo", cascade = CascadeType.ALL)
    private Pagamento pagamento;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="LIVRO_ID")
    private Livro livro;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="USUARIO_ID")
    private Usuario usuario;

    private LocalDate dataEmprestimo ;
    private LocalDate dataPrevista ;
    private LocalDate dataDevolucao;
    private LocalDate dataReserva ;
    private boolean status;

    public Emprestimo() {
        pagamento = new Pagamento();
        status = false;
        dataReserva = LocalDate.MAX;
    }

    /*public Emprestimo(Usuario usuario, LocalDate dataEmprestimo, LocalDate dataPrevista, LocalDate dataDevolucao, Livro livro) {
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevista = dataPrevista;
        this.dataDevolucao = dataDevolucao;
        this.livro = livro;
        this.valor = VALOR_ALUGUEL;
    }*/

    //GETTERS E SETTERS

    public Usuario getusuario() {
        return usuario;
    }

    public void setusuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(LocalDate dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }



    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    //OUTHERS
    //transformar em um service
    public void iniciaEmprestimo(){
        setStatus(false);
    }
    public double finalizaEmprestimo(){
        if(statusEmprestimo())
        setDataDevolucao(LocalDate.now());
        setStatus(true);
        /*PERGUNTAR PARA JOÃO SE ESSE THIS É UM PRÁTICA RUIM
        ***
        *****
         */
        return pagamento.calculaValor();
    }
    public boolean statusEmprestimo(){
        return getStatus();
    }
}
