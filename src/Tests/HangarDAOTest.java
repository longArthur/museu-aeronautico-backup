package Tests;
import Persistance.*;
import Logic.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class HangarDAOTest {
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
    public void testarHangarDAO(){
        HangarDAO hangarDAO = HangarDAO.getInstance();
        Endereco endereco = new Endereco("Rua Teste", "Teste", "Teste", 123, "Teste", "Teste", "Teste");
        Departamento departamento = DepartamentoDAO.getInstance().pesquisar(1);
        Hangar hangar = new Hangar("a", 1, 1, 1, 1, endereco, departamento);
        assert hangarDAO != null : "HangarDAO é nula!";
        assert hangarDAO.inserir(null)  == null : "HangarDAO inseriu um hangar nulo!";
        assert !hangarDAO.excluir(null) : "HangarDAO excluiu um hangar nulo!";
        assert !hangarDAO.editar(null) : "HangarDAO editou um hangar nulo!";

        hangar.setCodigo(hangarDAO.inserir(hangar));

        assert hangarDAO.inserir(hangar)  == null : "HangarDAO inseriu um hangar já existente!";

        assert hangarDAO.editar(hangar) : "HangarDAO não editou um hangar válido!";

        assert hangarDAO.pesquisar(hangar.getCodigo()) != null : "HangarDAO não pesquisou um hangar válido!";
        assert hangarDAO.excluir(hangar.getCodigo()) : "HangarDAO não excluiu um hangar válido!";
        assert hangarDAO.pesquisar(hangar.getCodigo()) == null : "HangarDAO não excluiu um hangar válido!";
        assert !hangarDAO.excluir(hangar.getCodigo()) : "HangarDAO excluiu um hangar inexistente!";
        assert !hangarDAO.editar(hangar) : "HangarDAO editou um hangar inexistente!";
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
