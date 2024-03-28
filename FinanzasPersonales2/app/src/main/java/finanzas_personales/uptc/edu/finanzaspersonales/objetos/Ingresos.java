package finanzas_personales.uptc.edu.finanzaspersonales.objetos;

public class Ingresos {

    private String valorIngreso, nota;

    public Ingresos(String valorIngreso, String nota) {
        this.valorIngreso = valorIngreso;
        this.nota = nota;
    }

    public String getValorIngreso() {
        return valorIngreso;
    }

    public void setValorIngreso(String valorIngreso) {
        this.valorIngreso = valorIngreso;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
