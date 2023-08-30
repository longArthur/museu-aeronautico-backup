import Logic.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Departamento departamento = new Departamento(LocalDateTime.now(), BigDecimal.valueOf(2022));
        Gerente teste = new Gerente(new CPF("00308550048"), LocalDate.now(), "Josnei", "Birimbau", BigDecimal.valueOf(2000),
                new Endereco("Osorio", "Costa Gama", "Albatroz", "2022A"),
                departamento, LocalDate.now());

        EmpregadoDAO empregadoDAO = EmpregadoDAO.getInstance();
        empregadoDAO.inserir(teste);

        LoginDAO loginDAO = LoginDAO.getInstance();
        loginDAO.inserir(new Login(teste, "abacate"));

        System.out.println(loginDAO);
        Interfaces.login.main(args);
    }
}
