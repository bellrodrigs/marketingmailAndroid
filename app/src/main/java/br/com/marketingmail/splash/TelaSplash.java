package br.com.marketingmail.splash;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



import br.com.marketingmail.activity.MainActivity;
import br.com.marketingmail.activity.R;

public class TelaSplash extends Activity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_splash);

        //_______________TELA NA VERTICAL__________________________
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

   //_____________FAZER TELA APARECER SOMENTE UMA VEZ_________
        SharedPreferences preferences =
        getSharedPreferences("user_preferences", MODE_PRIVATE);

        if (preferences.contains("app_aberto")) {
            mostrarMainActivity();
        } else {

            mostrarTelaSplash();
        }
    }
//_______________ARMAZENANDO CHAVE NO PREFERENCES_______________

    private void adicionarPreferenceAppAberto(SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("app_aberto", true);
        editor.commit();
    }
 //________________DELAY TELA SPLASH___________________________
    private void mostrarTelaSplash() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostrarMainActivity();
            }
        }, 2000);
    }


//____________________MÉTODO ___________________________

    private void  mostrarMainActivity() {
        Intent intent = new Intent(TelaSplash.this,
               MainActivity.class);
        startActivity(intent);
        finish();
    }






}


