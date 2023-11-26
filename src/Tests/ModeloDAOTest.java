package Tests;

import Persistance.*;
import Logic.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


public class ModeloDAOTest {
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
    public void testarModeloDAO(){
        ModeloDAO modeloDAO = ModeloDAO.getInstance();
        Modelo modelo = new Modelo("Teste", LocalDate.now(), 2, 1, "Teste", Modelo.Tipo.AVIAO, "Teste", "Teste", HangarDAO.getInstance().pesquisar(1), "RS");
        assert modeloDAO != null : "ModeloDAO é nula!";
        assert modeloDAO.inserir(null)  == null : "ModeloDAO inseriu um modelo nulo!";
        assert !modeloDAO.excluir(null) : "ModeloDAO excluiu um modelo nulo!";
        assert !modeloDAO.editar(null) : "ModeloDAO editou um modelo nulo!";

        modelo.setCodigo(modeloDAO.inserir(modelo));

        assert modeloDAO.inserir(modelo)  != null : "ModeloDAO inseriu um modelo já existente!";

        assert modeloDAO.editar(modelo) : "ModeloDAO não editou um modelo válido!";

        assert modeloDAO.pesquisar(modelo.getCodigo()) != null : "ModeloDAO não pesquisou um modelo válido!";
        assert modeloDAO.excluir(modelo.getCodigo()) : "ModeloDAO não excluiu um modelo válido!";
        assert modeloDAO.pesquisar(modelo.getCodigo()) == null : "ModeloDAO não excluiu um modelo válido!";
        assert !modeloDAO.excluir(modelo.getCodigo()) : "ModeloDAO excluiu um modelo inexistente!";
        assert !modeloDAO.editar(modelo) : "ModeloDAO editou um modelo inexistente!";
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
