import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class Visita {
    private final LocalDateTime dataIngresso;
    private final Visitante visitante;
    private final Hangar hangar;
    private Duration tempoEstadia;

    public Visita(LocalDateTime dataIngresso, Visitante visitante, Hangar hangar) {
        Objects.requireNonNull(dataIngresso, "Data de ingresso nao pode ser nula.");
        Objects.requireNonNull(visitante, "Visitante nao pode ser nulo.");
        Objects.requireNonNull(hangar, "Hangar nao pode ser nulo.");

        this.visitante = visitante;
        this.hangar = hangar;
        this.dataIngresso = dataIngresso;
    }

    public LocalDateTime getDataIngresso() {
        return dataIngresso;
    }

    public Duration getTempoEstadia() {
        return tempoEstadia;
    }

    public Visitante getVisitante() {
        return visitante;
    }

    public Hangar getHangar() {
        return hangar;
    }

    public void setTempoEstadia(Duration tempoEstadia) {
        Objects.requireNonNull(tempoEstadia, "Tempo de Estadia nao pode ser nulo.");
        this.tempoEstadia = tempoEstadia;
    }
}
