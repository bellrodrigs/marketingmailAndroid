package br.com.marketingmail.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import br.com.marketingmail.database.dbMarketingMail;
import br.com.marketingmail.controller.MarketingMailctrl;


public class TelaAlterar extends Activity implements AdapterView.OnItemSelectedListener{


    private Button BTNalterar;
    private Button BTNexcluir;
    private EditText TXTnomecad;
    private EditText TXTtelefone;
    private EditText TXTcidade;
    private EditText TXTcelular;

    private EditText TXTemail;
    public Spinner  SPgrupo;

    private Cursor cursor;
    MarketingMailctrl crud;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_alterar);

        SPgrupo = (Spinner) findViewById(R.id.spinner);

        adaptadorSpinner();

        SPgrupo.setOnItemSelectedListener(this);

        chamandoDados();

    //########################## AÇÃO DO BOTÃO ALTERAR ####################################

        BTNalterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SPgrupo = (Spinner) findViewById(R.id.spinner);



                if (TXTnomecad.getText().toString().equals("")) {
                    TXTnomecad.setError("Preencher o campo Nome é obrigatório!");
                    TXTnomecad.requestFocus();

                }

                else if (TXTemail.getText().toString().equals("")) {

                    TXTemail.setError("Preencher o campo E-mail é obrigatório!");
                    TXTemail.requestFocus();
                }


                else if (SPgrupo.getCount() == 0) {
                    menssagemConfirma();

                } else {









                crud.alterarDados(Integer.parseInt(codigo), //---> crud e alterarDados do controller

                TXTnomecad.getText().toString(),
                TXTtelefone.getText().toString(),
                TXTcidade.getText().toString(),
                TXTemail.getText().toString(),
                TXTcelular.getText().toString(),
                SPgrupo.getSelectedItem().toString());
               //SPgrupo.getSelectedItemPosition());

                Toast.makeText(TelaAlterar.this, "Cadastro alterado com sucesso!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TelaAlterar.this,TelaConsulta.class);
                startActivity(intent);
                finish();
                }
            }
        });



        //######################## BOTÃO DE EXCLUIR DADOS ##################################

        BTNexcluir= (Button)findViewById(R.id.btn_excluir);
        BTNexcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.excluirDados(Integer.parseInt(codigo));
                Toast.makeText(TelaAlterar.this, "O contato foi excluido", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(TelaAlterar.this,TelaConsulta.class);
                startActivity(intent);
                finish();
            }
        });



    }

    //##################### CHAMANDO DADOS DA TABELA PARA A TELA ALTERAR_##########################

    public void chamandoDados(){
        try {
            codigo = this.getIntent().getStringExtra("codigo");

            crud = new MarketingMailctrl(getBaseContext());
            TXTnomecad = (EditText) findViewById(R.id.txt_nomecad);
            TXTtelefone = (EditText) findViewById(R.id.txt_telefone);
            TXTcidade = (EditText) findViewById(R.id.txt_cidade);
            TXTemail = (EditText) findViewById(R.id.txt_email);
            TXTcelular = (EditText) findViewById(R.id.txt_celular);
            SPgrupo = (Spinner) findViewById(R.id.SP_grupo);

            BTNalterar = (Button) findViewById(R.id.btn_alterar);
            BTNexcluir = (Button) findViewById(R.id.btn_excluir);


            cursor = crud.chamarDados(Integer.parseInt(codigo)); //-------> Método do controller

            //-----> Retorna o indice para o nome da coluna

            TXTnomecad.setText(cursor.getString(cursor.getColumnIndexOrThrow(dbMarketingMail.NOME_CONTATO)));
            TXTtelefone.setText(cursor.getString(cursor.getColumnIndexOrThrow(dbMarketingMail.TELEFONE)));
            TXTcidade.setText(cursor.getString(cursor.getColumnIndexOrThrow(dbMarketingMail.CIDADE)));
            TXTemail.setText(cursor.getString(cursor.getColumnIndexOrThrow(dbMarketingMail.EMAIL)));
            TXTcelular.setText(cursor.getString(cursor.getColumnIndexOrThrow(dbMarketingMail.CELULAR)));
            //SPgrupo.setOnItemSelectedListener(cursor.get(dbMarketingMail.NOME_CONTATO));
            SPgrupo.setSelection(cursor.getShort(cursor.getColumnIndexOrThrow(dbMarketingMail.NOME_CONTATO)));

        }catch(Exception e) {



        }
        //SPgrupo.setSelected(cursor.getString(cursor.getColumnIndexOrThrow(dbMarketingMail.NOME_GRUPO)));

        //setOnItemSelectedListener
    }

    public void menssagemConfirma() {
        try {
            if (SPgrupo.getCount() == 0) {
                new AlertDialog.Builder(this).setTitle("")
                        .setMessage("Epa! Parece que você não cadastrou nenhum grupo para continuar. Deseja ir a tela de grupos?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialogInterface, int id) {
                                Intent intent = new Intent(TelaAlterar.this, TelaGrupos.class);
                                startActivity(intent);
                                finish();
                            }

                        })
                        .setNegativeButton("Voltar", null).show();
            }
        }catch(Exception e){
        }
    }


    //####################### ADAPTADOR (FORMA COMO O SPINNER É EXIBIDO)#####################

    private void adaptadorSpinner() {

        dbMarketingMail db = new dbMarketingMail(getApplicationContext());

        List<String> lista = db.listaSpinner();

        ArrayAdapter<String> grupoAdaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lista);


        grupoAdaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SPgrupo.setAdapter(grupoAdaptador);
    }

        //############################ VOLTAR TELA COM A SETA ###################################

    @Override
    public void onBackPressed(){
        startActivity(new Intent(TelaAlterar.this, TelaConsulta.class));
        finishAffinity();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
