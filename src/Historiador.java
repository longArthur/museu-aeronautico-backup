import java.util.Objects;

public class Historiador {
    private final Empregado empregado;
    private final String registro;

    public Historiador(Empregado empregado, String registro) {
        Objects.requireNonNull(empregado, "Empregado nao pode ser nulo");
        Objects.requireNonNull(registro, "Registro nao pode ser nulo");
        if(registro.equals("")) throw new IllegalArgumentException("Registro nao pode ser vazio");

        this.empregado = empregado;
        this.registro = registro;
    }

    public Empregado getEmpregado() {
        return empregado;
    }

    public String getRegistro() {
        return registro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Historiador that)) return false;

        return Objects.equals(empregado, that.empregado);
    }

    @Override
    public int hashCode() {
        return empregado != null ? empregado.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Historiador{" +
                "empregado=" + empregado +
                ", registro='" + registro + '\'' +
                '}';
    }
}
