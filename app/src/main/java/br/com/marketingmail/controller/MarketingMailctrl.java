package br.com.marketingmail.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.marketingmail.database.dbMarketingMail;
import br.com.marketingmail.activity.TelaCadastro;


public class MarketingMailctrl {
    private SQLiteDatabase db;
    private dbMarketingMail banco;


    public MarketingMailctrl(Context context) {
        banco = new dbMarketingMail(context);
    }


    //###################### INSERINDO INFORMAÇOES NA TABELA CONTATO ##########################

    public String incluirDados(String nome, String telefone,
                               String cidade, /*String estado*/
                               String email, String celular,
                               String nomegrupo /*,int idgrupo*/) {


        ContentValues contatos;
        long resposta;

        db = banco.getWritableDatabase();
        contatos = new ContentValues();


        contatos.put(dbMarketingMail.NOME_CONTATO, nome);
        contatos.put(dbMarketingMail.TELEFONE, telefone);
        contatos.put(dbMarketingMail.CIDADE, cidade);
        contatos.put(dbMarketingMail.EMAIL, email);
        contatos.put(dbMarketingMail.CELULAR, celular);
        contatos.put(dbMarketingMail.NOME_GRUPO, nomegrupo);

        //contatos.put(dbMarketingMail.ID_GRUPO, idgrupo);

        resposta = db.insert(dbMarketingMail.TABELA_CONTATO, null, contatos);
        db.close();


        //########################## VALIDAÇÃO DOS CAMPOS #############################

        if (resposta == -1) {
            return "Erro ao cadastrar! Reporte o erro ao administrador! ";
        }

        //else if (nome == null) {
  else if (nome.equals("")) {
                return "Campo nome não preenchido";

            }

        else if (nomegrupo == null){
            return"Lista de grupos vazia";

        }
        //else if (email == null)  {

        else if (email.equals(""))  {

            return "Campo e-mail não preenchido";

        }

        else
            {
            return "Um novo contato foi registrado!";
        }
    }


    //######################## INSERINDO INFORMAÇOES NA TABELA GRUPO_###########################

    public String incluirGrupo(/*int idgrupo,*/ String nomegrupo) {
        ContentValues contatos;
        long resposta;

        db = banco.getWritableDatabase();
        contatos = new ContentValues();

        //contatos.put(dbMarketingMail.ID_GRUPO, idgrupo);

        contatos.put(dbMarketingMail.NOME_GRUPO, nomegrupo);

        resposta = db.insert(dbMarketingMail.TABELA_GRUPO, null,contatos);
        db.close();

        if (resposta == -1)
            return "Erro ao cadastrar grupo!";


        else if (nomegrupo.equals("")) {
            return "Campo não preenchido";
        }

        else
            return "Um novo grupo foi registrado!";
    }


    //############################ LISTANDO NO LISTVIEW (TELA CONSULTA) ###########################

    public Cursor listarDados() {

        Cursor cursor; //--> Classe que salva as informações retornadas do BD

        //--> Aqui são os campos que estão sendo listados vindos do banco dbmarketingmail

        String[] campos = {dbMarketingMail.ID_CONTATO, dbMarketingMail.NOME_CONTATO,
                dbMarketingMail.TELEFONE, dbMarketingMail.CIDADE, dbMarketingMail.EMAIL,
                dbMarketingMail.CELULAR,dbMarketingMail.NOME_GRUPO};
        db = banco.getReadableDatabase();

        // --> DE qual tabela está sendo pega os dados

        cursor = db.query(dbMarketingMail.TABELA_CONTATO, campos, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor listarGrupos() {

        Cursor cursor; //--> Classe que salva as informações retornadas do BD

        //--> Aqui são os campos que estão sendo listados vindos do banco dbmarketingmail

        String[] campos = {dbMarketingMail.ID_GRUPO,dbMarketingMail.NOME_GRUPO};
        db = banco.getReadableDatabase();

        // --> DE qual tabela está sendo pega os dados

        cursor = db.query(dbMarketingMail.TABELA_GRUPO, campos, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    //####################### CHAMANDO DADOS PARA A TELA ALTERAR ##########################

    public Cursor chamarDados(int id){

        Cursor cursor;
        String[] campos =  {dbMarketingMail.ID_CONTATO,dbMarketingMail.NOME_CONTATO,
                dbMarketingMail.TELEFONE, dbMarketingMail.CIDADE, dbMarketingMail.EMAIL,
                dbMarketingMail.CELULAR, dbMarketingMail.NOME_GRUPO};

       //----------> Pegando informações pelo ID dos contatos

        String where = dbMarketingMail.ID_CONTATO + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(dbMarketingMail.TABELA_CONTATO, campos, where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }


    public Cursor chamarEmail(int id){

        Cursor cursor;
        String[] campos =  {dbMarketingMail.EMAIL};

        //----------> Pegando informações pelo ID dos contatos

        String where = dbMarketingMail.ID_CONTATO + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(dbMarketingMail.TABELA_CONTATO, campos, where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }



    //###################### ALTERANDO OS REGISTROS DOS CONTATOS ################################

    public void alterarDados(int id, String nome, String telefone,
                             String cidade, /*String estado*/ String email, String celular, String nomegrupo /*,int idgrupo*/){

        ContentValues contatos;
        String where;

        db = banco.getWritableDatabase();

        where = dbMarketingMail.ID_CONTATO + "=" + id;

        contatos = new ContentValues();

        contatos.put(dbMarketingMail.NOME_CONTATO, nome);
        contatos.put(dbMarketingMail.TELEFONE, telefone);
        contatos.put(dbMarketingMail.CIDADE, cidade);
        //contatos.put(dbMarketingMail.ESTADO, estado);
        contatos.put(dbMarketingMail.EMAIL, email);
        contatos.put(dbMarketingMail.CELULAR, celular);
        contatos.put(dbMarketingMail.NOME_GRUPO, nomegrupo);

        db.update(dbMarketingMail.TABELA_CONTATO,contatos,where,null);
        db.close();
    }


    public void alterarDadosGrupo(int id,  String nomegrupo){

        ContentValues contatos;
        String where;

        db = banco.getWritableDatabase();

        where = dbMarketingMail.ID_CONTATO + "=" + id;

        contatos = new ContentValues();


        contatos.put(dbMarketingMail.NOME_GRUPO, nomegrupo);

        db.update(dbMarketingMail.TABELA_GRUPO,contatos,where,null);
        db.close();
    }



    //############################# DELETANDO REGISTROS DOS CONTATOS_##############################

    public void excluirDados(int id){
        String where = dbMarketingMail.ID_CONTATO + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(dbMarketingMail.TABELA_CONTATO,where,null);//---------> Metodo delete
        db.close();
    }


    public void excluirDadosGrupo(int id){

        String where = dbMarketingMail.ID_GRUPO + "=" + id;
        db = banco.getReadableDatabase();

        db.delete(dbMarketingMail.TABELA_GRUPO,where,null);//---------> Metodo delete
        db.close();
    }



    public Cursor chamarDadosGrupo(int id){

        Cursor cursor;
        String[] campos =  {dbMarketingMail.ID_GRUPO, dbMarketingMail.NOME_GRUPO};

        //----------> Pegando informações pelo ID dos contatos

        String where = dbMarketingMail.ID_GRUPO+ "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(dbMarketingMail.TABELA_GRUPO, campos, where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }















}

















