import java.util.Objects;

public class Hangar {

    private final int codigo;
    private final String bloco;
    private final Endereco endereco;
    private final Departamento departamento;
    private final int quantidadeVagas;
    private final double larguraMetros;
    private final double comprimentoMetros;
    private final int capacidadeVisitantes;

    public Hangar(int codigo, String bloco, int quantidadeVagas, double larguraMetros, double comprimentoMetros, int capacidadeVisitantes, Endereco endereco, Departamento departamento) {
        if(codigo <= 0) throw new IllegalArgumentException("Codigo nao pode ser menor ou igual a zero");
        if(bloco.equals("")) throw new IllegalArgumentException("Bloco nao pode ser vazio");
        Objects.requireNonNull(bloco,"Bloco nao pode ser nulo");
        if(quantidadeVagas <= 0) throw new IllegalArgumentException("Quantidade de vagas nao pode ser menor ou igual a zero");
        if(larguraMetros <= 0) throw new IllegalArgumentException("Largura em metros nao pode ser menor ou igual a zero");
        if(comprimentoMetros <= 0) throw new IllegalArgumentException("Comprimento em metros nao pode ser menor ou igual a zero");
        if(capacidadeVisitantes <= 0) throw new IllegalArgumentException("Capacidade de visitantes nao pode ser menor ou igual a zero");
        Objects.requireNonNull(endereco,"Endereco nao pode ser nulo");
        Objects.requireNonNull(departamento, "Departamento nao pode ser nulo");


        this.codigo = codigo;
        this.bloco = bloco;
        this.quantidadeVagas = quantidadeVagas;
        this.larguraMetros = larguraMetros;
        this.comprimentoMetros = comprimentoMetros;
        this.capacidadeVisitantes = capacidadeVisitantes;
        this.endereco = endereco;
        this.departamento = departamento;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getBloco() {
        return bloco;
    }

    public int getQuantidadeVagas() {
        return quantidadeVagas;
    }

    public double getLarguraMetros() {
        return larguraMetros;
    }

    public double getComprimentoMetros() {
        return comprimentoMetros;
    }

    public int getCapacidadeVisitantes() {
        return capacidadeVisitantes;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hangar hangar)) return false;

        return codigo == hangar.codigo;
    }

    @Override
    public int hashCode() {
        return codigo;
    }
}
