package finanzas_personales.uptc.edu.finanzaspersonales;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import finanzas_personales.uptc.edu.finanzaspersonales.objetos.Usuario;
//Registrar al nuevo usuario
public class Registro extends AppCompatActivity {

    Preferencias preferncia;
    EditText tx_nombre,tx_correo,tx_password,tx_edad;
    String nombre,correo,password,edad;
    List<Usuario> users=new ArrayList<>();

    /*Clase para registro de usuarios*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//referencias
        setContentView(R.layout.activity_registro);
        tx_nombre=(EditText) findViewById(R.id.tv_nombre);
        tx_correo=(EditText) findViewById(R.id.tv_correo);
        tx_password=(EditText)findViewById(R.id.tv_password);
        tx_edad=(EditText)findViewById(R.id.tv_edad);
        preferncia =new Preferencias(Registro.this);
      //  leerUsuarios();
    }

    //Leer usuario que esta actualmente
    public void registrar (View v){
        nombre = tx_nombre.getText().toString();
        correo=tx_correo.getText().toString();
        password=tx_password.getText().toString();
        edad=tx_edad.getText().toString();
        Usuario usuario=new Usuario(nombre,correo,password,edad);

        //verificamos que no hayan camposvacos
        if(TextUtils.isEmpty(tx_nombre.getText())||TextUtils.isEmpty(tx_correo.getText())||TextUtils.isEmpty(tx_password.getText())||TextUtils.isEmpty(tx_edad.getText())){
            Toast.makeText(this, "Debe completar todos los campos", Toast.LENGTH_SHORT).show();
        }else{
            escribirRegistro(usuario);

        }

    }
    //Escribe los nuevos usuarios
    public void escribirRegistro(Usuario usuario){
        //utilizamos Gson para convertir la lista de usuarios a un jsonString
        try {
            users.add(usuario);
            String jsonUsuarios = new Gson().toJson(users);
            preferncia.escribirJsonUsuarios(jsonUsuarios);
            Toast.makeText(this, "Usuario Registrado", Toast.LENGTH_SHORT).show();

            preferncia.seRegistroCategorias(false);
        }catch (Exception e)
        {
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //Utilizamos Gson para convertir los usuarios de la preferencia a lista de usuarios
    public void leerUsuarios(){
        try{
            String jsonUsuarios = preferncia.jsonUsuarios();
            Gson gson = new Gson();
            Type type = new TypeToken<List<Usuario>>(){}.getType();
            users = gson.fromJson(jsonUsuarios, type);
        }catch (Exception e){
            //Toast.makeText(this, " ", Toast.LENGTH_SHORT).show();

            //users= new List<Usuario>();
        }

    }


}
