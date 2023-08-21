package Logic;

import java.util.Objects;

public class Login {
    private final Empregado empregado;
    private final byte[] senhaHash;

    public Login(Empregado empregado, byte[] senhaHash) {
        Objects.requireNonNull(empregado, "Empregado nao pode ser nulo");
        if(senhaHash.length != 64) throw new IllegalArgumentException("Hash tem que ser SHA512");

        this.empregado = empregado;
        this.senhaHash = senhaHash;
    }

    public Empregado getEmpregado() {
        return empregado;
    }

    public byte[] getSenhaHash() {
        return senhaHash;
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
