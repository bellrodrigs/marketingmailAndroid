package br.com.marketingmail.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.List;

import br.com.marketingmail.controller.MarketingMailctrl;
import br.com.marketingmail.database.dbMarketingMail;

public class TelaEmail extends Activity {

    private EditText edEmailUsuario, edSenhaUsuario, edAssunto, edMensagem, edEmail;
    private Button btnEnviar;
    private Exception erroInesperado = null;
    private ProgressDialog progressDialog;
    private int resp;
    private Switch switchtodos;
    private Switch switchgrupo;
    private Spinner SPgrupo;
    private ProgressBar progresso;
    private dbMarketingMail banco;
    private SQLiteDatabase db;
    MarketingMailctrl crud;
    String codigo;
    private Cursor cursor;

    public TelaEmail() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tela_email);

        //SPgrupo = (Spinner) findViewById(R.id.SP_grupo);
        //SPgrupo.setEnabled(false);
        //chamandoEmail();
        //adaptadorSpinner(); //-->Inicia carregando o spinner

        edEmailUsuario = (EditText) findViewById(R.id.edEmailUsuario);
        edSenhaUsuario = (EditText) findViewById(R.id.edSenhaUusuario);
        edEmail = (EditText) findViewById(R.id.edEmail);
        edAssunto = (EditText) findViewById(R.id.edAssunto);
        edMensagem = (EditText) findViewById(R.id.edMensagem);
        btnEnviar = (Button) findViewById(R.id.btn_enviaremail);


        btnEnviar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (edEmailUsuario.getText().toString().equals("")) {
                    edEmailUsuario.setError("É preciso informar um E-mail para continuar.");
                    edEmailUsuario.requestFocus();
                } else if (edSenhaUsuario.getText().toString().equals("")) {
                    edSenhaUsuario.setError("A senha não pode estar vazia.");
                    edSenhaUsuario.requestFocus();
                } else if (edEmail.getText().toString().equals("")) {
                    edEmail.setError("É preciso informar um e-mail de contato para que a mensagem seja enviada");
                    edEmail.requestFocus();
                } else if (edAssunto.getText().toString().equals("")) {
                    edAssunto.setError("O assunto não pode ser vazio");
                    edAssunto.requestFocus();
                } else if (edMensagem.getText().toString().equals("")) {
                    edMensagem.setError("A mensagem não pode estar vazia");
                    edMensagem.requestFocus();
                } else {

                    mensagemConfirma();
                    new ThreadConexaoSocker().execute();

                }
            }
        });





        //###############################  SOCKET ################################

    }

    private class ThreadConexaoSocker extends AsyncTask<Void, Void, Boolean> {

        //Primeiro Método a entrar
        @Override
        protected void onPreExecute() {

            super.onPreExecute();

            erroInesperado = null;


        }

        protected Boolean doInBackground(Void... voids) {

            Boolean flagProcessamentoOk = true;

            try {

                //Nova instância de Socket
                Socket socketCliente = new Socket("192.168.0.105", 8787); //IP DO COMPUTADOR ONDE ESTÁ RODANDO O SERVIDOR SOCKET
                DataInputStream in = new DataInputStream(socketCliente.getInputStream());
                DataOutputStream out = new DataOutputStream(socketCliente.getOutputStream());
                //" 192.168.43.193 "
                String emailUsario = edEmailUsuario.getText().toString();
                String senhaUsuario = edSenhaUsuario.getText().toString();
                String email = edEmail.getText().toString();
                String assunto = edAssunto.getText().toString();
                String mensagem = edMensagem.getText().toString();

                out.writeUTF(emailUsario);
                out.writeUTF(senhaUsuario);
                out.writeUTF(email);
                out.writeUTF(assunto);
                out.writeUTF(mensagem);
                out.flush();

                // 0 sucesso  / 1 erro

                resp = in.readInt();

                in.close();
                out.close();
                socketCliente.close();

                progressDialog.hide();

            } catch (Exception e) {

                e.printStackTrace();
                erroInesperado = e;
                flagProcessamentoOk = false;
            }

            return flagProcessamentoOk;
        }

    }



    public boolean mensagemConfirma() {
        progresso = (ProgressBar) findViewById(R.id.progresso);

if(!estaConectado()) {
    new AlertDialog.Builder(this).setTitle("")
            .setMessage("Você precisa estar conectado a internet para enviar um e-mail.\nVerifique as configurações do seu aparelho.")

            .setNegativeButton("Voltar", null).show();
}

            if (resp == 1) {
                new AlertDialog.Builder(this).setTitle("")
                        .setMessage("Erro ao enviar mensagem! \n Verifique se todos os dados estão corretos.")
                        .setNegativeButton("Voltar", null).show();
            }else if(resp == 0) {

                new AlertDialog.Builder(this).setTitle("")
                        .setMessage("Mensagem enviada com sucesso!!")
                        .setNegativeButton("Voltar", null).show();

            }
        return true;
        }


