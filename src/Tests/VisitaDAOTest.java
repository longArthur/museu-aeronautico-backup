package Tests;

import Persistance.*;
import Logic.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class VisitaDAOTest {
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
    public void testarVisitaDAO() {
        VisitaDAO visitaDAO = VisitaDAO.getInstance();
        Visita visita = new Visita(LocalDateTime.now(), VisitanteDAO.getInstance().pesquisar(new CPF("05579847009")), HangarDAO.getInstance().pesquisar(2));
        assert visitaDAO != null : "VisitaDAO é nula!";
        assert visitaDAO.inserir(null) == null : "VisitaDAO inseriu uma visita nula!";
        assert !visitaDAO.excluir(null) : "VisitaDAO excluiu uma visita nula!";
        assert !visitaDAO.editar(null) : "VisitaDAO editou uma visita nula!";
        assert visitaDAO.pesquisar(null) == null : "VisitaDAO pesquisou uma visita nula!";

        assert visitaDAO.inserir(visita) != null : "VisitaDAO não inseriu uma visita válida!";

        assert visitaDAO.inserir(visita) == null : "VisitaDAO inseriu uma visita já existente!";
        assert visitaDAO.editar(visita) : "VisitaDAO não editou uma visita válida!";
        assert visitaDAO.pesquisar(visita.getVisitante().getCpf()) != null : "VisitaDAO não pesquisou uma visita válida!";
        assert visitaDAO.excluir(visita.getVisitante().getCpf()) : "VisitaDAO não excluiu uma visita válida!";
        assert visitaDAO.pesquisar(visita.getVisitante().getCpf()) == null : "VisitaDAO não excluiu uma visita válida!";
        assert !visitaDAO.excluir(visita.getVisitante().getCpf()) : "VisitaDAO excluiu uma visita inexistente!";
        assert !visitaDAO.editar(visita) : "VisitaDAO editou uma visita inexistente!";

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
