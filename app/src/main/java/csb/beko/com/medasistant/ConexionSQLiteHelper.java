package csb.beko.com.medasistant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import Utilidades.SentenciasSQLite;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {


    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SentenciasSQLite.CREAR_TABLA_MEDICAMENTOS);
        db.execSQL(SentenciasSQLite.CREAR_TABLA_DOCTORES);
        db.execSQL(SentenciasSQLite.CREAR_TABLA_HORARIOS);
        Log.d("cr ConexionSQLiteHelper", "Se mando a llamar onCreate SQLite");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + SentenciasSQLite.NOMBRE_TABLA_MEDICAMENTOS);
        db.execSQL("DROP TABLE IF EXISTS " + SentenciasSQLite.NOMBRE_TABLA_DOCTORES);
        db.execSQL("DROP TABLE IF EXISTS " + SentenciasSQLite.NOMBRE_TABLA_HORARIOS);
        onCreate(db);
    }
}
