package Logic;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Stream;

public class VisitaDAO implements DAO{
    TreeSet<Visita> treeSet = new TreeSet<>();

    private static VisitaDAO visitaDAO;

    public static VisitaDAO getInstance() {
        if(visitaDAO == null){
            visitaDAO = new VisitaDAO();
        }
        return visitaDAO;
    }

    private VisitaDAO(){

    }
    @Override
    public boolean inserir(Object obj) {
        if (!(obj instanceof Visita visita)) return false;
        return treeSet.stream().noneMatch(visita::equals) && treeSet.add(visita);
    }

    @Override
    public boolean excluir(Object obj) {
        if (!(obj instanceof Visita visita)) return false;
        return treeSet.removeIf(visita::equals);
    }

    @Override
    public boolean editar(Object obj) {
        if (!(obj instanceof Visita visita)) return false;
        return treeSet.removeIf(visita1 -> visita1.getVisitante().equals(visita.getVisitante()) &&
                visita1.getHangar().equals(visita.getHangar()) &&
                visita1.getDataIngresso().equals(visita.getDataIngresso())) && treeSet.add(visita);

    }

    @Override
    public Object pesquisar(Object obj) {
        if (!(obj instanceof Visitante visitante)) return false;
        return treeSet.stream().filter(visita -> visitante.equals(visita.getVisitante())).findFirst();
    }

    public Visita pesquisar(LocalDateTime localDate, CPF cpf) {

        return treeSet.stream().filter(visita -> (visita.getDataIngresso().truncatedTo(java.time.temporal.ChronoUnit.SECONDS).isEqual(localDate) && visita.getVisitante().getCpf().equals(cpf))).findFirst().orElse(null);
    }

    public List<Visita> pesquisar(){
        return treeSet.stream().toList();
    }
}
