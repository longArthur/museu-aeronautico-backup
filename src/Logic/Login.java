package Logic;

import javax.swing.*;
import java.util.Arrays;
import java.util.Objects;
import java.security.MessageDigest;

public class Login {
    private final Empregado empregado;
    private final byte[] senhaHash;

    public Login(Empregado empregado, String senha) {
        Objects.requireNonNull(empregado, "Empregado nao pode ser nulo");

        this.senhaHash = this.hash(senha);
        this.empregado = empregado;
    }

    public Empregado getEmpregado() {
        return empregado;
    }

    public byte[] getSenhaHash() {
        return senhaHash;
    }

    private byte[] hash(String str){

        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            return digest.digest(str.getBytes());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Contate um administrador, CODIGO \"512\".");
        }
        return new byte[0];
    }

    public boolean compareSenha(String senha){
        return this.hash(senha).equals(this.senhaHash);
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
