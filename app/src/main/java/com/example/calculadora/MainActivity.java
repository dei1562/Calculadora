package com.example.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView t1, t2;
    Button accion;
    String cadena;
    String operacion;
    String numero;
    Boolean flagPrimeraOperacion;
    float resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1 = (TextView) findViewById(R.id.resultado);
        t2 = (TextView) findViewById(R.id.cadena);
        cadena = "";
        numero = "";
        operacion = "";
        flagPrimeraOperacion = true;
    }

    /**
     * Metodo encargado de validar la accion que se debe realizar segun las acciones del usuario
     * @param view
     */
    public void operar(View view) {

        accion = (Button) findViewById(view.getId());
        String boton = String.valueOf(accion.getText());

        if (cadena.isEmpty()) {
            if (boton.compareTo("+") != 0 && boton.compareTo("-") != 0 && boton.compareTo("*") != 0 && boton.compareTo("/") != 0) {
                this.crearCadena(boton);
            }
        } else {
            this.crearCadena(boton);
        }

        t2.setText(cadena);
    }

    /**
     * Crea la cadena de operacion ingresada por el usuario
     * @param boton
     */
    public void crearCadena(String boton) {

        switch (boton) {
            case "+":

                this.realizarOperacion();
                operacion = "+";
                numero = "";
                cadena = cadena + boton;
                break;
            case "-":

                this.realizarOperacion();
                operacion = "-";
                numero = "";
                cadena = cadena + boton;
                break;
            case "x":

                this.realizarOperacion();
                operacion = "x";
                numero = "";
                cadena = cadena + boton;
                break;
            case "/":

                this.realizarOperacion();
                operacion = "/";
                numero = "";

                if (boton != "0") {
                    operacion = "/";
                    numero = "";
                    cadena = cadena + boton;
                } else {
                    resultado = -1;
                }

                break;
            case "=":

                this.realizarOperacion();

                if (resultado == -1) {
                    t1.setText("");
                    resultado = 0;
                } else {
                    t1.setText("Resultado: " + String.valueOf(resultado));
                }

                operacion = "";
                numero = "";
                break;
            case "C":
                flagPrimeraOperacion = true;
                operacion = "";
                numero = "";
                cadena = "";
                resultado = 0;
                break;
            default:
                numero = numero + boton;
                cadena = cadena + boton;
        }
    }

    /**
     * Realiza la operacion aritmetica seleccionada por el usuario en la calculadora
     */
    public void realizarOperacion(){

        if (flagPrimeraOperacion && !numero.isEmpty()) {
            resultado = Float.parseFloat(numero);
            flagPrimeraOperacion = false;

        } else if (!numero.isEmpty()){
            float num = Float.parseFloat(numero);
            switch (operacion) {
                case "+":
                    resultado = resultado + num;
                    break;
                case "-":
                    resultado = resultado - num;
                    break;
                case "x":
                    resultado = resultado * num;
                    break;
                case "/":

                    if (num == 0) {
                        this.mensaje("Error, division por zero no permitida.");
                        flagPrimeraOperacion = true;
                        operacion = "";
                        numero = "";
                        cadena = "";
                        resultado = 0;

                        t1.setText("");
                        t2.setText("");
                    } else {
                        resultado = resultado / num;
                    }

                    break;
            }
        }
    }

    /**
     * Mensaje en pantalla que desaparece trans un tiempo (SHORT o LONG)
     * @param cadena
     */
    public void mensaje(String cadena) {
        Toast.makeText(this, cadena, Toast.LENGTH_SHORT).show();
    }
}
