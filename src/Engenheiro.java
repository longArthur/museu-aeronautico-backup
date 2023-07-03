import java.util.Objects;

public class Engenheiro {
    private final Empregado empregado;
    private final String crea;
    private final String areaAtuacao;

    public Engenheiro(Empregado empregado, String crea, String areaAtuacao) {
        Objects.requireNonNull(empregado, "Empregado nao pode ser nulo");
        Objects.requireNonNull(crea, "Crea nao pode ser nulo");
        Objects.requireNonNull(areaAtuacao, "Area de atuacao nao pode ser nulo");
        if(crea.equals("")) throw new IllegalArgumentException("Crea nao pode ser vazio");
        if(areaAtuacao.equals("")) throw new IllegalArgumentException("Area de atuacao nao pode ser vazio");


        this.empregado = empregado;
        this.crea = crea;
        this.areaAtuacao = areaAtuacao;
    }

    public Empregado getEmpregado() {
        return empregado;
    }

    public String getCrea() {
        return crea;
    }

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Engenheiro that)) return false;

        return Objects.equals(empregado, that.empregado);
    }

    @Override
    public int hashCode() {
        return empregado != null ? empregado.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Engenheiro{" +
                "empregado=" + empregado +
                ", crea='" + crea + '\'' +
                ", areaAtuacao='" + areaAtuacao + '\'' +
                '}';
    }
}
