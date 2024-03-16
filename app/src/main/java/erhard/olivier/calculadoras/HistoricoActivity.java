package erhard.olivier.calculadoras;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class HistoricoActivity extends AppCompatActivity {
    // Lista de operações e adaptador para a lista
    private List<Historico> operacoesList;
    private HistoricoAdapter historicoAdapter;
    // Banco de dados SQLite
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        // Inicializa a lista e o adaptador
        operacoesList = new ArrayList<>();
        historicoAdapter = new HistoricoAdapter(this, operacoesList);

        // Configura o ListView com o adaptador
        ListView listViewHistorico = findViewById(R.id.listViewHistorico);
        listViewHistorico.setAdapter(historicoAdapter);

        // Abre o banco de dados em modo leitura
        db = new DatabaseHelper(this, "historicos.db").getReadableDatabase();

        // Recupera os dados do banco de dados e atualiza a lista
        carregarDadosDoBanco();
    }

    @SuppressLint("Range")
    private void carregarDadosDoBanco() {
        operacoesList.clear();

        // Executa a consulta SQL para recuperar os registros da tabela de histórico
        Cursor cursor = db.rawQuery("SELECT * FROM historico", null);
        if (cursor.moveToFirst()) {
            do {
                // Cria um objeto Historico para cada registro retornado pela consulta
                Historico operacoes = new Historico();
                operacoes.setId(cursor.getInt(cursor.getColumnIndex("id")));
                operacoes.setValorA(cursor.getDouble(cursor.getColumnIndex("valorA")));
                operacoes.setValorB(cursor.getDouble(cursor.getColumnIndex("valorB")));
                operacoes.setOperacao(cursor.getString(cursor.getColumnIndex("operacao")));
                operacoes.setResultado(cursor.getDouble(cursor.getColumnIndex("resultado")));
                operacoes.setDataHora(cursor.getString(cursor.getColumnIndex("dataTime")));

                // Adiciona o objeto Historico à lista
                operacoesList.add(operacoes);
            } while (cursor.moveToNext());
        }
        cursor.close();

        // Notifica o adaptador sobre as mudanças nos dados
        historicoAdapter.notifyDataSetChanged();
    }
}
