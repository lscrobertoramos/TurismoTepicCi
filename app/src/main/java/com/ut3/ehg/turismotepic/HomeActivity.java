package com.ut3.ehg.turismotepic;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ut3.ehg.turismotepic.rc.rc_usuarios;

import static android.R.attr.defaultValue;
import static android.content.Context.MODE_PRIVATE;
import static java.lang.Integer.parseInt;

public class HomeActivity extends Fragment implements View.OnClickListener {

    private rc_usuarios rcUsuarios;
    private int idcat, idcat1, idcat2, idcat3;
    ViewGroup root;
    Context cntx;
    ImageButton bmenu;
    SharedPreferences poi;
    SharedPreferences cat;
    SharedPreferences.Editor editarCat;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final AppBarLayout appBarLayout;
        root = (ViewGroup) inflater.inflate(R.layout.activity_home, null);
        cntx = container.getContext();
        bmenu = (ImageButton)root.findViewById(R.id.bmenu);
        bmenu.setOnClickListener(this);


        SharedPreferences cat=this.getActivity().getSharedPreferences("user",MODE_PRIVATE);
        int idusuario = cat.getInt("idUser",0);
        String user = cat.getString("user", "");
        String pass = cat.getString("pass", "");

        System.out.println("El id almacenado es " +idusuario);
        System.out.println("El usuario almacenado es " +user);
        System.out.println("El pass almacenado es " +pass);

        rcUsuarios = new rc_usuarios(root.getContext());
        rcUsuarios.open();
        String idPerfil = rcUsuarios.idPerfil(user);
        rcUsuarios.close();

        System.out.println("El perfil del usuario es el " +idPerfil);

        ImageView imageone = (ImageView)root.findViewById(R.id.img1);
        ImageView imagetwo = (ImageView)root.findViewById(R.id.img2);
        ImageView imagethree = (ImageView)root.findViewById(R.id.img3);
        ImageView imagefour = (ImageView)root.findViewById(R.id.img4);
        TextView textone=(TextView)root.findViewById(R.id.text1);
        TextView texttwo=(TextView)root.findViewById(R.id.text2);
        TextView textthree=(TextView)root.findViewById(R.id.text3);
        TextView textfour=(TextView)root.findViewById(R.id.text4);



