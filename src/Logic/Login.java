package Logic;

import javax.swing.*;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Objects;

public class Login {
    private final Empregado empregado;
    private final byte[] senhaHash;

    public Login(Empregado empregado, byte[] senha) {
        Objects.requireNonNull(empregado, "Empregado nao pode ser nulo");

        this.senhaHash = senha;
        this.empregado = empregado;
    }

    public Empregado getEmpregado() {
        return empregado;
    }

    public byte[] getSenhaHash() {
        return senhaHash;
    }

    public static byte[] hash(String str){

        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            return digest.digest(str.getBytes());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Contate um administrador, CODIGO \"512\".");
        }

        return new byte[0];
    }

    public boolean compareSenha(String senha){
        return Arrays.equals(hash(senha), this.senhaHash);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Login login = (Login) o;
        return Objects.equals(empregado, login.empregado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empregado);
    }
}
