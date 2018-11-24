package br.com.marketingmail.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import br.com.marketingmail.controller.MarketingMailctrl;
import br.com.marketingmail.database.dbMarketingMail;


public class TelaCadastro extends Activity implements AdapterView.OnItemSelectedListener {

    private Button BTNcadastrar;
    private EditText TXTnomecad;
    private EditText TXTtelefone;
    private EditText TXTcidade;
    private EditText TXTcelular;
    private EditText TXTemail;
    private Spinner SPgrupo;
    private ImageButton BTNgerenciar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);


        BTNcadastrar = (Button) findViewById(R.id.btn_cadastrar);

        SPgrupo = (Spinner) findViewById(R.id.SP_grupo);

        adaptadorSpinner(); //-->Inicia carregando o spinner

        SPgrupo.setOnItemSelectedListener(this);


        //####################### PEGANDO DADOS PARA CADASTRAR ############################


        BTNcadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TXTnomecad = (EditText) findViewById(R.id.txt_nomecad);
                TXTtelefone = (EditText) findViewById(R.id.txt_telefone);
                TXTcidade = (EditText) findViewById(R.id.txt_cidade);
                TXTemail = (EditText) findViewById(R.id.txt_email);
                TXTcelular = (EditText) findViewById(R.id.txt_celular);
                SPgrupo = (Spinner) findViewById(R.id.SP_grupo);


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



                    novoCadastro();
                }
               }







        });


//######################### BOTÃO GERENCIAR IR PARA OUTRA TELA_ ##################################


        BTNgerenciar = (ImageButton) findViewById(R.id.btn_gerenciar);

        BTNgerenciar .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaCadastro.this, TelaConsulta.class);

                startActivity(intent);

                finish();
            }
        });


    }

//################################ INCLUINDO DADOS NOVO CADASTRO #############################

    public void novoCadastro() {

        MarketingMailctrl cadastro = new MarketingMailctrl(getBaseContext());

        String nomeString = TXTnomecad.getText().toString();
        String telefoneString = TXTtelefone.getText().toString();
        String cidadeString = TXTcidade.getText().toString();
        String emailString = TXTemail.getText().toString();
        String celularString = TXTcelular.getText().toString();
        String grupoString = SPgrupo.getSelectedItem().toString();

        String resposta;




        resposta = cadastro.incluirDados(nomeString, telefoneString,
                cidadeString, emailString, celularString, grupoString);



        Toast.makeText(getApplicationContext(), resposta, Toast.LENGTH_LONG).show();
        limpaDados();
    }


    //######################### LIMPAR CAMPOS APÓS CADASTRO #####################################


    public void limpaDados() {
        TXTnomecad.setText("");
        TXTtelefone.setText("");
        TXTcidade.setText("");
        TXTemail.setText("");
        TXTcelular.setText("");


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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        menssagemConfirma();
    }


    //############################ VOLTAR TELA COM A SETA #####################################


    @Override
    public void onBackPressed() {
        startActivity(new Intent(TelaCadastro.this, TelaEscolha.class));
        finishAffinity();

    }

    //############################ VALIDAR CAMPO VAZIO ################################

    public void validaDados() {

/*boolean res= false;

        if (campoVazio(nome)){
            TXTnomecad.requestFocus();
            TXTnomecad.setError("Preencher o campo Nome é obrigatório!");
        }

        else

            if (!emailValido(email)){

                TXTemail.requestFocus();
                TXTemail.setError("Preencher o campo E-mail é obrigatório!");
            }*/




    }


/*private boolean campoVazio(String valor){

        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty() );

                return resultado;


}


    private boolean emailValido(String email){

        boolean resultado = (campoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());//----> testa padrão de email
        return resultado;

    }*/


    //######################### MENSAGEM CASO O SPINNER ESTEJA VAZIO ##############################

  public void menssagemConfirma() {
        try {
            if (SPgrupo.getCount() == 0) {
                new AlertDialog.Builder(this).setTitle("")
                        .setMessage("Epa! Parece que você não cadastrou nenhum grupo para continuar. Deseja ir a tela de grupos?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialogInterface, int id) {
                                Intent intent = new Intent(TelaCadastro.this, TelaGrupos.class);
                                startActivity(intent);
                                finish();
                            }

                        })
                        .setNegativeButton("Voltar", null).show();
            }
        }catch(Exception e){
        }
  }










    }









