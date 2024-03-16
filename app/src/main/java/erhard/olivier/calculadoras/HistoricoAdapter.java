package erhard.olivier.calculadoras;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class HistoricoAdapter extends BaseAdapter {
    private final Context context;
    private final List<Historico> operacoesList;

    public HistoricoAdapter(Context context, List<Historico> operacoesList) {
        this.context = context;
        this.operacoesList = operacoesList;
    }

    @Override
    public int getCount() {
        return operacoesList.size();
    }

    @Override
    public Object getItem(int position) {
        return operacoesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_historico, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txtValorA = convertView.findViewById(R.id.txtValorA);
            viewHolder.txtValorB = convertView.findViewById(R.id.txtValorB);
            viewHolder.txtOperacao = convertView.findViewById(R.id.txtOperacao);
            viewHolder.txtResultado = convertView.findViewById(R.id.txtResultado);
            viewHolder.txtDataHora = convertView.findViewById(R.id.txtDataHora);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Historico operacoes = operacoesList.get(position);
        viewHolder.txtValorA.setText(String.valueOf(operacoes.getValorA()));
        viewHolder.txtValorB.setText(String.valueOf(operacoes.getValorB()));
        viewHolder.txtOperacao.setText(operacoes.getOperacao());
        viewHolder.txtResultado.setText(String.valueOf(operacoes.getResultado()));
        viewHolder.txtDataHora.setText(operacoes.getDataHora());

        return convertView;
    }

    private static class ViewHolder {
        TextView txtValorA;
        TextView txtValorB;
        TextView txtOperacao;
        TextView txtResultado;
        TextView txtDataHora;
    }
}
