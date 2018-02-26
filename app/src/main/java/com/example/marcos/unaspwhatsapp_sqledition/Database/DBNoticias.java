package com.example.marcos.unaspwhatsapp_sqledition.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBNoticias {
    private int id;
    private String post;

    public String _mensagem;
    public boolean _status;

    public DBNoticias(){
        this.id = -1;
        this.post = "";
    }

    public ArrayList<DBNoticias> getLista(){
        ArrayList<DBNoticias> lista = new ArrayList<>();
        try {
            ResultSet resultSet = DB.select("SELECT * FROM noticia");
            if (resultSet != null){
                while (resultSet.next()){

                    DBNoticias obj = new DBNoticias();

                    obj.setId(resultSet.getInt("id"));
                    obj.setPost(resultSet.getString("noticia"));
                    lista.add(obj);

                    obj = null;
                }
            }
        }catch (Exception ex){
            this._mensagem = ex.getMessage();
            this._status = false;
            this._status = false;
        }
        return lista;
    }

    public void salvar(int usuario) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        String comando = "";
        if (this.getId() == -1){
            comando = String.format("INSERT INTO noticia (noticias, id_usuario) VALUES ('%s',%d);",
                    this.getPost(), usuario);
        }else {
            comando = String.format("UPDATE noticia SET noticias ='%s',id_usuario = %d WHERE id = %d;",
                    this.getPost(), usuario);
        }
        DB db =  new DB();
        db.execute(comando);
    }

    public void apagar() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        String comando = String.format("DELETE FROM curso WHERE id = %d;", this.getId());

        DB db =  new DB();
        db.execute(comando);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

}
