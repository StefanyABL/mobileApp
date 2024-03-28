package finanzas_personales.uptc.edu.finanzaspersonales;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import finanzas_personales.uptc.edu.finanzaspersonales.objetos.Usuario;

public class Lector_Preferencias extends AppCompatActivity {
Preferencias preferencias;
List<Usuario> users= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lector__preferencias);
        preferencias= new Preferencias(Lector_Preferencias.this);
        leerUsuario();
    }
    public void leerUsuario (){
        try {
            String jsonUsuarios = preferencias.jsonUsuarios();
            Gson gson = new Gson();
            Type type = new TypeToken<List<Usuario>>(){}.getType();
            users = gson.fromJson(jsonUsuarios, type);

            TextView tx_Usuarios= (TextView)findViewById(R.id.tx_Usuarios);
            for(Usuario usuario:users){
                tx_Usuarios.setText(tx_Usuarios.getText()+usuario.getNombre()+" "+usuario.getCorreo()+" "+usuario.getPassword()+" "+usuario.getEdad()+"\n\n");

            }

        }catch (Exception E){
            Toast.makeText(this, "No hay preferencias", Toast.LENGTH_SHORT).show();
        }
    }
}
