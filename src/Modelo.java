import java.time.LocalDate;
import java.util.Objects;

public class Modelo {
    enum Tipo {
        AVIAO, REPLICA
    }

    private int codigo;
    private final String marca;
    private final LocalDate dataProducao;
    private final double comprimentoMetros;
    private final double envergaduraMetros;
    private final String historia;
    private final Tipo tipo;
    private final String areaAtuacao;
    private final String materialUsado;
    private final int codigoHangar;

    public Modelo(String marca, LocalDate dataProducao, double comprimentoMetros, double envergaduraMetros, String historia, Tipo tipo, String areaAtuacao, String materialUsado, int codigoHangar) {
        if (marca == null || dataProducao == null || historia == null || tipo == null || areaAtuacao == null || materialUsado == null) {
            throw new IllegalArgumentException("Argumentos não podem ser nulos");
        }
        if (marca.isEmpty() || historia.isEmpty() || areaAtuacao.isEmpty() || materialUsado.isEmpty()) {
            throw new IllegalArgumentException("Argumentos não podem ser vazios");
        }
        if (comprimentoMetros <= 0 || envergaduraMetros <= 0 || codigoHangar <= 0) {
            throw new IllegalArgumentException("Argumentos não podem ser menores ou iguais a zero");
        }

        this.marca = marca;
        this.dataProducao = dataProducao;
        this.comprimentoMetros = comprimentoMetros;
        this.envergaduraMetros = envergaduraMetros;
        this.historia = historia;
        this.tipo = tipo;
        this.areaAtuacao = areaAtuacao;
        this.materialUsado = materialUsado;
        this.codigoHangar = codigoHangar;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getMarca() {
        return marca;
    }

    public LocalDate getDataProducao() {
        return dataProducao;
    }

    public double getComprimentoMetros() {
        return comprimentoMetros;
    }

    public double getEnvergaduraMetros() {
        return envergaduraMetros;
    }

    public String getHistoria() {
        return historia;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    public String getMaterialUsado() {
        return materialUsado;
    }

    public int getCodigoHangar() {
        return codigoHangar;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Modelo modelo = (Modelo) o;
        return codigo == modelo.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return "Modelo{" +
                "codigo=" + codigo +
                ", marca='" + marca + '\'' +
                ", dataProducao=" + dataProducao +
                ", comprimentoMetros=" + comprimentoMetros +
                ", envergaduraMetros=" + envergaduraMetros +
                ", historia='" + historia + '\'' +
                ", tipo=" + tipo +
                ", areaAtuacao='" + areaAtuacao + '\'' +
                ", materialUsado='" + materialUsado + '\'' +
                ", codigoHangar=" + codigoHangar +
                '}';
    }
}
