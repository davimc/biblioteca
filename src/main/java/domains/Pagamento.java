package domains;

import javax.persistence.*;
import java.io.Serializable;
import java.time.temporal.ChronoUnit;

@Entity
public class Pagamento implements Serializable {
    private static final long serialVersionUID = 1L;

    private final static double VALOR_ALUGUEL = 5.0;
    private final static double VALOR_DIARIO_MULTA = 0.4;
    private final static double LIMITE_MULTA_APLICAVEL = 0.6;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private long valor;

    @OneToOne
    @MapsId
    private Emprestimo emprestimo;

    public Pagamento() {
    }

    public Pagamento(long valor, Emprestimo emprestimo) {
        this.valor = valor;
        this.emprestimo = emprestimo;
    }

    public Pagamento(long valor) {
        this.valor = valor;
    }

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
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

    public double calculaValor(){

        if(emprestimo.getDataDevolucao().isBefore(emprestimo.getDataPrevista()) || emprestimo.getDataDevolucao().isEqual(emprestimo.getDataPrevista()))
            return this.getVALOR_ALUGUEL();
        else{
            double valor = this.getVALOR_ALUGUEL() + (ChronoUnit.DAYS.between(emprestimo.getDataPrevista(), emprestimo.getDataDevolucao()))*getVALOR_DIARIO_MULTA();
            return Math.min(valor, calculaLimiteDeMulta());
        }
    }
    private double calculaLimiteDeMulta(){
        return this.getVALOR_ALUGUEL() + (this.getVALOR_ALUGUEL()*this.getLIMITE_MULTA_APLICAVEL());
    }
}
