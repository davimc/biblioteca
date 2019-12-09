package repositories.interfaces;

import domains.Pagamento;

import java.util.List;


public interface iPagamentoDAO {

    public void salva(Pagamento pagamento);

    public void atualiza(Pagamento pagamento);

    public Pagamento buscaPor(Integer id);
    public List<Pagamento> buscaTodos();
}
