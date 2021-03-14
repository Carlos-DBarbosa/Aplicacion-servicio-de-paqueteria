package com.example.parcial1_punto2_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Gestionar extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText Id, Nombre, Peso, Precio;
    private Spinner Continente, Pais;

    ArrayAdapter<String> adapterC, Ap;

    String[] OpcionesC = {"América del Norte", "América Central", "América del Sur", "Europa", "Asia"};// Arreglo de continentes
    String[] P1 = {"Canadá", "Estados Unidos"}; // Arreglo de paises de continente1
    String[] P2 = {"Belice", "Costa Rica", "El Salvador", "Guatemala", "Honduras"}; // Arreglo de paises de continente2
    String[] P3 = {"Argentina", "Bolivia", "Brasil", "Chile", "Colombia", "Ecuador"}; // Arreglo de paises de continente3
    String[] P4 = {"Alemania", "Austria", "Bélgica", "Bulgaria", "España"}; // Arreglo de paises de continente4
    String[] P5 = {"Afganistán", "Arabia Saudita", "China", "Corea del Sur", "Filipinas"}; // Arreglo de paises de continente5

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar);

        Id = (EditText) findViewById(R.id.etId);
        Nombre = (EditText) findViewById(R.id.etnmb);
        Peso = (EditText) findViewById(R.id.etpeso);
        Continente = (Spinner) findViewById(R.id.spinner3);
        Continente.setOnItemSelectedListener(this);
        adapterC = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, OpcionesC);
        Continente.setAdapter(adapterC);
        Pais = (Spinner) findViewById(R.id.spinner4);
        Precio = (EditText) findViewById(R.id.etPrecio);

        int continente = getIntent().getIntExtra("continente", -1);
        int pais = getIntent().getIntExtra("pais", -1);
        String peso = getIntent().getStringExtra("peso");
        String P = getIntent().getStringExtra("precio");

        Continente.setSelection(continente);
        Pais.setSelection(pais);
        Peso.setText(peso);
        Precio.setText(P);
    }

    public void Insertar(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "servicio", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nombre = Nombre.getText().toString();
        String peso = Peso.getText().toString();
        String continente = Continente.getSelectedItem().toString();
        String pais = Pais.getSelectedItem().toString();
        String precio = Precio.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("nombreP", nombre);
        registro.put("peso", peso);
        registro.put("continente", continente);
        registro.put("pais", pais);
        registro.put("costoE", precio);
        bd.insert("paquetes", null, registro);
        bd.close();
        Nombre.setText("");
        Peso.setText("");
        Continente.setSelection(0);
        Pais.setSelection(0);
        Precio.setText("");
        Toast.makeText(this, "Se cargaron los datos del paquete", Toast.LENGTH_SHORT).show();
    }

    public void Consultar(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "servicio", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String id = Id.getText().toString();
        Cursor fila = bd.rawQuery("SELECT nombreP,peso,costoE FROM paquetes WHERE id=" + id, null);
        if (fila.moveToFirst()){
            Nombre.setText(fila.getString(1));
            Peso.setText(fila.getInt(2));
            //Continente.setSelection(fila.getInt(3));
            //Pais.setSelection(fila.getInt(4));
            Precio.setText(fila.getString(5));
        }else {
            Toast.makeText(this, "No existe el paquete con dicho nombre", Toast.LENGTH_SHORT).show();
            bd.close();
        }
    }

    public void Modificar(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"servicio", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String id = Id.getText().toString();
        String nombre = Nombre.getText().toString();
        String peso = Peso.getText().toString();
        String continente = Continente.getSelectedItem().toString();
        String pais = Pais.getSelectedItem().toString();
        String precio = Precio.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("nombreP", nombre);
        registro.put("peso", peso);
        registro.put("continente", continente);
        registro.put("pais", pais);
        registro.put("costoE", precio);
        int cant = bd.update("paquetes", registro, "id=" + id, null);
        bd.close();
        if(cant == 1){
            Toast.makeText(this, "Se modificaron los datos", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "No existe un paquete con dicho nombre", Toast.LENGTH_SHORT).show();
        }
    }

    public void Eliminar(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"servicio", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String id = Id.getText().toString();
        int cant = bd.delete("paquetes", "id=" + id, null);
        bd.close();
        Nombre.setText("");
        Peso.setText("");
        //Continente.setSelection(0);
        //Pais.setSelection(0);
        Precio.setText("");
        if(cant == 1){
            Toast.makeText(this, "Se borro el paquete con dicho nombre", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "No existe un paquete con dicho nombre", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> a, View view, int p, long id) {
        switch (a.getId()){
            case R.id.spinner3:
                switch (p) {
                    case 0:
                        Ap = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, P1);
                        break;
                    case 1:
                        Ap = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, P2);
                        break;
                    case 2:
                        Ap = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, P3);
                        break;
                    case 3:
                        Ap = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, P4);
                        break;
                    case 4:
                        Ap = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, P5);
                        break;
                }
                Pais.setAdapter(Ap);
                break;
            case R.id.spinner4:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}