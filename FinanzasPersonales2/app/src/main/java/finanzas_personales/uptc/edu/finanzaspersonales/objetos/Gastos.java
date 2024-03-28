package finanzas_personales.uptc.edu.finanzaspersonales.objetos;

public class Gastos {

    private String valor, nota, categoria;

    public Gastos(String valor, String nota, String categoria) {
        this.valor = valor;
        this.nota = nota;
        this.categoria = categoria;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
