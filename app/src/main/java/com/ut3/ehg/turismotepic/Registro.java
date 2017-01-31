package com.ut3.ehg.turismotepic;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ut3.ehg.turismotepic.db.db_acompanying;
import com.ut3.ehg.turismotepic.db.db_motivo;
import com.ut3.ehg.turismotepic.db.db_origen;
import com.ut3.ehg.turismotepic.db.db_usuarios;
import com.ut3.ehg.turismotepic.rc.rc_usuarios;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Registro extends Activity {
    private String datoSexo;
    private EditText edad,usuario, pass;
    private TextView motivo, acompañantes, origen,sexo;
    private  RadioButton rbHombre, rbMujer;
    private RadioGroup rdgGrupo;
    private rc_usuarios rcUsuarios;
    private RequestQueue rqt;
    private Context ctx;
    private String url = "http://158.97.121.65/WebServiceT2/proceso.php";

    private StringRequest strq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        db_motivo dataMotivo = new db_motivo(this);
        db_origen dataOrigen = new db_origen(this);
        final db_acompanying dataAcompañante = new db_acompanying(this);
        final db_usuarios dataUsuarios = new db_usuarios(this);
        ctx = Registro.this;

        rqt = Volley.newRequestQueue(ctx);


        Typeface roboto = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
        //Typeface beba = Typeface.createFromAsset(getAssets(), "fonts/BebasKai-Regular.otf");
        Typeface maven = Typeface.createFromAsset(getAssets(), "fonts/MavenPro-Regular.ttf");


        sexo=(TextView) findViewById(R.id.tvTitleSexo);

        usuario = (EditText) findViewById(R.id.etUsuarioLog);
        pass = (EditText) findViewById(R.id.etPassLog);
        edad = (EditText) findViewById(R.id.etEdad);

        usuario.setTypeface(maven);
        pass.setTypeface(maven);
        edad.setTypeface(maven);



        motivo = (TextView) findViewById(R.id.tvTexto_spMotivo);
        acompañantes = (TextView) findViewById(R.id.tvTexto_spAcompa);
        origen = (TextView) findViewById(R.id.tvTexto_spOrigen);





        /*motivo.setTypeface(maven);
        acompañantes.setTypeface(maven);
        origen.setTypeface(maven);*/


        rbHombre=(RadioButton)findViewById(R.id.rbHombre);
        rbMujer=(RadioButton)findViewById(R.id.rbMujer);
        rdgGrupo = (RadioGroup)findViewById(R.id.RGB);
        ArrayList<String> listaOrigenes= dataOrigen.obtenerOrigenes();
        ArrayList<String> listaMotivos= dataMotivo.obtenerMotivo();
        ArrayList<String> listaAcompa= dataAcompañante.obtenerAcompañantes();
        final Spinner spinnerMotivo = (Spinner) findViewById(R.id.spMotivo);
        final Spinner spinnerCompañeros = (Spinner) findViewById(R.id.spCompaneros);
        final Spinner spinnerOrigen = (Spinner) findViewById(R.id.spOrigen);





        ImageButton btnGuardar = (ImageButton) findViewById(R.id.btnGuardarReg);
        //Button btnCancelar = (Button) findViewById(R.id.btnCancelarReg);
        ImageButton btnEdad = (ImageButton) findViewById(R.id.ibtnEdad);

        ArrayAdapter<String> adaptadorMotivo = new ArrayAdapter<String>(this, R.layout.sp_motivo,R.id.tvTexto_spMotivo,listaMotivos);
        spinnerMotivo.setAdapter(adaptadorMotivo);

        //Spinner spinner = (Spinner) findViewById(R.id.pioedittxt5);

       /* ArrayAdapter<String> adaptadorMotivo = new ArrayAdapter<String>(this, R.layout.sp_motivo,R.id.tvTexto_spMotivo,listaMotivos);
        adaptadorMotivo.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinnerMotivo.setAdapter(adaptadorMotivo);*/



        ArrayAdapter<String> adaptadorAcomp = new ArrayAdapter<String>(this, R.layout.sp_acompa,R.id.tvTexto_spAcompa,listaAcompa);
        spinnerCompañeros.setAdapter(adaptadorAcomp);

        ArrayAdapter<String> adaptadorOrigenes = new ArrayAdapter<String>(this, R.layout.sp_origen,R.id.tvTexto_spOrigen,listaOrigenes);
        spinnerOrigen.setAdapter(adaptadorOrigenes);

        rdgGrupo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if (checkedId == R.id.rbHombre) {
                    datoSexo="Hombre";//Toast.makeText(getApplicationContext(), "choice: Hombre", Toast.LENGTH_SHORT).show();
                    rbMujer.setChecked(false);
                } else if (checkedId == R.id.rbMujer) {
                    datoSexo="Mujer";//Toast.makeText(getApplicationContext(), "choice: Mujer", Toast.LENGTH_SHORT).show();
                    rbHombre.setChecked(false);
                }
            }
        });


        btnGuardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){


               System.out.println("Estoy en el boton");

                if(edad.getText().length() !=0 ||usuario.getText().length() !=0 ||pass.getText().length() !=0) {
                    final String Sexo = datoSexo;
                    final String Usuario = usuario.getText().toString();
                    final String Pass = pass.getText().toString();
                    final String Edad = edad.getText().toString();
                    final String Motivo = spinnerMotivo.getSelectedItem().toString();
                    final String Acompañantes = spinnerCompañeros.getSelectedItem().toString();
                    int EdadP = Integer.parseInt(Edad);
                    String Perfil = "";
                    System.out.println("la edad es de "+EdadP);

                    if (EdadP >= 65) {
                       Perfil = "10";
                    }
                    else if (Motivo.equals("Negocio") && Acompañantes.equals("Pareja")){
                        Perfil = "2";
                    }
                    else if (Motivo.equals("Negocio") && Acompañantes.equals("Familia")){
                        Perfil = "3";
                    }
                    else if (Motivo.equals("Negocio") && Acompañantes.equals("Compañero de trabajo")){
                        Perfil = "4";
                    }
                    else if (Motivo.equals("Placer") && Acompañantes.equals("Solo")){
                        Perfil = "5";
                    }
                    else if (Motivo.equals("Placer") && Acompañantes.equals("Pareja")){
                        Perfil = "6";
                    }
                    else if (Motivo.equals("Placer") && Acompañantes.equals("Familia")){
                        Perfil = "7";
                    }
                    else if (Motivo.equals("Placer") && Acompañantes.equals("Compañero de trabajo")){
                        Perfil = "8";
                    }
                    else if (Motivo.equals("Compras") && Acompañantes.equals("Solo") || Motivo.equals("Compras") && Acompañantes.equals("Pareja") || Motivo.equals("Compras") && Acompañantes.equals("Familia") || Motivo.equals("Compras") && Acompañantes.equals("Compañero de trabajo")){
                        Perfil = "9";
                    }
                    else if (Motivo.equals("Negocio") && Acompañantes.equals("Solo")){

                        Perfil = "1";
                    }

                    seleccionar(Sexo,Usuario,Pass,Edad,Motivo,Acompañantes,Perfil);

                    //System.out.println("El motivo es " +Motivo);
                    //System.out.println("El acompanante es " + Acompañantes);

                    //System.out.println("El perfil final es " +Perfil);

                    final String Origen = spinnerOrigen.getSelectedItem().toString();
                    rcUsuarios = new rc_usuarios(getApplicationContext());
                    rcUsuarios.open();
                    rcUsuarios.insertarUsuarios(Usuario,Pass,Sexo,Motivo,Acompañantes,Origen,Edad,Perfil);
                    rcUsuarios.close();
                    System.out.println("El origen es "+ Origen);
                    Toast.makeText(getApplicationContext(), "Registro Realizado", Toast.LENGTH_SHORT).show();
                    /*Intent intent = new Intent(v.getContext(), Login.class);
                    startActivity(intent);
                    finish();*/
                } else{
                        Toast.makeText(getApplicationContext(),"Uno o más campos estan vacios", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*btnCancelar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });*/

        btnEdad.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                numberPickerDialog();
            }
        });
    }

    private void seleccionar(final String Sexo, final String Usuario, final String Pass, final String Edad, final String Motivo,final String  Acompañante, final String Perfil ) {

        strq = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("rta_servidor", response);
                        Toast.makeText(ctx, response, Toast.LENGTH_SHORT).show();

                        if(response.equals("El usuario ya existe")){
                            usuario.setText("");
                        }
                        else{
                            Intent intent = new Intent(Registro.this, Login.class);
                            startActivity(intent);
                            finish();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error_servidor", error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams()  {
                Map<String, String> parametros = new HashMap<>();

                parametros.put("usuario", Usuario);
                parametros.put("sexo", Sexo);
                parametros.put("pass", Pass);
                parametros.put("edad", Edad);
                parametros.put("motivo", Motivo);
                parametros.put("acompañante", Acompañante);
                parametros.put("perfil", Perfil);
                parametros.put("operacion", "r");

                return parametros;
            }
        };

        rqt.add(strq);

    }

    ////////////////////Funciola antes de agregar esta parte
    public void numberPickerDialog(){
        NumberPicker npEdad = new NumberPicker(this);
        npEdad.setMaxValue(80);
        npEdad.setMinValue(12);
        NumberPicker.OnValueChangeListener miValor = new NumberPicker.OnValueChangeListener(){
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
               edad.setText(""+newVal);
            }
        };
        npEdad.setOnValueChangedListener(miValor);
        AlertDialog.Builder Dialogo = new AlertDialog.Builder(this).setView(npEdad);
        Dialogo.setTitle("Edad").setIcon(R.drawable.ic_menu_manage);

        Dialogo.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        Dialogo.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        Dialogo.show();
    }


}
