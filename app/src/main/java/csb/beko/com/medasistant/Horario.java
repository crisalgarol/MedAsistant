package csb.beko.com.medasistant;

import java.util.Calendar;
import java.util.Date;

public class Horario {

    int id_horario;
    int id_medicamento;
    String fechaFin;
    String HoraAviso;
    Calendar fecha_fin_Hora_Aviso;

    public Horario(){

    }

    public int getId_horario() {
        return id_horario;
    }

    public void setId_horario(int id_horario) {
        this.id_horario = id_horario;
    }

    public int getId_medicamento() {
        return id_medicamento;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getHoraAviso() {
        return HoraAviso;
    }

    public void setHoraAviso(String horaAviso) {
        HoraAviso = horaAviso;
    }

    public void setId_medicamento(int id_medicamento) {
        this.id_medicamento = id_medicamento;
    }

    public Calendar getFecha_fin_Hora_Aviso() {
        return fecha_fin_Hora_Aviso;
    }

    public void setFecha_fin_Hora_Aviso(Calendar fecha_fin_Hora_Aviso) {
        this.fecha_fin_Hora_Aviso = fecha_fin_Hora_Aviso;
    }
}
