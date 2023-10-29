package Persistance;

import Logic.CPF;
import Logic.DAO;
import Logic.Visitante;

import java.util.HashMap;
import java.util.List;

public class VisitanteDAO implements DAO {
    //todo change to mysql
    HashMap<CPF, Visitante> visitantes = new HashMap<>();

    private static VisitanteDAO visitanteDAO;

    public static VisitanteDAO getInstance(){
        if (visitanteDAO == null){
            visitanteDAO = new VisitanteDAO();
        }
        return visitanteDAO;
    }
    private VisitanteDAO(){

    }

    @Override
    public boolean inserir(Object obj) {
        Visitante e = (Visitante) obj;
        if (e==null) return false;
        return !visitantes.containsKey(e.getCpf()) && (visitantes.put(e.getCpf(), e) == null);
    }

    @Override
    public boolean excluir(Object obj) {
        CPF c = (CPF) obj;
        return visitantes.remove(c) != null;
    }

    @Override
    public boolean editar(Object obj) {
        Visitante e = (Visitante) obj;
        return excluir(e.getCpf()) && inserir(e);
    }

    @Override
    public Object pesquisar(Object obj) {
        return visitantes.get((CPF) obj);
    }

    public List<Visitante> pesquisar(){
        return visitantes.values().stream().toList();
    }
}
