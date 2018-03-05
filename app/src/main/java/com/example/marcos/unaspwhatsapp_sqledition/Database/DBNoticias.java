package com.example.marcos.unaspwhatsapp_sqledition.Database;

import android.support.v7.app.AppCompatActivity;

import com.example.marcos.unaspwhatsapp_sqledition.UsuarioLogado;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

<<<<<<< HEAD
public class DBNoticias{

    private int id;
    private String news_post;
=======
public class DBNoticias {
>>>>>>> new-branch

    private int id;
    public String newsTitle;
    public String newsPost;
    public boolean _status;
    public String _message;

    public DBNoticias(){
        super();
        this.id = -1;
<<<<<<< HEAD
        this.news_post = "";
=======
        this.newsTitle = "";
        this.newsPost = "";

    }

    public DBNoticias(AppCompatActivity activity) {

>>>>>>> new-branch
    }

    public ArrayList<DBNoticias> getLista(){
        DB db = new DB();
        ArrayList<DBNoticias> lista = new ArrayList<>();
        try {
            ResultSet resultSet = db.select("SELECT * FROM noticia");
            if (resultSet != null){
                while (resultSet.next()){

                    DBNoticias obj = new DBNoticias();

<<<<<<< HEAD
                    obj.setId(resultSet.getInt("id"));
                    obj.setNoticia(resultSet.getString("news_post"));
=======
                    obj.setId(resultSet.getInt("id_news"));
                    obj.setNewsTitle(resultSet.getString("news_title"));
                    obj.setNewsPost(resultSet.getString("news_message"));
>>>>>>> new-branch
                    lista.add(obj);
                }
            }
        }catch (Exception ex){
            this._message = ex.getMessage();
            this._status = false;
            this._status = false;
        }
        return lista;
    }

    public void salvar() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        String comando = "";
        if (this.getId() == -1){
<<<<<<< HEAD
            comando = String.format("INSERT INTO noticia (news_post) VALUES ('%s');",
                    this.getNoticia());
        }else {
            comando = String.format("UPDATE noticia SET news_post ='%s'WHERE id = %d;",
                    this.getNoticia());
=======
            comando = String.format("INSERT INTO news (news_title, news_message, fk_user_id) VALUES ('%s','%s',%d);",
                    this.getNewsTitle(), this.getNewsPost(), UsuarioLogado.getUser().getId());
        }else {
            comando = String.format("UPDATE news SET news_title ='%s', news_post = '%s', id_news = %d WHERE id = %d;",
                    this.getNewsTitle(), this.getNewsPost(), this.getId());
>>>>>>> new-branch
        }
        DB db =  new DB();
        db.executeQuery(comando);
    }

    public void apagar() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
<<<<<<< HEAD
        String comando = String.format("DELETE FROM noticia WHERE id = %d;", this.getId());
=======
        String comando = String.format("DELETE FROM news WHERE id = %d;", this.getId());
>>>>>>> new-branch

        DB db =  new DB();
        db.execute(comando);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

<<<<<<< HEAD
    public String getNoticia() {
        return news_post;
    }

    public void setNoticia(String news_post) {
        this.news_post = news_post;
    }
=======
    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsPost() {
        return newsPost;
    }

    public void setNewsPost(String newsPost) {
        this.newsPost = newsPost;
    }
>>>>>>> new-branch
}
