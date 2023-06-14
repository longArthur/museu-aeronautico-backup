import java.util.Objects;

public class Visitante {
    private final String cpf;
    private final String nome;
    private final String sobrenome;
    private final char genero;

    public Visitante(String cpf, String nome, String sobrenome, char genero){
        Objects.requireNonNull(cpf, "Cpf nao pode ser nulo");
        Objects.requireNonNull(nome, "Nome nao pode ser nulo");
        Objects.requireNonNull(sobrenome, "Sobrenome nao pode ser nulo");
        if(cpf.equals("")) throw new IllegalArgumentException("Cpf nao pode ser vazio");
        if(nome.equals("")) throw new IllegalArgumentException("Nome nao pode ser vazio");
        if(sobrenome.equals("")) throw new IllegalArgumentException("Sobrenome nao pode ser vazio");
        switch (genero) {
            case 'm', 'M' -> this.genero = 'm';
            case 'f', 'F' -> this.genero = 'f';
            case 'o', 'O' -> this.genero = 'o';
            default -> throw new IllegalArgumentException("Genero deve ser (F)eminino, (M)asculino ou (O)utro");
        }
        this.cpf = cpf;
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public char getGenero() {
        return genero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Visitante visitante)) return false;

        if (genero != visitante.genero) return false;
        if (!cpf.equals(visitante.cpf)) return false;
        if (!nome.equals(visitante.nome)) return false;
        return sobrenome.equals(visitante.sobrenome);
    }

    @Override
    public int hashCode() {
        return cpf.hashCode();
    }

    @Override
    public String toString() {
        return "Visitante{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", genero=" + genero +
                '}';
    }
}
