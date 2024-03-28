package finanzas_personales.uptc.edu.finanzaspersonales.objetos;

public class Usuario {
    String nombre,correo,password,edad;

    public Usuario(String nombre, String correo, String password, String edad) {
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }
}
