package erhard.olivier.calculadoras;
import android.database.Cursor;
import android.util.Log;
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




public class MainActivity extends AppCompatActivity {
    //Cria os metodos A e B para que possa fazer a operação em si
    private EditText valorAEditText;
    private EditText valorBEditText;

    private DatabaseHelper dbHelper;
     int idCounter = 0 ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Indica qual é a activity que é utilizada
        setContentView(R.layout.activity_main);

        // Inicializa os editText para poder guardar os dados
        valorAEditText = findViewById(R.id.valorA);
        valorBEditText = findViewById(R.id.valorB);
        dbHelper = new DatabaseHelper(this, "banco.db");



    }


        //Cria a função que vai guardar os dados no banco de dados
        private void inserirDadosNoBanco(double valorA, double valorB, String operacao, double resultado) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("valorA", valorA);
            values.put("valorB", valorB);
            values.put("operacao", operacao);
            values.put("resultado", resultado);
            values.put("dataTime", getDateTime());

            // Insere os valores no banco de dados
            long id = db.insert("historico", null, values);
            Log.d("InserirDados", "Inserindo dados no banco de dados: valorA=" + valorA + ", valorB=" + valorB + ", operacao=" + operacao + ", resultado=" + resultado + ", ID=" + id);

            // Feche o banco de dados após a inserção
            db.close();
        }





    //Usa a biblioteca padrão do java para pegar o horario e a formatação esta em dia-mes-ano hora:minuto
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

            valorAEditText.setText("");
            valorBEditText.setText("");

        }
    }
    // Método para lidar com o clique do botão de subtracao
    public void onClickSubtracao(View view) {
        // Verifique se os campos ValorA e ValorB são válidos
        if (isValidInput()) {
            // Aqui você pode continuar com a lógica de realização da operação
            // de soma e exibir o resultado
            double valorA = Double.parseDouble(valorAEditText.getText().toString());
            double valorB = Double.parseDouble(valorBEditText.getText().toString());
            idCounter++;
            double resultado = valorA - valorB;
            inserirDadosNoBanco(valorA,valorB,"-",resultado);
            Toast.makeText(this, "Resultado da subtracao: " + resultado, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Cálculo armazenado com sucesso: ", Toast.LENGTH_SHORT).show();
            valorAEditText.setText("");
            valorBEditText.setText("");

        }
    }
    // Método para lidar com o clique do botão de Multiplicaçao
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
            valorAEditText.setText("");
            valorBEditText.setText("");

        }
    }

    // Método para lidar com o clique do botão de divisão
    //Levando em conta que não pode deixar em branco e nem pode dividir 0
    // e nem dividir por zero
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
                valorAEditText.setText("");
                valorBEditText.setText("");

            }
        }
    }

    // Abre a activity HistoricoActivity ao apertar o botão abrirHistorico
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
