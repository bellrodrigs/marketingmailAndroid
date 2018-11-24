package br.com.marketingmail.splash;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.marketingmail.activity.R;
import br.com.marketingmail.activity.TelaEscolha;

public class TelaProntoSplash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_pronto_splash);

        //_____________________________Delay na Tela_______________________________


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(TelaProntoSplash.this, TelaEscolha.class);
                startActivity(intent);
                TelaProntoSplash.this.finish();
            }  //Criando delay para a tela.
        }, 4000);


    }
}
