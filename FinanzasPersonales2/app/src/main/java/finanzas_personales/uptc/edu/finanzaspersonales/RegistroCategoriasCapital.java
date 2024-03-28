package finanzas_personales.uptc.edu.finanzaspersonales;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompatSideChannelService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/*Clase para registrar capital y categorias*/
public class RegistroCategoriasCapital extends AppCompatActivity {
    Preferencias preferencias;
    EditText txCapital, txNuevaCategoria;
    List<String> listaCategorias = new ArrayList<>();
    CheckBox chkAlimentacion,chkVivienda,chkTransporte,chkEstudio,chkOcio;
    ConstraintLayout mainConstrain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana__principal);
        preferencias= new Preferencias(RegistroCategoriasCapital.this);

        txCapital = (EditText)findViewById(R.id.txCapital);
        txNuevaCategoria = (EditText)findViewById(R.id.txNuevaCategoria);
        mainConstrain = findViewById(R.id.mainConstrain);

        listaCategorias.add("Seleccione una categoria...");

        //------------------------chekAlimnetacion---------------------------------------------------
        chkAlimentacion=(CheckBox)findViewById(R.id.chkAlimentacion);
        chkAlimentacion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

                if(isChecked)
                {
                    listaCategorias.add("Alimentación");
                }
                else
                {
                    listaCategorias.remove("Alimentación");
                }
            }
            }
        );
        //----------------------------chekVivienda-------------------------------------------------------
        chkVivienda=(CheckBox)findViewById(R.id.chkVivienda);
        chkVivienda.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    listaCategorias.add("Vivienda");
                } else {
                    listaCategorias.remove("Vivienda");
                }
            }
        });
        //--------------------------chekTransporte-------------------------------------------------------
        chkTransporte=(CheckBox)findViewById(R.id.chkTransporte);
        chkTransporte.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    listaCategorias.add("Transporte");
                } else {
                    listaCategorias.remove("Transporte");
                }
            }

        }) ;
        //------------------------chekEstudio-------------------------------------------------------
        chkEstudio=(CheckBox)findViewById(R.id.chkEstudio);
        chkEstudio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    listaCategorias.add("Estudio");
                } else {
                    listaCategorias.remove("Estudio");
                }
            }
        });
        //-----------------------chekOcio-----------------------------------------------------------
        chkOcio=(CheckBox)findViewById(R.id.chkOcio);
        chkOcio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                listaCategorias.add("Ocio");
                } else {
                    listaCategorias.remove("Ocio");
                }
            }

        });
    }
    //Guarda el capital ingresado a la preferencia y verifica que la lista de categorias contenga almenos 1
    public void guardar(View v){
        String capital=txCapital.getText().toString();

        if(!TextUtils.isEmpty(txCapital.getText()))
        {
            preferencias.ingresarCapital(capital);

            if(listaCategorias.size()<=0)
            {
                Snackbar.make(mainConstrain, "Seleccione al menos una categoría", Snackbar.LENGTH_LONG)
                        .show();
            }
            else
            {
                //inicio main activuty
                registrarCategorias();
                preferencias.seRegistroCategorias(true);//Avisamos que ya se registran categorias
                startActivity(new Intent(RegistroCategoriasCapital.this,PrincipalIngresosGastos.class));
                finish();
            }

        }
        else if(TextUtils.isEmpty(txCapital.getText())){
            txCapital.setError("Ingrese un valor");
        }





    }

    //Convertimos la lista de categorias a jsonString y enviamos a preferencias
    public void registrarCategorias(){
        try {
            String jsonCategorias = new Gson().toJson(listaCategorias);
            preferencias.escribirJsonCategorias(jsonCategorias);
            Toast.makeText(this, "Datos Guardados", Toast.LENGTH_SHORT).show();
        }catch (Exception e)
        {
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    /*Metodo llamado desde el boton "más"*/
    public void nuevaCategoria(View v)
    {
        if(!TextUtils.isEmpty(txNuevaCategoria.getText()))
        {
            listaCategorias.add(txNuevaCategoria.getText().toString());

            Toast.makeText(this, "Se agrego "+txNuevaCategoria.getText().toString()+" a Categorías", Toast.LENGTH_SHORT).show();
            txNuevaCategoria.setText("");
        }
        else
        {
            txNuevaCategoria.setError("Ingrese una categoria");
        }


    }
}
