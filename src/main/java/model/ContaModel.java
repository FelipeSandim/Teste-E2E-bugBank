package model;

public class ContaModel {
    private String numero;
    private String digito;
    public ContaModel(String numero, String digito) {
        this.numero = numero;
        this.digito = digito;
    }
    public String getNumero() {
        return numero;
    }
    public String getDigito() {
        return digito;
    }
}
