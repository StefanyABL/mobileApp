package finanzas_personales.uptc.edu.finanzaspersonales;

import android.content.Intent;
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

/*Clase de validar los datos del login*/
public class Login extends AppCompatActivity {
    List<Usuario> users= new ArrayList<>();
    Preferencias preferencias;
    EditText tx_correo,tx_contrasena;
    String correo,contrasena;
    TextView tv_registrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         tv_registrar=(TextView) findViewById(R.id.tv_registrar);
         tx_correo=(EditText)findViewById(R.id.tv_Usuario);
         tx_contrasena=(EditText)findViewById(R.id.tv_Contrasena);
        tv_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReg = new Intent(Login.this, Registro.class);
                Login.this.startActivity(intentReg);
            }
        });
        preferencias= new Preferencias(Login.this);
        leerUsuarios();

    }

    //leemos los datos y verificamos que no esten vacios los campos
    public void iniciarSesion(View v){

        correo= tx_correo.getText().toString();
        contrasena=tx_contrasena.getText().toString();
        if (TextUtils.isEmpty(tx_correo.getText())||TextUtils.isEmpty(tx_contrasena.getText())){
            Toast.makeText(this, "Debe ingresar todos los campos", Toast.LENGTH_SHORT).show();
        }else{
            validarDatos();
        }


    }
    //leemos usuarios para comparar usuario y cotraseña
    public void leerUsuarios (){
        try {
            String jsonUsuarios = preferencias.jsonUsuarios();
            Gson gson = new Gson();
            Type type = new TypeToken<List<Usuario>>() {
            }.getType();
            users = gson.fromJson(jsonUsuarios, type);
        }catch (Exception e)
        {}
    }
    //validamos que el correo y contraseña coinciden
    public void validarDatos(){
        int cantidadEncontrados=0;
    for (Usuario user: users){
        if(correo.equals(user.getCorreo())){
            cantidadEncontrados++;
            if(contrasena.equals(user.getPassword())){

                //Si no hay categorias primero lo envia a registrarlas
                if(preferencias.hayCategorias())
                {
                    Toast.makeText(this, "Usuario Correcto", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this,PrincipalIngresosGastos.class));
                    finish();
                }
                else
                {
                    Toast.makeText(this, "Usuario Correcto", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this,RegistroCategoriasCapital.class));
                    finish();
                }



            }else {
                Toast.makeText(this, "Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
            }
        }

    }
    if(cantidadEncontrados==0){
        Toast.makeText(this, "No existe usuario registrado con el correo digitado", Toast.LENGTH_SHORT).show();

    }

    }


    @Override
    protected void onResume() {
        super.onResume();
        leerUsuarios();
    }

    public void verPreferencias(View v){
        startActivity(new Intent(Login.this,Lector_Preferencias.class));
    }

}
