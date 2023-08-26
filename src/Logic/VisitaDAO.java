package Logic;

import java.util.TreeSet;

public class VisitaDAO implements DAO{
    TreeSet<Visita> treeSet = new TreeSet<>();

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
        return treeSet.stream().filter(visita -> visitante.equals(visita.getVisitante())).findAny();
    }
}
