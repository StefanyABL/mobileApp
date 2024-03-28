package finanzas_personales.uptc.edu.finanzaspersonales;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import finanzas_personales.uptc.edu.finanzaspersonales.objetos.Gastos;
import finanzas_personales.uptc.edu.finanzaspersonales.objetos.Ingresos;

/*Clase para gestionar gastos e ingresos*/
public class PrincipalIngresosGastos extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Preferencias preferencias;
    TextView txCapital;
    List<Ingresos> listaIngresos = new ArrayList<>();
    List<Gastos> listaGastos = new ArrayList<>();
    List<String> listaCategorias = new ArrayList<>();
    EditText txIngreso, notaIngreso, txNuevoGasto, txNotaNuevoGasto;
    Spinner spinnerCategorias =  null;
    String categoriaSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        //Referencias
        txCapital = findViewById(R.id.txcapitalAhorro);
        txIngreso = findViewById(R.id.txIngresoCapital);
        notaIngreso = findViewById(R.id.notaIngresoCtal);


        txNuevoGasto = findViewById(R.id.txRegistrarNuevoGasto);
        txNotaNuevoGasto = findViewById(R.id.notaNuevoGasto);


        preferencias = new Preferencias(PrincipalIngresosGastos.this);


        spinnerCategorias = (Spinner) findViewById(R.id.spinnerCategorias);

        /*spinnerCategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });*/



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivity(new Intent(PrincipalIngresosGastos.this,Listar_Gastos.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Leemos capital
         cargarCapital();

    }

    private void cargarCapital()
    {
        txCapital.setText("$"+preferencias.leerCapital().toString());
    }

    public void nuevoIngreso(View v)
    {
        if(!TextUtils.isEmpty(txIngreso.getText().toString())) {
            //------------------Leemos ingresos si hay------------------------
            if (!TextUtils.isEmpty(preferencias.leerJsonIngresos())) {
                try {
                    String jsonIngresos = preferencias.leerJsonIngresos();
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Ingresos>>() {
                    }.getType();
                    listaIngresos = gson.fromJson(jsonIngresos, type);
                } catch (Exception e) {
                    Toast.makeText(this, " ", Toast.LENGTH_SHORT).show();

                    //users= new List<Usuario>();
                }
            }
            //-----------------Si no hay agregamos nuevos-----------------------
            String ingreso = txIngreso.getText().toString();
            String notaIngres = notaIngreso.getText().toString();

            listaIngresos.add(new Ingresos(ingreso, notaIngres));

            String jsonIngresos = new Gson().toJson(listaIngresos);
            preferencias.escribirJsonIngresos(jsonIngresos);
            Toast.makeText(this, "Ingreso Registrado", Toast.LENGTH_SHORT).show();
            actualizaCapital(false);
        }
        else
        {
            txIngreso.setError("Ingrese un valor");
        }

    }

    //
    private void actualizaCapital(boolean restar)
    {
        try {
            double capital = preferencias.leerCapital();


            //aqui entra si es gasto
            if(!restar)
            {
                double capitalASumar = Double.parseDouble(txIngreso.getText().toString());
                capital += capitalASumar;
            }
            //aqui si es ingreso
            else
            {
                double capitalARestar = Double.parseDouble(txNuevoGasto.getText().toString());
                capital -= capitalARestar;
            }

            //sobreescribimos capital
            preferencias.ingresarCapital(String.valueOf(capital));
            // volvemos a  leer
            capital = preferencias.leerCapital();
            //actualizamos en el texto
            txCapital.setText("$"+String.valueOf(capital));

        }catch (Exception e)
        {
            Toast.makeText(this, "Problema al registrar nuevo capital", Toast.LENGTH_SHORT).show();
        }

    }



    public void nuevoGasto(View v)
    {
        if(!TextUtils.isEmpty(txNuevoGasto.getText().toString())) {
            //------------------Leemos ingresos si hay------------------------
            if (!TextUtils.isEmpty(preferencias.leerJsonGastos())) {
                try {
                    String jsonGastos = preferencias.leerJsonGastos();
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Gastos>>() {
                    }.getType();
                    listaGastos = gson.fromJson(jsonGastos, type);
                } catch (Exception e) {
                    Toast.makeText(this, " ", Toast.LENGTH_SHORT).show();

                    //users= new List<Usuario>();
                }
            }
            //-----------------Si no hay agregamos nuevos-----------------------
            String gasto = txNuevoGasto.getText().toString();
            String notaGasto = txNotaNuevoGasto.getText().toString();
            categoriaSeleccionada = spinnerCategorias.getSelectedItem().toString();
            listaGastos.add(new Gastos(gasto, notaGasto,categoriaSeleccionada));

            String jsonGastos = new Gson().toJson(listaGastos);
            preferencias.escribirJsonGastos(jsonGastos);
            Toast.makeText(this, "Gasto Registrado", Toast.LENGTH_SHORT).show();
            actualizaCapital(true);
        }
        else
        {
            txNuevoGasto.setError("Ingrese un valor");
        }

    }


    public void leerCategorias()
    {
        try {
            String jsonCategorias = preferencias.leerJsonCategorias();
            Gson gson = new Gson();
            Type type = new TypeToken<List<String>>() {
            }.getType();
            listaCategorias = gson.fromJson(jsonCategorias, type);

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, listaCategorias);

            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);




// attaching data adapter to spinner
            spinnerCategorias.setAdapter(dataAdapter);


        } catch (Exception e) {
            Toast.makeText(this, " ", Toast.LENGTH_SHORT).show();

            //users= new List<Usuario>();
        }





    }









    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            finish();
           System.exit(0);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.id_verGastos) {
            // Handle the camera action
            startActivity(new Intent(PrincipalIngresosGastos.this,Listar_Gastos.class));
        } else if (id == R.id.id_verIngresos) {
            startActivity(new Intent(PrincipalIngresosGastos.this,Listar_Ingresos.class));

        } else if (id == R.id.id_administrarCtegories) {
            startActivity(new Intent(PrincipalIngresosGastos.this,AdministrarCategorias.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onResume() {
        //Leemos categorias
        leerCategorias();
        super.onResume();
    }
}
