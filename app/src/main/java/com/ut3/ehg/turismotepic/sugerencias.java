package com.ut3.ehg.turismotepic;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Path;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import static com.ut3.ehg.turismotepic.R.string.opinion;
import static com.ut3.ehg.turismotepic.R.string.q1;

public class sugerencias extends conexion {

    ImageButton btnEnviar;
    private RequestQueue rqt;
    private Context ctx;
    private String url = "https://arcadia.cicese.mx/WebServiceT2/proceso.php ";//"http://158.97.121.65/WebServiceT2/proceso.php";
    private StringRequest strq;
    private EditText opinion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugerencias);

        ctx = sugerencias.this;
        rqt = Volley.newRequestQueue(ctx);

        opinion = (EditText) findViewById(R.id.opinionText);
        btnEnviar = (ImageButton) findViewById(R.id.btnEnviar);
        final String Opinion = opinion.getText().toString();
        btnEnviar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                String Opinion = opinion.getText().toString();
                sugerencias(Opinion);

            }
        });
    }

    private void sugerencias(final String Opinion) {

        SharedPreferences cat=this.getApplicationContext().getSharedPreferences("user",MODE_PRIVATE);
        final String user = cat.getString("user", "");

        System.out.println("El usuario que realizara su opinion es "+user);

        strq = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("rta_servidor", response);
                        Toast.makeText(ctx, response, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error_servidor", error.toString());
                Toast.makeText(ctx, "Error en el servidor", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams()  {
                Map<String, String> parametros = new HashMap<>();

                parametros.put("usuario", user);
                parametros.put("opinion", Opinion);
                parametros.put("operacion", "sugerencias");

                return parametros;
            }
        };

        rqt.add(strq);

    }
}