public boolean estaConectado() {
    try {
        ConnectivityManager cm =(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null&& networkInfo.isConnectedOrConnecting();


    }catch(Exception ex){
        Toast.makeText(this, "Erro inesperado ao verificar conexão!", Toast.LENGTH_SHORT).show();
        return false;
    }

}



    //################################### VOLTAR TELA COM A SETA #################################

    @Override
    public void onBackPressed() {
        startActivity(new Intent(TelaEmail.this, TelaEscolha.class));
        finishAffinity();

    }

    //################## SWITCH TODOS OS CONTATOS #############################

        /*switchtodos = (Switch) findViewById(R.id.switchtodos);
        switchgrupo = (Switch) findViewById(R.id.switchgrupo);
        SPgrupo = (Spinner) findViewById(R.id.SP_grupo);*/




    //################## SWITCH TODOS OS GRUPOS ############################


        /*switchgrupo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // SE O SWITCH ESTIVER ON

                    edEmail.setFocusable(false);//---> Bloqueando edittext
                    switchtodos.setChecked(false);
                    edEmail.setFocusable(false);
                    SPgrupo.setEnabled(true);

                } else {
                    //SE O SWITCH ESTIVER OFF
                    edEmail.setFocusableInTouchMode(true);
                    SPgrupo.setEnabled(false);
                }
            }
        });*/



    //####################### ADAPTADOR (FORMA COMO O SPINNER É EXIBIDO)#####################


    /*private void adaptadorSpinner() {

        dbMarketingMail db = new dbMarketingMail(getApplicationContext());

        List<String> lista = db.listaSpinner();

        ArrayAdapter<String> grupoAdaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lista);


        grupoAdaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SPgrupo.setAdapter(grupoAdaptador);
    }*/



    /*public void enviaEmailTodos(String email) {

        StringBuilder emails = new StringBuilder();

        String selectQuery = "SELECT * FROM contato WHERE email =" + "'" + email + "'";

        db = banco.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        while (cursor.moveToNext()) {

            if (emails.toString().equals("")) {
                emails.append(cursor.getString(1));

            } else {
                emails.append(", ").append(cursor.getString(1));
            }*/


        /*if(cursor.getCount()>0) {
            do {
                String nomeString = cursor.getString(cursor.getColumnIndex("email"));


                StringBuilder conversor = new StringBuilder();
                conversor.append(nomeString);
            } while(cursor.moveToNext());

        }   cursor.close();

        return emails;
        }/*

        /*emails.toString();
    }*/

//#################### CHAMANDO EMAIL DA TELA DE CONSULTA ############################

    /*public void chamandoEmail(){

        edEmail = (EditText) findViewById(R.id.edEmail);
        codigo = this.getIntent().getStringExtra("codigo");

        cursor = crud.chamarEmail(Integer.parseInt(codigo)); //-------> Método do controller

        edEmail.setText(cursor.getString(cursor.getColumnIndexOrThrow(dbMarketingMail.EMAIL)));
    }*/

}