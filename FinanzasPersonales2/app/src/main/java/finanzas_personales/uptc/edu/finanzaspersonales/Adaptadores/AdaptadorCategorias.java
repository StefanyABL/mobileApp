package finanzas_personales.uptc.edu.finanzaspersonales.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;


import finanzas_personales.uptc.edu.finanzaspersonales.objetos.Gastos;
import finanzas_personales.uptc.edu.finanzaspersonales.R;

/**
 * Created by Asus on 13/9/2017.
 */

public class AdaptadorCategorias extends BaseAdapter {
    // Declare Variables
    private Context context;
    private List<String> listaCategorias;

    LayoutInflater inflater;
    public AdaptadorCategorias(Context context, List<String> listaCategorias){
        this.context = context;
        this.listaCategorias=listaCategorias;

    }

    @Override
    public int getCount() {
        return  listaCategorias.size();
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

            TextView txCategoria;


            //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //creamos el view inflater para aceder a los atributos de la actividad list_row
            View itemView = inflater.inflate(R.layout.list_modelo_cats, viewGroup, false);

            //referenciamos los textView y los imageView
            txCategoria = (TextView) itemView.findViewById(R.id.txCategoria);


            txCategoria.setText(listaCategorias.get(i));
            return itemView;



    }
}
