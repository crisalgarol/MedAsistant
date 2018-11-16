package Utilidades;

public class SentenciasSQLite {


    public static final String NOMBRE_BASE_DE_DATOS = "medAssistant";

    public static final String NOMBRE_TABLA_MEDICAMENTOS = "Medicamentos";
    public static final String NOMBRE_TABLA_HORARIOS = "Horarios";
    public static final String NOMBRE_TABLA_DOCTORES = "Doctores";


    public static final String CREAR_TABLA_DOCTORES = "CREATE TABLE 'Doctores' ('Nombre' TEXT NOT NULL,'Telefono' TEXT NOT NULL);";
    public static final String CREAR_TABLA_HORARIOS = "CREATE TABLE 'Horarios' ('id_medicamento' INTEGER NOT NULL,'FechaFin' TEXT NOT NULL, 'HoraAviso' TEXT NOT NULL);";
    public static final String CREAR_TABLA_MEDICAMENTOS = "CREATE TABLE 'Medicamentos' ('Nombre' TEXT NOT NULL,'DescripcionPadecimiento' TEXT NOT NULL,'RutaImagenMed' TEXT,'RutaImagenEmpaque' TEXT,'Dosis' TEXT NOT NULL,'id_doctor' INTEGER);";

    public static final String CAMPO_NOMBRE_MEDICAMENTOS = "Nombre";
    public static final String CAMPO_DESCRIPCION_MEDICAMENTOS = "DescripcionPadecimiento";
    public static final String CAMPO_RUTAIMAGENMED_MEDICAMENTOS = "RutaImagenMed";
    public static final String CAMPO_RUTAIMAGENEMPA_MEDICAMENTOS = "RutaImagenEmpaque";
    public static final String CAMPO_DOSIS_MEDICAMENTOS = "Dosis";
    public static final String CAMPO_IDDOCTOR_MEDICAMENTOS = "id_doctor";

    public static final String CAMPO_NOMBRE_DOCTORES = "Nombre";
    public static final String CAMPO_TELEFONO_DOCTORES = "Telefono";

    public static final String CAMPO_IDMEDICAMENTO_HORARIO = "id_medicamento";
    public static final String CAMPO_FECHAFIN_HORARIO = "FechaFin";
    public static final String CAMPO_HORARIOAVISO_HORARIO = "HoraAviso";




}
