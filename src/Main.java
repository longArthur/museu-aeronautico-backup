import Logic.*;
import Interfaces.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import Persistance.*;

public class Main {
    public static void main(String[] args) {
        Departamento departamento = new Departamento(LocalDateTime.now(), BigDecimal.valueOf(2022), "Roxo");

        departamento.setCodigo(1);
        DepartamentoDAO.getInstance().inserir(departamento);
        Departamento departamento2 = new Departamento(LocalDateTime.now(), BigDecimal.valueOf(22222), "Azul");
        departamento2.setCodigo(2);
        DepartamentoDAO.getInstance().inserir(departamento2);
        DepartamentoDAO.getInstance().inserir(new Departamento(LocalDateTime.now(), BigDecimal.valueOf(22222), "Azul"));

        Hangar hangar = new Hangar(1, "C", 20, 20.3, 40.4, 100,
                new Endereco("Porto Alegre", "Osvaldo Aranha", "Zona sul", 202, "95520000", "RS", null), departamento);
        HangarDAO hangarDAO = HangarDAO.getInstance();
        hangarDAO.inserir(hangar);

        EmpregadoDAO empregadoDAO = EmpregadoDAO.getInstance();


        Modelo modelo = new Modelo("ford", LocalDate.now(), 12.2, 12.2, "pinoquio",
                Modelo.Tipo.AVIAO, "eeee", "roxo", hangar,"Utilizavel");
        modelo.setCodigo(20);
        Modelo modelo2 = new Modelo("rey", LocalDate.now(), 12.2, 12.2, "pinoquio grande",
                Modelo.Tipo.REPLICA, "ssssssss", "verde", hangar,"Quebradíssimo");
        modelo2.setCodigo(40);

        ModeloDAO modeloDAO = ModeloDAO.getInstance();
        modeloDAO.inserir(modelo);
        modeloDAO.inserir(modelo2);

        try {
            ConnectBD connectBD = ConnectBD.getInstance();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        new login();
    }
}
