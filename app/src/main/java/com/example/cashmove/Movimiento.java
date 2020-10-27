package com.example.cashmove;

import java.util.ArrayList;
import java.util.HashMap;


import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class Movimiento extends Activity {
    TabHost tabs;
    ListView lista;

    EditText Nobjeto, cantidad, cantidad2, meses, interes, busqueda, Ncasa, tasain, codigo, codi;
    Button calcular, agregarHist, buscar, agregar;
    TextView total;
    ListView lista2;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movimiento);


        tabs = (TabHost) findViewById(R.id.thTab);

        lista = (ListView) findViewById(R.id.listView1);
        lista2 = (ListView) findViewById(R.id.listView3);

        Nobjeto = (EditText) findViewById(R.id.ediObjeto);
        cantidad = (EditText) findViewById(R.id.ediCantid);
        meses = (EditText) findViewById(R.id.ediTiempoPag);
        interes = (EditText) findViewById(R.id.ediInteres);
        //busqueda = (EditText)findViewById(R.id.ediBusca);
        Ncasa = (EditText) findViewById(R.id.ediHomeEmpen);
        tasain = (EditText) findViewById(R.id.ediTasaInters);
        codigo = (EditText) findViewById(R.id.ediHomeCodigo);
        codi = (EditText) findViewById(R.id.ediCodOb);

        calcular = (Button) findViewById(R.id.btnCalcular);

        // agregarHist = (Button)findViewById(R.id.btnAgrega3);
        // agregarHist.setBackgroundColor(Color.alpha(color.));
        //buscar = (Button)findViewById(R.id.btnBuscand);
        // agregar = (Button)findViewById(R.id.btnAgrega3);

        total = (TextView) findViewById(R.id.tvTotal);


        tabs.setup();
        TabSpec spec = tabs.newTabSpec("tag1");
        spec.setIndicator("Realizar Calculo", getResources().getDrawable(R.drawable.cal));//getResources().getDrawable(R.drawable.print)
        spec.setContent(R.id.tab1);
        tabs.addTab(spec);

        spec = tabs.newTabSpec("tag2");
        spec.setIndicator("Historial", getResources().getDrawable(R.drawable.fol));
        spec.setContent(R.id.tab2);
        tabs.addTab(spec);

        spec = tabs.newTabSpec("tag3");
        spec.setIndicator("Registros", getResources().getDrawable(R.drawable.shop));
        spec.setContent(R.id.tab3);
        tabs.addTab(spec);


        ///ArrayList<String> alista2 = new ArrayList<String>();
        ///ArrayAdapter<String> aa2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alista2);

        ArrayList<String> alista3 = new ArrayList<String>();
        ArrayAdapter<String> aa3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alista3);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    public void altar(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = codigo.getText().toString();
        String codigo2 = Ncasa.getText().toString();
        String nombre2 = tasain.getText().toString();

        if (cod.equals("") || codigo2.equals("") || nombre2.equals("")) {
            Toast.makeText(this, "Rellena los campos por favor", Toast.LENGTH_SHORT).show();
        } else {

            ContentValues regi = new ContentValues();

            regi.put("codigo", cod);
            regi.put("nuevo", codigo2);
            regi.put("inter", nombre2);


            bd.insert("registros", null, regi);
            bd.close();
            codigo.setText("");
            Ncasa.setText("");
            tasain.setText("");


            // //codigo integer primary key, nombre text,resivir integer,mes integer, interes integer,tot integer
            Toast.makeText(this, "Datos Grabados",
                    Toast.LENGTH_SHORT).show();
        }
    }


    public ArrayList<HashMap<String, String>> FillListViewProds1() {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);

        ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map;
        SQLiteDatabase bd = admin.getWritableDatabase();

//			ArrayList<String> result = new ArrayList<String>();
        try {
            Cursor fila = bd.rawQuery("select codigo, nuevo, inter from registros", null);

            if (fila.moveToFirst()) {
                do {
                    map = new HashMap<String, String>();
                    map.put("codigo", fila.getString(0));
                    map.put("nuevo", fila.getString(1));
                    map.put("inter", fila.getString(2));

                    result.add(map);
//		        		result.add(fila.getString(0)+ " "+ fila.getString(1));

                } while (fila.moveToNext());

            }
            fila.close();
            return result;
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return null;


    }

    public void delta2(View v) {
        SimpleAdapter adp = new SimpleAdapter(this,
                FillListViewProds1(), //datos
                R.layout.acti2,//layaout del formato de cada fila
                new String[]{"codigo", "nuevo", "inter"},  // titulos de las columnas
                new int[]{R.id.txtCol1, R.id.txtCol2, R.id.txtCol3});//celdas de la fla en el layout

        lista2.setAdapter(adp);


    }

    public ArrayList<HashMap<String, String>> FillListViewProds() {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);

        ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map;
        SQLiteDatabase bd = admin.getWritableDatabase();

