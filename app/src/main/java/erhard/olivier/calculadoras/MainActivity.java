package erhard.olivier.calculadoras;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Date;
import java.text.SimpleDateFormat;

import erhard.olivier.calculadoras.DatabaseHelper;
import erhard.olivier.calculadoras.R;


public class MainActivity extends AppCompatActivity {
    private EditText valorAEditText;
    private EditText valorBEditText;

    private DatabaseHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialize os EditTexts
        valorAEditText = findViewById(R.id.valorA);
        valorBEditText = findViewById(R.id.valorB);
        dbHelper = new DatabaseHelper(this, "banco.db");



    }



    private void inserirDadosNoBanco(double valorA, double valorB, String operacao, double resultado) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("valorA", valorA);
        values.put("valorB", valorB);
        values.put("operacao", operacao);
        values.put("resultado", resultado);
        values.put("dataTime", getDateTime());

        // Insere os valores no banco de dados
        db.insert("historico", null, values);


        // Feche o banco de dados após a inserção
        db.close();
    }


    private String getDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return sdf.format(new Date());
    }



    // Método para lidar com o clique do botão de soma
    public void onClickAdicao(View view) {
        // Verifique se os campos ValorA e ValorB são válidos
        if (isValidInput()) {
            // Aqui você pode continuar com a lógica de realização da operação
            // de soma e exibir o resultado
            double valorA = Double.parseDouble(valorAEditText.getText().toString());
            double valorB = Double.parseDouble(valorBEditText.getText().toString());
            double resultado = valorA + valorB;
            inserirDadosNoBanco(valorA,valorB,"+",resultado);
            Toast.makeText(this, "Resultado da soma: " + resultado, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Cálculo armazenado com sucesso: ", Toast.LENGTH_SHORT).show();

        }
    }

    public void onClickSubtracao(View view) {
        // Verifique se os campos ValorA e ValorB são válidos
        if (isValidInput()) {
            // Aqui você pode continuar com a lógica de realização da operação
            // de soma e exibir o resultado
            double valorA = Double.parseDouble(valorAEditText.getText().toString());
            double valorB = Double.parseDouble(valorBEditText.getText().toString());
            double resultado = valorA - valorB;
            inserirDadosNoBanco(valorA,valorB,"-",resultado);
            Toast.makeText(this, "Resultado da subtracao: " + resultado, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Cálculo armazenado com sucesso: ", Toast.LENGTH_SHORT).show();
        }
    }
    public void onClickMultiplicacao(View view) {
        // Verifique se os campos ValorA e ValorB são válidos
        if (isValidInput()) {
            // Aqui você pode continuar com a lógica de realização da operação
            // de soma e exibir o resultado
            double valorA = Double.parseDouble(valorAEditText.getText().toString());
            double valorB = Double.parseDouble(valorBEditText.getText().toString());
            double resultado = valorA * valorB;
            inserirDadosNoBanco(valorA,valorB,"*",resultado);
            Toast.makeText(this, "Resultado da subtracao: " + resultado, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Cálculo armazenado com sucesso: ", Toast.LENGTH_SHORT).show();
        }
    }
    public void onClickDivisao(View view) {
        // Verifique se os campos ValorA e ValorB são válidos
        if (isValidInput()) {
            double valorA = Double.parseDouble(valorAEditText.getText().toString());
            double valorB = Double.parseDouble(valorBEditText.getText().toString());

            if (valorA == 0) {
                valorAEditText.setError("Impossível fazer divisão por 0");
            } else if (valorB == 0) {
                valorBEditText.setError("Impossível fazer divisão por 0");
            } else {
                double resultado = valorA / valorB;
                inserirDadosNoBanco(valorA, valorB, "÷", resultado);
                Toast.makeText(this, "Resultado da divisão: " + resultado, Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Cálculo armazenado com sucesso", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void abrirHistorico(View view) {
        Intent intent = new Intent(this, HistoricoActivity.class);
        startActivity(intent);
    }



    // Método para validar os campos ValorA e ValorB
    private boolean isValidInput() {
        // Ver se ambos valores estão preenchidos
        if (TextUtils.isEmpty(valorAEditText.getText().toString())) {
            valorAEditText.setError("Campo obrigatório");
            return false;
        }

        if (TextUtils.isEmpty(valorBEditText.getText().toString())) {
            valorBEditText.setError("Campo obrigatório");
            return false;
        }

        // se tudo preenchido retorna verdadeiro
        return true;
    }
}
