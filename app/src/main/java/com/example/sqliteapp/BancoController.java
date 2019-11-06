package com.example.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sqliteapp.BancoDeDados;

public class BancoController {

    private SQLiteDatabase db;
    private BancoDeDados banco;

    public BancoController(Context context){
        banco = new BancoDeDados(context);
    }

    public String insereDado(String titulo, String autor, String editora){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(BancoDeDados.TITULO, titulo);
        valores.put(BancoDeDados.AUTOR, autor);
        valores.put(BancoDeDados.EDITORA, editora);

        resultado = db.insert(BancoDeDados.TABELA, null, valores);
        db.close();

        if (resultado ==-1)
        { return "Erro ao inserir registro";}
        else
        {return "Registro Inserido com sucesso";}

    }
    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.TITULO};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.TITULO,banco.AUTOR,banco.EDITORA};
        String where = BancoDeDados.ID + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(BancoDeDados.TABELA,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void alteraRegistro(int id, String titulo, String autor, String editora){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = BancoDeDados.ID + "=" + id;

        valores = new ContentValues();
        valores.put(BancoDeDados.TITULO, titulo);
        valores.put(BancoDeDados.AUTOR, autor);
        valores.put(BancoDeDados.EDITORA, editora);

        db.update(BancoDeDados.TABELA,valores,where,null);
        db.close();
    }

    public void deletaRegistro(int id){
        String where = BancoDeDados.ID + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(BancoDeDados.TABELA,where,null);
        db.close();
    }
}