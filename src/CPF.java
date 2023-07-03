

public class CPF {
    private final String cpf;
    public CPF(String stringCPF){
        stringCPF = stringCPF.replaceAll("\\D", "");
        if(stringCPF.length() != 11) throw new IllegalArgumentException("CPF PRECISA TER 11 NUMEROS");
        if((stringCPF.equals("00000000000"))|(stringCPF.equals("11111111111"))|
            (stringCPF.equals("22222222222"))|(stringCPF.equals("33333333333"))|
            (stringCPF.equals("44444444444"))|(stringCPF.equals("55555555555"))|
            (stringCPF.equals("66666666666"))|(stringCPF.equals("77777777777"))|
            (stringCPF.equals("88888888888"))|(stringCPF.equals("99999999999"))) throw new IllegalArgumentException("Os numeros do CPF nao podem ser todos iguais");

        String meioDeCampo = stringCPF.substring(0,9);

        meioDeCampo = meioDeCampo.concat(calcularEnesimo(meioDeCampo));
        meioDeCampo = meioDeCampo.concat(calcularEnesimo(meioDeCampo));
        if(!stringCPF.equals(meioDeCampo)) throw new IllegalArgumentException("Cpf nao e valido");

        cpf = stringCPF;
    }

    private String calcularEnesimo(String operando){
        int subTotal = 0;
        for(int i = 0; i < operando.length(); i++){
            subTotal += (((int) operando.charAt(i)) - 48) * (operando.length()+1-i);
        }
        return ""+((11-(subTotal%11) == 10) ? (0) : ((11-(subTotal%11))));
    }


    public String getCpf() {
        return cpf;
    }
}