import Logic.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Departamento departamento = new Departamento(LocalDateTime.now(), BigDecimal.valueOf(2022));
        Gerente teste = new Gerente(new CPF("50460597035"), LocalDate.now(), "Josnei", "Birimbau", BigDecimal.valueOf(2000),
                new Endereco("Osorio", "Costa Gama", "Albatroz", "2022A"),
                departamento, LocalDate.now());

        Empregado empregado = new Empregado(new CPF("30839211015"), LocalDate.now(), "Cardoso", "cornelio",
                BigDecimal.valueOf(1000.20), new Endereco("Capao da canoa", "Bacacaca", "guri", "2222"),
                departamento);

        HangarDAO hangarDAO;

        EmpregadoDAO empregadoDAO = EmpregadoDAO.getInstance();
        empregadoDAO.inserir(teste);
        empregadoDAO.inserir(empregado);

        LoginDAO loginDAO = LoginDAO.getInstance();
        loginDAO.inserir(new Login(empregado, "abacate"));

        new Interfaces.login();
    }
}
