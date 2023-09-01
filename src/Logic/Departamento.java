package Logic;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Departamento {
    private int codigo;
    private final LocalDateTime data_criacao;
    private final BigDecimal orcamento;
    private final String nome;

    public Departamento(LocalDateTime data_criacao, BigDecimal orcamento, String nome) {
        Objects.requireNonNull(data_criacao, "Data criacao nao pode ser nulo");
        Objects.requireNonNull(orcamento, "Orcamento nao pode ser nulo");
        Objects.requireNonNull(nome, "Nome nao pode ser nulo");
        if(orcamento.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Orcamento nao pode ser negativo");
        if(nome.isEmpty()) throw new IllegalArgumentException("Nome nao pode ser vazio");

        this.data_criacao = data_criacao;
        this.orcamento = orcamento;
        this.nome = nome;
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

    public String getNome(){
        return this.nome;
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
        return "Logic.Departamento{" +
                "codigo=" + codigo +
                ", data_criacao=" + data_criacao +
                ", orcamento=" + orcamento +
                ", nome= " + nome +
                '}';
    }
}
