package Persistance;

import Logic.DAO;
import Logic.Modelo;

import java.util.HashMap;
import java.util.List;

public class ModeloDAO implements DAO {
    //todo change to mysql
    HashMap<Integer, Modelo> hashMap = new HashMap<>();

    private static ModeloDAO modeloDAO;

    public static ModeloDAO getInstance(){
        if (modeloDAO == null){
            modeloDAO = new ModeloDAO();
        }
        return modeloDAO;
    }
    private ModeloDAO(){

    }

    @Override
    public boolean inserir(Object obj) {
        Modelo e = (Modelo) obj;
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
        Modelo e = (Modelo) obj;
        return excluir(e.getCodigo()) && inserir(e);
    }

    @Override
    public Object pesquisar(Object obj) {
        return hashMap.get((Integer) obj);
    }

    public List<Modelo> pesquisar(){
        return hashMap.values().stream().toList();
    }
}
