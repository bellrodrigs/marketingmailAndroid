package br.com.marketingmail.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import br.com.marketingmail.splash.TelaProntoSplash;
import br.com.marketingmail.splash.TelaSplash;


public class MainActivity extends Activity {
//



    private Button BTNentrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //##################### BOTÃO ENTRAR IR PARA OUTRA TELA ##################################

        BTNentrar = (Button) findViewById(R.id.btn_entrar);

        BTNentrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TelaTermo.class);



                startActivity(intent);

                finish();
            }
        });


    }
}
