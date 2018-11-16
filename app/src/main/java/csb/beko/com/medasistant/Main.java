package csb.beko.com.medasistant;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import Utilidades.SentenciasSQLite;

public class Main extends AppCompatActivity {


    private DrawerLayout drawerLayout;

    public ConexionSQLiteHelper baseDeDatos;
    public static SQLiteDatabase dataBase;

    @Override
    protected void onStart() {
        super.onStart();

        try {
            //Inicializar la base de datos
            baseDeDatos = new ConexionSQLiteHelper(this, SentenciasSQLite.NOMBRE_BASE_DE_DATOS, null, 1);
            //Abrir la base para escritura
            dataBase = baseDeDatos.getWritableDatabase();
        }catch (Exception e){
            Log.d("crisalgarol Main",  e.toString());
        }
        /*

        ContentValues cv = new ContentValues();
        cv.put(SentenciasSQLite.CAMPO_NOMBRE_MEDICAMENTOS, "Paracetamol");
        cv.put(SentenciasSQLite.CAMPO_DESCRIPCION_MEDICAMENTOS, "Sirve para el dolor de cabeza");
        cv.put(SentenciasSQLite.CAMPO_RUTAIMAGENMED_MEDICAMENTOS, "/beko/");
        cv.put(SentenciasSQLite.CAMPO_RUTAIMAGENEMPA_MEDICAMENTOS, "/bekoEmp/");
        cv.put(SentenciasSQLite.CAMPO_DOSIS_MEDICAMENTOS, "1 mg");
        cv.put(SentenciasSQLite.CAMPO_IDDOCTOR_MEDICAMENTOS, "Dr. Beko");*/


        //Long idRes = dataBase.insert(SentenciasSQLite.NOMBRE_TABLA_MEDICAMENTOS, SentenciasSQLite.CAMPO_DESCRIPCION_MEDICAMENTOS, cv) ;
       // Log.d("crisalgarol Main", "ID Devuelto: " + idRes );

    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        /*Inicializa la lista de fragmentos*/

        Fragment f = new dashboard();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, f);
        transaction.commit();


        configureNavigationDrawer();
        configureToolbar();
    }

    private void configureToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionbar.setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                Log.d("Cristian", "Me presiono");
                drawerLayout.openDrawer(GravityCompat.START );
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void configureNavigationDrawer() {

        NavigationView navView = (NavigationView) findViewById(R.id.navigation);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                Fragment f = null;

                switch (menuItem.getItemId()){

                    case R.id.inicioly:
                        f = new dashboard();
                        break;

                    case R.id.gestionarly:
                        f = new gestionarMedicamentos();
                        break;


                    case R.id.farmaciasly:
                        f = new farmaciasCercanas();
                        break;

                    case R.id.respaldarly:
                        f = new respaldar();
                        break;

                    case R.id.calendarioly:
                        f = new calendarioProgramado();
                        break;


                    default:
                        break;
                }


                if (f != null) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame, f);
                    transaction.commit();
                    drawerLayout.closeDrawers();
                    return true;
                }
                return false;
            }

        });
    }
}
