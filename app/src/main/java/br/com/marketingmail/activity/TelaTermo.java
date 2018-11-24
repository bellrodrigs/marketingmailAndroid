package br.com.marketingmail.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ScrollView;

import br.com.marketingmail.splash.TelaProntoSplash;

public class TelaTermo extends Activity {


    private Button BTNcontinuar;
    private CheckBox CBconcordo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_termo);

        //################ Verificando se o termo já foi aceito #########################

        final SharedPreferences preferences = getSharedPreferences(null, 0);
        if(preferences.getBoolean("termoaceito", false)){
            Intent i = new Intent(this,TelaEscolha.class); //---->Se o termo já foi aceito
            startActivity(i);

        }

        //################### ATIVAR BOTÃO AO CLICAR NA CHECKBOX ################################

        CBconcordo = (CheckBox) findViewById(R.id.cb_concordo);
        BTNcontinuar = (Button) findViewById(R.id.btn_continuar);
        BTNcontinuar.setEnabled(false);


        CBconcordo.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {


                if (((CheckBox) view).isChecked()) {
                    BTNcontinuar.setEnabled(true);

                } else
                    BTNcontinuar.setEnabled(false);
            }
        });


        //###################### BOTÃO CONTINUAR IR PARA OUTRA TELA_###########################


        BTNcontinuar = (Button) findViewById(R.id.btn_continuar);

        BTNcontinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            //GUARDANDO SHARED PREFERENCES AO ACEITAR TERMO

                //obs: isso fara o app pular a tela de termo quando o app for inicado novemente...

                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("termoaceito",true );
                editor.apply();


                Intent intent = new Intent(TelaTermo.this, TelaProntoSplash.class);


                //Recebendo o Intent

                startActivity(intent);

                finish();


            }
        });


    }

    //############################ VOLTAR TELA COM A SETA #####################################

    @Override
    public void onBackPressed() { //----> Botão BACK padrão do android
        startActivity(new Intent(TelaTermo.this, MainActivity.class)); //---->O efeito ao ser pressionado do botão (no caso abre a activity)
        finishAffinity();
        return;
    }




}

