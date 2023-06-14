import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class Visita {
    private final LocalDateTime dataIngresso;
    private Duration tempoEstadia;

    public Visita(LocalDateTime dataIngresso) {
        Objects.requireNonNull(dataIngresso, "Data de ingresso nao pode ser nula.");
        this.dataIngresso = dataIngresso;
    }

    public LocalDateTime getDataIngresso() {
        return dataIngresso;
    }

    public Duration getTempoEstadia() {
        return tempoEstadia;
    }

    public void setTempoEstadia(Duration tempoEstadia) {
        Objects.requireNonNull(tempoEstadia, "Tempo de Estadia nao pode ser nulo.");
        this.tempoEstadia = tempoEstadia;
    }
}
