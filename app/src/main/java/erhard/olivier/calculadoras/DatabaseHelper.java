package erhard.olivier.calculadoras;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// Classe para ajudar na criação e atualização do banco de dados
public class DatabaseHelper extends SQLiteOpenHelper {
    // Construtor da classe
    public DatabaseHelper(Context context, String s) {
        super(context, "banco", null, 1); // Define o nome do banco de dados e a versão
    }

    // Método chamado quando o banco de dados é criado pela primeira vez
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Criação da tabela 'historico' com as colunas necessárias
        String sql = "CREATE TABLE historico(id INTEGER PRIMARY KEY, valorA REAL, valorB REAL, operacao TEXT, resultado REAL, dataTime TEXT);";
        db.execSQL(sql); // Executa o comando SQL para criar a tabela
    }

    // Método chamado quando uma nova versão do banco de dados é detectada
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Caso exista o banco de dados anterior, ele é apagado e uma nova versão é criada
        String sql = "DROP TABLE IF EXISTS historico"; // Comando para apagar a tabela existente
        db.execSQL(sql); // Executa o comando SQL para apagar a tabela
        onCreate(db); // Chama o método onCreate() para criar a nova versão do banco de dados
    }
}
