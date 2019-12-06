package domain;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {
    //é interessante botar final ou um static seria mais interessante para estes valores?
    private final double VALOR_ALUGUEL = 5.0;
    private final double VALOR_DIARIO_MULTA = 0.4;
    private final double LIMITE_MULTA_APLICAVEL = 0.6;

    private Usuario usuario;
    private LocalDate dataEmprestimo ;
    private LocalDate dataPrevista ;
    private LocalDate dataDevolucao;
    private LocalDate dataReserva ;
    private Livro livro;
    private double valor;
    private boolean status;

    public Emprestimo() {
        valor = 0.0;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
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

    public double getVALOR_ALUGUEL() {
        return VALOR_ALUGUEL;
    }

    public double getVALOR_DIARIO_MULTA() {
        return VALOR_DIARIO_MULTA;
    }

    public double getLIMITE_MULTA_APLICAVEL() {
        return LIMITE_MULTA_APLICAVEL;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    //OUTHERS
    public void iniciaEmprestimo(){
        setStatus(false);
    }
    public double finalizaEmprestimo(){
        if(statusEmprestimo())
        setDataDevolucao(LocalDate.now());
        setStatus(true);
        return calculaValor();
    }
    public boolean statusEmprestimo(){
        return getStatus();
    }

    private double calculaValor(){

        if(this.getDataDevolucao().isBefore(this.getDataPrevista()) || this.getDataDevolucao().isEqual(this.getDataPrevista()))
            return this.getVALOR_ALUGUEL();
        else{
            double valor = this.getVALOR_ALUGUEL() + (ChronoUnit.DAYS.between(this.getDataPrevista(), this.getDataDevolucao()))*this.getVALOR_DIARIO_MULTA();
            return valor>calculaLimiteDeMulta()?calculaLimiteDeMulta():valor;
        }
    }

    private double calculaLimiteDeMulta(){
        return this.getVALOR_ALUGUEL() + (this.getVALOR_ALUGUEL()*this.getLIMITE_MULTA_APLICAVEL());
    }
    /*public boolean reservaLivro(Livro livro, Usuario usuario){
        if(this.usuario.verificaLimite())
            return true;
        else{
            for(int i = 0; i < this.usuario.getLivros().size();i++){

            }
        }
        return false;
    }*/

}
