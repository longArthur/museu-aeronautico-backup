package Logic;

import java.util.Objects;

public class Endereco {
    private int codigo;
    private final String cidade;
    private final String rua;
    private final String bairro;
    private final String numero_endereco;

    public Endereco(String cidade, String rua, String bairro, String numero_endereco){
        Objects.requireNonNull(cidade, "Cidade nao pode ser nula");
        Objects.requireNonNull(rua, "Rua nao pode ser nula");
        Objects.requireNonNull(bairro, "Bairro nao pode ser nulo");
        Objects.requireNonNull(numero_endereco, "Numero de endereco nao pode ser nulo");
        if(cidade.isEmpty()) throw new IllegalArgumentException("Cidade nao pode ser vazia");
        if(rua.isEmpty()) throw new IllegalArgumentException("Rua nao pode ser vazia");
        if(bairro.isEmpty()) throw new IllegalArgumentException("Bairro nao pode ser vazio");
        if(numero_endereco.isEmpty()) throw new IllegalArgumentException("Numero de endereco nao pode ser vazio");


        this.cidade = cidade;
        this.rua = rua;
        this.bairro = bairro;
        this.numero_endereco = numero_endereco;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getCidade() {
        return cidade;
    }

    public String getRua() {
        return rua;
    }

    public String getBairro() {
        return bairro;
    }

    public String getNumero_endereco() {
        return numero_endereco;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Endereco endereco)) return false;

        return codigo == endereco.codigo;
    }

    @Override
    public int hashCode() {
        return codigo;
    }

    @Override
    public String toString() {
        return  cidade + ", Rua " + rua
                + ", bairro " + bairro +
                ", numero " + numero_endereco;
    }
}
