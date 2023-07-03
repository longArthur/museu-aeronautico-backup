import java.util.Objects;

public class Piloto{
    private final Empregado empregado;
    private final String CHT;

    public Piloto(Empregado empregado, String CHT) {
        Objects.requireNonNull(empregado, "Empregado nao pode ser nulo");
        Objects.requireNonNull(CHT, "CHT nao pode ser nulo");
        if(CHT.equals("")) throw new IllegalArgumentException("CHT nao pode ser vazio");

        this.empregado = empregado;
        this.CHT = CHT;
    }

    public Empregado getEmpregado() {
        return empregado;
    }

    public String getCHT() {
        return CHT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Piloto piloto)) return false;

        return Objects.equals(empregado, piloto.empregado);
    }

    @Override
    public int hashCode() {
        return empregado != null ? empregado.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Piloto{" +
                "empregado=" + empregado +
                ", CHT='" + CHT + '\'' +
                '}';
    }
}
