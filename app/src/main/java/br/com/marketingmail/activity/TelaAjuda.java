package br.com.marketingmail.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TelaAjuda extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_ajuda);


    }

    //############################ VOLTAR TELA COM A SETA #####################################

    @Override
    public void onBackPressed(){
        startActivity(new Intent(TelaAjuda.this, TelaEscolha.class));
        finishAffinity();
        return;
    }


}