        if(idPerfil.equals("1")){
            imageone.setImageResource(R.drawable.restaurantes);
            textone.setText("Restaurantes");
             idcat = 2;
            imagetwo.setImageResource(R.drawable.tiendas);
            texttwo.setText("Tiendas");
             idcat1 = 7;
            imagethree.setImageResource(R.drawable.bancos1);
            textthree.setText("Bancos");
             idcat2 = 5;
            imagefour.setImageResource(R.drawable.mall1);
            textfour.setText("Plazas Comerciales");
             idcat3 = 8;
        }else if(idPerfil.equals("2")){
            imageone.setImageResource(R.drawable.restaurantes);
            textone.setText("Restaurantes");
            idcat = 2;
            imagetwo.setImageResource(R.drawable.mall1);
            texttwo.setText("Plazas comerciales");
            idcat1 = 8;
            imagethree.setImageResource(R.drawable.museos1);
            textthree.setText("Museos");
            idcat2 = 4;
            imagefour.setImageResource(R.drawable.parques1);
            textfour.setText("Parques");
            idcat3 = 9;
        }else if(idPerfil.equals("3")){
            imageone.setImageResource(R.drawable.parques1);
            textone.setText("Parques");
            idcat = 9;
            imagetwo.setImageResource(R.drawable.museos1);
            texttwo.setText("Museos");
            idcat1 = 4;
            imagethree.setImageResource(R.drawable.monumentos1);
            textthree.setText("Monumentos");
            idcat2 = 3;
            imagefour.setImageResource(R.drawable.mall1);
            textfour.setText("Plazas Comerciales");
            idcat3 = 8;
        }else if(idPerfil.equals("4")){
            imageone.setImageResource(R.drawable.restaurantes);
            textone.setText("Restaurantes");
            idcat = 2;
            imagetwo.setImageResource(R.drawable.museos1);
            texttwo.setText("Museos");
            idcat1 = 4;
            imagethree.setImageResource(R.drawable.bancos1);
            textthree.setText("Bancos");
            idcat2 = 5;
            imagefour.setImageResource(R.drawable.mall1);
            textfour.setText("Plazas Comerciales");
            idcat3 = 8;
        }else if(idPerfil.equals("5")){
            imageone.setImageResource(R.drawable.restaurantes);
            textone.setText("Restaurantes");
            idcat = 2;
            imagetwo.setImageResource(R.drawable.bancos1);
            texttwo.setText("Bancos");
            idcat1 = 5;
            imagethree.setImageResource(R.drawable.hoteles1);
            textthree.setText("Hoteles");
            idcat2 = 1;
            imagefour.setImageResource(R.drawable.monumentos1);
            textfour.setText("Monumentos");
            idcat3 = 3;
        }else if(idPerfil.equals("6")){
            imageone.setImageResource(R.drawable.museos1);
            textone.setText("Museos");
            idcat = 4;
            imagetwo.setImageResource(R.drawable.tiendas);
            texttwo.setText("Tiendas");
            idcat1 = 7;
            imagethree.setImageResource(R.drawable.bancos1);
            textthree.setText("Bancos");
            idcat2 = 5;
            imagefour.setImageResource(R.drawable.mall1);
            textfour.setText("Plazas Comerciales");
            idcat3 = 8;
        }else if(idPerfil.equals("7")){
            imageone.setImageResource(R.drawable.museos1);
            textone.setText("Museos");
            idcat = 4;
            imagetwo.setImageResource(R.drawable.monumentos);
            texttwo.setText("Monumentos");
            idcat1 = 3;
            imagethree.setImageResource(R.drawable.parques1);
            textthree.setText("Parques");
            idcat2 = 9;
            imagefour.setImageResource(R.drawable.mall1);
            textfour.setText("Plazas Comerciales");
            idcat3 = 8;
        }else if(idPerfil.equals("8")){
            imageone.setImageResource(R.drawable.museos1);
            textone.setText("Museos");
            idcat = 4;
            imagetwo.setImageResource(R.drawable.tiendas);
            texttwo.setText("Tiendas");
            idcat1 = 7;
            imagetwo.setImageResource(R.drawable.monumentos);
            texttwo.setText("Monumentos");
            idcat1 = 3;
            imagefour.setImageResource(R.drawable.mall1);
            textfour.setText("Plazas Comerciales");
            idcat3 = 8;
        }else if(idPerfil.equals("9")){
            imageone.setImageResource(R.drawable.restaurantes);
            textone.setText("Restaurantes");
            idcat = 2;
            imagetwo.setImageResource(R.drawable.tiendas);
            texttwo.setText("Tiendas");
            idcat1 = 7;
            imagethree.setImageResource(R.drawable.parques1);
            textthree.setText("Parques");
            idcat2 = 9;
            imagefour.setImageResource(R.drawable.mall1);
            textfour.setText("Plazas Comerciales");
            idcat3 = 8;
        }else if(idPerfil.equals("10")){
            imageone.setImageResource(R.drawable.restaurantes);
            textone.setText("Restaurantes");
            idcat = 2;
            imagetwo.setImageResource(R.drawable.tiendas);
            texttwo.setText("Tiendas");
            idcat1 = 7;
            imagethree.setImageResource(R.drawable.parques1);
            textthree.setText("Parques");
            idcat2 = 9;
            imagefour.setImageResource(R.drawable.mall1);
            textfour.setText("Plazas Comerciales");
            idcat3 = 8;
        }else if(idPerfil.equals("11")){
            imageone.setImageResource(R.drawable.restaurantes);
            textone.setText("Restaurantes");
            idcat = 2;
            imagetwo.setImageResource(R.drawable.tiendas);
            texttwo.setText("Tiendas");
            idcat1 = 7;
            imagethree.setImageResource(R.drawable.parques1);
            textthree.setText("Parques");
            idcat2 = 9;
            imagefour.setImageResource(R.drawable.mall1);
            textfour.setText("Plazas Comerciales");
            idcat3 = 8;
        }else if(idPerfil.equals("12")){
            imageone.setImageResource(R.drawable.restaurantes);
            textone.setText("Restaurantes");
            idcat = 2;
            imagetwo.setImageResource(R.drawable.tiendas);
            texttwo.setText("Tiendas");
            idcat1 = 7;
            imagethree.setImageResource(R.drawable.parques1);
            textthree.setText("Parques");
            idcat2 = 9;
            imagefour.setImageResource(R.drawable.mall1);
            textfour.setText("Plazas Comerciales");
            idcat3 = 8;
        }
        // click en la imagen uno
        imageone.setOnClickListener(new View.OnClickListener() {

            int dato = idcat ;
            //@Override
            public void onClick(View v) {
                SharedPreferences cat;
                cat = getContext().getSharedPreferences("categoria",MODE_PRIVATE);
                editarCat=cat.edit();
                editarCat.putInt("idCat",dato);
                editarCat.commit();
                String fragmentTemp="com.ut3.ehg.turismotepic.CategoriaActivity";
                DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
                FragmentTransaction tx = getActivity().getSupportFragmentManager().beginTransaction();
                tx.replace(R.id.lframe, Fragment.instantiate(getContext(), fragmentTemp)).addToBackStack("tag");
                tx.commit();
                drawer.closeDrawer(GravityCompat.START);
            }
        });


