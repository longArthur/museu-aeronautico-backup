package Tests;

import Persistance.*;
import Logic.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
public class VisitanteDAOTest {
    @BeforeAll
    public static void transactionRollbackStart(){
        try {
            ConnectBD bd = ConnectBD.getInstance();
            ConnectBD.getConexao().setAutoCommit(false);
            ConnectBD.getConexao().setSavepoint();
            bd = null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testarVisitanteDAO(){
        VisitanteDAO visitanteDAO = VisitanteDAO.getInstance();
        Visitante visitante = new Visitante(new CPF("11740838084"), "Teste", "Teste", Visitante.Genero.HOMEM);
        assert visitanteDAO != null : "VisitanteDAO é nula!";
        assert visitanteDAO.inserir(null) == null : "VisitanteDAO inseriu um visitante nulo!";
        assert !visitanteDAO.excluir(null) : "VisitanteDAO excluiu um visitante nulo!";
        assert !visitanteDAO.editar(null) : "VisitanteDAO editou um visitante nulo!";
        assert visitanteDAO.pesquisar(null) == null : "VisitanteDAO pesquisou um visitante nulo!";

        assert visitanteDAO.inserir(visitante) != null : "VisitanteDAO não inseriu um visitante válido!";

        assert visitanteDAO.inserir(visitante) == null : "VisitanteDAO inseriu um visitante já existente!";
        assert visitanteDAO.editar(visitante) : "VisitanteDAO não editou um visitante válido!";
        assert visitanteDAO.pesquisar(visitante.getCpf()) != null : "VisitanteDAO não pesquisou um visitante válido!";
        assert visitanteDAO.excluir(visitante.getCpf()) : "VisitanteDAO não excluiu um visitante válido!";
        assert visitanteDAO.pesquisar(visitante.getCpf()) == null : "VisitanteDAO não excluiu um visitante válido!";
        assert !visitanteDAO.excluir(visitante.getCpf()) : "VisitanteDAO excluiu um visitante inexistente!";
        assert !visitanteDAO.editar(visitante) : "VisitanteDAO editou um visitante inexistente!";

    }

    @AfterAll
    public static void transactionRollbackEnd(){
        try {
            ConnectBD.getConexao().rollback();
            ConnectBD.getConexao().setAutoCommit(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
