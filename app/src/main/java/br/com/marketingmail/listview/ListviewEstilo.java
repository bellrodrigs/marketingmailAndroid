package br.com.marketingmail.listview;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import br.com.marketingmail.activity.R;
import br.com.marketingmail.activity.TelaConsulta;
import br.com.marketingmail.activity.TelaEmail;
import br.com.marketingmail.controller.MarketingMailctrl;
import br.com.marketingmail.database.dbMarketingMail;

public class ListviewEstilo extends Activity {
    MarketingMailctrl crud = new MarketingMailctrl(getBaseContext());
    private Cursor cursor;
    private ImageView BTN_levaemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_estilo);

       /*BTN_levaemail= (ImageView)findViewById(R.id.btn_levaemail);

        BTN_levaemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            String codigo;

            codigo = cursor.getString(cursor.getColumnIndexOrThrow(dbMarketingMail.ID_CONTATO));

            //A classe cursor ira pegar o código, o ID do banco de dados dbMarketingmail

            Intent intent = new Intent(ListviewEstilo.this, TelaEmail.class);
            intent.putExtra("codigo", codigo);


            startActivity(intent);

            finish();






            }
        });*/
        BTN_levaemail = (ImageView) findViewById(R.id.btn_levaemail);
        class CustomAdapter extends ArrayAdapter<String> {


            CustomAdapter(ListviewEstilo obj) {


                super(ListviewEstilo.this, R.layout.listview_estilo, R.id.btn_levaemail);


            }

            public View getView(final int position, View convertView, ViewGroup parent) {

                BTN_levaemail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ListviewEstilo.this, TelaEmail.class);



                        startActivity(intent);

                        finish();

                    }

                });
                return convertView;
            }


        }


    }


}