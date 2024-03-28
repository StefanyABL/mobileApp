package finanzas_personales.uptc.edu.finanzaspersonales;

import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import finanzas_personales.uptc.edu.finanzaspersonales.Adaptadores.AdaptadorCategorias;

public class AdministrarCategorias extends AppCompatActivity {
    ListView listaCategorias;
    Preferencias preferencias;
    List<String> listaCategorias2 = new ArrayList<>();
    EditText  txNuevaCategoria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_categorias);


        txNuevaCategoria = (EditText)findViewById(R.id.txNuevaCategoria);
        preferencias = new Preferencias(AdministrarCategorias.this);


        listaCategorias = (ListView) findViewById(R.id.list_categorias);

        listaCategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                //Dialogo de confirmacion
                AlertDialog alertDialog = new AlertDialog.Builder(AdministrarCategorias.this).create();
                alertDialog.setTitle("Se eliminara esta categoría");
                alertDialog.setMessage("¿Esta seguro?");
                alertDialog.setIcon(R.mipmap.ic_launcher);

                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ELIMINAR", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        listaCategorias2.remove(listaCategorias2.get(position));
                        registrarCategorias();
                        Toast.makeText(getApplicationContext(), "Se elimino la categoría", Toast.LENGTH_SHORT).show();
                    }
                });

                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCELAR", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                       dialog.dismiss();
                    }
                });
                alertDialog.show();




            }
        });


      cargarSpinner();
        
    }


    public  void cargarSpinner()
    {
        String jsonCategorias = preferencias.leerJsonCategorias();
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        listaCategorias2 = gson.fromJson(jsonCategorias, type);
        //listaCategorias2.remove(0);

        listaCategorias.setAdapter(new AdaptadorCategorias(AdministrarCategorias.this,listaCategorias2));
    }

    /*Metodo llamado desde el boton "más"*/
    public void nuevaCategoria(View v)
    {
        if(!TextUtils.isEmpty(txNuevaCategoria.getText()))
        {
            listaCategorias2.add(txNuevaCategoria.getText().toString());
            Toast.makeText(AdministrarCategorias.this, "Se agrego "+txNuevaCategoria.getText().toString()+" a Categorías", Snackbar.LENGTH_LONG)
                    .show();
            registrarCategorias();
            txNuevaCategoria.setText("");
        }
        else
        {
            txNuevaCategoria.setError("Ingrese una categoria");
        }



    }

    public void registrarCategorias(){
        try {
            String jsonCategorias = new Gson().toJson(listaCategorias2);
            preferencias.escribirJsonCategorias(jsonCategorias);
            Toast.makeText(this, "Datos Guardados", Toast.LENGTH_SHORT).show();
        }catch (Exception e)
        {
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
cargarSpinner();
    }
}
