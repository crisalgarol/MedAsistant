package csb.beko.com.medasistant;

public class Medicamento {

    int id;

    String nombre;
    String descripcionPadecimiento;
    String rutaImagenMedicina;
    String rutaImagenEmpaque;
    String dosis;
    String id_doctor;
    int id_medicamento;

    public int getId_medicamento() {
        return id_medicamento;
    }

    public void setId_medicamento(int id_medicamento) {
        this.id_medicamento = id_medicamento;
    }

    public Medicamento(int id, String nombre, String descripcionPadecimiento, String rutaImagenMedicina, String rutaImagenEmpaque, String dosis, String id_doctor) {
        this.id = id;
        this.nombre = nombre;
        this.descripcionPadecimiento = descripcionPadecimiento;
        this.rutaImagenMedicina = rutaImagenMedicina;
        this.rutaImagenEmpaque = rutaImagenEmpaque;
        this.dosis = dosis;
        this.id_doctor = id_doctor;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcionPadecimiento() {
        return descripcionPadecimiento;
    }

    public void setDescripcionPadecimiento(String descripcionPadecimiento) {
        this.descripcionPadecimiento = descripcionPadecimiento;
    }

    public String getRutaImagenMedicina() {
        return rutaImagenMedicina;
    }

    public void setRutaImagenMedicina(String rutaImagenMedicina) {
        this.rutaImagenMedicina = rutaImagenMedicina;
    }

    public String getRutaImagenEmpaque() {
        return rutaImagenEmpaque;
    }

    public void setRutaImagenEmpaque(String rutaImagenEmpaque) {
        this.rutaImagenEmpaque = rutaImagenEmpaque;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public String getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(String id_doctor) {
        this.id_doctor = id_doctor;
    }
}
