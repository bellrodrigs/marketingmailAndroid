package br.com.marketingmail.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import br.com.marketingmail.controller.MarketingMailctrl;
import br.com.marketingmail.database.dbMarketingMail;
import br.com.marketingmail.listview.ListviewEstilo;

public class TelaConsulta extends Activity implements AdapterView.OnItemClickListener{

    private ListView Lista;
    private ImageView BTN_levaemail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_consulta);
        BTN_levaemail = (ImageButton) findViewById(R.id.btn_levaemail);

        //########################### PEGANDO AS INFORMAÇÕES DA LISTA #############################

        MarketingMailctrl crud = new MarketingMailctrl(getBaseContext());
        final Cursor cursor = crud.listarDados();



        /*------>  Abaixo são quantas informações serão pegas para listar, obs: caso inserir mais um
        campo na tabela (contato) modificar para não dar array out of bounds*/

        String[] nomeCampos = new String[]{dbMarketingMail.ID_CONTATO, dbMarketingMail.NOME_CONTATO,
                dbMarketingMail.TELEFONE, dbMarketingMail.CIDADE, dbMarketingMail.EMAIL,
                dbMarketingMail.CELULAR, dbMarketingMail.NOME_GRUPO};

        //----> Aqui é cada informação que vai aparecer pegando pelo id do xml listview_estilo.xml

        int[] idViews = new int[]{R.id.idcontato, R.id.nomecontato, R.id.telefone, R.id.cidade, R.id.email,
                R.id.celular, R.id.grupo};


        //-------> ABAIXO É A CLASSE ONDE É UM ADAPTADOR QUE EXIBIRA A LISTA COM OS CONTATOS

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.listview_estilo, cursor, nomeCampos, idViews, 0);



        Lista = (ListView) findViewById(R.id.listview);





        Lista = (ListView) findViewById(R.id.listview);

        Lista.setAdapter(adaptador);



        //----> ABAIXO É O EVENTO DE CLICK NO LIST VIEW SERÁ LEVADO A TELA DE ALTERAÇÃO

            Lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                String codigo;

                cursor.moveToPosition(position);


                codigo = cursor.getString(cursor.getColumnIndexOrThrow(dbMarketingMail.ID_CONTATO));


                //A classe cursor ira pegar o código, o ID do banco de dados dbMarketingmail

                Intent intent = new Intent(TelaConsulta.this, TelaAlterar.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();





                /*BTN_levaemail= (ImageView)findViewById(R.id.btn_levaemail);

                BTN_levaemail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        String codigo;

                        codigo = cursor.getString(cursor.getColumnIndexOrThrow(dbMarketingMail.EMAIL));

                        Intent intent = new Intent(TelaConsulta.this, TelaEmail.class);
                        intent.putExtra("codigo", codigo);

                        startActivity(intent);

                        finish();



                    }
                });*/


        /*adaptador.getView(int position, View convertView, ViewGroup parent);
        adaptador.setViewBinder(new SimpleCursorAdapter.ViewBinder(){

            @Override
            public boolean setViewValue(View view, final Cursor cursor, int columnIndex) {
                BTN_levaemail= (ImageView)findViewById(R.id.btn_levaemail);
                if (view.getId()== R.id.btn_levaemail){
                    ((ImageView)view).setImageDrawable(null);

                    BTN_levaemail.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            String codigo;

                            codigo = cursor.getString(cursor.getColumnIndexOrThrow(dbMarketingMail.EMAIL));

                            Intent intent = new Intent(TelaConsulta.this, TelaEmail.class);
                            intent.putExtra("codigo", codigo);

                            startActivity(intent);

                            finish();



                        }
                    });
                    return true;

                }
                return false;
            }


        });*/

            }
        });


    }






    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



    }


    //############################ VOLTAR TELA COM A SETA #####################################
    @Override
    public void onBackPressed(){
        startActivity(new Intent(TelaConsulta.this, TelaEscolha.class));
        finishAffinity();

    }








}

