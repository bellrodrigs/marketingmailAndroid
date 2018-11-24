package br.com.marketingmail.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import br.com.marketingmail.controller.MarketingMailctrl;
import br.com.marketingmail.database.dbMarketingMail;

public class TelaConsultarGrupos extends Activity {

    private ListView Lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_consultar_grupos);

        //########################### PEGANDO AS INFORMAÇÕES DA LISTA #################################

        MarketingMailctrl crud = new MarketingMailctrl(getBaseContext());

        final Cursor cursor = crud.listarGrupos();

        /*------>  Abaixo são quantas informações serão pegas para listar, obs: caso insirir mais um
        campo na tabela (contato) modificar para não dar array out of bounds*/

        String[] nomeCampos = new String[]{dbMarketingMail.ID_GRUPO, dbMarketingMail.NOME_GRUPO};

        int[] idViews = new int[]{R.id.idcontato, R.id.grupo};



        //-------> ABAIXO É A CLASSE ONDE É UM ADAPTADOR QUE EXIBIRA A LISTA COM OS GRUPOS

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.listview_grupos, cursor, nomeCampos, idViews, 0);

        Lista = (ListView) findViewById(R.id.listview);

        Lista.setAdapter(adaptador);


        //----> ABAIXO É O EVENTO DE CLICK NO LIST VIEW SERÁ LEVADO A TELA DE ALTERAÇÃO

        Lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String codigo;

                cursor.moveToPosition(position);


                codigo = cursor.getString(cursor.getColumnIndexOrThrow(dbMarketingMail.ID_GRUPO));

                //A classe cursor ira pegar o código, o ID do banco de dados dbMarketingmail

                Intent intent = new Intent(TelaConsultarGrupos.this, TelaAlterarGrupos.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();


            }
        });



    }





    //############################ VOLTAR TELA COM A SETA ###################################

    @Override
    public void onBackPressed(){
        startActivity(new Intent(TelaConsultarGrupos.this, TelaEscolha.class));
        finishAffinity();

    }





}