        // click en la imagen dos
        imagetwo.setOnClickListener(new View.OnClickListener() {

            int dato = idcat1 ;
            //@Override
            public void onClick(View v) {
                SharedPreferences cat;
                cat = getContext().getSharedPreferences("categoria",MODE_PRIVATE);
                editarCat=cat.edit();
                editarCat.putInt("idCat",dato);
                editarCat.commit();
                String fragmentTemp="com.ut3.ehg.turismotepic.CategoriaActivity";
                DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
                FragmentTransaction tx = getActivity().getSupportFragmentManager().beginTransaction();
                tx.replace(R.id.lframe, Fragment.instantiate(getContext(), fragmentTemp)).addToBackStack("tag");
                tx.commit();
                drawer.closeDrawer(GravityCompat.START);
            }
        });
        // click en la imagen tres
        imagethree.setOnClickListener(new View.OnClickListener() {

            int dato = idcat2 ;
            //@Override
            public void onClick(View v) {
                SharedPreferences cat;
                cat = getContext().getSharedPreferences("categoria",MODE_PRIVATE);
                editarCat=cat.edit();
                editarCat.putInt("idCat",dato);
                editarCat.commit();
                String fragmentTemp="com.ut3.ehg.turismotepic.CategoriaActivity";
                DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
                FragmentTransaction tx = getActivity().getSupportFragmentManager().beginTransaction();
                tx.replace(R.id.lframe, Fragment.instantiate(getContext(), fragmentTemp)).addToBackStack("tag");
                tx.commit();
                drawer.closeDrawer(GravityCompat.START);
            }
        });
        //click en la imagen cuatro
        imagefour.setOnClickListener(new View.OnClickListener() {

            int dato = idcat3 ;
            //@Override
            public void onClick(View v) {
                SharedPreferences cat;
                cat = getContext().getSharedPreferences("categoria",MODE_PRIVATE);
                editarCat=cat.edit();
                editarCat.putInt("idCat",dato);
                editarCat.commit();
                String fragmentTemp="com.ut3.ehg.turismotepic.CategoriaActivity";
                DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
                FragmentTransaction tx = getActivity().getSupportFragmentManager().beginTransaction();
                tx.replace(R.id.lframe, Fragment.instantiate(getContext(), fragmentTemp)).addToBackStack("tag");
                tx.commit();
                drawer.closeDrawer(GravityCompat.START);
            }
        });




        return root;
    }





    @Override
    public void onClick(View v) {
        DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        if(!drawer.isDrawerOpen(GravityCompat.START))
            drawer.openDrawer(GravityCompat.START);
        else
            drawer.closeDrawer(GravityCompat.START);

    }


}
