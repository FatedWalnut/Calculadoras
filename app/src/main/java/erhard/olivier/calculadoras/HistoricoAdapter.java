package erhard.olivier.calculadoras;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class HistoricoAdapter extends BaseAdapter {
    // Contexto da aplicação e lista de operações
    private final Context context;
    private final List<Historico> operacoesList;

    // Construtor da classe
    public HistoricoAdapter(Context context, List<Historico> operacoesList) {
        this.context = context;
        this.operacoesList = operacoesList;
    }

    // Retorna o número de itens na lista
    @Override
    public int getCount() {
        return operacoesList.size();
    }

    // Retorna o item na posição especificada
    @Override
    public Object getItem(int position) {
        return operacoesList.get(position);
    }

    // Retorna o ID do item na posição especificada
    @Override
    public long getItemId(int position) {
        return position;
    }

    // Cria e retorna a visualização de cada item na lista
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        // Verifica se a visualização está sendo reutilizada ou se é nova
        if (convertView == null) {
            // Se a visualização é nova, infla o layout do item
            convertView = LayoutInflater.from(context).inflate(R.layout.item_historico, parent, false);
            viewHolder = new ViewHolder();
            // Inicializa os TextViews do layout do item
            viewHolder.txtId = convertView.findViewById(R.id.txtId);
            viewHolder.txtValorA = convertView.findViewById(R.id.txtValorA);
            viewHolder.txtValorB = convertView.findViewById(R.id.txtValorB);
            viewHolder.txtOperacao = convertView.findViewById(R.id.txtOperacao);
            viewHolder.txtResultado = convertView.findViewById(R.id.txtResultado);
            viewHolder.txtDataHora = convertView.findViewById(R.id.txtDataTime);
            // Define a tag do ViewHolder na visualização para reutilização
            convertView.setTag(viewHolder);
        } else {
            // Se a visualização está sendo reutilizada, obtém o ViewHolder da tag
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Obtém a operação na posição atual
        Historico operacoes = operacoesList.get(position);
        // Define os valores dos TextViews com os dados da operação
        viewHolder.txtId.setText(String.valueOf(operacoes.getId()));
        viewHolder.txtValorA.setText(String.valueOf(operacoes.getValorA()));
        viewHolder.txtValorB.setText(String.valueOf(operacoes.getValorB()));
        viewHolder.txtOperacao.setText(operacoes.getOperacao());
        viewHolder.txtResultado.setText(String.valueOf(operacoes.getResultado()));
        viewHolder.txtDataHora.setText(operacoes.getDataTime());

        return convertView;
    }

    // Classe interna para armazenar os TextViews do layout do item
    private static class ViewHolder {
        TextView txtId;
        TextView txtValorA;
        TextView txtValorB;
        TextView txtOperacao;
        TextView txtResultado;
        TextView txtDataHora;
    }
}
