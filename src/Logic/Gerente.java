package Logic;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Gerente extends Empregado{
    private final LocalDate inicio_gerencia;

    public Gerente(CPF cpf, LocalDate dataIngresso, String nome, String sobrenome, BigDecimal salario, Endereco endereco, Departamento departamento, LocalDate inicio_gerencia) {
        //reqnonnull
        super(cpf, dataIngresso, nome, sobrenome, salario, endereco, departamento);
        Objects.requireNonNull(inicio_gerencia, "Inicio de gerencia nao pode ser nulo");

        this.inicio_gerencia = inicio_gerencia;
    }

    public LocalDate getInicio_gerencia() {
        return inicio_gerencia;
    }

    @Override
    public String toString() {
        return "Logic.Gerente{" +
                "empregado=" + super.toString() +
                ", inicio_gerencia=" + inicio_gerencia +
                '}';
    }
}
