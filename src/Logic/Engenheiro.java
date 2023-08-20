package Logic;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Engenheiro extends Empregado{
    private final String crea;
    private final String areaAtuacao;

    public Engenheiro(CPF cpf, LocalDate dataIngresso, String nome, String sobrenome, BigDecimal salario, Endereco endereco, Departamento departamento, String crea, String areaAtuacao) {
        super(cpf, dataIngresso, nome, sobrenome, salario, endereco, departamento);
        Objects.requireNonNull(crea, "Crea nao pode ser nulo");
        Objects.requireNonNull(areaAtuacao, "Area de atuacao nao pode ser nulo");
        if(crea.isEmpty()) throw new IllegalArgumentException("Crea nao pode ser vazio");
        if(areaAtuacao.isEmpty()) throw new IllegalArgumentException("Area de atuacao nao pode ser vazio");


        this.crea = crea;
        this.areaAtuacao = areaAtuacao;
    }


    public String getCrea() {
        return crea;
    }

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    @Override
    public String toString() {
        return "Engenheiro{" +
                "empregado=" + super.toString() +
                ", crea='" + crea + '\'' +
                ", areaAtuacao='" + areaAtuacao + '\'' +
                '}';
    }
}
