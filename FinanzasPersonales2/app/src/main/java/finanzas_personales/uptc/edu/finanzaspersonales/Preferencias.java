package finanzas_personales.uptc.edu.finanzaspersonales;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferencias {
    private Context context;
    private SharedPreferences escritorPreferencias;
    private SharedPreferences.Editor editorPreferencias;

    @SuppressLint("CommitPrefEdits")
    public Preferencias(Context context)
    {   this.context = context;
        escritorPreferencias = PreferenceManager.getDefaultSharedPreferences(context);
        editorPreferencias = escritorPreferencias.edit();

    }
    public void escribirJsonUsuarios(String jsonStringUsuarios)
    {
        editorPreferencias.putString("jsonUsuarios", jsonStringUsuarios);
        editorPreferencias.apply();
        eliminarPreferenciasAntiguas();
        // return true;
    }
    public String jsonUsuarios(){
        SharedPreferences preferencia = PreferenceManager.getDefaultSharedPreferences(context);
        return preferencia.getString("jsonUsuarios","{ \"nombre\":\"\", \"correo\":\"\", \"edad\":\"\", \"password\":\"\"}");
    }
    //
    public void ingresarCapital(String capital){
        editorPreferencias.putString("capital", capital);
        editorPreferencias.apply();
    }
    public Double leerCapital() {

        SharedPreferences preferencia = PreferenceManager.getDefaultSharedPreferences(context);
        String capitalg = preferencia.getString("capital", "0000");
    return Double.parseDouble(capitalg);
    }
    public void escribirJsonCategorias(String jsonStringCategorias)
    {
        editorPreferencias.putString("jsonCategorias", jsonStringCategorias);
        editorPreferencias.apply();
        // return true;
    }

    public String leerJsonCategorias() {

        SharedPreferences preferencia = PreferenceManager.getDefaultSharedPreferences(context);

        return  preferencia.getString("jsonCategorias", "");
    }
    //Preferencia para evitar que depues de registrar categorias vuelva a entrar a la ventana de registro de categorias
    public void seRegistroCategorias(boolean b)
    {
        editorPreferencias.putBoolean("seRegistroCategorias", b);
        editorPreferencias.apply();
    }
    public boolean hayCategorias() {

        SharedPreferences preferencia = PreferenceManager.getDefaultSharedPreferences(context);

        return  preferencia.getBoolean("seRegistroCategorias", false);
    }

    public void escribirJsonIngresos(String jsonStringIngresos)
    {
        editorPreferencias.putString("jsonIngresos", jsonStringIngresos);
        editorPreferencias.apply();
        // return true;
    }

    public String leerJsonIngresos() {

        SharedPreferences preferencia = PreferenceManager.getDefaultSharedPreferences(context);

        return  preferencia.getString("jsonIngresos", "");
    }

    public void escribirJsonGastos(String jsonStringGastos)
    {
        editorPreferencias.putString("jsonGastos", jsonStringGastos);
        editorPreferencias.apply();
        // return true;
    }

    public String leerJsonGastos() {

        SharedPreferences preferencia = PreferenceManager.getDefaultSharedPreferences(context);

        return  preferencia.getString("jsonGastos", "");
    }

    //Limpiar preferencias antiguas
    public void eliminarPreferenciasAntiguas()
    {
        editorPreferencias.putString("jsonGastos", "");
        editorPreferencias.putString("jsonIngresos", "");
        editorPreferencias.putString("jsonCategorias", "");


        editorPreferencias.apply();


    }

}
