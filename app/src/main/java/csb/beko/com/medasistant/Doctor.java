package csb.beko.com.medasistant;

public class Doctor {

    int id_doctor;
    String nombre;
    String numeroTelefono;

    public Doctor(int id_doctor, String nombre, String numeroTelefono) {
        this.id_doctor = id_doctor;
        this.nombre = nombre;
        this.numeroTelefono = numeroTelefono;
    }

    public int getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }
}
