package com.example.testarporta;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.InetSocketAddress;
import java.net.Socket;

import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {
    final int timeout = 200;

    EditText ip;
    EditText porta;
    TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ip = findViewById(R.id.ip);
        porta = findViewById(R.id.porta);
        resultado = findViewById(R.id.resultado);


        resultado.setVisibility(View.INVISIBLE);


        final Button scanSend = findViewById(R.id.scanSend);
        scanSend.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                resultado.setVisibility(View.INVISIBLE);

                if (portaEstaAberta(ip.getText().toString(),  Integer.parseInt(porta.getText().toString()), timeout)){

                    resultado.setText("A porta "+Integer.parseInt(porta.getText().toString())+" está Aberta.");
                    resultado.setBackgroundResource(R.color.colorPrimary);
                    resultado.setVisibility(View.VISIBLE);

                }else{
                    resultado.setText("A porta "+Integer.parseInt(porta.getText().toString())+" está Fechada.");
                    resultado.setBackgroundResource(R.color.colorAccent);
                    resultado.setVisibility(View.VISIBLE);
                }

            }



        });
    }

    public static boolean portaEstaAberta(String ip, int porta, int timeout) {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(ip, porta), timeout);
            socket.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
