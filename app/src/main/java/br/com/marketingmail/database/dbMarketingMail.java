package br.com.marketingmail.database;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class dbMarketingMail extends SQLiteOpenHelper{

    //____________CAMPOS DA TABELA CONTATO_____________

    public static final String NOME_DB = "dbmarketingmail.db"; //---->NOME DO BANCO
    public static final String TABELA_CONTATO = "contato";

    public static final String ID_CONTATO = "_id";
    public static final String NOME_CONTATO = "nome";
    public static final String TELEFONE= "telefone";
    public static final String CELULAR ="celular";
    public static final String CIDADE ="cidade";
   // public static final String ESTADO= "estado";
    public static final String EMAIL="email";

 //____________CAMPOS DA TABELA GRUPOS_________________

    public static final String TABELA_GRUPO= "grupo";
    public static final String ID_GRUPO = "_id";
    public static final String NOME_GRUPO= "nomegrupo";

    public static final int VERSAO = 8;
    private final Context context;


    public dbMarketingMail(Context context) { // ----> AQUI VAI PASSAR A VERSÃO E O LOCAL DO BANCO

        super(context, NOME_DB, null, VERSAO);
        this.context = context;

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        // ----> ESTE AQUI É O MÉTODO QUE CRIA AS TABELAS

        db.execSQL("create table if not exists " + TABELA_GRUPO + "(" + ID_GRUPO + " integer primary key," +
                NOME_GRUPO + " text" + ")");

              db.execSQL("create table if not exists "
                + TABELA_CONTATO + " (" + ID_CONTATO + " integer primary key,"
                + NOME_CONTATO + " text NOT NULL,"
                + TELEFONE + " text,"
                + CIDADE+ " text,"
                + EMAIL + " text NOT NULL,"
                + CELULAR + " text,"
                //+ ID_GRUPO+ " integer,"
                + NOME_GRUPO + " text NOT NULL,"
                //+ " FOREIGN KEY ("+ID_GRUPO+")REFERENCES "+TABELA_GRUPO+"("+ID_GRUPO+"),"
                + " FOREIGN KEY ("+NOME_GRUPO+")REFERENCES "+TABELA_GRUPO+"("+NOME_GRUPO+") )");




    }


        @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            // ----> ESTE AQUI É O MÉTODO QUE ATUALIZA O BANCO

            db.execSQL("DROP TABLE IF EXISTS "+" TABELA_CONTATO");
        db.execSQL("DROP TABLE IF EXISTS "+" TABELA_GRUPO");

            onCreate(db); // ----> ELE FAZ O DROP E DEPOIS RECRIA O BANCO ATUALIZADO

    }




    public List<String> listaSpinner() {

        List<String> lista = new ArrayList<String>();

        String selectQuery = "SELECT  * FROM " + dbMarketingMail.TABELA_GRUPO;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                lista.add(cursor.getString(1));
            } while (cursor.moveToNext());
            cursor.close();
            db.close();



        }
        return lista;
    }

}