package Logic;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Piloto extends Empregado{
    private final String CHT;

    public Piloto(CPF cpf, LocalDate dataIngresso, String nome, String sobrenome, BigDecimal salario, Endereco endereco, Departamento departamento, String CHT) {
        super(cpf, dataIngresso, nome, sobrenome, salario, endereco, departamento);
        Objects.requireNonNull(CHT, "CHT nao pode ser nulo");
        if(CHT.equals("")) throw new IllegalArgumentException("CHT nao pode ser vazio");

        this.CHT = CHT;
    }


    public String getCHT() {
        return CHT;
    }

    @Override
    public String toString() {
        return "Logic.Piloto{" +
                "empregado=" + super.toString() +
                ", CHT='" + CHT + '\'' +
                '}';
    }
}
