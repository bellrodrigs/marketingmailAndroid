package br.com.marketingmail.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class TelaEscolha extends Activity {

    private ImageButton BTNcontato;
    private ImageButton BTNemail;
    private ImageButton BTNgrupos;
    private ImageButton BTNgerenciar;
    private ImageButton BTNgerenciargrupos;
    private ImageButton BTNajuda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_escolha);


        //###################### BOTÃO CADASTRAR CONTATO IR PARA OUTRA TELA #######################


        BTNcontato=(ImageButton) findViewById(R.id.btn_contato);

        BTNcontato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaEscolha.this, TelaCadastro.class);



                startActivity(intent);

                finish();

            }
        });

        //###################### BOTÃO CADASTRAR GRUPOS IR PARA OUTRA TELA ########################

        BTNgrupos=(ImageButton) findViewById(R.id.btn_grupo);

        BTNgrupos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaEscolha.this, TelaGrupos.class);



                startActivity(intent);

                finish();
            }
        });

        //##################### BOTÃO CADASTRAR EMIAL IR PARA OUTRA TELA #########################

        BTNemail= (ImageButton) findViewById(R.id.btn_email);

        BTNemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaEscolha.this, TelaEmail.class);



                startActivity(intent);

                finish();

            }
        });

        //########################## BOTÃO AJUDA IR PARA OUTRA TELA_#############################

        BTNajuda = (ImageButton) findViewById(R.id.btn_ajuda);

        BTNajuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaEscolha.this, TelaAjuda.class);



                startActivity(intent);

                finish();
            }
        });


    //########################### BOTÃO GERENCIAR IR PARA OUTRA TELA ############################

        BTNgerenciar = (ImageButton) findViewById(R.id.btn_gerenciar);

        BTNgerenciar .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaEscolha.this, TelaConsulta.class);



                startActivity(intent);

                finish();
            }
        });





        //################## BOTÃO GERENCIAR GRUPOS IR PARA OUTRA TELA ###################

        BTNgerenciargrupos = (ImageButton) findViewById(R.id.btn_gerenciargrupo);

        BTNgerenciargrupos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaEscolha.this, TelaConsultarGrupos.class);



                startActivity(intent);

                finish();
            }
        });







    }




    @Override
    public void onBackPressed(){
        startActivity(new Intent(TelaEscolha.this, MainActivity.class));
        finishAffinity();

    }




    }


