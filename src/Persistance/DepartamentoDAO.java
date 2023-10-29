package Persistance;

import Logic.DAO;
import Logic.Departamento;

import java.util.HashMap;
import java.util.List;

public class DepartamentoDAO implements DAO {
    //todo change to mysql
    HashMap<Integer, Departamento> hashMap = new HashMap<>();

    private static DepartamentoDAO departamentoDAO;

    public static DepartamentoDAO getInstance(){
        if (departamentoDAO == null){
            departamentoDAO = new DepartamentoDAO();
        }
        return departamentoDAO;
    }
    private DepartamentoDAO(){

    }

    @Override
    public boolean inserir(Object obj) {
        Departamento e = (Departamento) obj;
        if (e==null) return false;
        return !hashMap.containsKey(e.getCodigo()) && (hashMap.put(e.getCodigo(), e) == null);
    }

    @Override
    public boolean excluir(Object obj) {
        Integer c = (Integer) obj;
        return hashMap.remove(c) != null;
    }

    @Override
    public boolean editar(Object obj) {
        Departamento e = (Departamento) obj;
        return excluir(e.getCodigo()) && inserir(e);
    }

    @Override
    public Object pesquisar(Object obj) {
        return hashMap.get((Integer) obj);
    }

    public List<Departamento> pesquisar(){
        return hashMap.values().stream().toList();
    }
}
