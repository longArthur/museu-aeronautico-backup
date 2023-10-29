package Logic;

import java.util.Objects;

public class Endereco {
    private int codigo;
    private final String cidade;
    private final String rua;
    private final String bairro;
    private final int numero_endereco;
    private final String cep;
    private final String estado;
    private final String complemento;


    public Endereco(String cidade, String rua, String bairro, int numero_endereco, String cep, String estado, String complemento){
        Objects.requireNonNull(cidade, "Cidade nao pode ser nula");
        Objects.requireNonNull(rua, "Rua nao pode ser nula");
        Objects.requireNonNull(bairro, "Bairro nao pode ser nulo");
        Objects.requireNonNull(cep, "Cep nao pode ser nulo");
        Objects.requireNonNull(estado, "Estado nao pode ser nulo");
        if(cidade.isEmpty()) throw new IllegalArgumentException("Cidade nao pode ser vazia");
        if(rua.isEmpty()) throw new IllegalArgumentException("Rua nao pode ser vazia");
        if(bairro.isEmpty()) throw new IllegalArgumentException("Bairro nao pode ser vazio");
        if(numero_endereco <= 0) throw new IllegalArgumentException("Numero tem que ser maior do que 0");
        if(cep.isEmpty()) throw new IllegalArgumentException("Cep nao pode ser vazio");
        if(estado.isEmpty()) throw new IllegalArgumentException("Estado nao pode ser vazio");

        this.cidade = cidade;
        this.rua = rua;
        this.bairro = bairro;
        this.numero_endereco = numero_endereco;
        this.cep = cep;
        this.estado = estado;
        this.complemento = complemento;

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

    public int getNumero_endereco() {
        return numero_endereco;
    }

    public String getCep() {
        return cep;
    }

    public String getEstado() {
        return estado;
    }

    public String getComplemento() {
        return complemento;
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
