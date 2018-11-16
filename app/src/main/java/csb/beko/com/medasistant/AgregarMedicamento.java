package csb.beko.com.medasistant;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import Utilidades.SentenciasSQLite;

import static csb.beko.com.medasistant.Main.dataBase;

public class AgregarMedicamento extends AppCompatActivity {


    TextView fecha_tv;
    TextView hora_tv;

    Button botonAceptar;
    Button agregar_hora_boton;

    EditText nombre_ed;
    EditText padecimiento_ed;
    EditText dosis_ed;
    EditText doctor_ed;
    EditText numero_ed;
    EditText lista_horas_ed;

    String rutaImagenMedicina;
    String rutaImagenEmpaque;

    ImageView agregarMedicina_boton;
    ImageView agregarEmpaque_boton;

    Boolean esMedicina = false;

    ArrayList<Horario> horarios_medicinas = new ArrayList<>();


    Calendar c = Calendar.getInstance();
    Calendar fechaFin = Calendar.getInstance();

    DatePickerDialog datePickerDialog;
    TimePickerDialog mTimePickerDialog;



    int dia = c.get(Calendar.DAY_OF_MONTH);
    int mes = c.get(Calendar.MONTH) + 1;
    int anio = c.get(Calendar.YEAR);
    int hora = c.get(Calendar.HOUR_OF_DAY) ;
    int minuto = c.get(Calendar.MINUTE);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregarmedicamento);

        fecha_tv = (TextView) findViewById(R.id.fecha_agregarMed_tv);
        hora_tv = (TextView) findViewById(R.id.hora_agregarMedicamento_tv);
        nombre_ed = (EditText) findViewById(R.id.nombre_ed);
        padecimiento_ed = (EditText) findViewById(R.id.padecimiento_ed);
        dosis_ed = (EditText) findViewById(R.id.dosis_ed);
        doctor_ed = (EditText) findViewById(R.id.dosis_ed);
        numero_ed = (EditText) findViewById(R.id.numero_ed);
        agregar_hora_boton = (Button) findViewById(R.id.agregar_hora_btn);
        lista_horas_ed = (EditText) findViewById(R.id.listaHoras_ed);

        agregarMedicina_boton = (ImageView) findViewById(R.id.agregarmedicina_imv);
        agregarMedicina_boton.setClickable(true);
        agregarEmpaque_boton = (ImageView) findViewById(R.id.agregarempaque_imv);
        agregarEmpaque_boton.setClickable(true);


        botonAceptar = (Button) findViewById(R.id.anadir_agregarMedicamento_bt);

        /*Poner la fecha actual*/
        fecha_tv.setText(dia+"/"+(mes+1)+"/"+anio);
        /*Inicializar a "" las horas*/
        lista_horas_ed.setText("");
        hora_tv.setText(hora + ":" + minuto);

        /*Permitir añadir imagen*/
        agregarMedicina_boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent tomarFotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                tomarFotoIntent.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                esMedicina = true;

                /*VERIFICAR SI LA CAMARA PUEDE INICIARSE*/
                if(tomarFotoIntent.resolveActivity(getPackageManager()) != null) {

                    //Crear una imagen
                    File imagen = null;


                    //Intentamos crear la imagen
                    try {
                        imagen = crearImagen(true);
                        Log.d("AgregarMedicamento", "R: " + imagen.getAbsolutePath());
                    } catch (Exception e) {
                        Log.d("agregarMedicinaBoton", "Error al crear la imagen" );
                    }

                    if (imagen != null) {

                        Uri fotoUri = FileProvider.getUriForFile(getApplicationContext(), "com.csb.android.fileprovider", imagen);
                        Log.d("AgregarNMedicamento", "Se creo la imagen");


                        tomarFotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);

                        try {
                            startActivityForResult(tomarFotoIntent, 1);
                        }catch(Exception e){
                            Log.d("AgregarMedicamento", e.toString());
                        }



                    }
                }
            }
        });

        agregarEmpaque_boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tomarFotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                esMedicina = false;

                /*VERIFICAR SI LA CAMARA PUEDE INICIARSE*/
                if(tomarFotoIntent.resolveActivity(getPackageManager()) != null) {

                    //Crear una imagen
                    File imagen = null;

                    //Intentamos crear la imagen
                    try {
                        imagen = crearImagen(esMedicina);
                        Log.d("AgregarMedicamento", "R: " + imagen.getAbsolutePath());
                    } catch (Exception e) {
                        Log.d("agregarEmpaqueBoton", "Error al crear la imagen" );
                    }

                    if (imagen != null) {

                        Uri fotoUri = FileProvider.getUriForFile(getApplicationContext(), "com.csb.android.fileprovider", imagen);
                        Log.d("AgregarMedicamento", "Se creo la imagen en: " + imagen.getAbsolutePath());


                        tomarFotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);

                        try {
                            startActivityForResult(tomarFotoIntent, 1);
                        }catch(Exception e){
                            Log.d("AgregarMedicamento", e.toString());
                        }



                    }
                }
            }
        });

        fecha_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarFecha();
            }
            });

        hora_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarHora();
            }
        });

        botonAceptar.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                ContentValues cv = new ContentValues();

                /*Agregar a la tabla doctores*/
                cv.put(SentenciasSQLite.CAMPO_NOMBRE_DOCTORES, doctor_ed.getText().toString());
                cv.put(SentenciasSQLite.CAMPO_TELEFONO_DOCTORES, doctor_ed.getText().toString());
                Long rowIDDoctor =  dataBase.insert(SentenciasSQLite.NOMBRE_TABLA_DOCTORES, SentenciasSQLite.CAMPO_NOMBRE_DOCTORES, cv) ;

                /*Agregar a la tabla de medicamentos*/
                cv = new ContentValues();
                cv.put(SentenciasSQLite.CAMPO_NOMBRE_MEDICAMENTOS, nombre_ed.getText().toString());
                cv.put(SentenciasSQLite.CAMPO_DESCRIPCION_MEDICAMENTOS, padecimiento_ed.getText().toString());
                cv.put(SentenciasSQLite.CAMPO_RUTAIMAGENMED_MEDICAMENTOS, rutaImagenMedicina);
                cv.put(SentenciasSQLite.CAMPO_RUTAIMAGENEMPA_MEDICAMENTOS, rutaImagenEmpaque);
                cv.put(SentenciasSQLite.CAMPO_DOSIS_MEDICAMENTOS, dosis_ed.getText().toString());
                cv.put(SentenciasSQLite.CAMPO_IDDOCTOR_MEDICAMENTOS, rowIDDoctor + "");
                cv.put(SentenciasSQLite.CAMPO_RUTAIMAGENMED_MEDICAMENTOS, rutaImagenMedicina);
                cv.put(SentenciasSQLite.CAMPO_RUTAIMAGENEMPA_MEDICAMENTOS, rutaImagenEmpaque);

                int numMed = (int) dataBase.insert(SentenciasSQLite.NOMBRE_TABLA_MEDICAMENTOS, SentenciasSQLite.CAMPO_DESCRIPCION_MEDICAMENTOS, cv);


                //Agregar todos los horarios con el ID arrojado
                    for(int i=0; i<horarios_medicinas.size(); i++){
                        cv = new ContentValues();

                        //Agregar a los horarios la fecha final
                        Calendar cc = horarios_medicinas.get(i).getFecha_fin_Hora_Aviso();
                        cc.set(anio, mes, dia);
                        horarios_medicinas.get(i).setFecha_fin_Hora_Aviso(cc);
                        //Agregar a los horarios el numMed como id_medicamento
                        horarios_medicinas.get(i).setId_medicamento(numMed);

                        /*Agregar esos nuevos valores a la base de datos*/
                        cv.put(SentenciasSQLite.CAMPO_FECHAFIN_HORARIO, dia + "/" + mes + "/" + dia);
                        cv.put(SentenciasSQLite.CAMPO_IDMEDICAMENTO_HORARIO, numMed);
                        cv.put(SentenciasSQLite.CAMPO_HORARIOAVISO_HORARIO, hora+":"+minuto);

                        int br = (int) dataBase.insert(SentenciasSQLite.NOMBRE_TABLA_HORARIOS, SentenciasSQLite.CAMPO_FECHAFIN_HORARIO, cv);
                        Log.d("Cristian", "Inserto Hora: " + br);
                    }

                Toast.makeText(getApplicationContext(), "Se agrego: " + nombre_ed.getText().toString() + " #TDoc " + rowIDDoctor + " #TMedi " + numMed, Toast.LENGTH_LONG).show();
                finish();
            }
        });

        //Boton para agregar la hora
        agregar_hora_boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Horario horario = new Horario();

                Calendar c_aux = Calendar.getInstance();
                c_aux.set(anio, mes, dia, hora, minuto);

               horario.setFecha_fin_Hora_Aviso(c_aux);

               horarios_medicinas.add(horario);
               lista_horas_ed.setText(serializarHoras());
            }
        });
}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK){
            agregarAGaleria(esMedicina);
            agregarFotoFullSize(esMedicina);
        }

    }

    private void mostrarFecha(){


       // Toast.makeText(getApplicationContext(), "Fecha actual: " + dia + "/" + (mes+1) + "/" + anio, Toast.LENGTH_LONG).show();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int d, int m, int a) {
                fecha_tv.setText(d+"/"+(m+1)+"/"+a);
                dia = d; mes = m+1; anio = a;
            }

        }, anio, mes, dia);

         datePickerDialog.show();
    }

    private void mostrarHora() {

        c = Calendar.getInstance();
        hora = c.get(Calendar.HOUR_OF_DAY);
        minuto = c.get(Calendar.MINUTE);

        mTimePickerDialog = new TimePickerDialog(AgregarMedicamento.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                hora = i; minuto = i1;
                String h = "";

                if(i<10)
                    h+="0" + i;
                else
                    h+=i;

                h+=":";

                if(i1<10)
                    h+="0" + i1;
                else
                    h+=i1;

                hora_tv.setText(h);
            }
        }, hora, minuto, true);

        mTimePickerDialog.show();

    }

    private File crearImagen(Boolean isFotoMedicina) {

        //Directorio donde se guardara la imagen
        File directorio = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        try {


            //Crear el archivo
            File imagenFile;

            if (isFotoMedicina) {
                imagenFile = File.createTempFile("medicamento", ".jpg", directorio);
                rutaImagenMedicina = imagenFile.getAbsolutePath();
                //Log.d("AgregarMedicamento", "crearImagen: rutaImMed " + rutaImagenMedicina);
            } else {
                imagenFile = File.createTempFile("empaque", ".jpg", directorio);
                rutaImagenEmpaque = imagenFile.getAbsolutePath();
                //Log.d("AgregarMedicamento", "crearImagen: rutaEmpMed " + rutaImagenMedicina);
            }

            Log.d("AgregarMedicamento", "Imagen creada desde crearImagen(): " + imagenFile.getAbsolutePath());
            return imagenFile;

        }catch (Exception e){
            Log.e("AgregarMedicamento", "crearImagen: " + e.toString());
        }

        Log.e("AgregarMedicamento", "Imagen creada desde crearImagen(): " + " NULL ");
        return null;
    }

    private void agregarAGaleria(Boolean isMedicine){

        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);

        try {
            File f;

            if(isMedicine)
                f = new File(rutaImagenMedicina);
            else
                f = new File(rutaImagenEmpaque);

            Uri contentUri = Uri.fromFile(f);
            mediaScanIntent.setData(contentUri);
            this.sendBroadcast(mediaScanIntent);

        }catch (Exception e){
            Log.e("AgregarMedicamento","Error al leer el archivo");
        }
    }

    private void agregarFotoFullSize(Boolean isMedicine){

        String ruta;

        if(isMedicine)
            ruta = rutaImagenMedicina;
        else
            ruta = rutaImagenEmpaque;


        // Get the dimensions of the View
        int targetW, targetH;

        if(isMedicine){
            targetH = agregarMedicina_boton.getWidth();
            targetW = agregarMedicina_boton.getHeight();
        }else{
            targetH = agregarEmpaque_boton.getWidth();
            targetW = agregarEmpaque_boton.getHeight();
        }



        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;


        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;

        Bitmap bitmapF = null;

        try {
            bitmapF = BitmapFactory.decodeFile(ruta, bmOptions);
        }catch(Exception e){
            Log.e("AgregarMedicamento", e.toString());
            String ee = e.toString();
        }

        if(bitmapF == null)
            Log.d("AgregarMedicamento", "Es nulo " + ruta);

        if(isMedicine) {
            agregarMedicina_boton.setImageBitmap(bitmapF);
            Log.d("AgregarMedicamento: ", "Se creo una imagen de una medicina");
        }else {
            agregarEmpaque_boton.setImageBitmap(bitmapF);
            Log.d("AgregarMedicamento: ", "Se creo una imagen de un empaque");
        }



    }

    private String serializarHoras(){
        String hs = "";

        for(int i=0; i<horarios_medicinas.size(); i++){
            Horario h = horarios_medicinas.get(i);
            Calendar cc = h.getFecha_fin_Hora_Aviso();

            if(cc.get(Calendar.HOUR_OF_DAY) < 10){
                hs += "0" + cc.get(Calendar.HOUR_OF_DAY);
            }else{
                hs += cc.get(Calendar.HOUR_OF_DAY);
            }

            hs += ":";

            if(cc.get(Calendar.MINUTE) <10){
                hs += "0" + cc.get(Calendar.MINUTE);
            }else{
                hs += cc.get(Calendar.MINUTE);
            }

            if(i+1 != horarios_medicinas.size())
                hs += "\n";

        }

        return hs;

    }


}
