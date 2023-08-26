package Logic;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class Visita implements Comparable<Visita> {
    private final LocalDateTime dataIngresso;
    private final Visitante visitante;
    private final Hangar hangar;
    private Duration tempoEstadia;

    public Visita(LocalDateTime dataIngresso, Visitante visitante, Hangar hangar) {
        Objects.requireNonNull(dataIngresso, "Data de ingresso nao pode ser nula.");
        Objects.requireNonNull(visitante, "Logic.Visitante nao pode ser nulo.");
        Objects.requireNonNull(hangar, "Logic.Hangar nao pode ser nulo.");

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

    @Override
    public int compareTo(Visita o) {
        if (!hangar.equals(o.getHangar()))
            return hangar.compareTo((o.getHangar()));

        if (!dataIngresso.isEqual(o.dataIngresso))
            return dataIngresso.compareTo(o.dataIngresso);

        if (!visitante.equals(o.getVisitante()))
            return visitante.compareTo(o.getVisitante());

        return this.tempoEstadia.compareTo(o.getTempoEstadia());
    }
}
