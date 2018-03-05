package com.example.marcos.unaspwhatsapp_sqledition.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBNoticias{

    private int id;
    private String news_post;

    public String _mensagem;
    public boolean _status;

    public DBNoticias(){
        super();
        this.id = -1;
        this.news_post = "";
    }

    public ArrayList<DBNoticias> getLista(){
        DB db = new DB();
        ArrayList<DBNoticias> lista = new ArrayList<>();
        try {
            ResultSet resultSet = db.select("SELECT * FROM noticia");
            if (resultSet != null){
                while (resultSet.next()){

                    DBNoticias obj = new DBNoticias();

                    obj.setId(resultSet.getInt("id"));
                    obj.setNoticia(resultSet.getString("news_post"));
                    lista.add(obj);
                }
            }
        }catch (Exception ex){
            this._mensagem = ex.getMessage();
            this._status = false;
            this._status = false;
        }
        return lista;
    }

    public void salvar() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        String comando = "";
        if (this.getId() == -1){
            comando = String.format("INSERT INTO noticia (news_post) VALUES ('%s');",
                    this.getNoticia());
        }else {
            comando = String.format("UPDATE noticia SET news_post ='%s'WHERE id = %d;",
                    this.getNoticia());
        }
        DB db =  new DB();
        db.execute(comando);
    }

    public void apagar() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        String comando = String.format("DELETE FROM noticia WHERE id = %d;", this.getId());

        DB db =  new DB();
        db.execute(comando);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoticia() {
        return news_post;
    }

    public void setNoticia(String news_post) {
        this.news_post = news_post;
    }
}
