package br.com.marketingmail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import br.com.marketingmail.controller.MarketingMailctrl;

public class TelaGrupos extends Activity {
private Button BTNcadastrargrupo;
 private EditText TXTnomegrupo;
    private ImageButton BTNgerenciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_grupos);


        //############################ PEGANDO DADOS PARA CADSTRAR ########################

                BTNcadastrargrupo = (Button)findViewById(R.id.btn_cadastrargrupo);


                BTNcadastrargrupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TXTnomegrupo = (EditText)findViewById(R.id.txt_nomegrupo);



                MarketingMailctrl crud = new MarketingMailctrl (getBaseContext());

                //----> Convertendo os edittext para string

                if (TXTnomegrupo.getText().toString().equals("")) {
                    TXTnomegrupo.setError("Preencher o campo Nome é obrigatório!");


                }else {

                    String nomegrupoString = TXTnomegrupo.getText().toString();

                    String resposta;

                    resposta = crud.incluirGrupo(nomegrupoString);

                    Toast.makeText(getApplicationContext(), resposta, Toast.LENGTH_LONG).show();
                    //----> mensagem de confirmaçao aparecendo
                    limpaDados();
                }
            }
        });

        //######################### BOTÃO GERENCIAR IR PARA OUTRA TELA_ ##################################


        BTNgerenciar = (ImageButton) findViewById(R.id.btn_gerenciar);

        BTNgerenciar .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaGrupos.this, TelaConsultarGrupos.class);

                startActivity(intent);

                finish();
            }
        });


    }



    //############################ VOLTAR TELA COM A SETA ########################################

    @Override
    public void onBackPressed(){
        startActivity(new Intent(TelaGrupos.this, TelaEscolha.class));
        finishAffinity();

    }


    //################################ LIMPAR CAMPOS APÓS CADASTRO ################################

    public void limpaDados(){
        TXTnomegrupo.setText("");


    }




        }




