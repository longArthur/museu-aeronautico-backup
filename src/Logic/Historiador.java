package Logic;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Historiador extends Empregado{
    private final String registro;

    public Historiador(CPF cpf, LocalDate dataIngresso, String nome, String sobrenome, BigDecimal salario, Endereco endereco, Departamento departamento, String registro) {
        super(cpf, dataIngresso, nome, sobrenome, salario, endereco, departamento);
        Objects.requireNonNull(registro, "Registro nao pode ser nulo");
        if(registro.isEmpty()) throw new IllegalArgumentException("Registro nao pode ser vazio");

        this.registro = registro;
    }



    public String getRegistro() {
        return registro;
    }

    @Override
    public String toString() {
        return "Logic.Historiador{" +
                "empregado=" + super.toString() +
                ", registro='" + registro + '\'' +
                '}';
    }
}
