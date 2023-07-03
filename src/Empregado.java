import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Empregado {
    private final CPF cpf;
    private final LocalDate dataIngresso;
    private final String nome;
    private final String sobrenome;
    private final BigDecimal salario;
    private final Endereco endereco;
    private final Departamento departamento;

    public Empregado(CPF cpf, LocalDate dataIngresso, String nome, String sobrenome, BigDecimal salario, Endereco endereco, Departamento departamento) {
        //reqnonnull
        Objects.requireNonNull(cpf, "CPF nao pode ser nulo");
        Objects.requireNonNull(dataIngresso, "Data de ingresso nao pode ser nulo");
        Objects.requireNonNull(nome, "Nome nao pode ser nulo");
        Objects.requireNonNull(sobrenome, "Sobrenome nao pode ser nulo");
        Objects.requireNonNull(salario, "Salario nao pode ser nulo");
        Objects.requireNonNull(endereco, "Endereco nao pode ser nulo");
        Objects.requireNonNull(departamento, "Departamento nao pode ser nulo");
        if(nome.equals("")) throw new IllegalArgumentException("Nome nao pode ser vazio");
        if(sobrenome.equals("")) throw new IllegalArgumentException("Sobrenome nao pode ser vazio");
        if(salario.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Salario nao pode ser negativo");

        this.cpf = cpf;
        this.dataIngresso = dataIngresso;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.salario = salario;
        this.endereco = endereco;
        this.departamento = departamento;
    }

    public CPF getCpf() {
        return cpf;
    }

    public LocalDate getDataIngresso() {
        return dataIngresso;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Empregado empregado)) return false;

        return Objects.equals(cpf, empregado.cpf);
    }

    @Override
    public int hashCode() {
        return cpf != null ? cpf.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Empregado{" +
                "cpf=" + cpf +
                ", dataIngresso=" + dataIngresso +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", salario=" + salario +
                ", endereco=" + endereco +
                ", departamento=" + departamento +
                '}';
    }
}
