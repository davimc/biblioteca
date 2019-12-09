package domains;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Emprestimo implements Serializable {
    private static final long serialVersionUID = 1L;
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
    private Cliente cliente;

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

    //GETTERS E SETTERS

    public Cliente getusuario() {
        return cliente;
    }

    public void setusuario(Cliente cliente) {
        this.cliente = cliente;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
    public double finalizaEmprestimo() {
        if (!statusEmprestimo()) {
            setDataDevolucao(LocalDate.now());
            setStatus(true);
        }
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
