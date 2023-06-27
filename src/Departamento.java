import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Departamento {
    private int codigo;
    private final LocalDateTime data_criacao;
    private final BigDecimal orcamento;

    public Departamento(LocalDateTime data_criacao, BigDecimal orcamento) {
        Objects.requireNonNull(data_criacao, "Data criacao nao pode ser nulo");
        Objects.requireNonNull(orcamento, "Orcamento nao pode ser nulo");
        if(orcamento.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Orcamento nao pode ser negativo");

        this.data_criacao = data_criacao;
        this.orcamento = orcamento;
    }

    public int getCodigo() {
        return codigo;
    }

    public LocalDateTime getData_criacao() {
        return data_criacao;
    }

    public BigDecimal getOrcamento() {
        return orcamento;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Departamento that)) return false;

        return codigo == that.codigo;
    }

    @Override
    public int hashCode() {
        return codigo;
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "codigo=" + codigo +
                ", data_criacao=" + data_criacao +
                ", orcamento=" + orcamento +
                '}';
    }
}
