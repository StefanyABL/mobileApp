package finanzas_personales.uptc.edu.finanzaspersonales;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import finanzas_personales.uptc.edu.finanzaspersonales.Adaptadores.AdaptadorGastos;
import finanzas_personales.uptc.edu.finanzaspersonales.objetos.Gastos;
import finanzas_personales.uptc.edu.finanzaspersonales.objetos.Ingresos;

public class Listar_Gastos extends AppCompatActivity {

    ListView listaGastos;
    Preferencias preferencias;
    List<Gastos> listaGastos2 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar__gastos);

        try {
            preferencias = new Preferencias(Listar_Gastos.this);


            listaGastos = (ListView) findViewById(R.id.list_Gastos);

            //leemos jsonstring de gastos
            String jsonGastos = preferencias.leerJsonGastos();
            Gson gson = new Gson();
            Type type = new TypeToken<List<Gastos>>() {
            }.getType();
            listaGastos2 = gson.fromJson(jsonGastos, type);

            if(listaGastos2.size()>0)
            listaGastos.setAdapter(new AdaptadorGastos(Listar_Gastos.this, listaGastos2));

        }catch (Exception e){
            //Dialogo de confirmacion
            AlertDialog alertDialog = new AlertDialog.Builder(Listar_Gastos.this).create();
            alertDialog.setTitle("No hay gastos registrados");
            alertDialog.setIcon(R.mipmap.ic_launcher);

            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ACEPTAR", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                   finish();
                }
            });


            alertDialog.show();

        }

    }
}
