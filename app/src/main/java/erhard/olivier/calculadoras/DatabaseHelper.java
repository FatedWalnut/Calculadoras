package erhard.olivier.calculadoras;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//Criando o Bd
public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context, String s) {
        super(context, "banco",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE historico(id INTEGER, valorA REAL,valorB REAL,operacao TEXT, resultado REAL, dataTime TEXT);";
            db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql  = "DROP TABLE IF EXISTS historico";
        db.execSQL(sql);
        onCreate(db);
    }


}
