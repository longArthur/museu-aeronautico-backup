package Logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EmpregadoDAO implements DAO{
    HashMap<CPF, Empregado> empregados = new HashMap<>();
    private static EmpregadoDAO empregadoDAO;

    public static EmpregadoDAO getInstance(){
        if (empregadoDAO == null){
            empregadoDAO = new EmpregadoDAO();
        }
        return empregadoDAO;
    }
    private EmpregadoDAO(){

    }
    @Override
    public boolean inserir(Object obj) {
        Empregado e = (Empregado) obj;
        if (e==null) return false;
        return !empregados.containsKey(e.getCpf()) && (empregados.put(e.getCpf(), e) == null);
    }

    @Override
    public boolean excluir(Object obj) {
        CPF c = (CPF) obj;
        return empregados.remove(c) != null;
    }

    @Override
    public boolean editar(Object obj) {
        Empregado e = (Empregado) obj;
        return excluir(e.getCpf()) && inserir(e);
    }

    @Override
    public Object pesquisar(Object obj) {
        return empregados.get((CPF) obj);
    }

    public List<Empregado> pesquisar(){
        return empregados.values().stream().toList();
    }
}
