package Logic;

import java.util.HashMap;

public class LoginDAO implements DAO {
    private HashMap<CPF, Login> logins = new HashMap<>();

    @Override
    public boolean inserir(Object obj) {
        Login l = (Login) obj;
        if (l==null) return false;
        return !logins.containsKey(l.getEmpregado().getCpf()) && (logins.put(l.getEmpregado().getCpf(), l) == null);
    }

    @Override
    public boolean excluir(Object obj) {
        CPF c = (CPF) obj;
        return logins.remove(c) != null;
    }

    @Override
    public boolean editar(Object obj) {
        Login l = (Login) obj;
        return excluir(l.getEmpregado().getCpf()) && inserir(l);
    }

    @Override
    public Object pesquisar(Object obj) {
        return logins.get((CPF) obj);
    }
    }
}
