package Logic;

import java.util.Objects;



public class Visitante implements Comparable<Visitante> {
    public enum Genero {
        HOMEM,
        MULHER,
        NAOBINARIO
    }

    private final CPF cpf;
    private final String nome;
    private final String sobrenome;
    private final Genero genero;

    public Visitante(CPF cpf, String nome, String sobrenome, Genero genero){
        Objects.requireNonNull(cpf, "Cpf nao pode ser nulo");
        Objects.requireNonNull(nome, "Nome nao pode ser nulo");
        Objects.requireNonNull(sobrenome, "Sobrenome nao pode ser nulo");
        if(cpf.equals("")) throw new IllegalArgumentException("Cpf nao pode ser vazio");
        if(nome.equals("")) throw new IllegalArgumentException("Nome nao pode ser vazio");
        if(sobrenome.equals("")) throw new IllegalArgumentException("Sobrenome nao pode ser vazio");

        this.genero = genero;
        this.cpf = cpf;
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public CPF getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public Genero getGenero() {
        return genero;
    }

    public String getNomeSobrenome(){
        return nome + " " + sobrenome;
    }

    public String getGeneroString() {
        switch (genero){
            case HOMEM -> {return "Homem";}
            case MULHER -> {return "Mulher";}
            default -> {return "Nao-binario";}
        }
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
        return "Logic.Visitante{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", genero=" + genero +
                '}';
    }

    @Override
    public int compareTo(Visitante o) {
        return this.cpf.compareTo(o.getCpf());
    }
}
