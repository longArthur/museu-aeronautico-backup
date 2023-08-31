package Logic;

import java.util.HashMap;
import java.util.List;

public class HangarDAO implements DAO{
    HashMap<Integer, Hangar> hashMap = new HashMap<>();

    private static HangarDAO hangarDAO;

    public static HangarDAO getInstance(){
        if (hangarDAO == null){
            hangarDAO = new HangarDAO();
        }
        return hangarDAO;
    }
    private HangarDAO(){

    }

    @Override
    public boolean inserir(Object obj) {
        Hangar e = (Hangar) obj;
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
        Hangar e = (Hangar) obj;
        return excluir(e.getCodigo()) && inserir(e);
    }

    @Override
    public Object pesquisar(Object obj) {
        return hashMap.get((Integer) obj);
    }

    public List<Hangar> pesquisar(){
        return hashMap.values().stream().toList();
    }
}