//			ArrayList<String> result = new ArrayList<String>();
        try {
            Cursor fila = bd.rawQuery("select cod, nombre, resivir,mes,interes,tot from casa_empeno", null);

            if (fila.moveToFirst()) {
                do {
                    map = new HashMap<String, String>();
                    map.put("cod", fila.getString(0));
                    map.put("nombre", fila.getString(1));
                    map.put("resivir", fila.getString(2));
                    map.put("mes", fila.getString(3));
                    map.put("interes", fila.getString(4));
                    map.put("tot", fila.getString(5));
                    result.add(map);
//		        		result.add(fila.getString(0)+ " "+ fila.getString(1));

                } while (fila.moveToNext());

            }
            fila.close();
            return result;
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return null;


    }

    public void delta(View v) {
        SimpleAdapter adp = new SimpleAdapter(this,
                FillListViewProds(), //datos
                R.layout.acti,//layaout del formato de cada fila
                new String[]{"cod", "nombre", "resivir", "mes", "interes", "tot"},  // titulos de las columnas
                new int[]{R.id.txtCol1, R.id.txtCol2, R.id.txtCol3, R.id.txtCol4, R.id.txtCol5, R.id.txtCol6});//celdas de la fla en el layout

        lista.setAdapter(adp);


    }


    public void alta(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String codii = codi.getText().toString();
        String codigo2 = Nobjeto.getText().toString();
        String nombre2 = cantidad.getText().toString();
        String direccion2 = meses.getText().toString();
        String inter = interes.getText().toString();
        String tol = total.getText().toString();

        ContentValues registro = new ContentValues();

        if (codii.equals("") || codigo2.equals("") || nombre2.equals("") || direccion2.equals("") || inter.equals("")) {
            Toast.makeText(this, "Rellena los datos por favor", Toast.LENGTH_SHORT).show();
        } else {
            if (!tol.equals("")) {

                registro.put("cod", codii);
                registro.put("nombre", codigo2);
                registro.put("resivir", nombre2);
                registro.put("mes", direccion2);
                registro.put("interes", inter);
                registro.put("tot", tol);

                bd.insert("casa_empeno", null, registro);
                bd.close();
                codi.setText("");
                Nobjeto.setText("");
                cantidad.setText("");
                meses.setText("");
                interes.setText("");
                total.setText("");


                // //codigo integer primary key, nombre text,resivir integer,mes integer, interes integer,tot integer
                Toast.makeText(this, "Datos Grabados",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Realiza los calculos",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void busca(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String codigo2 = codi.getText().toString();

        if (codigo2.equals("")) {
            Toast.makeText(this, "Rellena con un numero el codigo", Toast.LENGTH_SHORT).show();
        } else {

            Cursor fila = bd.rawQuery(
                    "select nombre,resivir,mes,interes,tot  from casa_empeno where cod=" + codigo2, null);
            //cod integer primary key,nombre text,evaluada integre,resivir integer,mes integer, interes integer,tot integer
            if (fila.moveToFirst()) {
                Nobjeto.setText(fila.getString(0));
                cantidad.setText(fila.getString(1));
                meses.setText(fila.getString(2));
                interes.setText(fila.getString(3));
                total.setText(fila.getString(4));

            } else
                Toast.makeText(this, "No existe el registro",
                        Toast.LENGTH_SHORT).show();

            bd.close();
        }
    }


    public void borrar(View v) {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String codigo2 = codi.getText().toString();

        if (codigo2.equals("")) {
            Toast.makeText(this, "Rellena los datos por favor", Toast.LENGTH_SHORT).show();
        } else {

            int cant = bd.delete("casa_empeno", "cod=" + codigo2, null);
            bd.close();
            codi.setText("");
            Nobjeto.setText("");
            cantidad.setText("");
            meses.setText("");
            interes.setText("");
            total.setText("");
            if (cant == 1)
                Toast.makeText(this, "Dato eliminado",
                        Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "No existe el dato",
                        Toast.LENGTH_SHORT).show();
        }
    }

    public void modi2(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String codii2 = codi.getText().toString();
        String codigo3 = Nobjeto.getText().toString();
        String nombre3 = cantidad.getText().toString();
        String direccion3 = meses.getText().toString();
        String inter2 = interes.getText().toString();
        String tol2 = total.getText().toString();

        if (codii2.equals("") || codigo3.equals("") || nombre3.equals("") || direccion3.equals("") || inter2.equals("")) {
            Toast.makeText(this, "Tiene que rellenar los datos para modificar el registro", Toast.LENGTH_SHORT).show();
        } else {

            ContentValues registro = new ContentValues();
            registro.put("cod", codii2);
            registro.put("nombre", codigo3);
            registro.put("resivir", nombre3);
            registro.put("mes", direccion3);
            registro.put("interes", inter2);
            registro.put("tot", tol2);
            //cod integer primary key,nombre text,resivir integer,mes integer, interes integer,tot integer
            int cant = bd.update("casa_empeno", registro, "cod=" + codii2, null);
            bd.close();
            if (cant == 1)
                Toast.makeText(this, "Se modificaron los datos", Toast.LENGTH_SHORT)
                        .show();
            else
                Toast.makeText(this, "No existe un registro con dicho documento",
                        Toast.LENGTH_SHORT).show();
        }
    }

    public void calcular(View v) {

        if (cantidad.getText().toString() == "" && meses.getText().toString() == "" && interes.getText().toString() == "") {
            Toast.makeText(this, "Debe de llenar los campos a calcular",
                    Toast.LENGTH_SHORT).show();
        } else {
            String valor = cantidad.getText().toString();
            String tiempo = meses.getText().toString();
            String inter = interes.getText().toString();

            if (valor.equals("") || tiempo.equals("") || inter.equals("")) {
                Toast.makeText(this, "Rellena los campos correspondientes", Toast.LENGTH_SHORT).show();
            } else {

                double editexta = Integer.parseInt(valor);
                double editextb = Integer.parseInt(tiempo);
                double editextc = Integer.parseInt(inter);

                double intxt = editextc / 100;

                double calcula = 1 + intxt * editextb;
                double calcula1 = calcula * editexta;

                String result = String.valueOf(calcula1);
                total.setText("$" + result);
            }

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void busca2(View v) {
        AdminSQLiteOpenHelper admin2 = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd2 = admin2.getWritableDatabase();
        String codigo3 = codigo.getText().toString();

        if (codigo3.equals("")) {
            Toast.makeText(this, "Ingresa un codigo a buscar", Toast.LENGTH_SHORT).show();
        } else {

            Cursor fila2 = bd2.rawQuery(
                    "select nuevo,inter  from registros where codigo=" + codigo3, null);
            if (fila2.moveToFirst()) {
                Ncasa.setText(fila2.getString(0));
                tasain.setText(fila2.getString(1));


            } else
                Toast.makeText(this, "No existe el registro de la casa de empeño",
                        Toast.LENGTH_SHORT).show();
            bd2.close();
        }
    }

    public void borrar2(View v) {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String codigo2 = codigo.getText().toString();

        if (codigo2.equals("")) {
            Toast.makeText(this, "Rellena los campos a eliminar", Toast.LENGTH_SHORT).show();
        } else {

            int cant = bd.delete("registros", "codigo=" + codigo2, null);
            bd.close();
            Ncasa.setText("");
            tasain.setText("");

            if (cant == 1)
                Toast.makeText(this, "Dato eliminado",
                        Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "No existe el dato",
                        Toast.LENGTH_SHORT).show();
        }
    }

    public void modi(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String codigo2 = codigo.getText().toString();
        String nuevis = Ncasa.getText().toString();
        String tee = tasain.getText().toString();

        if (codigo2.equals("") || nuevis.equals("") || tee.equals("")) {
            Toast.makeText(this, "Se tienen que rellenar los datos", Toast.LENGTH_SHORT).show();
        } else {

            ContentValues registro = new ContentValues();
            registro.put("codigo", codigo2);
            registro.put("nuevo", nuevis);
            registro.put("inter", tee);
            int cant = bd.update("registros", registro, "codigo=" + codigo2, null);
            bd.close();
            if (cant == 1)
                Toast.makeText(this, "Se modificaron los datos", Toast.LENGTH_SHORT)
                        .show();
            else
                Toast.makeText(this, "No existe un registro con dicho documento",
                        Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Movimiento Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.cashmove/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Movimiento Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.cashmove/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}

