import Logic.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Departamento departamento = new Departamento(LocalDateTime.now(), BigDecimal.valueOf(2022), "Roxo");
        Gerente teste = new Gerente(new CPF("10010010017"), LocalDate.now(), "Josnei", "Birimbau", BigDecimal.valueOf(2000),
                new Endereco("Osorio", "Costa Gama", "Albatroz", "2022A"),
                departamento, LocalDate.now());

        Empregado empregado = new Empregado(new CPF("20020020023"), LocalDate.now(), "Cardoso", "cornelio",
                BigDecimal.valueOf(1000.20), new Endereco("Capao da canoa", "Bacacaca", "guri", "2222"),
                departamento);

        DepartamentoDAO.getInstance().inserir(departamento);
        DepartamentoDAO.getInstance().inserir(new Departamento(LocalDateTime.now(), BigDecimal.valueOf(3003), "Verde"));

        Hangar hangar = new Hangar(1, "C", 20, 20.3, 40.4, 100,
                new Endereco("Porto Alegre", "Osvaldo Aranha", "Zona sul", "2002"), departamento);
        HangarDAO hangarDAO = HangarDAO.getInstance();
        hangarDAO.inserir(hangar);

        EmpregadoDAO empregadoDAO = EmpregadoDAO.getInstance();
        empregadoDAO.inserir(teste);
        empregadoDAO.inserir(empregado);

        Modelo modelo = new Modelo("ford", LocalDate.now(), 12.2, 12.2, "pinoquio",
                Modelo.Tipo.AVIAO, "eeee", "roxo", hangar,"Utilizavel");
        modelo.setCodigo(20);
        Modelo modelo2 = new Modelo("rey", LocalDate.now(), 12.2, 12.2, "pinoquio grande",
                Modelo.Tipo.REPLICA, "ssssssss", "verde", hangar,"Quebradíssimo");
        modelo2.setCodigo(40);

        ModeloDAO modeloDAO = ModeloDAO.getInstance();
        modeloDAO.inserir(modelo);
        modeloDAO.inserir(modelo2);


        LoginDAO loginDAO = LoginDAO.getInstance();
        loginDAO.inserir(new Login(teste, "a"));

        new Interfaces.login();
    }
}
