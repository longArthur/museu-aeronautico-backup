import java.time.LocalDate;
import java.util.Objects;

public class Gerente {
    private final Empregado empregado;
    private final LocalDate inicio_gerencia;

    public Gerente(Empregado empregado, LocalDate inicio_gerencia) {
        //reqnonnull
        Objects.requireNonNull(empregado, "Empregado nao pode ser nulo");
        Objects.requireNonNull(inicio_gerencia, "Inicio de gerencia nao pode ser nulo");

        this.empregado = empregado;
        this.inicio_gerencia = inicio_gerencia;
    }

    public Empregado getEmpregado() {
        return empregado;
    }

    public LocalDate getInicio_gerencia() {
        return inicio_gerencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gerente gerente)) return false;

        return Objects.equals(empregado, gerente.empregado);
    }

    @Override
    public int hashCode() {
        return empregado.hashCode();
    }

    @Override
    public String toString() {
        return "Gerente{" +
                "empregado=" + empregado +
                ", inicio_gerencia=" + inicio_gerencia +
                '}';
    }
}
