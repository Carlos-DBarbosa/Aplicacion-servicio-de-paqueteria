package com.example.parcial1_punto2_3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Calcular extends AppCompatActivity implements OnItemSelectedListener {

    private EditText Peso;
    private Button BotonG;
    private Spinner Continente, Pais;
    int P;
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
        setContentView(R.layout.activity_calcular);

        Peso = (EditText) findViewById(R.id.et1);
        BotonG = (Button) findViewById(R.id.btnGestion);
        Continente = (Spinner) findViewById(R.id.spinner);
        Continente.setOnItemSelectedListener(this);
        Pais = (Spinner) findViewById(R.id.spinner2);
        //Pais.setOnItemSelectedListener(this);

        adapterC = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, OpcionesC);
        Continente.setAdapter(adapterC);

        BotonG.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Calcular.this, Gestionar.class);
                i.putExtra("continente", Continente.getSelectedItemPosition()+"");
                i.putExtra("pais", Pais.getSelectedItemPosition()+"");
                i.putExtra("peso", Peso.getText()+"");
                i.putExtra("precio", P);
                startActivity(i);
            }
        });
    }

    public void calcular(View v){
        int precio;
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        int gramos = Integer.parseInt(Peso.getText().toString());
        String selec = Continente.getSelectedItem().toString();
        if(selec.equals("América del Norte") && gramos <= 5000){
         precio = 3800 * gramos;
         alert.setMessage("El precio es:" + precio);
         alert.setPositiveButton("Aceptar", null);
         alert.show();
         P= precio;
        }else if(selec.equals("América Central") && gramos <= 5000){
            precio = 3100 * gramos;
            alert.setMessage("El precio es:" + precio);
            alert.setPositiveButton("Aceptar", null);
            alert.show();
            P= precio;
        }else if(selec.equals("América del Sur") && gramos <= 5000){
            precio = 2900 * gramos;
            alert.setMessage("El precio es:" + precio);
            alert.setPositiveButton("Aceptar", null);
            alert.show();
            P= precio;
        }else if(selec.equals("Europa") && gramos <= 5000){
            precio = 4200 * gramos;
            alert.setMessage("El precio es:" + precio);
            alert.setPositiveButton("Aceptar", null);
            alert.show();
            P= precio;
        }else if(selec.equals("Asia") && gramos <= 5000){
            precio = 5300 * gramos;
            alert.setMessage("El precio es:" + precio);
            alert.setPositiveButton("Aceptar", null);
            alert.show();
            P= precio;
        }else if(gramos > 5000){
            Toast.makeText(this, "No se puede realizar el envio", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> a, View view, int p, long id) {
        switch (a.getId()){
            case R.id.spinner:
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
            case R.id.spinner2:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}