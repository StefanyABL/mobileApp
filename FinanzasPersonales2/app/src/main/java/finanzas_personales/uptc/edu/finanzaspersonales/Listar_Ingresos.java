package finanzas_personales.uptc.edu.finanzaspersonales;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import finanzas_personales.uptc.edu.finanzaspersonales.Adaptadores.AdaptadorGastos;
import finanzas_personales.uptc.edu.finanzaspersonales.Adaptadores.AdaptadorIngresos;
import finanzas_personales.uptc.edu.finanzaspersonales.objetos.Gastos;
import finanzas_personales.uptc.edu.finanzaspersonales.objetos.Ingresos;

public class Listar_Ingresos extends AppCompatActivity {
    ListView listaIngresos;
    Preferencias preferencias;
    List<Ingresos> listaIngresos2 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar__ingresos);
try{
        preferencias = new Preferencias(Listar_Ingresos.this);


        listaIngresos = (ListView) findViewById(R.id.list_Ingresos);

        String jsonGastos = preferencias.leerJsonIngresos();
        Gson gson = new Gson();
        Type type = new TypeToken<List<Ingresos>>() {
        }.getType();
        listaIngresos2 = gson.fromJson(jsonGastos, type);

        if(listaIngresos2.size()>0)
        listaIngresos.setAdapter(new AdaptadorIngresos(Listar_Ingresos.this,listaIngresos2));


    }catch (Exception e){
        //Dialogo de confirmacion
        AlertDialog alertDialog = new AlertDialog.Builder(Listar_Ingresos.this).create();
        alertDialog.setTitle("No hay ingresos registrados");
        alertDialog.setIcon(R.mipmap.ic_launcher);

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ACEPTAR", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });


        alertDialog.show();

    }
}}
