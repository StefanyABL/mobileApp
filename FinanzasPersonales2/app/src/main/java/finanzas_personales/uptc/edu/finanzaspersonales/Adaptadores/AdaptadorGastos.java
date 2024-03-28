package finanzas_personales.uptc.edu.finanzaspersonales.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;


import finanzas_personales.uptc.edu.finanzaspersonales.objetos.Gastos;
import finanzas_personales.uptc.edu.finanzaspersonales.R;

/**
 * Created by Asus on 13/9/2017.
 */

public class AdaptadorGastos extends BaseAdapter {
    // Declare Variables
    private Context context;
    private List<Gastos> listaGastos;

    LayoutInflater inflater;
    public AdaptadorGastos(Context context, List<Gastos> listaGastos){
        this.context = context;
        this.listaGastos=listaGastos;

    }

    @Override
    public int getCount() {
        return  listaGastos.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        // Declare Variables

        TextView txValor,txCategoria,txNota;
     

        //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //creamos el view inflater para aceder a los atributos de la actividad list_row
        View itemView = inflater.inflate(R.layout.list_modelo_gastos, viewGroup, false);

        //referenciamos los textView y los imageView
        txValor = (TextView) itemView.findViewById(R.id.txValor);
        txCategoria = (TextView) itemView.findViewById(R.id.txCategoria);
        txNota = (TextView) itemView.findViewById(R.id.txNota);


        txValor.setText(listaGastos.get(i).getValor());
        txCategoria.setText(listaGastos.get(i).getCategoria());
        txNota.setText(listaGastos.get(i).getNota());


        return itemView;
    }
}
