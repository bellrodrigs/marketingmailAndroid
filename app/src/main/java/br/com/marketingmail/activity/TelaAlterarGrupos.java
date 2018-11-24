package br.com.marketingmail.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.marketingmail.controller.MarketingMailctrl;
import br.com.marketingmail.database.dbMarketingMail;

public class TelaAlterarGrupos extends Activity {

    private EditText TXTnomegrupo;
    private Button BTNalterar;
    private Button BTNexcluir;

    private Cursor cursor;
    MarketingMailctrl crud;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_alterar_grupos);

        BTNalterar= (Button) findViewById(R.id.btn_alterar);

        chamandoDados();


//############################### BOTÃO  ALTERAR DADOS ####################################

        BTNalterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BTNalterar= (Button) findViewById(R.id.btn_alterar);

                if (TXTnomegrupo.getText().toString().equals("")) {
                    TXTnomegrupo.setError("Preencher o campo Nome é obrigatório!");


                }else {

                    crud.alterarDadosGrupo(Integer.parseInt(codigo), //---> crud e alterarDados do controller

                            TXTnomegrupo.getText().toString());
                    Toast.makeText(TelaAlterarGrupos.this, "Grupo alterado com sucesso!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TelaAlterarGrupos.this, TelaConsultarGrupos.class);
                    startActivity(intent);
                    finish();
                }
            }
        });


        //################################ BOTÃO EXCLUIR CONTATO #############################
        BTNexcluir = (Button) findViewById(R.id.btn_excluir);
        BTNexcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                excluirGrupo();




//                crud.excluirDadosGrupo(Integer.parseInt(codigo));
//                Toast.makeText(TelaAlterarGrupos.this, "O Grupo foi excluido!", Toast.LENGTH_SHORT).show();
//
//                Intent intent = new Intent(TelaAlterarGrupos.this, TelaConsultarGrupos.class);
//                startActivity(intent);
//                finish();
            }
        });

    }
        //##################### CHAMANDO DADOS PARA A TELA ALTERAR_##########################

        public void chamandoDados(){

            try {
                codigo = this.getIntent().getStringExtra("codigo");
                crud = new MarketingMailctrl(getBaseContext());



                TXTnomegrupo = (EditText) findViewById(R.id.txt_nomegrupo);


                cursor = crud.chamarDadosGrupo((Integer.parseInt(codigo)));

                TXTnomegrupo.setText(cursor.getString(cursor.getColumnIndexOrThrow(dbMarketingMail.NOME_GRUPO)));


            } catch (Exception e) {
                Toast.makeText(this, "Erro inesperado! Consulte o desenvolvedor", Toast.LENGTH_SHORT).show();

            }

        }

    //############################ VOLTAR TELA COM A SETA ###################################

    @Override
    public void onBackPressed(){
        startActivity(new Intent(TelaAlterarGrupos.this, TelaConsultarGrupos.class));
        finishAffinity();

    }


public void excluirGrupo(){

        new AlertDialog.Builder(this).setTitle("Atenção")
            .setMessage("Ao deletar um grupo você estará afetando todos os contatos atrelados a ele e terá de alterar seus contatos, deseja continuar?")
            .setPositiveButton("Sim", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialogInterface, int id) {
                    Intent intent = new Intent(TelaAlterarGrupos.this, TelaGrupos.class);
                    startActivity(intent);
                    finish();
                    crud.excluirDadosGrupo(Integer.parseInt(codigo));
                    Toast.makeText(TelaAlterarGrupos.this, "O Grupo foi excluido!", Toast.LENGTH_SHORT).show();
                }

            })
                .setNegativeButton("Não", null).show();
            }




}











